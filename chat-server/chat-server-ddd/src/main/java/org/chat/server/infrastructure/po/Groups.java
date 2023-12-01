package org.chat.server.infrastructure.po;

import java.util.Date;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * (Groups)表实体类
 *
 * @author XiaoRed
 * @since 2023-12-01 16:00:43
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Groups  {
    //自增ID@TableId
    private Long id;
    //群组ID
    private String groupId;
    //群组名称
    private String groupName;
    //群组头像
    private String groupHead;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;
}

