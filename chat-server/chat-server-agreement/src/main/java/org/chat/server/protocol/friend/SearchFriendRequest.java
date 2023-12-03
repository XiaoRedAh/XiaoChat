package org.chat.server.protocol.friend;

import org.chat.server.protocol.Command;
import org.chat.server.protocol.Packet;

/**
 * 搜索好友请求协议：发起方id + 搜索关键字
 * @author XiaoRed
 * @date 2023/12/2 23:33
 */
public class SearchFriendRequest extends Packet {
    private String userId; // 用户ID
    private String searchKey; // 搜索字段

    public SearchFriendRequest() {
    }

    public SearchFriendRequest(String userId, String searchKey) {
        this.userId = userId;
        this.searchKey = searchKey;
    }

    @Override
    public Byte getCommand() {
        return Command.SearchFriendRequest;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

}
