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
    public static final String LX_USER_ID_KEY = "lx_userId_key";
    //开户步骤
    public static final String LX_OPEN_ACCOUNT_STEP_KEY = "lx_open_account_step_key";

    public static void putToken(String tokenValue) {
        SPUtils.putAndApply(LX_TOKEN_KEY, tokenValue);
    }

    public static String getToken() {
        return (String) SPUtils.get(LX_TOKEN_KEY, "");
    }

    public static void putUserId(String userId) {
        SPUtils.putAndApply(LX_USER_ID_KEY, userId);
    }

    public static String getUserId() {
        return (String) SPUtils.get(LX_USER_ID_KEY, "");
    }

    public static void putOpenAccountStep(int openAccountStep) {
        SPUtils.putAndApply(LX_OPEN_ACCOUNT_STEP_KEY, openAccountStep);
    }

    public static int getOpenAccountStep() {
        return (int) SPUtils.get(LX_OPEN_ACCOUNT_STEP_KEY, -1);
    }

    public static void clearAll(Context context) {
        SPUtils.clear(context);
    }
}
