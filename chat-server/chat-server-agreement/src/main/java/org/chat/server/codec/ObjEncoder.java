package org.chat.server.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.chat.server.protocol.Packet;
import org.chat.server.util.SerializationUtil;

/**
 * 自定义编码器，将待发送的业务对象(继承于Packet)转换为字节，并封装为统一的通信协议格式
 * @author XiaoRed
 * @date 2023/11/30 15:07
 */
public class ObjEncoder extends MessageToByteEncoder<Packet> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Packet in, ByteBuf out) {
        byte[] data = SerializationUtil.serialize(in); //先将业务对象序列化为字节数组
        //封装待位统一的通信协议格式：int类型的帧长 + byte类型的指令码 + byte[]业务数据
        out.writeInt(data.length + 1); //通信协议包长度：数据长度 + 指令码1位
        out.writeByte(in.getCommand()); //该业务对象对应的指令码
        out.writeBytes(data);
    }

}
