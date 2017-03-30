package com.framework.util;

import android.support.annotation.NonNull;
import android.util.TypedValue;
import android.widget.TextView;

import com.framework.app.CommApp;

/**
 * @author luoyl
 * @created 2017/3/28
 */

public class ResUtils {
    public static String getString(@NonNull int resId) {
        return CommApp.getApp().getResources().getString(resId);
    }

    public static int getColor(@NonNull int resId) {
        return CommApp.getApp().getResources().getColor(resId);
    }


    public static void setTextSizeDip(@NonNull int resId, TextView tv) {
        tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, CommApp.getApp().getResources().getDimension(resId));
    }
}
