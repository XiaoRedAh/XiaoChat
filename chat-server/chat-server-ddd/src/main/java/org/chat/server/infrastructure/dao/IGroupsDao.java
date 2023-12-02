package org.chat.server.infrastructure.dao;

import org.apache.ibatis.annotations.Mapper;
import org.chat.server.infrastructure.po.Groups;


/**
 * (Groups)表数据库访问层
 *
 * @author XiaoRed
 * @since 2023-12-01 16:00:42
 */
@Mapper
public interface IGroupsDao {

    Groups queryGroupsById(String talkId);
}
