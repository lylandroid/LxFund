package com.kscf.app.android.base;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
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
import com.kscf.app.android.ui.activity.DetailsActivity;
import com.kscf.app.android.util.framing.LxRxBus;
import com.kscf.app.android.widget.LoadingPage;
import com.umeng.analytics.MobclickAgent;

import java.lang.reflect.Method;

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

    protected void onResume() {
        super.onResume();
        //umeng session统计
        MobclickAgent.onResume(this);
    }

    protected void onPause() {
        super.onPause();
        //umeng session统计
        MobclickAgent.onPause(this);
    }

    @Override
    public void onHttpErr(int reqCode, int respCode, String errMsg, Throwable e) {
        if (L.isDebug) {
            L.e("reqCode: " + reqCode + "errMsg: " + errMsg, e);
        }
    }

    public String getIntentFragmentClazzName() {
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String fragmentClazzName = null;
        if (extras != null) {
            fragmentClazzName = extras.getString(LxConstants.fragmentClazzNameKey, null);
        }
        return fragmentClazzName;
    }

    public BaseFragment newFragment(String fragmentClazzName) {
        if (TextUtils.isEmpty(fragmentClazzName)) {
            return null;
        }
        BaseFragment fragment = null;
        try {
            //完整类名
            Class<?> fragmentClass = Class.forName(fragmentClazzName);
            fragment = (BaseFragment) fragmentClass.newInstance();//获得实例

            /*Class<?> fragmentClass = Class.forName(fragmentClazzName);
            Method newInstanceMethod = fragmentClass.getMethod("newInstance");
            fragment = (BaseFragment) newInstanceMethod.invoke(null);//获得实例*/
        } catch (Throwable e) {
            L.e(e);
        }
        return fragment;

    }

    /**
     * 添加Fragment到Activity中
     *
     * @param context
     * @param toActivityClass
     * @param toFragmentClassName
     * @param isNeedLogin         是否需要登录
     */
    public static void addFragmentToActivity(Context context, Class toActivityClass, String toFragmentClassName, boolean isNeedLogin) {
        if (TextUtils.isEmpty(toFragmentClassName)) {
            return;
        }
        Intent intent = new Intent(context, toActivityClass);
        intent.putExtra(LxConstants.fragmentClazzNameKey, toFragmentClassName);
        App.getInstance().startTargetActivity(context, intent, isNeedLogin);
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
