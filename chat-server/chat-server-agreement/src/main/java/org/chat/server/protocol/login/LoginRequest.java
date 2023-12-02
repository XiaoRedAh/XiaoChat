package org.chat.server.protocol.login;

import org.chat.server.protocol.Command;
import org.chat.server.protocol.Packet;

/**
 * 登录请求协议：用户名 + 密码
 * @author XiaoRed
 * @date 2023/12/1 18:56
 */
public class LoginRequest extends Packet {
    private String userId;
    private String userPassword;

    public LoginRequest(String userId, String userPassword) {
        this.userId = userId;
        this.userPassword = userPassword;
    }

    @Override
    public Byte getCommand() {
        return Command.LoginRequest;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

}
