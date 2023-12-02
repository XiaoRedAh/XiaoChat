package org.chat.server.infrastructure.po;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * (ChatRecord)表实体类
 *
 * @author XIaoRed
 * @since 2023-12-01 16:00:47
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatRecord  {
    //自增ID@TableId
    private Long id;
    //自己ID
    private String userId;
    //好友ID
    private String friendId;
    //消息内容
    private String msgContent;
    //消息时间
    private Date msgDate;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;
    private Integer talkType;
    private Integer msgType;
}

