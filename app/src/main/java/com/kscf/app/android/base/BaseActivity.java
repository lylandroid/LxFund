package com.kscf.app.android.base;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import com.framework.base.SupportActivity;
import com.framework.util.L;
import com.kscf.app.android.app.App;
import com.kscf.app.android.app.LxConstants;
import com.kscf.app.android.base.view.BaseView;
import com.kscf.app.android.base.view.IView;
import com.kscf.app.android.di.component.ActivityComponent;
import com.kscf.app.android.di.component.DaggerActivityComponent;
import com.kscf.app.android.di.mode.ActivityModule;
import com.kscf.app.android.util.framing.LxRxBus;
import com.kscf.app.android.widget.LoadingPage;

import javax.inject.Inject;

/**
 * Created by luoyl on 2016/12/22.
 */

public abstract class BaseActivity<DataBinding extends ViewDataBinding, P extends BasePresenter> extends SupportActivity implements IView, BaseView {


    protected DataBinding mDataBinding;

    protected LoadingPage mLoadingPage;

    @Inject
    protected volatile P mPresenter;

    protected boolean isDestroy;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (L.isDebug) {
            L.i(getClass().getSimpleName() + " BaseActivity onCreate " + getClass().getSimpleName());
        }
        //setContentView(getLayoutResId());
        //mDataBinding = DataBindingUtil.setContentView(this, getLayoutResId());
        mLoadingPage = new LoadingPage(this, (ViewGroup) getWindow().getDecorView(), getLayoutResId()) {
            @Override
            public void onClick(View v) {
                onErrClick(v);
            }
        };
        setContentView(mLoadingPage.getRootView());
        mDataBinding = DataBindingUtil.bind(mLoadingPage.getSuccessView());
        initInject();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        initView();
        initListener();
        initEventAndData();
        if (isRegisterRxBus()) {
            registerRxBus();
            //EventBus.getDefault().register(this);
        }
    }


    public ActivityComponent getActivityComponent() {
        return DaggerActivityComponent.builder()
                .appComponent(App.getInstance().getAppComponent())
                .activityModule(getActivityModule()).build();
    }

    public ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    @Override
    public void onHttpErr(int reqCode, int respCode, String errMsg, Throwable e) {
        if (L.isDebug) {
            L.e("reqCode: " + reqCode + "errMsg: " + errMsg, e);
        }
    }

    public int getIntentFragmentHashCodeValue() {
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        int fragmentHashCodeValue = 0;
        if (extras != null) {
            fragmentHashCodeValue = extras.getInt(LxConstants.fragmentHashCodeKey, 0);
        }
        return fragmentHashCodeValue;
    }

    public void onErrClick(View v) {

    }

    protected boolean isRegisterRxBus() {
        return false;
    }

    protected void registerRxBus() {
        LxRxBus.getInstance().get().register(this);
    }

    protected void unregisterRxBus() {
        LxRxBus.getInstance().get().unregister(this);
    }

    @Override
    public void free() {
        isDestroy = true;
        if (isRegisterRxBus()) {
            //EventBus.getDefault().unregister(this);
            unregisterRxBus();
        }
        if (mDataBinding != null) {
            mDataBinding.unbind();
            mDataBinding = null;
        }
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
        }

        if (mLoadingPage != null) {
            mLoadingPage.free();
            mLoadingPage = null;
        }


        if (L.isDebug) {
            L.i(getClass().getSimpleName() + "  free");
        }

    }

    @Override
    protected void onDestroy() {
        free();
        super.onDestroy();
    }
}
