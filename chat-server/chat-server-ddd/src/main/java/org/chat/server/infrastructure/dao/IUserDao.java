package org.chat.server.infrastructure.dao;

import org.apache.ibatis.annotations.Mapper;
import org.chat.server.infrastructure.po.User;


/**
 * (User)表数据库访问层
 *
 * @author XiaoRed
 * @since 2023-12-01 16:00:47
 */
@Mapper
public interface IUserDao {

    String queryUserPassword(String userId);

    User queryUserById(String userId);
}
