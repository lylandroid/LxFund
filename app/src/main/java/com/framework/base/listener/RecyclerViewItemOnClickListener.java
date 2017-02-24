package com.framework.base.listener;

import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;

/**
 * Created by luoyl on 2017/1/22.
 * RecyclerView Item点击事件封装
 */

public abstract class RecyclerViewItemOnClickListener extends RecyclerView.SimpleOnItemTouchListener {

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        switch (e.getAction()) {
            case MotionEvent.ACTION_UP:
                onItemClick(rv);
                break;
        }
    }

    public abstract void onItemClick(RecyclerView rv);


}
