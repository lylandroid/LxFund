package com.kscf.app.android.base.view;

/**
 * Created by luoyl on 2016/12/22
 * View基类
 */
public interface BaseView {

    void onHttpErr(int reqCode, int respCode, String errMsg, Throwable e);

}