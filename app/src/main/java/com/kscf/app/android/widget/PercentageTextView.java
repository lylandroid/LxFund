package com.kscf.app.android.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.TextView;

import com.kscf.app.android.R;
import com.framework.util.L;
import com.framework.util.SizeUtils;

/**
 * Created by luoyl on 2017/1/6.
 * 百分比TextView
 */

public class PercentageTextView extends TextView {

    int mEndTextSize = 0;
    int mTextSizeResId = 0;
    int mEndTextSizeResId;
    CharSequence mText;
    BufferType mType;

    public PercentageTextView(Context context) {
        this(context, null);
    }

    public PercentageTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PercentageTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        final TypedArray a = context.obtainStyledAttributes(
                attrs, R.styleable.LxAttrs, defStyleAttr, 0);
        mTextSizeResId = a.getResourceId(R.styleable.LxAttrs_textSize, 0);
        mEndTextSizeResId = a.getResourceId(R.styleable.LxAttrs_endTextSize, 0);
        a.recycle();
        init();
    }

    private void init() {
        setTextColor(getResources().getColor(R.color.colorButtonSubmit));
        setGravity(Gravity.CENTER);
        if (mEndTextSizeResId == 0) {
            mEndTextSize = SizeUtils.sp2px(getContext(), 18);
        } else {
            mEndTextSize = (int) getResources().getDimension(mEndTextSizeResId);
        }

        if (mTextSizeResId == 0) {
            setTextSize(28);
        } else {
            setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(mTextSizeResId));
        }


        //如果有初始化
        if (mText != null && mText.length() > 0) {
            setText(mText, mType);
        }

    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        this.mText = text;
        this.mType = type;
        if (mEndTextSize != 0 || mTextSizeResId != 0) {
            super.setText(getPercentageText(text), type);
        }
    }


    public CharSequence getPercentageText(CharSequence text) {
        try {
            if (text != null && text.length() > 0 && text.toString().substring(text.length() - 1).equals("%")) {
                SpannableString spannableString = new SpannableString(text);
                spannableString.setSpan(new AbsoluteSizeSpan(mEndTextSize)
                        , text.length() - 1
                        , text.length()
                        , Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                return spannableString;
            }
        } catch (Exception e) {
            L.e(e);
        }
        return text;
    }
}
