package com.kscf.app.android.web.inter;

import android.webkit.JavascriptInterface;

/**
 * Created by luoyl on 2017/3/24.
 * js调用java接口
 */

public interface JumpToApp {
    @JavascriptInterface
    void jumpToApp(String jumpJson);
}
