package org.chat.server.socket.handler;

import io.netty.channel.Channel;
import org.chat.server.application.UserService;
import org.chat.server.infrastructure.common.SocketChannelUtil;
import org.chat.server.protocol.login.ReconnectRequest;
import org.chat.server.socket.MyHandler;

import java.util.List;

/**
 * 服务端处理断线重连请求
 * @author XiaoRed
 * @date 2023/12/9 10:44
 */
public class ReconnectHandler extends MyHandler<ReconnectRequest> {

    public ReconnectHandler(UserService userService) {
        super(userService);
    }

    @Override
    public void channelRead(Channel channel, ReconnectRequest msg) {
        logger.info("客户端断线重连处理。userId：{}", msg.getUserId());
        // 将uid原来映射的channel去除，改绑为这个新的channel
        SocketChannelUtil.removeUserChannelByUserId(msg.getUserId());
        SocketChannelUtil.addChannel(msg.getUserId(), channel);
        // uid对应群聊的ChannelGroup也要更新
        List<String> groupsIdList = userService.queryTalkBoxGroupsIdList(msg.getUserId());
        for (String groupsId : groupsIdList) {
            SocketChannelUtil.addChannelGroup(groupsId, channel);
        }
    }
}
