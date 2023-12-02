package org.chat.server.infrastructure.repository;

import org.chat.server.domain.user.model.*;
import org.chat.server.domain.user.repository.IUserRepository;
import org.chat.server.infrastructure.dao.*;
import org.chat.server.infrastructure.po.ChatRecord;
import org.chat.server.infrastructure.po.Groups;
import org.chat.server.infrastructure.po.TalkBox;
import org.chat.server.infrastructure.po.User;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static org.chat.server.infrastructure.common.Constants.TalkType.Friend;
import static org.chat.server.infrastructure.common.Constants.TalkType.Group;

/**
 * @author XiaoRed
 * @date 2023/12/2 8:13
 */
@Repository("userRepository")
public class UserRepository implements IUserRepository {
    @Resource
    private IChatRecordDao chatRecordDao;
    @Resource
    private IGroupsDao groupsDao;
    @Resource
    private ITalkBoxDao talkBoxDao;
    @Resource
    private IUserDao userDao;
    @Resource
    private IUserFriendDao userFriendDao;
    @Resource
    private IUserGroupDao userGroupDao;


    @Override
    public String queryUserPassword(String userId) {
        return userDao.queryUserPassword(userId);
    }

    @Override
    public UserInfo queryUserInfo(String userId) {
        User user = userDao.queryUserById(userId);
        return new UserInfo(user.getUserId(), user.getUserNickName(), user.getUserHead());
    }

    @Override
    public List<TalkBoxInfo> queryTalkBoxInfoList(String userId) {
        List<TalkBoxInfo> talkBoxInfoList = new ArrayList<>();
        List<TalkBox> talkBoxList = talkBoxDao.queryTalkBoxList(userId);
        for (TalkBox talkBox : talkBoxList) {
            TalkBoxInfo talkBoxInfo = new TalkBoxInfo();
            if (Friend.getCode().equals(talkBox.getTalkType())) {
                User user = userDao.queryUserById(talkBox.getTalkId());
                talkBoxInfo.setTalkType(Friend.getCode());
                talkBoxInfo.setTalkId(user.getUserId());
                talkBoxInfo.setTalkName(user.getUserNickName());
                talkBoxInfo.setTalkHead(user.getUserHead());
            } else if (Group.getCode().equals(talkBox.getTalkType())) {
                Groups groups = groupsDao.queryGroupsById(talkBox.getTalkId());
                talkBoxInfo.setTalkType(Group.getCode());
                talkBoxInfo.setTalkId(groups.getGroupId());
                talkBoxInfo.setTalkName(groups.getGroupName());
                talkBoxInfo.setTalkHead(groups.getGroupHead());
            }
            talkBoxInfoList.add(talkBoxInfo);
        }
        return talkBoxInfoList;
    }

    @Override
    public List<UserFriendInfo> queryUserFriendInfoList(String userId) {
        List<UserFriendInfo> userFriendInfoList = new ArrayList<>();
        List<String> friendIdList = userFriendDao.queryUserFriendIdList(userId);
        for (String friendId : friendIdList) {
            User user = userDao.queryUserById(friendId);
            UserFriendInfo friendInfo = new UserFriendInfo();
            friendInfo.setFriendId(user.getUserId());
            friendInfo.setFriendName(user.getUserNickName());
            friendInfo.setFriendHead(user.getUserHead());
            userFriendInfoList.add(friendInfo);
        }
        return userFriendInfoList;
    }

    @Override
    public List<GroupsInfo> queryGroupsInfoList(String userId) {
        List<GroupsInfo> groupsInfoList = new ArrayList<>();
        List<String> groupsIdList = userGroupDao.queryUserGroupsIdList(userId);
        for (String groupsId : groupsIdList) {
            Groups groups = groupsDao.queryGroupsById(groupsId);
            GroupsInfo groupsInfo = new GroupsInfo();
            groupsInfo.setGroupId(groups.getGroupId());
            groupsInfo.setGroupName(groups.getGroupName());
            groupsInfo.setGroupHead(groups.getGroupHead());
            groupsInfoList.add(groupsInfo);
        }
        return groupsInfoList;
    }

    @Override
    public List<ChatRecordInfo> queryChatRecordInfoList(String talkId, String userId, Integer talkType) {
        List<ChatRecordInfo> chatRecordInfoList = new ArrayList<>();
        List<ChatRecord> list = new ArrayList<>();
        if (Friend.getCode().equals(talkType)){
            list = chatRecordDao.queryUserChatRecordList(talkId, userId);
        } else if (Group.getCode().equals(talkType)){
            list =  chatRecordDao.queryGroupsChatRecordList(talkId, userId);
        }
        for (ChatRecord chatRecord : list) {
            ChatRecordInfo chatRecordInfo = new ChatRecordInfo();
            chatRecordInfo.setUserId(chatRecord.getUserId());
            chatRecordInfo.setFriendId(chatRecord.getFriendId());
            chatRecordInfo.setMsgContent(chatRecord.getMsgContent());
            chatRecordInfo.setMsgType(chatRecord.getMsgType());
            chatRecordInfo.setMsgDate(chatRecord.getMsgDate());
            chatRecordInfoList.add(chatRecordInfo);
        }
        return chatRecordInfoList;
    }
}
