package com.kscf.app.android.base;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.framework.base.SupportFragment;
import com.framework.util.L;
import com.kscf.app.android.app.App;
import com.kscf.app.android.base.view.BaseView;
import com.kscf.app.android.base.view.IView;
import com.kscf.app.android.di.component.DaggerFragmentComponent;
import com.kscf.app.android.di.component.FragmentComponent;
import com.kscf.app.android.di.mode.FragmentModule;
import com.kscf.app.android.util.framing.LxRxBus;
import com.kscf.app.android.widget.LoadingPage;

import javax.inject.Inject;

/**
 * Created by luoyl on 2016/12/11.
 */

public abstract class BaseFragment<DataBinding extends ViewDataBinding, P extends RxPresenter> extends SupportFragment implements IView, BaseView {

    protected BaseActivity mActivity;

    protected DataBinding mDataBinding;
    @Inject
    protected P mPresenter;

    protected boolean isDestroy;

    protected LoadingPage mLoadingPage;

    /**
     * 是否初始化
     */
    protected boolean mIsInit;


    @Override
    public void onAttach(Context context) {
        mActivity = (BaseActivity) context;
        super.onAttach(context);
        if (L.isDebug) {
            L.i(getClass().getSimpleName() + " BaseFragment onAttach ");
        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (L.isDebug) {
            L.i(getClass().getSimpleName() + " BaseFragment onCreateView    mLoadingPage==null: " + (mLoadingPage == null));
        }
        if (mLoadingPage == null) {
            //DataBinding加载布局文件
            //mDataBinding = DataBindingUtil.inflate(inflater, getLayoutResId(), container, false);
            mLoadingPage = new LoadingPage(mActivity, inflater, container, getLayoutResId()) {
                @Override
                public void onClick(View v) {
                    onErrClick(v);
                }
            };
            mDataBinding = DataBindingUtil.bind(mLoadingPage.getSuccessView());
            //dagger2注解P层
            initInject();
        }
        return mLoadingPage.getRootView();
    }

    public FragmentComponent getFragmentComponent() {
        return DaggerFragmentComponent.builder()
                .appComponent(App.getInstance().getAppComponent())
                .fragmentModule(getFragmentModule())
                .build();
    }

    public FragmentModule getFragmentModule() {
        return new FragmentModule(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        if (L.isDebug) {
            L.i(getClass().getSimpleName() + " BaseFragment onViewCreated !mIsInit: " + (!mIsInit));
        }
        if (!mIsInit) {
            if (mPresenter != null) {
                mPresenter.attachView(this);
            }

            if (savedInstanceState == null) {
                if (!isHidden()) {
                    mIsInit = true;
                    initView();
                    initListener();
                    initEventAndData();
                }
            } else {
                if (!isSupportHidden()) {
                    mIsInit = true;
                    initView();
                    initListener();
                    initEventAndData();
                }
            }
            if (isRegisterRxBus()) {
                //EventBus.getDefault().register(this);
                LxRxBus.getInstance().get().register(this);
            }
        }


    }

    /**
     * 解决内存重启bug
     *
     * @param hidden
     */
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (L.isDebug) {
            L.i(getClass().getSimpleName() + " BaseFragment onHiddenChanged ");
        }
        if (!mIsInit && !hidden) {
            mIsInit = true;
            initView();
            initEventAndData();
        }
    }

    @Override
    public void onHttpErr(int reqCode, int respCode, String errMsg, Throwable e) {
        if (L.isDebug) {
            L.e(errMsg, e);
        }
    }


    public void onErrClick(View v) {

    }

    protected boolean isRegisterRxBus() {
        return false;
    }


    @Override
    public void onDestroy() {
        free();
        super.onDestroy();
    }

    public void free() {
        if (isRegisterRxBus()) {
            //EventBus.getDefault().unregister(this);
            LxRxBus.getInstance().get().unregister(this);
        }
        isDestroy = true;
        mActivity = null;
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
        }

        if (mDataBinding != null) {
            mDataBinding.unbind();
            mDataBinding = null;
        }

        if (mLoadingPage != null) {
            mLoadingPage.free();
            mLoadingPage = null;
        }

        if (L.isDebug) {
            L.i(getClass().getSimpleName() + " BaseFragment free ");
        }
    }
}
