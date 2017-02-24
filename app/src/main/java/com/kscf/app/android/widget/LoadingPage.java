package com.kscf.app.android.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kscf.app.android.R;

/**
 * view控制器：成功，失败，loading页面切换控制类
 * Created by luoyl on 2016/12/23
 */

public class LoadingPage implements View.OnClickListener {
    // 加载默认的状态
    public static final int STATE_DEF = 1;
    // 加载的状态
    public static final int STATE_LOADING = 2;
    // 加载失败的状态
    public static final int STATE_ERROR = 3;
    // 页面没有找到
    public static final int STATE_ERROR_404 = 4;
    // 加载空的状态
    public static final int STATE_EMPTY = 5;
    // 加载成功的状态
    public static final int STATE_SUCCEED = 6;

    private Context mContext;

    /**
     * 根布局，控制布局显示的view
     */
    private View mRootView;

    private View mSuccessView;

    private ViewGroup mControlSuccessView;

    private View mErrView;

    private View mErrView404;

    private View mLoadingView;


    /**
     * 是否显示过成功页面
     */
    private boolean mIsSuccess;

    public LoadingPage(Context context, ViewGroup parentView, int contentResId) {
        this(context, LayoutInflater.from(context), parentView, contentResId);
    }

    public LoadingPage(Context context, LayoutInflater inflater, ViewGroup parentView, int contentResId) {
        mContext = context;
        mRootView = inflater.inflate(R.layout.root_layout, parentView, false);
        mControlSuccessView = (ViewGroup) mRootView.findViewById(R.id.control_success);
        mSuccessView = inflater.inflate(contentResId, mControlSuccessView, false);
        //mLoadingView = .findViewById(R.id.control_loading);
        mLoadingView = mRootView.findViewById(R.id.item_loading_layout);

        //mErrView = mRootView.findViewById(R.id.control_err);
        mErrView = mRootView.findViewById(R.id.item_err_layout);

        //mErrView404 = mRootView.findViewById(R.id.control_err_404);
        mErrView404 = mRootView.findViewById(R.id.item_err_404_layout);

        mControlSuccessView.addView(mSuccessView);

        //mErrView.findViewById(R.id.btn_confirm).setOnClickListener(this);
    }

    private View mErrorReLoadingView;

    /**
     * 控制view显示逻辑
     *
     * @param pageState
     */
    public void showPage(int pageState) {
        //如果成功过，不再切换UI的状态
        if (mIsSuccess) {
            return;
        }
        if (mLoadingView.isShown())
            mLoadingView.setVisibility(View.GONE);

        if (mErrView.isShown())
            mErrView.setVisibility(View.GONE);

        if (mErrView404.isShown())
            mErrView404.setVisibility(View.GONE);

        if (mControlSuccessView.isShown())
            mControlSuccessView.setVisibility(View.GONE);

        switch (pageState) {
            case STATE_LOADING:
                mLoadingView.setVisibility(View.VISIBLE);
                break;
            case STATE_SUCCEED:
                mIsSuccess = true;
                mControlSuccessView.setVisibility(View.VISIBLE);
                break;
            case STATE_ERROR:
                mErrView.setVisibility(View.VISIBLE);
                if (mErrorReLoadingView == null) {
                    mErrorReLoadingView = mErrView.findViewById(R.id.btn_reloading);
                    mErrorReLoadingView.setOnClickListener(this);
                }
                break;
            case STATE_ERROR_404:
                mErrView404.setVisibility(View.VISIBLE);
                break;

        }
    }

    @Override
    public void onClick(View v) {

    }

    public View getRootView() {
        return mRootView;
    }

    public View getSuccessView() {
        return mSuccessView;
    }

    /**
     * 获取是否成功过的状态
     *
     * @return
     */
    public boolean isShowSuccessPage() {
        return mIsSuccess;
    }

    public void free() {
        mContext = null;
        mRootView = null;
        mLoadingView = null;
        mControlSuccessView = null;
        mSuccessView = null;
        mErrView = null;
    }
}
