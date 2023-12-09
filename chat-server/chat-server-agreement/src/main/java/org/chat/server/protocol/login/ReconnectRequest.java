package org.chat.server.protocol.login;

import org.chat.server.protocol.Command;
import org.chat.server.protocol.Packet;

/**
 * 断线重连请求协议
 * @author XiaoRed
 * @date 2023/12/9 10:27
 */
public class ReconnectRequest extends Packet {

    private String userId; //断线重连的用户的id

    public ReconnectRequest(String userId) {
        this.userId = userId;
    }

    @Override
    public Byte getCommand() {
        return Command.ReconnectRequest;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
