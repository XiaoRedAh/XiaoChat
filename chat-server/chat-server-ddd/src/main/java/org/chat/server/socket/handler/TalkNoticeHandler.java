package org.chat.server.socket.handler;

import com.alibaba.fastjson.JSON;
import io.netty.channel.Channel;
import org.chat.server.application.UserService;
import org.chat.server.domain.user.model.UserInfo;
import org.chat.server.infrastructure.common.SocketChannelUtil;
import org.chat.server.protocol.talk.TalkNoticeRequest;
import org.chat.server.protocol.talk.TalkNoticeResponse;
import org.chat.server.socket.MyHandler;

import java.util.Date;

/**
 * 服务端处理对话通知请求
 * @author XiaoRed
 * @date 2023/12/8 21:48
 */
public class TalkNoticeHandler extends MyHandler<TalkNoticeRequest> {

    public TalkNoticeHandler(UserService userService) {
        super(userService);
    }

    @Override
    public void channelRead(Channel channel, TalkNoticeRequest msg) {
        logger.info("对话通知应答处理：{}", JSON.toJSONString(msg));

        switch (msg.getTalkType()) {
            //好友对话请求【单聊】
            case 0:
                // 1. 对话框数据落库
                userService.addTalkBoxInfo(msg.getUserId(), msg.getFriendUserId(), 0);
                userService.addTalkBoxInfo(msg.getFriendUserId(), msg.getUserId(), 0);
                // 2. 查询发起方的用户信息[将发起方发给目标方的对话框中]
                UserInfo userInfo = userService.queryUserInfo(msg.getUserId());
                // 3. 发送对话框消息给好友
                TalkNoticeResponse response = new TalkNoticeResponse();
                response.setTalkId(userInfo.getUserId());
                response.setTalkName(userInfo.getUserNickName());
                response.setTalkHead(userInfo.getUserHead());
                response.setTalkSketch(null);
                response.setTalkDate(new Date());
                // 获取好友通信管道，好友处于登录状态，才把响应发送给好友
                Channel friendChannel = SocketChannelUtil.getChannel(msg.getFriendUserId());
                if (null == friendChannel) {
                    logger.info("用户id：{}未登录！", msg.getFriendUserId());
                    return;
                }
                friendChannel.writeAndFlush(response);
                break;
            //群组通讯请求【群聊】
            case 1:
                userService.addTalkBoxInfo(msg.getUserId(), msg.getFriendUserId(), 1);
                break;
            default:
                break;
        }
    }
}