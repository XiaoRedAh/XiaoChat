package org.chat.server.socket.handler;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import org.chat.server.application.UserService;
import org.chat.server.domain.user.model.ChatRecordInfo;
import org.chat.server.domain.user.model.UserInfo;
import org.chat.server.infrastructure.common.Constants;
import org.chat.server.infrastructure.common.SocketChannelUtil;
import org.chat.server.protocol.msg.MsgGroupRequest;
import org.chat.server.protocol.msg.MsgGroupResponse;
import org.chat.server.socket.MyHandler;

/**
 * 服务端处理群聊消息请求
 * @author XiaoRed
 * @date 2023/12/9 9:12
 */
public class MsgGroupHandler extends MyHandler<MsgGroupRequest> {

    public MsgGroupHandler(UserService userService) {
        super(userService);
    }

    @Override
    public void channelRead(Channel channel, MsgGroupRequest msg) {
        //获取目标群组的ChannelGroup，如果没有，就新建
        ChannelGroup channelGroup = SocketChannelUtil.getChannelGroup(msg.getTalkId());
        if (null == channelGroup) {
            SocketChannelUtil.addChannelGroup(msg.getTalkId(), channel);
            channelGroup = SocketChannelUtil.getChannelGroup(msg.getTalkId());
        }
        //将这个新的群聊消息异步入库
        userService.asyncAppendChatRecord(new ChatRecordInfo(msg.getUserId(), msg.getTalkId(), msg.getMsgText(), msg.getMsgType(), msg.getMsgDate(), Constants.TalkType.Group.getCode()));
        //封装响应
        UserInfo userInfo = userService.queryUserInfo(msg.getUserId());
        MsgGroupResponse msgGroupResponse = new MsgGroupResponse();
        msgGroupResponse.setTalkId(msg.getTalkId());
        msgGroupResponse.setUserId(msg.getUserId());
        msgGroupResponse.setUserNickName(userInfo.getUserNickName());
        msgGroupResponse.setUserHead(userInfo.getUserHead());
        msgGroupResponse.setMsg(msg.getMsgText());
        msgGroupResponse.setMsgType(msg.getMsgType());
        msgGroupResponse.setMsgDate(msg.getMsgDate());
        //通过channelGroup群发消息，将响应发送给group中所有的channel
        channelGroup.writeAndFlush(msgGroupResponse);
    }

}

