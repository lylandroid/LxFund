package com.kscf.app.android.di.mode;

import android.app.Activity;

import com.kscf.app.android.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by luoyl on 2016/12/4.
 */
@Module
public class ActivityModule {
    Activity mActivity;

    public ActivityModule(Activity activity) {
        mActivity = activity;
    }

    @Provides
    @ActivityScope
    public Activity providesActivity() {
        return mActivity;
    }
}
