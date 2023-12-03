package org.chat.server.socket;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.chat.server.application.UserService;
import org.chat.server.infrastructure.common.SocketChannelUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 各个业务Handler都会用到以下方法，因此这里做个抽象类，之后的Handler都继承它，直接获取这些通用方法
 * @author XiaoRed
 * @date 2023/12/1 15:32
 */
public abstract class MyHandler<T> extends SimpleChannelInboundHandler<T> {

    protected Logger logger = LoggerFactory.getLogger(MyHandler.class);

    protected UserService userService;

    public MyHandler(UserService userService) {this.userService = userService;}

    public abstract void channelRead(Channel channel, T msg);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, T msg) throws Exception {
        channelRead(ctx.channel(), msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        logger.info("有新的客户端连接：" + ctx.channel());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        SocketChannelUtil.removeChannel(ctx.channel().id().toString());
        SocketChannelUtil.removeChannelGroupByChannel(ctx.channel());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.error("与客户端" + ctx.channel().remoteAddress() + "在通讯中发生异常：" + cause.getMessage() + " 打印异常路径如下：");
        cause.printStackTrace();
        SocketChannelUtil.removeChannel(ctx.channel().id().toString());
        SocketChannelUtil.removeChannelGroupByChannel(ctx.channel());
    }
}
