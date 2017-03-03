package com.framework.view;

import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by luoyl on 2017/2/28.
 */

public class DeleteLineTextView extends TextView {
    public DeleteLineTextView(Context context) {
        this(context, null);
    }

    public DeleteLineTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DeleteLineTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(text, type);
    }
}
