package com.kscf.app.android.util;

import android.content.Context;

import com.framework.util.SPUtils;
import com.kscf.app.android.app.App;

/**
 * Created by luoyl on 2017/2/8.
 */

public class LxSPUtils {

    //token
    public static final String LX_TOKEN_KEY = "lx_token_key";

    public static void putToken(String tokenValue) {
        SPUtils.putAndApply(App.getInstance(), LX_TOKEN_KEY, tokenValue);
    }

    public static String getToken() {
        return (String) SPUtils.get(App.getInstance(), LX_TOKEN_KEY, "");
    }

    public static void clearAll(Context context){
        SPUtils.clear(context);
    }
}
