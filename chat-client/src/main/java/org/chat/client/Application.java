package org.chat.client;

import io.netty.channel.Channel;
import javafx.stage.Stage;
import org.chat.client.application.UIService;
import org.chat.client.event.ChatEvent;
import org.chat.client.event.LoginEvent;
import org.chat.client.socket.NettyClient;
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
        // 1. 启动窗口
        IChatMethod chat = new ChatController(new ChatEvent());
        ILoginMethod login = new LoginController(new LoginEvent(), chat);
        login.doShow();

        UIService uiService = new UIService();
        uiService.setChat(chat);
        uiService.setLogin(login);

        // 2. 启动socket连接
        logger.info("NettyClient连接服务开始 inetHost：{} inetPort：{}", "127.0.0.1", 7397);
        NettyClient nettyClient = new NettyClient(uiService);
        Future<Channel> future = executorService.submit(nettyClient);
        Channel channel = future.get();
        if (null == channel) throw new RuntimeException("netty client start error channel is null");

        while (!nettyClient.isActive()) {
            logger.info("NettyClient启动服务 ...");
            Thread.sleep(500);
        }
        logger.info("NettyClient连接服务完成 {}", channel.localAddress());

    }

    public static void main(String[] args) {
        launch(args);
    }

}
