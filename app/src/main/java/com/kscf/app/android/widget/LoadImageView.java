package com.kscf.app.android.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatImageView;
import android.text.TextUtils;
import android.util.AttributeSet;

import com.kscf.app.android.R;
import com.kscf.app.android.util.framing.LoadImageUtils;

/**
 * Created by Administrator on 2017/3/8.
 */

public class LoadImageView extends AppCompatImageView {

    public LoadImageView(Context context) {
        this(context, null);
    }

    public LoadImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.LxAttrs, defStyleAttr, 0);
        String url = a.getString(R.styleable.LxAttrs_loadUrl);
        int defImageResId = a.getResourceId(R.styleable.LxAttrs_defImageResId, 0);
        a.recycle();
        if (!TextUtils.isEmpty(url)) {
            loadImage(url, defImageResId);
        }

    }


    public void loadImage(String url, int defImageResId) {
        LoadImageUtils.load(getContext(), this, url, defImageResId);
    }

    public void loadImage(Fragment fragment, String url, int defImageResId) {
        LoadImageUtils.load(fragment, this, url, defImageResId);
    }


}
