package org.chat.server.socket.handler;

import com.alibaba.fastjson.JSON;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import org.chat.server.application.UserService;
import org.chat.server.domain.user.model.LuckUserInfo;
import org.chat.server.infrastructure.common.SocketChannelUtil;
import org.chat.server.protocol.friend.SearchFriendRequest;
import org.chat.server.protocol.friend.SearchFriendResponse;
import org.chat.server.protocol.friend.dto.UserDto;
import org.chat.server.socket.MyHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * 服务端处理搜索好友请求
 * @author XiaoRed
 * @date 2023/12/3 0:13
 */
public class SearchFriendHandler extends MyHandler<SearchFriendRequest> {

    public SearchFriendHandler(UserService userService) {
        super(userService);
    }

    @Override
    public void channelRead(Channel channel, SearchFriendRequest msg) {
        logger.info("处理搜索好友请求：{}", JSON.toJSONString(msg));
        //1. 根据关键字，模糊搜索出对应用户列表
        List<UserDto> userDtoList = new ArrayList<>();
        List<LuckUserInfo> userInfoList = userService.queryFuzzyUserInfoList(msg.getUserId(), msg.getSearchKey());
        //2. 转换为response里面要求的dto对象
        for (LuckUserInfo userInfo : userInfoList) {
            UserDto userDto = new UserDto();
            userDto.setUserId(userInfo.getUserId());
            userDto.setUserNickName(userInfo.getUserNickName());
            userDto.setUserHead(userInfo.getUserHead());
            userDto.setStatus(userInfo.getStatus());
            userDtoList.add(userDto);
        }
        //3. 将UserDto列表封装为response，通过channel推送给客户端
        SearchFriendResponse response = new SearchFriendResponse();
        response.setList(userDtoList);
        channel.writeAndFlush(response);
    }

}
