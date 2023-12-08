package org.chat.server.socket.handler;

import com.alibaba.fastjson.JSON;
import io.netty.channel.Channel;
import org.chat.server.application.UserService;
import org.chat.server.domain.user.model.ChatRecordInfo;
import org.chat.server.infrastructure.common.Constants;
import org.chat.server.infrastructure.common.SocketChannelUtil;
import org.chat.server.protocol.msg.MsgRequest;
import org.chat.server.protocol.msg.MsgResponse;
import org.chat.server.socket.MyHandler;

/**
 * 服务端处理单聊消息请求
 * @author XiaoRed
 * @date 2023/12/9 0:06
 */
public class MsgHandler extends MyHandler<MsgRequest> {

    public MsgHandler(UserService userService) {
        super(userService);
    }

    @Override
    public void channelRead(Channel channel, MsgRequest msg) {
        logger.info("消息信息处理：{}", JSON.toJSONString(msg));
        // 异步写库
        userService.asyncAppendChatRecord(new ChatRecordInfo(msg.getUserId(), msg.getFriendId(), msg.getMsgText(), msg.getMsgType(), msg.getMsgDate()));
        // 添加对话框[如果对方没有你的对话框则添加]
        userService.addTalkBoxInfo(msg.getFriendId(), msg.getUserId(), Constants.TalkType.Friend.getCode());
        // 获取好友通信管道
        Channel friendChannel = SocketChannelUtil.getChannel(msg.getFriendId());
        if (null == friendChannel) {
            logger.info("用户id：{}未登录！", msg.getFriendId());
            return;
        }
        // 发送消息
        friendChannel.writeAndFlush(new MsgResponse(msg.getUserId(), msg.getMsgText(), msg.getMsgType(), msg.getMsgDate()));
    }
}
