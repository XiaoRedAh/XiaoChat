package org.chat.server.protocol.friend.dto;

/**
 * @author XiaoRed
 * @date 2023/12/2 23:40
 */
public class UserDto {

    private String userId; // 用户ID
    private String userNickName; // 用户昵称
    private String userHead; // 用户头像
    private Integer status; // 状态；0添加、1[保留]、2已添加

    public UserDto() {
    }

    public UserDto(String userId, String userNickName, String userHead) {
        this.userId = userId;
        this.userNickName = userNickName;
        this.userHead = userHead;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    public String getUserHead() {
        return userHead;
    }

    public void setUserHead(String userHead) {
        this.userHead = userHead;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
