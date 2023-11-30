package org.example.protocol;

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
