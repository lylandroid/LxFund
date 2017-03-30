package com.kscf.app.android.model.bean.response;

/**
 * @author luoyl
 * @created 2017/3/28
 * <p>
 * /**
 * SUCCESS("0", "成功"),
 * LOGIN_SUCESS("200000","登录成功"),
 * REGISTER_SUCCESS("200001","注册成功"),
 * <p>
 * LOGIN_FAIL("500000","登录失败"),
 * REGISTER_FAIL("500001","注册失败"),
 * <p>
 * INVALID_CELL_NO("500002","手机号码不正确"),
 * VERIFY_CODE_EXPIRED("500003","验证码已过期，请重新获取！"),
 * INVALID_VERIFY_CODE("500004","您输入的验证码不正确！"),
 * SMS_RETURN_SEND_FAIL("500005","短信发送服务返回发送失败"),
 * <p>
 * LOGIN_STATE_EXPIRED("500006","登录已失效，请重新登录后重试");
 */

public class LoginOrRegisterResponseBean {

    /**
     * userId : 2112365586070528
     * token : 13b07562f88d460c93d05e5b1e2fe3f4
     */
    public String userId;
    public String token;


}
