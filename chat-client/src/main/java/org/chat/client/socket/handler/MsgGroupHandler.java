package org.chat.client.socket.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import javafx.application.Platform;
import org.chat.client.application.UIService;
import org.chat.server.protocol.msg.MsgGroupResponse;
import org.chat.ui.view.chat.IChatMethod;

/**
 * 客服端处理群聊消息响应
 * @author XiaoRed
 * @date 2023/12/9 9:09
 */
public class MsgGroupHandler extends SimpleChannelInboundHandler<MsgGroupResponse> {

    private UIService uiService;

    public MsgGroupHandler(UIService uiService) {
        this.uiService = uiService;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MsgGroupResponse msg) throws Exception {
        IChatMethod chat = uiService.getChat();
        Platform.runLater(() -> { //将新消息展示到聊天界面左侧
            chat.addTalkMsgGroupLeft(msg.getTalkId(), msg.getUserId(), msg.getUserNickName(), msg.getUserHead(), msg.getMsg(), msg.getMsgType(), msg.getMsgDate(), true, false, true);
        });
    }

}
