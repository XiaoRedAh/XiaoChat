package org.chat.server.domain.user.repository;

import org.chat.server.domain.user.model.*;
import org.chat.server.infrastructure.po.UserFriend;

import java.util.List;

/**
 * @author XiaoRed
 * @date 2023/12/2 8:05
 */
public interface IUserRepository {

    String queryUserPassword(String userId);

    /**
     * 查询用户信息
     * @param userId 用户ID
     * @return 用户信息
     */
    UserInfo queryUserInfo(String userId);

    /**
     * 查询个人用户对话框列表
     * @param userId 个人用户ID
     * @return 对话框列表
     */
    List<TalkBoxInfo> queryTalkBoxInfoList(String userId);

    /**
     * 查询个人用户好友列表
     * @param userId 个人用户ID
     * @return 好友列表
     */
    List<UserFriendInfo> queryUserFriendInfoList(String userId);

    /**
     * 查询个人用户群组列表
     * @param userId 个人用户ID
     * @return 群组列表
     */
    List<GroupsInfo> queryGroupsInfoList(String userId);

    /**
     * 查询聊天记录
     * @param talkId   对话框ID
     * @param userId   好友ID
     * @param talkType 对话类型；0好友、1群组
     * @return 聊天记录(10条)
     */
    List<ChatRecordInfo> queryChatRecordInfoList(String talkId, String userId, Integer talkType);

    /**
     * 模糊查询用户
     * @param userId    用户ID
     * @param searchKey 用户名、用户ID
     * @return < 10个用户集合
     */
    List<LuckUserInfo> queryFuzzyUserInfoList(String userId, String searchKey);

    /**
     * 一次性入库多条好友记录
     * @param userFriendList 多条好友记录
     */
    void addUserFriend(List<UserFriend> userFriendList);

    /**
     * 添加对话框
     * @param userId   用户ID
     * @param talkId   好友ID
     * @param talkType 对话框类型[0好友、1群组]
     */
    void addTalkBoxInfo(String userId, String talkId, int talkType);

    /**
     * 删除用户对话框
     * @param userId  用户ID
     * @param talkId  对话框ID
     */
    void deleteUserTalk(String userId, String talkId);

    /**
     * 添加聊天记录
     * @param chatRecordInfo 聊天记录信息
     */
    void appendChatRecord(ChatRecordInfo chatRecordInfo);

    /**
     * 查询用户群组ID集合
     * @param userId  用户ID
     * @return        用户群组ID集合
     */
    List<String> queryUserGroupsIdList(String userId);
}
