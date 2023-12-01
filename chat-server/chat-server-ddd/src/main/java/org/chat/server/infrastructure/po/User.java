package org.chat.server.infrastructure.po;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * (User)表实体类
 *
 * @author XiaoRed
 * @since 2023-12-01 16:00:47
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User  {
    //自增ID@TableId
    private Long id;
    //用户ID
    private String userid;
    //用户昵称
    private String userNickname;
    //用户头像
    private String userHead;
    //用户密码
    private String userPassword;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;
}

