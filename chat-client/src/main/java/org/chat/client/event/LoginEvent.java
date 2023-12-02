package org.chat.client.event;

import io.netty.channel.Channel;
import org.chat.client.infrastructure.util.BeanUtil;
import org.chat.server.protocol.login.LoginRequest;
import org.chat.ui.view.login.ILoginEvent;

/**
 * 实现UI登录事件
 * @author XiaoRed
 * @date 2023/11/30 18:48
 */
public class LoginEvent implements ILoginEvent {
    /**
     * 客户端将登录请求发送给服务端校验
     */
    @Override
    public void doLoginCheck(String userId, String userPassword) {
        Channel channel = BeanUtil.getBean("Channel");
        //客户端通过连接的信道，直接将登录请求发送给服务端校验
        channel.writeAndFlush(new LoginRequest(userId, userPassword));
        //todo：断线重连业务
    }
}
