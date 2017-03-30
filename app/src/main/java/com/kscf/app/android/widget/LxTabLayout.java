package com.kscf.app.android.widget;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.util.AttributeSet;

import com.kscf.app.android.R;

/**
 * Created by Administrator on 2017/3/22.
 */

public class LxTabLayout extends TabLayout {
    public LxTabLayout(Context context) {
        super(context, null);
    }

    public LxTabLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LxTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        setSelectedTabIndicatorColor(getResources().getColor(R.color.colorButtonSubmitSelect));
        setTabTextColors(getResources().getColor(R.color.txt_subtitle_color),
                getResources().getColor(R.color.colorButtonSubmitSelect));
        setBackgroundColor(getResources().getColor(R.color.white));
    }
}
