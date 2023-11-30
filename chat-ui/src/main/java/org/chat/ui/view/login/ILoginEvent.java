package org.chat.ui.view.login;

/**
 * 登陆窗口实现，外部给予实现
 */
public interface ILoginEvent {

    /**
     * 登陆验证
     * @param userId        用户ID
     * @param userPassword  用户密码
     */
    void doLoginCheck(String userId, String userPassword);

}
