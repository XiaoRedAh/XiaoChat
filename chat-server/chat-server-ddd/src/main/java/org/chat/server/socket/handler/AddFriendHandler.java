package org.chat.server.socket.handler;

import com.alibaba.fastjson.JSON;
import io.netty.channel.Channel;
import org.chat.server.application.UserService;
import org.chat.server.domain.user.model.UserInfo;
import org.chat.server.infrastructure.common.SocketChannelUtil;
import org.chat.server.infrastructure.po.UserFriend;
import org.chat.server.protocol.friend.AddFriendRequest;
import org.chat.server.protocol.friend.AddFriendResponse;
import org.chat.server.socket.MyHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * 服务端处理添加好友请求
 * @author XiaoRed
 * @date 2023/12/3 0:12
 */
public class AddFriendHandler extends MyHandler<AddFriendRequest> {

    public AddFriendHandler(UserService userService) {
        super(userService);
    }

    @Override
    public void channelRead(Channel channel, AddFriendRequest msg) {
        logger.info("处理添加好友请求：{}", JSON.toJSONString(msg));
        //不用管另一方同不同意，直接加位好友即可
        //1. 好友记录入库，双向的：(发起方，目标方)，(目标方，发起方)
        List<UserFriend> userFriendList = new ArrayList<>();
        userFriendList.add(new UserFriend(msg.getUserId(), msg.getFriendId()));
        userFriendList.add(new UserFriend(msg.getFriendId(), msg.getUserId()));
        userService.addUserFriend(userFriendList);
        //2. 通过channel，向发起方推送添加好友响应
        UserInfo userInfo = userService.queryUserInfo(msg.getFriendId());
        channel.writeAndFlush(new AddFriendResponse(userInfo.getUserId(), userInfo.getUserNickName(), userInfo.getUserHead()));
        //3. 通过channel，向目标方推送添加好友响应
        Channel friendChannel = SocketChannelUtil.getChannel(msg.getFriendId()); //先拿到目标方的channel
        if (null == friendChannel) return;
        UserInfo friendInfo = userService.queryUserInfo(msg.getUserId());
        friendChannel.writeAndFlush(new AddFriendResponse(friendInfo.getUserId(), friendInfo.getUserNickName(), friendInfo.getUserHead()));
    }
}
