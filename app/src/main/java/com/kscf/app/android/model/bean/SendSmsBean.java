package com.kscf.app.android.model.bean;

/**
 * Created by luoyl on 2017/1/18.
 * 发送短信验证码bean
 */

public class SendSmsBean {

    public String cellNo;

    public SendSmsBean(){

    }

    public SendSmsBean(String cellNo) {
        this.cellNo = cellNo;
    }
}
