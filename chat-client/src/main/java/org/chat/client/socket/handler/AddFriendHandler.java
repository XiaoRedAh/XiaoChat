package org.chat.client.socket.handler;

import io.netty.channel.Channel;
import javafx.application.Platform;
import org.chat.client.application.UIService;
import org.chat.client.socket.MyHandler;
import org.chat.server.protocol.friend.AddFriendResponse;
import org.chat.ui.view.chat.IChatMethod;

/**
 * 客户端处理添加好友响应
 * @author XiaoRed
 * @date 2023/12/3 0:01
 */
public class AddFriendHandler extends MyHandler<AddFriendResponse> {

    public AddFriendHandler(UIService uiService) {
        super(uiService);
    }

    @Override
    public void channelRead(Channel channel, AddFriendResponse msg) {
        IChatMethod chat = uiService.getChat();
        //在非Fx线程中操作JavaFx线程相关任务时，需要在Platform.runLater中执行，但它不是线程阻塞型的，在javafx的主线程完全清空或者空闲的时候，才会执行
        Platform.runLater(() -> {
            //将新好友展示到好友列表中
            chat.addFriendUser(true, msg.getFriendId(), msg.getFriendNickName(), msg.getFriendHead());
        });
    }

}