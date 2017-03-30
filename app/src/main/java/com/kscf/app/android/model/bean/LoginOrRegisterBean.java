package com.kscf.app.android.model.bean;

/**
 * @author luoyl
 * @created 2017/3/28
 */

public class LoginOrRegisterBean extends SendSmsBean {

    public String verifyCode;

    public LoginOrRegisterBean(String cellNo, String verifyCode) {
        super(cellNo);
        this.verifyCode = verifyCode;
    }
}
