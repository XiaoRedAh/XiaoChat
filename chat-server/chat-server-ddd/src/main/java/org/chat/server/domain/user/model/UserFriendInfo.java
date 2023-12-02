package org.chat.server.domain.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author XiaoRed
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserFriendInfo {
    private String friendId;  // 好友ID
    private String friendName; // 好友名称
    private String friendHead; // 好友头像
}
