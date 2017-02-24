package com.kscf.app.android.di.component;

import android.app.Activity;

import com.kscf.app.android.di.mode.ActivityModule;
import com.kscf.app.android.di.scope.ActivityScope;
import com.kscf.app.android.ui.activity.MainActivity;
import com.kscf.app.android.ui.activity.WelcomeActivity;

import dagger.Component;

/**
 * Created by luoyl on 2016/12/4.
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    Activity getActivity();

    void inject(WelcomeActivity welcomeActivity);


    void inject(MainActivity mainActivity);


}
