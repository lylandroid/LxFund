package com.kscf.app.android.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.TintTypedArray;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.kscf.app.android.R;
import com.kscf.app.android.base.BaseActivity;
import com.framework.base.SupportActivity;

/**
 * Created by luoyl on 2017/1/13.
 */

public class DefToolBar extends Toolbar implements View.OnClickListener {


    private View mRootView;
    private String mTitle;
    private int mBackgroundResId;
    private boolean mIsShowReturn;
    private boolean mIsShowTooBarRight;
    private TextView mTvTitle;
    private View mIvRightView;

    private OnClickListener mBackOnClickListener;

    public DefToolBar(Context context) {
        this(context, null);
    }

    public DefToolBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DefToolBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        final TintTypedArray a = TintTypedArray.obtainStyledAttributes(getContext(), attrs,
                R.styleable.LxAttrs, defStyleAttr, 0);
        mTitle = a.getString(R.styleable.LxAttrs_title);
        mBackgroundResId = a.getResourceId(R.styleable.LxAttrs_background, -1);
        mIsShowReturn = a.getBoolean(R.styleable.LxAttrs_isShowReturnView, false);
        mIsShowTooBarRight = a.getBoolean(R.styleable.LxAttrs_isShowTooBarLeftView, false);
        a.recycle();
        init();

    }

    private void init() {
        if (mRootView == null) {
            LayoutInflater factory = LayoutInflater.from(getContext());
            mRootView = factory.inflate(R.layout.toolbar_def, this, false);

            if (mBackgroundResId != -1) {
                mRootView.setBackgroundResource(mBackgroundResId);
            }
            mTvTitle = ((TextView) mRootView.findViewById(R.id.tv_title));

            if (!TextUtils.isEmpty(mTitle)) {
                mTvTitle.setText(mTitle);
            }

            View ivBackView = mRootView.findViewById(R.id.iv_back);
            ivBackView.setVisibility(mIsShowReturn ? View.VISIBLE : View.INVISIBLE);
            ivBackView.setOnClickListener(this);

            mIvRightView = mRootView.findViewById(R.id.iv_toolbar_right);
            mIvRightView.setVisibility(mIsShowTooBarRight ? View.VISIBLE : View.INVISIBLE);

            addView(mRootView);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                if (mBackOnClickListener == null) {
                    Context context = getContext();
                    if (context != null && context instanceof BaseActivity) {
                        ((SupportActivity) getContext()).onBackPressed();
                    }
                } else {
                    mBackOnClickListener.onClick(v);
                }
                break;
        }
    }


    public void setTitle(String title) {
        mTvTitle.setText(title);
    }

    public void setBackOnClickListener(OnClickListener backOnClickListener) {
        mBackOnClickListener = backOnClickListener;
    }
}
