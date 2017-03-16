package com.framework.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.framework.util.L;
import com.kscf.app.android.R;

/**
 * 同一个TextView不颜色,不同大小【主要把一段文字分为2段】
 * Created by luoyl on 2017/2/28.
 */

public class SpannableColorSizeTextView extends TextView {
    /*文字*/
    private CharSequence mText;
    /**/
    private int mRightIndex;
    /*文字颜色*/
    private int mRightTextColor;
    /*文字大小*/
    private int mRightTextSizeResId;
    private int mRightTextSize;

    /*右边文字点击事件*/
    private OnClickListener mRightTextOnClickListener;
    private SpannableString mSpannableString;

    public SpannableColorSizeTextView(Context context) {
        this(context, null);
    }

    public SpannableColorSizeTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SpannableColorSizeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        final TypedArray a = context.obtainStyledAttributes(
                attrs, R.styleable.LxAttrs, defStyleAttr, 0);
        int defColor = getResources().getColor(R.color.txt_subtitle_color);

        mRightIndex = a.getInt(R.styleable.LxAttrs_rightIndex, 0);

        mRightTextColor = a.getColor(R.styleable.LxAttrs_rightTextColor, defColor);
        mRightTextSizeResId = a.getResourceId(R.styleable.LxAttrs_rightTextSize, 0);


        if (mRightTextSizeResId != 0) {
            mRightTextSize = (int) getResources().getDimension(mRightTextSizeResId);
        } else {
            mRightTextSize = (int) getTextSize();
        }

        if (mRightIndex != 0 && !TextUtils.isEmpty(mText)) {
            setText(mText);
        }

    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        mText = text;
        super.setText(mRightIndex != 0 ? getPercentageText(text) : text, type);
        //局部点击事件需要在setText后调用
        if (mRightIndex != 0) {
            setMovementMethod(LinkMovementMethod.getInstance());
        }
    }


    public CharSequence getPercentageText(CharSequence text) {
        try {
            if (text != null && text.length() > 0) {
                mSpannableString = new SpannableString(text);
                /*设置字体大小*/
                if (mRightIndex != 0) {
                    mSpannableString.setSpan(new AbsoluteSizeSpan(mRightTextSize)
                            , mRightIndex
                            , text.length()
                            , Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                }

                //设置字体前景色
                if (mRightTextColor != 0) {
                    mSpannableString.setSpan(new ForegroundColorSpan(mRightTextColor)
                            , mRightIndex
                            , text.length()
                            , Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                    //给右边文字添加点击事件
                    mSpannableString.setSpan(new ClickableSpan() {
                        @Override
                        public void onClick(View v) {
                            if (mRightTextOnClickListener != null) {
                                mRightTextOnClickListener.onClick(v);
                            }
                        }

                        @Override
                        public void updateDrawState(TextPaint ds) {
                            ds.setColor(mRightTextColor);//设置颜色
                            ds.setUnderlineText(false);
                        }
                    }, mRightIndex, mText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    setHighlightColor(getResources().getColor(android.R.color.transparent));
                }
                return mSpannableString;
            }
        } catch (Exception e) {
            L.e(e);
        }
        return text;
    }

    /**
     * 给右边文字添加点击事件
     *
     * @param onClickListener
     */
    public void setRightTextOnClickListener(OnClickListener onClickListener) {
        mRightTextOnClickListener = onClickListener;
    }
}
