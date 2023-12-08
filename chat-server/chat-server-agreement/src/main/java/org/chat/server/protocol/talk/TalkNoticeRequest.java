package org.chat.server.protocol.talk;

import org.chat.server.protocol.Command;
import org.chat.server.protocol.Packet;

/**
 * 对话通知请求协议：发起方id + 目标方id + 通讯类型（单聊or群聊）
 * @author XiaoRed
 * @date 2023/12/8 20:57
 */
public class TalkNoticeRequest extends Packet {

    private String userId; // 用户ID【发起方】
    private String friendUserId; // 目标方id【好友id或群组id，看是啥类型】
    private Integer talkType; // 对话框类型[0好友、1群组]

    public TalkNoticeRequest(){}

    public TalkNoticeRequest(String userId, String friendUserId, Integer talkType) {
        this.userId = userId;
        this.friendUserId = friendUserId;
        this.talkType = talkType;
    }

    @Override
    public Byte getCommand() {
        return Command.TalkNoticeRequest;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFriendUserId() {
        return friendUserId;
    }

    public void setFriendUserId(String friendUserId) {
        this.friendUserId = friendUserId;
    }

    public Integer getTalkType() {
        return talkType;
    }

    public void setTalkType(Integer talkType) {
        this.talkType = talkType;
    }

}
