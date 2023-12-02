package org.chat.client.socket.handler;

import com.alibaba.fastjson.JSON;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import javafx.application.Platform;
import org.chat.client.application.UIService;
import org.chat.server.protocol.login.LoginResponse;
import org.chat.server.protocol.login.dto.ChatRecordDto;
import org.chat.server.protocol.login.dto.GroupsDto;
import org.chat.server.protocol.login.dto.TalkBoxDto;
import org.chat.server.protocol.login.dto.UserFriendDto;
import org.chat.ui.view.chat.IChatMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 客户端处理登录响应
 * @author XiaoRed
 * @date 2023/12/2 8:31
 */
public class LoginHandler extends SimpleChannelInboundHandler<LoginResponse> {

    protected Logger logger = LoggerFactory.getLogger(LoginHandler.class);

    private UIService uiService;

    public LoginHandler(UIService uiService) {
        this.uiService = uiService;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponse msg) throws Exception {
        logger.info("\r\n> msg handler ing ...");
        logger.info("收到登录响应消息：" + JSON.toJSONString(msg));
        if (!msg.isSuccess()) {
            logger.info("登录失败...");
            return;
        }
        Platform.runLater(() -> {
            uiService.getLogin().doLoginSuccess();
            IChatMethod chat = uiService.getChat();
            // 设置用户信息
            chat.setUserInfo(msg.getUserId(), msg.getUserNickName(), msg.getUserHead());
            // 对话框
            List<TalkBoxDto> chatTalkList = msg.getChatTalkList();
            if (null != chatTalkList) {
                chatTalkList.forEach(talk -> {
                            chat.addTalkBox(0, talk.getTalkType(), talk.getTalkId(), talk.getTalkName(), talk.getAvatar(), talk.getDesc(), talk.getTalkDate(), true);
                            switch (talk.getTalkType()) {
                                // 好友
                                case 0:
                                    List<ChatRecordDto> userRecordList = talk.getChatRecordList();
                                    if (null == userRecordList || userRecordList.isEmpty()) return;
                                    for (int i = userRecordList.size() - 1; i >= 0; i--) {
                                        ChatRecordDto chatRecord = userRecordList.get(i);
                                        //  自己的消息
                                        if (0 == chatRecord.getMsgType()) {
                                            chat.addTalkMsgRight(chatRecord.getTalkId(), chatRecord.getMsgContent(), chatRecord.getMsgType(), chatRecord.getMsgDate(), true, false, false);
                                            continue;
                                        }
                                        // 好友的消息
                                        if (1 == chatRecord.getMsgType()) {
                                            chat.addTalkMsgUserLeft(chatRecord.getTalkId(), chatRecord.getMsgContent(), chatRecord.getMsgType(), chatRecord.getMsgDate(), true, false, false);
                                        }
                                    }
                                    break;
                                // 群组
                                case 1:
                                    List<ChatRecordDto> groupRecordList = talk.getChatRecordList();
                                    if (null == groupRecordList || groupRecordList.isEmpty()) return;
                                    for (int i = groupRecordList.size() - 1; i >= 0; i--) {
                                        ChatRecordDto chatRecord = groupRecordList.get(i);
                                        //  自己的消息
                                        if (0 == chatRecord.getMsgUserType()) {
                                            chat.addTalkMsgRight(chatRecord.getTalkId(), chatRecord.getMsgContent(), chatRecord.getMsgType(), chatRecord.getMsgDate(), true, false, false);
                                            continue;
                                        }
                                        // 他人的消息
                                        if (1 == chatRecord.getMsgUserType()) {
                                            chat.addTalkMsgGroupLeft(chatRecord.getTalkId(), chatRecord.getUserId(), chatRecord.getUserNickName(), chatRecord.getUserHead(), chatRecord.getMsgContent(), chatRecord.getMsgType(), chatRecord.getMsgDate(), true, false, false);
                                        }
                                    }
                                    break;
                                default:
                                    break;
                            }

                        }
                );

            }
            // 群组
            List<GroupsDto> groupsList = msg.getGroupsList();
            if (null != groupsList) {
                groupsList.forEach(groups -> chat.addFriendGroup(groups.getGroupId(), groups.getGroupName(), groups.getGroupHead()));
            }
            // 好友
            List<UserFriendDto> userFriendList = msg.getUserFriendList();
            if (null != userFriendList) {
                userFriendList.forEach(friend -> chat.addFriendUser(false, friend.getFriendId(), friend.getFriendName(), friend.getFriendHead()));
            }
        });
        logger.info("成功进入IM系统...");
    }
}
