package com.framework.util;

import android.content.Context;
import android.util.DisplayMetrics;

import com.framework.app.CommApp;

import java.lang.reflect.Field;

/* @author: xiaolijuan
 * @description: 屏幕分辨率工具类
 * @date: 2016-06-05
 * @time: 22:55
 */
public class ScreenUtil {
    /**
     * 根据手机分辨率将dp转为px单位
     */
    public static int dip2px(Context mContext, float dpValue) {
        final float scale = mContext.getResources()
                .getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context mContext, float pxValue) {
        final float scale = mContext.getResources()
                .getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 屏幕宽高
     *
     * @param mContext 上下文
     * @return
     */
    public static int[] getScreenSize(Context mContext) {
        DisplayMetrics dm = mContext
                .getResources().getDisplayMetrics();
        int screenWidth = dm.widthPixels;
        int screenHeight = dm.heightPixels;

        return new int[]{screenWidth, screenHeight};
    }

    /**
     * 获取状态栏高度
     *
     * @param mContext 上下文
     * @return
     */
    public static int getStatusBarHeight(Context mContext) {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, statusBarHeight = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = mContext.getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return statusBarHeight;
    }

    /**
     * 获取手机屏幕的宽度
     * @return
     */
    public static int getScreenWidth() {
        int screen[] = getScreenSize(CommApp.getApp());
        return screen[0];
    }

    /**
     * 获取手机屏幕的高度
     * @return
     */
    public static int getScreenHeight() {
        int screen[] = getScreenSize(CommApp.getApp());
        return screen[1];
    }
}