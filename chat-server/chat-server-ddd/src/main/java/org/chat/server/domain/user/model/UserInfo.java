package org.chat.server.domain.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author XiaoRed
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {
    private String userId; //用户ID
    private String userNickName; //用户昵称
    private String userHead; //用户头像
}
