package org.chat.server.protocol.login.dto;

/**
 * @author XiaoRed
 * @date 2023/12/1 19:56
 */
public class UserFriendDto {

    private String friendId;    // 好友ID
    private String friendName;  // 好友名称
    private String friendHead;  // 好友头像

    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }

    public String getFriendName() {
        return friendName;
    }

    public void setFriendName(String friendName) {
        this.friendName = friendName;
    }

    public String getFriendHead() {
        return friendHead;
    }

    public void setFriendHead(String friendHead) {
        this.friendHead = friendHead;
    }
}
