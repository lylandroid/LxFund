package com.kscf.app.android.web;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;

import com.framework.view.webview.CustomWebView;
import com.kscf.app.android.R;
import com.kscf.app.android.base.BaseActivity;
import com.kscf.app.android.widget.DefToolBar;

/**
 * Created by luoyl on 2017/3/24.
 */

public class LxWebView extends CustomWebView {

    public LxWebView(Context context, ViewGroup containerView) {
        super(context, containerView);

    }

    public LxWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LxWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void handlerBack(DefToolBar defToolBar) {
        //处理toolBar返回事件
        defToolBar.setBackOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!onCustomBack()) {
                    if (getContext() != null) {
                        ((BaseActivity) getContext()).onBackPressed();
                    }
                }
            }
        });
    }
}
