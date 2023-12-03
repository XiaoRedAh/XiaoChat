package org.chat.client.socket;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.chat.client.application.UIService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 各个业务Handler都会用到以下方法，因此这里做个抽象类，之后的Handler都继承它，直接获取这些通用方法
 * @author XiaoRed
 * @date 2023/11/30 18:53
 */
public abstract class MyHandler<T> extends SimpleChannelInboundHandler<T> {

    protected Logger logger = LoggerFactory.getLogger(MyHandler.class);

    protected UIService uiService;

    public MyHandler(UIService uiService) {this.uiService = uiService;}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, T msg) throws Exception {
        channelRead(ctx.channel(), msg);
    }

    public abstract void channelRead(Channel channel, T msg);

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        logger.info("连接断开了...");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.info("关闭" + ctx.channel().id());
    }
}
