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
 * @author XiaoRed
 * @date 2023/12/1 15:26
 */
@Service("nettyServer")
public class NettyServer implements Callable<Channel> {

    private Logger logger = LoggerFactory.getLogger(NettyServer.class);
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
