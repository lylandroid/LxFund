package com.kscf.app.android.web.inter.imp;

import android.webkit.JavascriptInterface;

import com.framework.util.L;
import com.framework.util.ToastUtils;
import com.google.gson.Gson;
import com.kscf.app.android.web.bean.Jump;
import com.kscf.app.android.web.inter.JumpToApp;

/**
 * Created by luoyl on 2017/3/24.
 */

public class JumpToAppImp implements JumpToApp {

    @Override
    @JavascriptInterface
    public void jumpToApp(String jumpJson) {
        if (L.isDebug) {
            ToastUtils.show("jumpJson:" + jumpJson);
        }
        Jump jump = new Gson().fromJson(jumpJson, Jump.class);
        jumpToApp(jump);
    }

    public void jumpToApp(Jump jump) {

    }
}
