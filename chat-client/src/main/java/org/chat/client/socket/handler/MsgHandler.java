package org.chat.client.socket.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import javafx.application.Platform;
import org.chat.client.application.UIService;
import org.chat.server.protocol.msg.MsgResponse;
import org.chat.ui.view.chat.IChatMethod;

/**
 * 客户端处理单聊消息响应
 * @author XiaoRed
 * @date 2023/12/9 0:01
 */
public class MsgHandler extends SimpleChannelInboundHandler<MsgResponse> {
    private UIService uiService;

    public MsgHandler(UIService uiService) {
        this.uiService = uiService;
    }


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MsgResponse msg) throws Exception {
        IChatMethod chat = uiService.getChat();
        Platform.runLater(() -> { //将接收到的消息展示到聊天记录的左侧（他人发来的消息）
            chat.addTalkMsgUserLeft(msg.getFriendId(), msg.getMsgText(), msg.getMsgType(), msg.getMsgDate(), true, false, true);
        });

    }
}
