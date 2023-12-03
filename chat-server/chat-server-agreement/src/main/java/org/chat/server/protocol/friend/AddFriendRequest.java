package org.chat.server.protocol.friend;

import org.chat.server.protocol.Command;
import org.chat.server.protocol.Packet;

/**
 * 添加好友请求协议：发起方id + 目标好友id
 * @author XiaoRed
 * @date 2023/12/2 23:33
 */
public class AddFriendRequest extends Packet {

    private String userId; //用户ID[自己的ID]
    private String friendId; //目标好友ID

    public AddFriendRequest(){}

    public AddFriendRequest(String userId, String friendId) {
        this.userId = userId;
        this.friendId = friendId;
    }

    @Override
    public Byte getCommand() {
        return Command.AddFriendRequest;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }

}
