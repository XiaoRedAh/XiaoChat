package org.chat.server.infrastructure.dao;

import org.apache.ibatis.annotations.Mapper;
import org.chat.server.infrastructure.po.TalkBox;

import java.util.List;


/**
 * (TalkBox)表数据库访问层
 *
 * @author makejava
 * @since 2023-12-01 16:00:47
 */
@Mapper
public interface ITalkBoxDao {

    List<TalkBox> queryTalkBoxList(String userId);
}
