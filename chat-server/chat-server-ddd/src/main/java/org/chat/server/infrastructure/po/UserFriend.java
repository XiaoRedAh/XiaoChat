package org.chat.server.infrastructure.po;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * (UserFriend)表实体类
 *
 * @author XiaoRed
 * @since 2023-12-01 16:00:47
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserFriend  {
    //自增ID@TableId
    private Long id;
    //用户ID
    private Long userid;
    //好友用户ID
    private Long userFriendId;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;
}

