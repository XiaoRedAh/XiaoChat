package org.chat.server.socket.handler;

import com.alibaba.fastjson.JSON;
import io.netty.channel.Channel;
import org.chat.server.application.UserService;
import org.chat.server.domain.user.model.*;
import org.chat.server.infrastructure.common.Constants;
import org.chat.server.infrastructure.common.SocketChannelUtil;
import org.chat.server.protocol.login.LoginRequest;
import org.chat.server.protocol.login.LoginResponse;
import org.chat.server.protocol.login.dto.ChatRecordDto;
import org.chat.server.protocol.login.dto.GroupsDto;
import org.chat.server.protocol.login.dto.TalkBoxDto;
import org.chat.server.protocol.login.dto.UserFriendDto;
import org.chat.server.socket.MyHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * 登录业务处理
 * @author XiaoRed
 * @date 2023/12/1 23:38
 */
public class LoginHandler extends MyHandler<LoginRequest> {

    public LoginHandler(UserService userService){super(userService);}
    @Override
    public void channelRead(Channel channel, LoginRequest msg) {
        logger.info("正在处理登录请求： {}", JSON.toJSON(msg));
        boolean success = userService.checkAuth(msg.getUserId(), msg.getUserPassword());
        if(!success){ //登录校验不通过
            channel.writeAndFlush(new LoginResponse(false));
            logger.info("登录请求处理完成，响应为登录失败...");
            return;
        }
        //登录校验通过
        //1. 将用户与channel的对应关系存储起来，方便后续服务端处理聊天业务
        SocketChannelUtil.addChannel(msg.getUserId(), channel);
        //2. 初始化数据：用户信息
        UserInfo userInfo = userService.queryUserInfo(msg.getUserId());
        //3. 初始化数据：好友列表
        List<UserFriendInfo> friendInfos = userService.queryUserFriendInfoList(msg.getUserId());
        //4. 初始化数据：群组列表
        List<GroupsInfo> groupsInfos = userService.queryGroupsInfoList(msg.getUserId());
        //5. 初始化数据：聊天框
        List<TalkBoxInfo> talkBoxInfos = userService.queryTalkBoxInfoList(msg.getUserId());
        //6. 封装LoginResponse对象
        LoginResponse loginResponse = new LoginResponse();
        for (TalkBoxInfo talkBoxInfo : talkBoxInfos) {
            TalkBoxDto talkBox = new TalkBoxDto();
            talkBox.setTalkId(talkBoxInfo.getTalkId());
            talkBox.setTalkType(talkBoxInfo.getTalkType());
            talkBox.setTalkName(talkBoxInfo.getTalkName());
            talkBox.setAvatar(talkBoxInfo.getTalkHead());
            talkBox.setDesc(talkBoxInfo.getDesc());
            talkBox.setTalkDate(talkBoxInfo.getTalkDate());
            loginResponse.getChatTalkList().add(talkBox);

            // 好友；聊天消息
            if (Constants.TalkType.Friend.getCode().equals(talkBoxInfo.getTalkType())) {
                List<ChatRecordDto> chatRecordDtoList = new ArrayList<>();
                List<ChatRecordInfo> chatRecordInfoList = userService.queryChatRecordInfoList(talkBoxInfo.getTalkId(), msg.getUserId(), Constants.TalkType.Friend.getCode());
                for (ChatRecordInfo chatRecordInfo : chatRecordInfoList) {
                    ChatRecordDto chatRecordDto = new ChatRecordDto();
                    chatRecordDto.setTalkId(talkBoxInfo.getTalkId());
                    boolean msgType = msg.getUserId().equals(chatRecordInfo.getUserId());
                    // 自己发的消息
                    if (msgType) {
                        chatRecordDto.setUserId(chatRecordInfo.getUserId());
                        chatRecordDto.setMsgUserType(0); // 消息类型[0自己/1好友]
                    }
                    // 好友发的消息
                    else {
                        chatRecordDto.setUserId(chatRecordInfo.getFriendId());
                        chatRecordDto.setMsgUserType(1); // 消息类型[0自己/1好友]
                    }
                    chatRecordDto.setMsgContent(chatRecordInfo.getMsgContent());
                    chatRecordDto.setMsgType(chatRecordInfo.getMsgType());
                    chatRecordDto.setMsgDate(chatRecordInfo.getMsgDate());
                    chatRecordDtoList.add(chatRecordDto);
                }
                talkBox.setChatRecordList(chatRecordDtoList);
            }
            // 群组；聊天消息
            else if (Constants.TalkType.Group.getCode().equals(talkBoxInfo.getTalkType())) {
                List<ChatRecordDto> chatRecordDtoList = new ArrayList<>();
                List<ChatRecordInfo> chatRecordInfoList = userService.queryChatRecordInfoList(talkBoxInfo.getTalkId(), msg.getUserId(), Constants.TalkType.Group.getCode());
                for (ChatRecordInfo chatRecordInfo : chatRecordInfoList) {
                    UserInfo memberInfo = userService.queryUserInfo(chatRecordInfo.getUserId());
                    ChatRecordDto chatRecordDto = new ChatRecordDto();
                    chatRecordDto.setTalkId(talkBoxInfo.getTalkId());
                    chatRecordDto.setUserId(memberInfo.getUserId());
                    chatRecordDto.setUserNickName(memberInfo.getUserNickName());
                    chatRecordDto.setUserHead(memberInfo.getUserHead());
                    chatRecordDto.setMsgContent(chatRecordInfo.getMsgContent());
                    chatRecordDto.setMsgDate(chatRecordInfo.getMsgDate());
                    boolean msgType = msg.getUserId().equals(chatRecordInfo.getUserId());
                    chatRecordDto.setMsgUserType(msgType ? 0 : 1); // 消息类型[0自己/1好友]
                    chatRecordDto.setMsgType(chatRecordInfo.getMsgType());
                    chatRecordDtoList.add(chatRecordDto);
                }
                talkBox.setChatRecordList(chatRecordDtoList);
            }

        }
        // 3.3 群组
        List<GroupsInfo> groupsInfoList = userService.queryGroupsInfoList(msg.getUserId());
        for (GroupsInfo groupsInfo : groupsInfoList) {
            GroupsDto groups = new GroupsDto();
            groups.setGroupId(groupsInfo.getGroupId());
            groups.setGroupName(groupsInfo.getGroupName());
            groups.setGroupHead(groupsInfo.getGroupHead());
            loginResponse.getGroupsList().add(groups);
        }
        // 3.4 好友
        List<UserFriendInfo> userFriendInfoList = userService.queryUserFriendInfoList(msg.getUserId());
        for (UserFriendInfo userFriendInfo : userFriendInfoList) {
            UserFriendDto userFriend = new UserFriendDto();
            userFriend.setFriendId(userFriendInfo.getFriendId());
            userFriend.setFriendName(userFriendInfo.getFriendName());
            userFriend.setFriendHead(userFriendInfo.getFriendHead());
            loginResponse.getUserFriendList().add(userFriend);
        }
        loginResponse.setSuccess(true);
        loginResponse.setUserId(userInfo.getUserId());
        loginResponse.setUserNickName(userInfo.getUserNickName());
        loginResponse.setUserHead(userInfo.getUserHead());
        //7. 通过channel向客户端响应LoginResponse
        channel.writeAndFlush(loginResponse);
        logger.info("登录请求处理完成，响应为登录成功...");
    }
}
