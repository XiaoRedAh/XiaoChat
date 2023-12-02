package org.chat.client.socket;


import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.chat.client.application.UIService;
import org.chat.client.infrastructure.util.BeanUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.concurrent.Callable;

/**
 * Netty客户端实现Callable的call方法，在call方法中进行Netty客户端的启动，连接
 * 实现Callable的类，可以当作线程任务交给线程池执行
 * 线程池会分配给这个类一个线程，去执行call方法
 * 这样，Netty客户端的启动就是一个交给其他线程执行的异步操作，不会影响(阻塞)主线程，主线程只需要等它执行完后，拿结果即可
 * @author XiaoRed
 * @date 2023/11/30 18:52
 */
public class NettyClient implements Callable<Channel> {

    private final Logger logger = LoggerFactory.getLogger(NettyClient.class);

    private final String host = "127.0.0.1";
    private final int port = 3000;

    private final EventLoopGroup workerGroup = new NioEventLoopGroup();
    private Channel channel;

    private final UIService uiService;

    public NettyClient(UIService uiService) {
        this.uiService = uiService;
    }

    @Override
    public Channel call() throws Exception {
        ChannelFuture future = null;
        try{
            Bootstrap bootstrap = new Bootstrap();
            bootstrap
                    .group(workerGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.AUTO_READ, true)
                    .handler(new MyChannelInitializer(uiService));
            future = bootstrap.connect(host, port).syncUninterruptibly(); //同步阻塞，直到连接建立成功，如果中断，不会抛异常
            this.channel = future.channel();
            // 其他地方要用到这个通信管道，因此存入BeanUtil中，哪里要用到，就从BeanUtil里拿出来用
            BeanUtil.addBean("Channel", this.channel);
        }catch (Exception e){
            logger.error("客户端启动失败 {}", e.getMessage());
        }finally {
            if(future != null && future.isSuccess())
                logger.info("客户端启动成功...");
            else logger.error("客户端启动失败...");
        }
        return channel;
    }

    public void destroy(){
        if(channel == null) return;
        channel.close();
        workerGroup.shutdownGracefully();
    }

    public boolean isActive(){
        return channel.isActive();
    }

    public Channel channel(){
        return channel;
    }
}
