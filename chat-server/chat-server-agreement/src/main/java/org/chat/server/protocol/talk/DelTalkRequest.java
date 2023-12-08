package org.chat.server.protocol.talk;

import org.chat.server.protocol.Command;
import org.chat.server.protocol.Packet;

/**
 * 删除对话框请求协议：发起方id + 要删除的对话框id
 * @author XiaoRed
 * @date 2023/12/8 21:03
 */
public class DelTalkRequest extends Packet {

    private String userId; // 用户ID【谁要删对话框】
    private String talkId;  // 对话框ID【删哪个对话框】

    public DelTalkRequest() {
    }

    public DelTalkRequest(String userId, String talkId) {
        this.userId = userId;
        this.talkId = talkId;
    }

    @Override
    public Byte getCommand() {
        return Command.DelTalkRequest;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTalkId() {
        return talkId;
    }

    public void setTalkId(String talkId) {
        this.talkId = talkId;
    }

}