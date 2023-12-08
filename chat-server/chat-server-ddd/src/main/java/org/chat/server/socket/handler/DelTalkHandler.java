package org.chat.server.socket.handler;

import io.netty.channel.Channel;
import org.chat.server.application.UserService;
import org.chat.server.protocol.talk.DelTalkRequest;
import org.chat.server.socket.MyHandler;

/**
 * 服务端处理删除对话框请求
 * @author XiaoRed
 * @date 2023/12/8 22:06
 */
public class DelTalkHandler extends MyHandler<DelTalkRequest> {

    public DelTalkHandler(UserService userService) {
        super(userService);
    }

    @Override
    public void channelRead(Channel channel, DelTalkRequest msg) {
        userService.deleteUserTalk(msg.getUserId(), msg.getTalkId());
    }
}

