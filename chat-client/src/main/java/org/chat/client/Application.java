package org.chat.client;

import io.netty.channel.Channel;
import javafx.stage.Stage;
import org.chat.client.application.UIService;
import org.chat.client.event.ChatEvent;
import org.chat.client.event.LoginEvent;
import org.chat.client.infrastructure.util.CacheUtil;
import org.chat.client.socket.NettyClient;
import org.chat.server.protocol.login.ReconnectRequest;
import org.chat.ui.view.chat.ChatController;
import org.chat.ui.view.chat.IChatMethod;
import org.chat.ui.view.login.ILoginMethod;
import org.chat.ui.view.login.LoginController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * @author XiaoRed
 * @date 2023/11/30 18:48
 */
public class Application extends javafx.application.Application {

    private final Logger logger = LoggerFactory.getLogger(Application.class);

    //默认线程池
    private static final ExecutorService executorService = Executors.newFixedThreadPool(2);
    private final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

    @Override
    public void start(Stage primaryStage) throws Exception {
        //1. 初始化登录，聊天method，展示登录界面
        IChatMethod chat = new ChatController(new ChatEvent());
        ILoginMethod login = new LoginController(new LoginEvent(), chat);
        login.doShow();

        //2. 初始化UIService，将chat和login装入
        UIService uiService = new UIService();
        uiService.setChat(chat);
        uiService.setLogin(login);

        //3. 多线程启动Netty客户端
        logger.info("Netty客户端正在启动 inetHost：{} inetPort：{}", "127.0.0.1", 3000);
        NettyClient nettyClient = new NettyClient(uiService);
        //Netty客户端实现了Callable接口，可以把它作为线程任务交给线程池去执行call方法，这样一来，Netty客户端的启动就是异步操作，主线程继续做其它事，等异步任务做完后，主线程再来拿执行结果
        Future<Channel> future = executorService.submit(nettyClient);
        Channel channel = future.get();
        if (null == channel) throw new RuntimeException("Netty客户端启动失败：channel is null");

        //4. 每0.5秒检测一次channel是否为active状态，直到channel变为active状态
        while (!nettyClient.isActive()) {
            logger.info("Netty客户端正在进行连接服务...");
            Thread.sleep(500);
        }
        logger.info("Netty客户端连接服务完成 {}", channel.localAddress());

        //客户端定时检查连接：Channel状态定时巡检；3秒后每5秒执行一次
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            while (!nettyClient.isActive()) {
                logger.info("通信管道连接检查：通信管道状态 " + nettyClient.isActive());
                try {
                    logger.info("通信管道连接检查：断线重连[Begin]");
                    //Netty重新启动一次，拿到新的channel
                    Channel freshChannel = executorService.submit(nettyClient).get();
                    if (null == CacheUtil.userId) continue;
                    //通过新的channel向服务端发送断线重连请求
                    freshChannel.writeAndFlush(new ReconnectRequest(CacheUtil.userId));
                    logger.info("通信管道连接检查：断线重连[End-Success]");
                } catch (InterruptedException | ExecutionException e) {
                    System.out.println("通信管道连接检查：断线重连[End-Error]");
                }
            }
        }, 3, 5, TimeUnit.SECONDS);

    }

    public static void main(String[] args) {
        launch(args);
    }

}
