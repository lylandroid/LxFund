package com.kscf.app.android.model.http;

import com.framework.http.HttpSubscriber;
import com.framework.http.NetCount;
import com.framework.http.RetrofitHelper;
import com.kscf.app.android.app.LxConstants;
import com.kscf.app.android.model.bean.GetOpenAccountStepBean;
import com.kscf.app.android.model.bean.LoginOrRegisterBean;
import com.kscf.app.android.model.bean.base.BaseRequestBean;
import com.kscf.app.android.model.bean.SendSmsBean;

import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import rx.Observable;
import rx.Subscription;

/**
 * Created by Administrator on 2017/3/23.
 */

public class ApiHelper {
    /**
     * 发送短信验证码
     *
     * @param sendSmsBean
     * @param httpSubscriber
     * @param <T>
     * @return
     */
    public static <T> Subscription sendSmsVerificationCode(SendSmsBean sendSmsBean, final HttpSubscriber<T> httpSubscriber) {
       /* Map<String, String> headerMap = new HashMap<>();*/
        Map<String, String> paramMap = BaseRequestBean.getInstance().toBuildMap(sendSmsBean);
        HttpService httpService = RetrofitHelper.getInstance().getRetrofit(NetCount.URL_1).create(HttpService.class);
        Observable<ResponseBody> responseBodyObservable = httpService.sendPostHttp(HttpService.Code.sendSmsVerifyCode, LxConstants.HTTP.VER
                , new HashMap<String, String>(0), paramMap);
        return RetrofitHelper.sendNet(responseBodyObservable, httpSubscriber);
    }

    /**
     * 登录或注册
     *
     * @param loginOrRegisterBean
     * @param httpSubscriber
     * @param <T>
     * @return
     */
    public static <T> Subscription loginOrRegister(LoginOrRegisterBean loginOrRegisterBean, final HttpSubscriber<T> httpSubscriber) {
       /* Map<String, String> headerMap = new HashMap<>();*/
        Map<String, String> paramMap = BaseRequestBean.getInstance().toBuildMap(loginOrRegisterBean);
        HttpService httpService = RetrofitHelper.getInstance().getRetrofit(NetCount.URL_1).create(HttpService.class);
        Observable<ResponseBody> responseBodyObservable = httpService.sendPostHttp(HttpService.Code.loginAndRegisterCode, LxConstants.HTTP.VER
                , new HashMap<String, String>(0), paramMap);
        return RetrofitHelper.sendNet(responseBodyObservable, httpSubscriber);
    }

    /**
     * 获取开户步骤
     *
     * @param loginOrRegisterBean
     * @param httpSubscriber
     * @param <T>
     * @return
     */
    public static <T> Subscription getOpenAccountStep(GetOpenAccountStepBean loginOrRegisterBean, final HttpSubscriber<T> httpSubscriber) {
       /* Map<String, String> headerMap = new HashMap<>();*/
        Map<String, String> paramMap = BaseRequestBean.getInstance().toBuildMap(loginOrRegisterBean);
        HttpService httpService = RetrofitHelper.getInstance().getRetrofit(NetCount.URL_1).create(HttpService.class);
        Observable<ResponseBody> responseBodyObservable = httpService.sendPostHttp(HttpService.Code.getOpenAccountStateCode, LxConstants.HTTP.VER
                , new HashMap<String, String>(0), paramMap);
        return RetrofitHelper.sendNet(responseBodyObservable, httpSubscriber);
    }
}
