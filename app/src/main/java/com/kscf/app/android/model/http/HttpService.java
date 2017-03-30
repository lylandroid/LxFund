package com.kscf.app.android.model.http;

import com.kscf.app.android.app.Apis;
import com.kscf.app.android.app.LxConstants;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HEAD;
import retrofit2.http.HeaderMap;
import retrofit2.http.Multipart;
import retrofit2.http.OPTIONS;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;

/**
 * retrofit2对应的http接口类
 * Created by luoyl on 2016/12/4.
 */
public interface HttpService {
    /**
     * http网络请求接口
     *
     * @param param
     * @return
     */
    @POST(Apis.BASE_API/* + "?code=auth-sendLoginSmsVerifyCode&ver=1"*/)
    Observable<ResponseBody> sendPostHttp(@Query("code") String code, @Query("ver") String version, @HeaderMap Map<String, String> headers, @QueryMap Map<String, String> param);

    interface Code {
        //发送验证码code
        String sendSmsVerifyCode = "auth-sendLoginSmsVerifyCode";
        //登录 & 注册code
        String loginAndRegisterCode = "auth-loginByVerifyCode";
        //获取开户状态
        String getOpenAccountStateCode = "account-getOpanAccountState";
    }
}