package com.kscf.app.android.model.http;

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
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;

/**
 * retrofit2对应的http接口类
 * Created by luoyl on 2016/12/4.
 */
public interface HttpService {
    @GET
    Observable<ResponseBody> get(@Url String url, @HeaderMap Map<String, Object> headers, @QueryMap Map<String, Object> param);

    @HEAD
    Observable<ResponseBody> head(@Url String url, @HeaderMap Map<String, Object> headers, @QueryMap Map<String, Object> param);

    @FormUrlEncoded
    @POST
    Observable<ResponseBody> post(@Url String url, @HeaderMap Map<String, Object> headers, @FieldMap Map<String, Object> param);

    @PUT
    Observable<ResponseBody> put(@Url String url, @HeaderMap Map<String, Object> headers, @QueryMap Map<String, Object> param);

    @DELETE
    Observable<ResponseBody> delete(@Url String url, @HeaderMap Map<String, Object> headers, @QueryMap Map<String, Object> param);

    /*
    @CONNECT
    Observable<ResponseBody> connect(@Url String url, @HeaderMap Map<String, Object> headers, @QueryMap Map<String, Object> param);
    */

    @OPTIONS
    Observable<ResponseBody> options(@Url String url, @HeaderMap Map<String, Object> headers, @QueryMap Map<String, Object> param);


    /*@TRACE
    Observable<ResponseBody> TRACE(@Url String url, @HeaderMap Map<String, Object> headers, @QueryMap Map<String, Object> param);
    */

    @PATCH
    Observable<ResponseBody> patch(@Url String url, @HeaderMap Map<String, Object> headers, @QueryMap Map<String, Object> param);



    @Multipart
    @POST
    Call<String> postUpload(@Url String url, @Part("filedes") String des, @PartMap Map<String, RequestBody> params);

    @Multipart
    @GET
    Call<String> getUpload(@Url String url, @Part("filedes") String des, @PartMap Map<String, RequestBody> params);
}