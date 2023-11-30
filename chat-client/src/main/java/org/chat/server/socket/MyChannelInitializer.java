package org.chat.server.socket;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import org.chat.server.codec.ObjDecoder;
import org.chat.server.codec.ObjEncoder;

/**
 * @author XiaoRed
 * @date 2023/11/30 18:53
 */
public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        channel.pipeline()
                .addLast(new ObjDecoder()) //对象解码器
                //todo：后续业务功能实现时，不断补全接收数据实现方法
                .addLast(new ObjEncoder()); //对象编码器

    }
}
