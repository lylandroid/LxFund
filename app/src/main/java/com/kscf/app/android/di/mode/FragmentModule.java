package com.kscf.app.android.di.mode;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.kscf.app.android.di.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by luoyl on 2016/12/11.
 */

@Module
public class FragmentModule {

    Fragment mFragment;


    public FragmentModule(Fragment fragment) {
        mFragment = fragment;
    }

    @Provides
    @FragmentScope
    public Activity providesFragment() {
        return mFragment.getActivity();
    }
}
