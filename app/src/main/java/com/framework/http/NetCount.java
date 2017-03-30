package com.framework.http;

/**
 * 记录http请求数量
 */
public enum NetCount {
    /*http总数*/
    URL_TOTAL(5)//
    , URL_1(0), URL_2(1), URL_3(2), URL_4(3), URL_5(4);
    private int retrofitCount;

    NetCount(int retrofitCount) {
        this.retrofitCount = retrofitCount;
    }

    public int getValue() {
        return retrofitCount;
    }
}