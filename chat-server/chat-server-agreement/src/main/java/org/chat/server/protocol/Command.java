package org.chat.server.protocol;

/**
 * 定义各个传输对象的指令码
 * @author XiaoRed
 * @date 2023/11/30 15:08
 */
public interface Command {
    Byte LoginRequest = 1;
    Byte LoginResponse = 2;
    Byte AddFriendRequest = 3;
    Byte AddFriendResponse = 4;
    Byte SearchFriendRequest = 5;
    Byte SearchFriendResponse = 6;
    Byte TalkNoticeRequest = 7;
    Byte TalkNoticeResponse = 8;
    Byte DelTalkRequest = 9;
    Byte MsgRequest = 10;
    Byte MsgResponse = 11;
}
