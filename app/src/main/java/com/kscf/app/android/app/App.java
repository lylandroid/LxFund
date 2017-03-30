package com.kscf.app.android.app;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.framework.app.CommApp;
import com.framework.http.RetrofitHelper;
import com.framework.util.L;
import com.kscf.app.android.di.component.AppComponent;
import com.kscf.app.android.di.component.DaggerAppComponent;
import com.kscf.app.android.di.mode.AppModule;
import com.kscf.app.android.other.UmengUtils;
import com.kscf.app.android.ui.activity.LoginActivity;
import com.kscf.app.android.util.LxSPUtils;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by luoyl on 2016/12/22.
 */

public class App extends CommApp {

    private AppComponent mAppComponent;


    @Override
    public void onCreate() {
        super.onCreate();
        L.isDebug = false;
        RetrofitHelper.setBaseUrl(Apis.getBaseUrl());

        UmengUtils.init(this);

        if (L.isDebug) {
            initLeakCanary();
        }

    }


    public static App getInstance() {
        return (App) getApp();
    }

    public AppComponent getAppComponent() {
        if (mAppComponent == null) {
            mAppComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(getInstance()))
                    .build();
        }
        return mAppComponent;
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
