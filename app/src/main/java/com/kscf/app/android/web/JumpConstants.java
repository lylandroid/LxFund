package com.kscf.app.android.web;

/**
 * Created by luoyl on 2017/3/24.
 */

public class JumpConstants {
    public interface ActionType {
        int SHOW_ONLY = 11;//仅展示，我动作
        int WEBVIEW_H5 = 12;//加载h5,打开一个h5页面
        int APP_H5 = 13;//应用内h5,打开应用内置h5页面
        int APP_PAGE = 14;//app页面，打开一个app页面
        int START_APP = 15;//启动app
    }

    public interface ActionSystem {
        String APP_WEALTH = "app.wealth";//凯石财富app
        String APP_H5_WEALTH = "apph5.wealth";//凯石财富内嵌h5
        String WEB_H5_WEALTH = "webh5.wealth";//凯石财富提供独立的h5页面
        String WEB_H5_PARTNER = "webh5.partner";//合作伙伴提供的独立h5页面
    }
}
