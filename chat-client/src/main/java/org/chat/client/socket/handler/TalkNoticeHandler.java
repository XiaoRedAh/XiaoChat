package org.chat.client.socket.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import javafx.application.Platform;
import org.chat.client.application.UIService;
import org.chat.server.protocol.talk.TalkNoticeResponse;
import org.chat.ui.view.chat.IChatMethod;

/**
 * 客户端处理对话通知响应：向聊天界面中添加对应的对话框（通过加载响应中传来的数据）
 * @author XiaoRed
 * @date 2023/12/8 21:24
 */
public class TalkNoticeHandler extends SimpleChannelInboundHandler<TalkNoticeResponse> {

    private UIService uiService;

    public TalkNoticeHandler(UIService uiService) {this.uiService = uiService;}

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TalkNoticeResponse msg) throws Exception {
        IChatMethod chat = uiService.getChat();
        Platform.runLater(() -> {
            chat.addTalkBox(0, 0, msg.getTalkId(), msg.getTalkName(), msg.getTalkHead(), msg.getTalkSketch(), msg.getTalkDate(), false);
        });
    }
}
