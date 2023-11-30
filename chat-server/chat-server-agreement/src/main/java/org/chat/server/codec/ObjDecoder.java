package org.chat.server.codec;

import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import org.chat.server.protocol.Packet;
import org.chat.server.util.SerializationUtil;

import java.util.List;

/**
 * 自定义解码器，将接收到的字节转换为指定的传输对象实例
 * @author XiaoRed
 * @date 2023/11/30 15:06
 */
public class ObjDecoder extends ByteToMessageDecoder {

    /**
     * 对接收到的字节进行编码，首先明确统一的通信协议格式：int类型的帧长 + byte类型的指令码 + byte[]业务数据
     * 先根据协议格式，分别获取到这三个部分，然后再转换为对应的业务对象
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        if (in.readableBytes() < 4) { //字节数据长度<4，连一开头的帧长都凑不出来，直接返回，暂不处理
            return;
        }
        in.markReaderIndex(); //记录当前读取位置，之后resetReaderIndex()方法可以返回到该位置
        //1. 获取int类型的帧长，并判断接下来的字节是否包含了这个完整的帧
        int dataLength = in.readInt(); //获取到帧长
        if (in.readableBytes() < dataLength) { //剩余可读部分小于帧长，说明数据包还未接收完整，读取位回退到mark，暂不处理
            in.resetReaderIndex();
            return;
        }
        //2. 获取byte类型的指令码，明确需要将数据转换为什么业务对象
        byte command = in.readByte();
        //3. 获取byte[]的业务数据
        byte[] data = new byte[dataLength - 1]; //指令码占了一位，剔除掉
        in.readBytes(data);
        //4. 将业务数据data转换为指令码对应的业务对象
        out.add(SerializationUtil.deserialize(data, Packet.get(command)));
    }

}
