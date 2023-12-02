package org.chat.server.infrastructure.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * (UserGroup)表数据库访问层
 *
 * @author makejava
 * @since 2023-12-01 16:00:47
 */
@Mapper
public interface IUserGroupDao {

    List<String> queryUserGroupsIdList(String userId);
}
