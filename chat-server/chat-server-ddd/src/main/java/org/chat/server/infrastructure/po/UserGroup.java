package org.chat.server.infrastructure.po;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * (UserGroup)表实体类
 *
 * @author XiaoRed
 * @since 2023-12-01 16:00:47
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserGroup  {
    //自增ID@TableId
    private Long id;
    //用户ID
    private String userid;
    //群组ID
    private String groupId;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;
}

