package org.chat.server.socket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.chat.server.application.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.Channel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.net.InetSocketAddress;
import java.util.concurrent.Callable;

/**
 * Netty服务实现Callable的call方法，在call方法中进行Netty服务端的启动
 * 实现Callable的类，可以当作线程任务交给线程池执行
 * 线程池会分配给这个类一个线程，去执行call方法
 * 这样，Netty服务端的启动就是一个交给其他线程执行的异步操作，不会影响(阻塞)主线程，主线程只需要等它执行完后，拿结果即可
 * @author XiaoRed
 * @date 2023/12/1 15:26
 */
@Service("nettyServer")
public class NettyServer implements Callable<Channel> {

    private final Logger logger = LoggerFactory.getLogger(NettyServer.class);
    @Resource
    private UserService userService;
    private final EventLoopGroup bossGroup = new NioEventLoopGroup(2);
    private final EventLoopGroup workerGroup = new NioEventLoopGroup();
    private Channel channel;
    @Override
    public Channel call() throws Exception {
        ChannelFuture future = null;
        try{
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap
                    .group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childHandler(new MyChannelInitializer(userService));
            future = bootstrap.bind(new InetSocketAddress(3000)).syncUninterruptibly();
            this.channel = future.channel();
        }catch (Exception e){
            logger.error("服务端启动失败：{}", e.getMessage());
        }finally {
            if(future != null && future.isSuccess())
                logger.info("服务端启动成功 {}", channel.localAddress());
            else logger.error("服务端启动失败...");
        }
        return this.channel;
    }

    public void destroy(){
        if(channel == null) return;
        channel.close();
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    }

    public Channel channel() {return channel;}
}
