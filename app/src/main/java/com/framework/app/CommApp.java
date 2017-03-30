package com.framework.app;

import android.app.Application;
import android.os.Handler;

/**
 * Created by luoyl on 2017/3/24.
 */

public class CommApp extends Application {

    private static CommApp sCommApp;

    /**
     * 全局Handler
     */
    private Handler mHandler;

    @Override
    public void onCreate() {
        super.onCreate();
        sCommApp = this;
        mHandler = new Handler();
    }


    public static CommApp getApp() {
        return sCommApp;
    }

    public Handler getHandler() {
        return mHandler;
    }

    /**
     * post发送Handler消息，主要用于跟新UI
     *
     * @param run
     */
    public void post(Runnable run) {
        postDelayed(run, 0);
    }


    /**
     * 使用Hnadler发送延迟消息
     *
     * @param run
     * @param delayMillis
     */
    public void postDelayed(Runnable run, long delayMillis) {
        mHandler.postDelayed(run, delayMillis);
    }

    /**
     * 移除Handler消息，避免内存泄漏
     *
     * @param run
     */
    public void removeCallbacks(Runnable run) {
        if (run != null) {
            mHandler.removeCallbacks(run);
        }
    }

    /**
     * 在应用退出时，移除所有Handler消息
     */
    public void removeAllCallbacksAndMessages() {
        mHandler.removeCallbacksAndMessages(null);
    }
}
