package org.chat.server.protocol.msg;

import org.chat.server.protocol.Command;
import org.chat.server.protocol.Packet;

import java.util.Date;

/**
 * 群聊消息响应协议：群聊id + 发送方id + 发送方相关信息（名称，头像） + 消息内容 + 消息类型 + 消息时间
 * @author XiaoRed
 * @date 2023/12/9 8:57
 */
public class MsgGroupResponse extends Packet {

    private String talkId; //对话框ID，也就是群组id
    private String userId; //群员用户ID（发送方id）
    private String userNickName; //发送方昵称
    private String userHead; //发送方头像
    private String msg;  //群员用户发送消息内容
    private Integer msgType; //消息类型；0文字消息、1固定表情
    private Date msgDate;  // 群员用户发送消息时间

    public MsgGroupResponse() {
    }

    @Override
    public Byte getCommand() {
        return Command.MsgGroupResponse;
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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
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