package com.kscf.app.android.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.kscf.app.android.R;

/**
 * Created by luoyl on 2017/1/17.
 */

public class LxRecyclerView extends RecyclerView {
    private int mSpanCount;

    public LxRecyclerView(Context context) {
        this(context, null);
    }

    public LxRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LxRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.LxRecyclerView, defStyle, 0);
        mSpanCount = a.getInt(R.styleable.LxRecyclerView_spanCount, 0);
        setLayoutManager();
        a.recycle();
    }

    public void setLayoutManager() {
        LayoutManager layoutManager = null;
        if (mSpanCount >= 2 && mSpanCount <= 10) {
            layoutManager = new GridLayoutManager(getContext(), mSpanCount
                    , GridLayoutManager.VERTICAL, false);
        } else {
            layoutManager = new LinearLayoutManager(getContext()
                    , LinearLayoutManager.VERTICAL, false);
        }
        setLayoutManager(layoutManager);
    }

    @Override
    protected void onAttachedToWindow() {
        if (!isInEditMode()) {
            super.onAttachedToWindow();
        }
    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        int heightMode = MeasureSpec.getMode(heightSpec);
        switch (heightMode) {
            case MeasureSpec.AT_MOST:
            case MeasureSpec.UNSPECIFIED:
                super.onMeasure(widthSpec, MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST));
                break;
            default:
                super.onMeasure(widthSpec, heightSpec);
                break;

        }
    }
}
