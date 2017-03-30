package com.framework.http;

import com.framework.util.L;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;

/**
 * http回调抽象类
 * @param <T>
 */
public abstract class HttpResponseListener<T> {

    public Type getType() {
        Type mySuperClass = getClazz().getGenericSuperclass();
        Type type = ((ParameterizedType) mySuperClass).getActualTypeArguments()[0];
        return type;
    }

    public Class getClazz() {
        return getClass();
    }

    public abstract void onResponse(T t);

    public void onFailure(ResponseBody responseBody, Throwable e) {
        L.e(e);
    }
}