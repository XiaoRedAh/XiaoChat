package org.chat.server.protocol;

/**
 * 定义各个传输对象的指令码
 * @author XiaoRed
 * @date 2023/11/30 15:08
 */
public interface Command {
    Byte LoginRequest = 1;
    Byte LoginResponse = 2;
}
