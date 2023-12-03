package org.chat.client.event;

import com.alibaba.fastjson.JSON;
import io.netty.channel.Channel;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import org.chat.client.infrastructure.util.BeanUtil;
import org.chat.server.protocol.friend.AddFriendRequest;
import org.chat.server.protocol.friend.SearchFriendRequest;
import org.chat.ui.view.chat.IChatEvent;

import java.util.Date;

/**
 * @author XiaoRed
 * @date 2023/11/30 18:48
 */
public class ChatEvent implements IChatEvent {
    @Override
    public void doQuit() {

    }

    @Override
    public void doSendMsg(String userId, String talkId, Integer talkType, String msg, Integer msgType, Date msgDate) {

    }

    @Override
    public void doEventAddTalkUser(String userId, String userFriendId) {

    }

    @Override
    public void doEventAddTalkGroup(String userId, String groupId) {

    }

    @Override
    public void doEventDelTalkUser(String userId, String talkId) {

    }

    /**
     * 打开“新的朋友”窗体时触发，将发送方和空关键字封装为SearchFriendRequest，通过channel发送给服务端
     */
    @Override
    public void addFriendLuck(String userId, ListView<Pane> listView) {
        Channel channel = BeanUtil.getBean("Channel", Channel.class);
        channel.writeAndFlush(new SearchFriendRequest(userId, ""));
    }

    /**
     * 搜索好友时触发，将发送方和搜索关键字封装为SearchFriendRequest，通过channel发送给服务端
     */
    @Override
    public void doFriendLuckSearch(String userId, String text) {
        Channel channel = BeanUtil.getBean("Channel", Channel.class);
        channel.writeAndFlush(new SearchFriendRequest(userId, text));
    }

    /**
     * 点击“添加好友”时触发，将发送方id和目标添加id封装为AddFriendRequest，通过channel发送给服务端
     */
    @Override
    public void doEventAddLuckUser(String userId, String friendId) {
        Channel channel = BeanUtil.getBean("Channel", Channel.class);
        channel.writeAndFlush(new AddFriendRequest(userId, friendId));
    }
}
