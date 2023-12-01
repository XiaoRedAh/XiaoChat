package org.chat.server.infrastructure.po;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * (TalkBox)表实体类
 *
 * @author XiaoRed
 * @since 2023-12-01 16:00:47
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TalkBox  {
    //自增ID@TableId
    private Long id;
    //用户ID
    private String userId;
    //对话框ID(好友ID、群组ID)
    private String talkId;
    //对话框类型；0好友、1群组
    private Integer talkType;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;
}

