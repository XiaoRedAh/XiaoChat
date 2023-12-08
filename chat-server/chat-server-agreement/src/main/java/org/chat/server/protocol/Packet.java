package org.chat.server.protocol;

import org.chat.server.protocol.friend.AddFriendRequest;
import org.chat.server.protocol.friend.AddFriendResponse;
import org.chat.server.protocol.friend.SearchFriendRequest;
import org.chat.server.protocol.friend.SearchFriendResponse;
import org.chat.server.protocol.login.LoginRequest;
import org.chat.server.protocol.login.LoginResponse;
import org.chat.server.protocol.talk.DelTalkRequest;
import org.chat.server.protocol.talk.TalkNoticeRequest;
import org.chat.server.protocol.talk.TalkNoticeResponse;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 数据包抽象类，由传输对象继承，主要是为了实现getCommand方法，获取这个传输对象对应的指令码
 * @author XiaoRed
 * @date 2023/11/30 15:09
 */
public abstract class Packet {

    /**
     * key为指令码，value为继承了Packet的传输对象，这个map存储了每个指令码对应的传输对象
     */
    private final static Map<Byte, Class<? extends Packet>> packetType = new ConcurrentHashMap<>();

    /**
     * 将所有<指令码，传输对象>的映射关系提前存到packetType中
     */
    static {
        packetType.put(Command.LoginRequest, LoginRequest.class);
        packetType.put(Command.LoginResponse, LoginResponse.class);
        packetType.put(Command.AddFriendRequest, AddFriendRequest.class);
        packetType.put(Command.AddFriendResponse, AddFriendResponse.class);
        packetType.put(Command.SearchFriendRequest, SearchFriendRequest.class);
        packetType.put(Command.SearchFriendResponse, SearchFriendResponse.class);
        packetType.put(Command.TalkNoticeRequest, TalkNoticeRequest.class);
        packetType.put(Command.TalkNoticeResponse, TalkNoticeResponse.class);
        packetType.put(Command.DelTalkRequest, DelTalkRequest.class);
    }

    /**
     * 根据指令码，返回它对应的传输对象
     */
    public static Class<? extends Packet> get(Byte command) {
        return packetType.get(command);
    }

    /**
     * 获取协议指令
     * @return 返回指令值
     */
    public abstract Byte getCommand();

}
