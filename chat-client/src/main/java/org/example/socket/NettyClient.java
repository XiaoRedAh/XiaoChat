package org.example.socket;


import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.concurrent.Callable;

/**
 * @author XiaoRed
 * @date 2023/11/30 18:52
 */
public class NettyClient implements Callable<Channel> {

    private Logger logger = LoggerFactory.getLogger(NettyClient.class);

    private String host = "127.0.0.1";
    private int port = 3000;

    private EventLoopGroup workerGroup = new NioEventLoopGroup();
    private Channel channel;

    @Override
    public Channel call() throws Exception {
        ChannelFuture future = null;
        try{
            Bootstrap bootstrap = new Bootstrap();
            bootstrap
                    .group(workerGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.AUTO_READ, true)
                    .handler(new MyChannelInitializer());
            future = bootstrap.connect(host, port).syncUninterruptibly(); //同步阻塞，直到连接建立成功，如果中断，不会抛异常
            this.channel = future.channel();
        }catch (Exception e){
            logger.error("客户端启动失败，", e.getMessage());
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
