package org.chat.server.domain.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.chat.server.infrastructure.common.Constants;

import java.util.Date;

/**
 * @author XiaoRed
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatRecordInfo {
    private String userId;     // 用户ID
    private String friendId;   // 好友ID
    private String msgContent; // 消息内容
    private Integer msgType;   // 消息类型；0文字消息、1固定表情
    private Date msgDate;      // 消息时间
    private Integer talkType;  // 对话框类型；0好友、1群组

    public ChatRecordInfo(String userId, String friendId, String msgContent, Integer msgType, Date msgDate) {
        this.friendId = friendId;
        this.userId = userId;
        this.msgContent = msgContent;
        this.msgType = msgType;
        this.msgDate = msgDate;
        this.talkType = Constants.TalkType.Friend.getCode();
    }
}
