package com.kscf.app.android.model.http;

/**
 * 记录http请求数量
 */
public enum NetCount {
    /*http总数*/
    HTTP_TOTAL(5)//
    , HTTP_1(0), HTTP_2(1), HTTP_3(2), HTTP_4(3), HTTP_5(4);
    private int retrofitCount;

    NetCount(int retrofitCount) {
        this.retrofitCount = retrofitCount;
    }

    public int getValue() {
        return retrofitCount;
    }
}