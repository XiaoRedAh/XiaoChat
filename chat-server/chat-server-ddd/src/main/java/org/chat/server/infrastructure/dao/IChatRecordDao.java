package org.chat.server.infrastructure.dao;


import org.apache.ibatis.annotations.Mapper;
import org.chat.server.infrastructure.po.ChatRecord;

import java.util.List;

/**
 * (ChatRecord)表数据库访问层
 *
 * @author XiaoRed
 * @since 2023-12-01 16:00:47
 */
@Mapper
public interface IChatRecordDao {

    List<ChatRecord> queryUserChatRecordList(String talkId, String userId);

    List<ChatRecord> queryGroupsChatRecordList(String talkId, String userId);

    void appendChatRecord(ChatRecord chatRecord);
}
