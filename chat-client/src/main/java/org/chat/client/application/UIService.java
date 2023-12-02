package org.chat.client.application;

import org.chat.ui.view.chat.IChatMethod;
import org.chat.ui.view.login.ILoginMethod;

/**
 * @author XiaoRed
 * @date 2023/12/2 8:36
 */
public class UIService {

    private ILoginMethod login;
    private IChatMethod chat;

    public ILoginMethod getLogin() {
        return login;
    }

    public void setLogin(ILoginMethod login) {
        this.login = login;
    }

    public IChatMethod getChat() {
        return chat;
    }

    public void setChat(IChatMethod chat) {
        this.chat = chat;
    }
}
