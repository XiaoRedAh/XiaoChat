package org.chat.server.protocol.msg;

import org.chat.server.protocol.Command;
import org.chat.server.protocol.Packet;

import java.util.Date;

/**
 * 单聊消息请求协议：发送方id + 目标方id + 消息内容 + 消息类型 + 消息时间
 * @author XiaoRed
 * @date 2023/12/8 23:44
 */
public class MsgRequest extends Packet {

    private String userId;  // 用户ID[发送方我]
    private String friendId; // 好友ID[目标方]
    private String msgText;  // 传输消息内容
    private Integer msgType; // 消息类型；0文字消息、1表情包
    private Date msgDate; // 消息时间

    public MsgRequest() {
    }

    public MsgRequest(String userId, String friendId, String msgText, Integer msgType, Date msgDate) {
        this.userId = userId;
        this.friendId = friendId;
        this.msgText = msgText;
        this.msgType = msgType;
        this.msgDate = msgDate;
    }

    @Override
    public Byte getCommand() {
        return Command.MsgRequest;
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

    public String getMsgText() {
        return msgText;
    }

    public void setMsgText(String msgText) {
        this.msgText = msgText;
    }

    public Integer getMsgType() {
        return msgType;
    }

    public void setMsgType(Integer msgType) {
        this.msgType = msgType;
    }

    public Date getMsgDate() {
        return msgDate;
    }

    public void setMsgDate(Date msgDate) {
        this.msgDate = msgDate;
    }


}
