package org.chat.ui.view.login;


public class LoginEventDefine {

    private LoginInit loginInit;
    private ILoginEvent loginEvent;
    private ILoginMethod loginMethod;

    public LoginEventDefine(LoginInit loginInit, ILoginEvent loginEvent, ILoginMethod loginMethod) {
        this.loginInit = loginInit;
        this.loginEvent = loginEvent;
        this.loginMethod = loginMethod;

        loginInit.move();
        min();
        quit();
        doEventLogin();
    }

    // 事件；最小化
    private void min() {
        loginInit.login_min.setOnAction(event -> {
            loginInit.setIconified(true);
        });
    }

    // 事件；退出
    private void quit() {
        loginInit.login_close.setOnAction(event -> {
            loginInit.close();
            System.exit(0);
        });
    }

    // 事件；登陆
    private void doEventLogin() {
        loginInit.login_button.setOnAction(event -> {
            loginEvent.doLoginCheck(loginInit.userId.getText(), loginInit.userPassword.getText());
        });
    }
}
