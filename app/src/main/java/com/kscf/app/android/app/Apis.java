package com.kscf.app.android.app;

/**
 * Created by luoyl on 2016/12/23.
 */

public class Apis {

    //    private static final String baseUrl = "http://112.124.22.238:8081/course_api/";
    //公司测试服务器
    private static final String baseUrl = "http://192.168.52.108:8206/";


    private static final String IP = "192.168.52.225";
    private static final String BASE_URL = "http://" + IP + ":10000/";
    public static final String BASE_API = "app/call";
    //http://192.168.52.168:10000/app/call?code=auth-sendLoginSmsVerifyCode&ver=1.0


    public static final String testApi = "api-out-fund/out/combo/recom";

    /*登录发送验证码接口*/
    public static final String loginSendSmsCodeApi = "api-out-trade/auth/code/sms/send";

    /*登录或者注册接口*/
    public static final String loginOrRegisterApi = "api-out-trade/ec/session/sms";


    /*开户步骤接口*/
    public static final String openAccountStep = "api-out-trade/ec/session/risk/status";


    //主页banner接口
    public static final String api_fund_selected_banner = "banner/query";


    public final static String getBaseUrl() {
        return BASE_URL /*baseUrl*/;
    }
}
