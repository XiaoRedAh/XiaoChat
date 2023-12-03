package org.chat.client.socket.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import javafx.application.Platform;
import org.chat.client.application.UIService;
import org.chat.server.protocol.friend.SearchFriendResponse;
import org.chat.server.protocol.friend.dto.UserDto;
import org.chat.ui.view.chat.IChatMethod;

import java.util.List;

/**
 * 客户端处理搜索好友响应
 * @author XiaoRed
 * @date 2023/12/3 0:01
 */
public class SearchFriendHandler extends SimpleChannelInboundHandler<SearchFriendResponse> {

    private UIService uiService;

    public SearchFriendHandler(UIService uiService) {
        this.uiService = uiService;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SearchFriendResponse msg) throws Exception {
        List<UserDto> list = msg.getList();
        if (null == list || list.isEmpty()) return;
        IChatMethod chat = uiService.getChat();
        //在非Fx线程中操作JavaFx线程相关任务时，需要在Platform.runLater中执行，但它不是线程阻塞型的，在javafx的主线程完全清空或者空闲的时候，才会执行
        Platform.runLater(() -> {
            //展示搜索结果
            for (UserDto user : list) {
                chat.addLuckFriend(user.getUserId(), user.getUserNickName(), user.getUserHead(), user.getStatus());
            }
        });
    }

}
