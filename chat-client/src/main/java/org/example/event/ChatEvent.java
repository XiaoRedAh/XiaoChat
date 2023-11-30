package org.example.event;

import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import org.itstack.naive.chat.ui.view.chat.IChatEvent;

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

    @Override
    public void addFriendLuck(String userId, ListView<Pane> listView) {

    }

    @Override
    public void doFriendLuckSearch(String userId, String text) {

    }

    @Override
    public void doEventAddLuckUser(String userId, String friendId) {

    }
}
