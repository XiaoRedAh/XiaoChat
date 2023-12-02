package org.chat.server.application;

import org.chat.server.domain.user.model.*;

import java.util.List;

/**
 * @author XiaoRed
 * @date 2023/12/1 15:34
 */
public interface UserService {
    /**
     * 登录校验
     * @param userId       用户ID
     * @param userPassword 用户密码
     * @return 登陆状态
     */
    boolean checkAuth(String userId, String userPassword);

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
}
