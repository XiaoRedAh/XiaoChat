package org.chat.server.protocol.msg;

import org.chat.server.protocol.Command;
import org.chat.server.protocol.Packet;

import java.util.Date;

/**
 * 群聊消息请求协议：群组id + 发送方id + 消息内容 + 消息类型 + 消息时间
 * @author XiaoRed
 * @date 2023/12/9 8:50
 */
public class MsgGroupRequest extends Packet {

    private String talkId; //对话框ID，也就是群组id
    private String userId; //群员ID（发送方id）
    private String msgText; //传输消息内容
    private Integer msgType; //消息类型；0文字消息、1固定表情
    private Date msgDate; //消息时间

    public MsgGroupRequest() {
    }

    public MsgGroupRequest(String talkId, String userId, String msgText,Integer msgType, Date msgDate) {
        this.talkId = talkId;
        this.userId = userId;
        this.msgText = msgText;
        this.msgType = msgType;
        this.msgDate = msgDate;
    }

    @Override
    public Byte getCommand() {
        return Command.MsgGroupRequest;
    }

    public String getTalkId() {
        return talkId;
    }

    public void setTalkId(String talkId) {
        this.talkId = talkId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
