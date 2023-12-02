package org.chat.server.infrastructure.dao;


import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * (UserFriend)表数据库访问层
 *
 * @author XiaoRed
 * @since 2023-12-01 16:00:47
 */
@Mapper
public interface IUserFriendDao {

    List<String> queryUserFriendIdList(String userId);
}
