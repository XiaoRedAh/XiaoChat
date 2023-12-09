package org.chat.client.event;

import io.netty.channel.Channel;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import org.chat.client.infrastructure.util.BeanUtil;
import org.chat.server.protocol.friend.AddFriendRequest;
import org.chat.server.protocol.friend.SearchFriendRequest;
import org.chat.server.protocol.msg.MsgGroupRequest;
import org.chat.server.protocol.msg.MsgRequest;
import org.chat.server.protocol.talk.DelTalkRequest;
import org.chat.server.protocol.talk.TalkNoticeRequest;
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

    /**
     * 聊天框点击“发送消息”时触发
     */
    @Override
    public void doSendMsg(String userId, String talkId, Integer talkType, String msg, Integer msgType, Date msgDate) {
        Channel channel = BeanUtil.getBean("Channel", Channel.class);
        if (0 == talkType) { //单聊0
            channel.writeAndFlush(new MsgRequest(userId, talkId, msg, msgType, msgDate));
        }else{ //群聊1
           channel.writeAndFlush(new MsgGroupRequest(talkId, userId, msg, msgType, msgDate));
        }
    }

    /**
     * 对好友点击“发送消息”按钮时触发，向服务端发送对话通知请求
     */
    @Override
    public void doEventAddTalkUser(String userId, String userFriendId) {
        Channel channel = BeanUtil.getBean("Channel", Channel.class);
        channel.writeAndFlush(new TalkNoticeRequest(userId, userFriendId, 0));
    }

    /**
     * 对群组点击“发送消息”按钮时触发，向服务端发送对话通知请求
     */
    @Override
    public void doEventAddTalkGroup(String userId, String groupId) {
        Channel channel = BeanUtil.getBean("Channel", Channel.class);
        channel.writeAndFlush(new TalkNoticeRequest(userId, groupId, 1));
    }

    /**
     * 用户删除一个对话框时触发，向服务端发送删除对话框请求
     */
    @Override
    public void doEventDelTalkUser(String userId, String talkId) {
        Channel channel = BeanUtil.getBean("Channel", Channel.class);
        channel.writeAndFlush(new DelTalkRequest(userId, talkId));
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
