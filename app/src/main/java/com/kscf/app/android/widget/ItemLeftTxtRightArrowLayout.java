package com.kscf.app.android.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kscf.app.android.R;
import com.framework.util.SizeUtils;

/**
 * Created by luoyl on 2017/1/13.
 */

public class ItemLeftTxtRightArrowLayout extends RelativeLayout {

    private String mLeftText;
    private int mLeftTextSizeResId;
    private int mTopAndBottomPaddingSize;
    private int mLeftTextColorResId;
    private int mRightTextColorResId;
    private String mRightText;
    private TextView mRightTextView;
    private int mRightTextSizeResId;
    private boolean mIsShowArrowView;
    //private boolean mIsSetPadding;
    private int mDistance16dp;
    private int mDistance8dp;
    private int mDistance12dp;

    public ItemLeftTxtRightArrowLayout(Context context) {
        this(context, null);
    }


    public ItemLeftTxtRightArrowLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ItemLeftTxtRightArrowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mDistance16dp = SizeUtils.dp2px(getContext(), 16);
        mDistance8dp = SizeUtils.dp2px(getContext(), 8);
        mDistance12dp = SizeUtils.dp2px(getContext(), 12);

        final TypedArray a = context.obtainStyledAttributes(
                attrs, R.styleable.LxAttrs, defStyleAttr, 0);
        mLeftText = a.getString(R.styleable.LxAttrs_leftText);
        mRightText = a.getString(R.styleable.LxAttrs_rightText);
        mLeftTextSizeResId = a.getResourceId(R.styleable.LxAttrs_leftTextSize, 0);
        mLeftTextColorResId = a.getResourceId(R.styleable.LxAttrs_leftTextColor, 0);
        mRightTextColorResId = a.getResourceId(R.styleable.LxAttrs_rightTextColor, 0);
        mRightTextSizeResId = a.getResourceId(R.styleable.LxAttrs_rightTextSize, 0);
        mTopAndBottomPaddingSize = a.getDimensionPixelSize(R.styleable.LxAttrs_topAndBottomPadding, mDistance16dp);
        mIsShowArrowView = a.getBoolean(R.styleable.LxAttrs_isShowArrowView, true);


        a.recycle();
        setClickable(true);

        initChildView();
    }

    private void initChildView() {
        //leftTextView
        TextView leftTextView = new TextView(getContext());
        LayoutParams leftTextLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        leftTextLayoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
        leftTextLayoutParams.leftMargin = mDistance12dp;
        leftTextLayoutParams.topMargin = mTopAndBottomPaddingSize;
        leftTextLayoutParams.bottomMargin = mTopAndBottomPaddingSize;
        leftTextView.setLayoutParams(leftTextLayoutParams);
        if (mLeftTextColorResId == 0) {
            leftTextView.setTextColor(getResources().getColor(R.color.txt_title_color));
        } else {
            leftTextView.setTextColor(getResources().getColor(mLeftTextColorResId));
        }
        if (mLeftTextSizeResId != 0) {
            leftTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(mLeftTextSizeResId));
        }
        leftTextView.setGravity(Gravity.CENTER);
        if (!TextUtils.isEmpty(mLeftText)) {
            leftTextView.setText(mLeftText);
        }
        addView(leftTextView);


        if (mIsShowArrowView) {
            //箭头View
            Button buttonArrow = new Button(getContext());
            buttonArrow.setId(R.id.iv_right_arrow);
            buttonArrow.setBackgroundResource(R.drawable.icon_right_arrow);
            LayoutParams arrowLayoutParams = new LayoutParams(mDistance8dp, mDistance12dp);
            arrowLayoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
            arrowLayoutParams.setMargins(mDistance8dp, mDistance16dp, mDistance12dp, mDistance16dp);
            arrowLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            buttonArrow.setLayoutParams(arrowLayoutParams);
            addView(buttonArrow);
        }


        //rightTextView
        mRightTextView = new TextView(getContext());
        LayoutParams rightTextLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //int rightTextMargins = SizeUtils.dp2px(getContext(), 3);
        if (mIsShowArrowView) {
            rightTextLayoutParams.addRule(RelativeLayout.LEFT_OF, R.id.iv_right_arrow);
        } else {
            rightTextLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            rightTextLayoutParams.rightMargin = mDistance12dp;
        }
        rightTextLayoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
        mRightTextView.setLayoutParams(rightTextLayoutParams);


        if (mRightTextColorResId == 0) {
            mRightTextView.setTextColor(getResources().getColor(R.color.txt_item_right_color));
        } else {
            mRightTextView.setTextColor(getResources().getColor(mRightTextColorResId));
        }

        if (mRightTextSizeResId == 0) {
            mRightTextView.setTextSize(12);
        } else {
            mRightTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(mRightTextSizeResId));
        }
        mRightTextView.setGravity(Gravity.CENTER);
        if (!TextUtils.isEmpty(mRightText)) {
            setRightText(mRightText);
        }
        addView(mRightTextView);

    }


    public void setRightText(String txt) {
        mRightTextView.setText(txt);
    }
}
