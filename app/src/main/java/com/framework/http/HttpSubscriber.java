package com.framework.http;

import com.google.gson.Gson;
import com.framework.util.L;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import rx.Subscriber;

/**
 * 网络回调监听
 * Created by lyl on 2016/12/4.
 */

public abstract class HttpSubscriber<T> extends Subscriber<ResponseBody> {

    /**
     * 把json数据解析成对应的Bean(不建议重写该方法)
     *
     * @param responseBody
     */
    @Override
    public void onNext(ResponseBody responseBody) {
        try {
            //获取网络数据
            String httpData = responseBody.string();
            if (L.isDebug) {
                L.i("http response data:" + httpData);
            }
            //判断解析的数据类型是否为String
            if (String.class.equals(this.getType())) {
                onSuccess((T) httpData);
            } else {
                Gson gson = new Gson();
                T t = gson.fromJson(httpData, this.getType());
                onSuccess(t);
            }
        } catch (Exception e) {
            if (L.isDebug) {
                L.e("parse Http data Exception: ", e);
            }
            onError(e);
        }
    }

    public abstract void onSuccess(T t);

    @Override
    public void onCompleted() {
    }

    @Override
    public void onError(Throwable e) {
        if (e != null){
            L.e(e);
        }
    }


    public Type getType() {
        Type mySuperClass = getClazz().getGenericSuperclass();
        Type type = ((ParameterizedType) mySuperClass).getActualTypeArguments()[0];
        return type;
    }

    public Class getClazz() {
        return getClass();
    }
}
