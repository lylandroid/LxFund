package com.kscf.app.android.app;

/**
 * Created by luoyl on 2017/1/18.
 * 常量类
 */

public class LxConstants {
    /*fragment传值用的key*/
    public static final String fragmentClazzNameKey = "FragmentHashCodeKey";
    /*手机号码*/
    public static final String mobile = "mobile";
    /*验证码*/
    public static final String authcode = "authcode";

    public static final String token = "token";

    public interface DATASECRET {

    }
    public interface HTTP {
        //Android端APPid， 凯石财富C端安卓
        String APP_ID = "vwma101";

        String VSTONE_DES = "blue!@#$%"; // appbody数据加密
        // app签名
        String VSTONE_MD5 = "Gwn1zaQtCPUnd688jIruSS6gZvfShvNB"; // app签名
        //接口版本号
        String VER = "1";
    }
}
