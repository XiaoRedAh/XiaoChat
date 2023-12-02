package org.chat.server.protocol.login;

import org.chat.server.protocol.Command;
import org.chat.server.protocol.Packet;
import org.chat.server.protocol.login.dto.GroupsDto;
import org.chat.server.protocol.login.dto.TalkBoxDto;
import org.chat.server.protocol.login.dto.UserFriendDto;

import java.util.ArrayList;
import java.util.List;

/**
 * 登录响应协议：是否成功 + 用户基本信息 + 初始化数据（对话框，好友，群组）
 * @author XiaoRed
 * @date 2023/12/1 18:56
 */
public class LoginResponse extends Packet {
    Boolean success; //登录成功为true
    private String userId; // 用户ID
    private String userNickName; //用户昵称
    private String userHead;  //用户头像
    private List<TalkBoxDto> chatTalkList = new ArrayList<>(); //聊天对话框数据
    private List<UserFriendDto> userFriendList = new ArrayList<>(); //加载好友列表
    private List<GroupsDto> groupsList = new ArrayList<>(); //加载群组列表

    public LoginResponse(){}

    public LoginResponse(boolean success){
        this.success = success;
    }
    @Override
    public Byte getCommand() {
        return Command.LoginResponse;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserHead() {
        return userHead;
    }

    public void setUserHead(String userHead) {
        this.userHead = userHead;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    public List<TalkBoxDto> getChatTalkList() {
        return chatTalkList;
    }

    public void setChatTalkList(List<TalkBoxDto> chatTalkList) {
        this.chatTalkList = chatTalkList;
    }

    public List<GroupsDto> getGroupsList() {
        return groupsList;
    }

    public void setGroupsList(List<GroupsDto> groupsList) {
        this.groupsList = groupsList;
    }

    public List<UserFriendDto> getUserFriendList() {
        return userFriendList;
    }

    public void setUserFriendList(List<UserFriendDto> userFriendList) {
        this.userFriendList = userFriendList;
    }
}
