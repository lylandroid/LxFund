package com.kscf.app.android.di.mode;

import com.framework.http.RetrofitHelper;
import com.kscf.app.android.app.App;
import com.kscf.app.android.di.scope.ContextLife;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by luoyl on 2016/12/4.
 */

@Module
public class AppModule {

    private final App mApp;

    public AppModule(App app) {
        mApp = app;
    }

    @Provides
    @Singleton
    public RetrofitHelper providesRetrofitHelper() {
        return RetrofitHelper.getInstance();
    }

    @Provides
    @Singleton
    @ContextLife
    public App providesApplicationContext() {
        return mApp;
    }
}
