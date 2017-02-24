package com.kscf.app.android.model.http;

/**
 * Http请求方式
 * Created by luoyl on 2016/12/4.
 */
public enum HttpMethod {

    //1,一般来说GET方法应该只用于数据的读取
    GET("GET"),

    //2,HEAD方法常被用于客户端查看服务器的性能。
    HEAD("HEAD"),

    //3,这个请求可能会创建新的资源或/和修改现有资源。
    POST("POST"),

    //4,通过该方法客户端可以将指定资源的最新数据传送给服务器取代指定的资源的内容。
    PUT("PUT"),

    //5,请求后指定资源会被删除，
    DELETE("DELETE"),
    //6,CONNECT方法是HTTP/1.1协议预留的，能够将连接改为管道方式的代理服务器。
    // 通常用于SSL加密服务器的链接与非加密的HTTP代理服务器的通信。
    CONNECT("CONNECT"),

    //7,OPTIONS请求与HEAD类似，一般也是用于客户端查看服务器的性能。
    OPTIONS("OPTIONS"),


    //8,TRACE请求服务器回显其收到的请求信息，该方法主要用于HTTP请求的测试或诊断。
    TRACE("TRACE"),

    //9,但PATCH一般用于资源的部分更新，而PUT一般用于资源的整体更新。
    //当资源不存在时，PATCH会创建一个新的资源，而PUT只会对已在资源进行更新。
    PATCH("PATCH");


    private final String value;

    HttpMethod(String value) {
        this.value = value;
    }

}