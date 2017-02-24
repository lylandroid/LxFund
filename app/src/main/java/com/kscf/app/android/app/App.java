package com.kscf.app.android.app;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.text.TextUtils;

import com.framework.util.L;
import com.kscf.app.android.di.component.AppComponent;
import com.kscf.app.android.di.component.DaggerAppComponent;
import com.kscf.app.android.di.mode.AppModule;
import com.kscf.app.android.model.http.RetrofitHelper;
import com.kscf.app.android.other.GrowingioUtils;
import com.kscf.app.android.ui.activity.LoginActivity;
import com.kscf.app.android.util.LxSPUtils;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by luoyl on 2016/12/22.
 */

public class App extends Application {

    private static App sInstance;

    private AppComponent mAppComponent;

    /**
     * 全局handler用户更新UI
     */
    private Handler mHandler;


    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        mHandler = new Handler();
        RetrofitHelper.setBaseUrl(Apis.getBaseUrl());

        GrowingioUtils.init(this);

        if (L.isDebug) {
            initLeakCanary();
        }

    }


    public static App getInstance() {
        return sInstance;
    }

    public AppComponent getAppComponent() {
        if (mAppComponent == null) {
            mAppComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(getInstance()))
                    .build();
        }
        return mAppComponent;
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

    public void exit() {
        mTargetIntent = null;
        removeAllCallbacksAndMessages();
    }


    private Intent mTargetIntent;
    private final String isNeedLoginKey = "isNeedLoginKey";

    /**
     * 跳转到指定页面,在跳转前检查是否登录
     *
     * @param context
     * @param intent
     * @param isNeedLogin 跳转到目标页面是否需要先登录
     */
    public void startTargetActivity(Context context, Intent intent, boolean isNeedLogin) {
        //mTargetIntent = null;
        if (isNeedLogin && !isLogin()) {
            //需要登录但是没有登录，保存目标Intent,跳转到登录页面
            mTargetIntent = intent;
            mTargetIntent.putExtra(isNeedLoginKey, isNeedLogin);
            context.startActivity(new Intent(context, LoginActivity.class));
        } else if (intent != null) {//intent在退出登录页面，需要跳转到目标页面时可能等于null
            //如果已经登录或不需要登录，直接跳转到目标页面
            context.startActivity(intent);
        }
    }

    /**
     * 是否登录
     *
     * @return true登录
     */
    public boolean isLogin() {
        return !TextUtils.isEmpty(LxSPUtils.getToken());
    }

    public Intent getTargetIntent() {
        Intent intent = mTargetIntent;
        mTargetIntent = null;
        return intent;
    }

    //------------LeakCanary start---------------------------------------------

    private RefWatcher refWatcher;

    /**
     * 初始化 LeakCanary
     */
    public void initLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        refWatcher = LeakCanary.install(this);

    }

    public static RefWatcher getRefWatcher(Context context) {
        App application = (App) context.getApplicationContext();
        return application.refWatcher;
    }
    //------------LeakCanary start---------------------------------------------

}
