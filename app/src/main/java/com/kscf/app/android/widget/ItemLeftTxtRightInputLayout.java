package com.kscf.app.android.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.text.InputFilter;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.kscf.app.android.R;

/**
 * Created by luoyl on 2017/1/16.
 */

public class ItemLeftTxtRightInputLayout extends FrameLayout {

    private View mRootView;
    private TextView mLeftTextView;

    private EditText mInputEditText;
    private TextInputLayout mTextInputLayout;


    private String mLeftText;
    private String mHintText;
    private int maxLength;
    private int mInputType = -1;

    public ItemLeftTxtRightInputLayout(Context context) {
        this(context, null);
    }

    public ItemLeftTxtRightInputLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ItemLeftTxtRightInputLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(
                attrs, R.styleable.LxAttrs, defStyleAttr, 0);
        mLeftText = a.getString(R.styleable.LxAttrs_leftText);
        mHintText = a.getString(R.styleable.LxAttrs_hintText);
        maxLength = a.getInt(R.styleable.LxAttrs_maxLength, 0);
        //mInputType = a.getInt(R.styleable.LxAttrs_inputType, -1);
        a.recycle();
        a = null;

        TypedArray androidTypedArray = context.obtainStyledAttributes(attrs,
                R.styleable.EditText, defStyleAttr, 0);
        mInputType = androidTypedArray.getInt(R.styleable.EditText_inputType, -1);
        androidTypedArray.recycle();
        androidTypedArray = null;

        init();
    }

    private void init() {
        if (mRootView == null) {
            LayoutInflater factory = LayoutInflater.from(getContext());
            mRootView = factory.inflate(R.layout.item_left_txt_and_right_input, this, false);
            mLeftTextView = (TextView) mRootView.findViewById(R.id.tv_item_left_txt_and_right_input);
            mInputEditText = (EditText) mRootView.findViewById(R.id.et_item_left_txt_and_right_input);
            mTextInputLayout = (TextInputLayout) mRootView.findViewById(R.id.text_input_layout);
            if (mInputType != -1) {
                mInputEditText.setInputType(mInputType);
            }
            if (!TextUtils.isEmpty(mLeftText)) {
                setLeftTxt(mLeftText);
            }
            if (!TextUtils.isEmpty(mHintText)) {
                setInputHintText(mHintText);
            }
            if (maxLength != 0) {
                //mInputEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength)});
                setEditTextMaxLength(maxLength);
            }
            addView(mRootView);
        }


    }


    public void setLeftTxt(String txt) {
        mLeftTextView.setText(txt);
    }

    public void setLeftTxt(int txtResId) {
        mLeftTextView.setText(txtResId);
    }

    public void setInputHintText(String hintText) {
        mInputEditText.setHint(hintText);
    }

    public void setInputHintText(int hintTextResId) {
        mInputEditText.setHint(hintTextResId);
    }

    public void setTextInputType(int inputTextType) {
        mInputEditText.setInputType(inputTextType);
    }

    public String getInputEditText() {
        return mInputEditText.getText().toString();
    }

    public void setEditTextMaxLength(int maxLength) {
        mInputEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength)});
    }

    public EditText getEditText() {
        return mInputEditText;
    }

    public TextInputLayout getTextInputLayout() {
        return mTextInputLayout;
    }

    public void setTextInputLayoutError(int errTextResId) {
        getTextInputLayout().setError(getResources().getString(errTextResId));
    }
}
