package org.chat.server.domain.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author XiaoRed
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TalkBoxInfo {
    private Integer talkType; // 对话框类型；0好友、1群组
    private String talkId;    // 对话框ID(好友ID、群组ID)
    private String talkName;  // 对话框名称
    private String talkHead;  // 对话框头像
    private String desc;  // 消息简述
    private Date talkDate;    // 消息时间
}
