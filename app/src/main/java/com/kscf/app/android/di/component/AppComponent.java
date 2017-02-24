package com.kscf.app.android.di.component;

import com.kscf.app.android.app.App;
import com.kscf.app.android.di.mode.AppModule;
import com.kscf.app.android.di.scope.ContextLife;
import com.kscf.app.android.model.http.RetrofitHelper;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by luoyl on 2016/12/4.
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    @ContextLife
    App getContext();

    RetrofitHelper retrofitHelper();
}
