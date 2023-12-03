package org.chat.server.protocol.friend;

import org.chat.server.protocol.Command;
import org.chat.server.protocol.Packet;
import org.chat.server.protocol.friend.dto.UserDto;

import java.util.List;

/**
 * 搜索好友响应协议：匹配搜索关键字的所有用户
 * @author XiaoRed
 * @date 2023/12/2 23:34
 */
public class SearchFriendResponse extends Packet {

    private List<UserDto> list;

    public List<UserDto> getList() {
        return list;
    }

    public void setList(List<UserDto> list) {
        this.list = list;
    }

    @Override
    public Byte getCommand() {
        return Command.SearchFriendResponse;
    }
}
