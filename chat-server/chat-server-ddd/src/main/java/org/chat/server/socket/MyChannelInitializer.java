package org.chat.server.socket;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import org.chat.server.application.UserService;
import org.chat.server.codec.ObjDecoder;
import org.chat.server.codec.ObjEncoder;
import org.chat.server.socket.handler.AddFriendHandler;
import org.chat.server.socket.handler.LoginHandler;
import org.chat.server.socket.handler.SearchFriendHandler;

/**
 * @author XiaoRed
 * @date 2023/12/1 15:33
 */
public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {

    private final UserService userService;

    public MyChannelInitializer(UserService userService){
        this.userService = userService;
    }
    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        channel.pipeline()
                .addLast(new ObjDecoder()) //对象解码器
                //todo：后续业务功能实现时，不断补全接收数据实现方法
                .addLast(new LoginHandler(userService))
                .addLast(new SearchFriendHandler(userService))
                .addLast(new AddFriendHandler(userService))
                .addLast(new ObjEncoder()); //对象编码器
    }
}
