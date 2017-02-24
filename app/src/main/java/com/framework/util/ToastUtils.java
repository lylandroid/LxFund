package com.framework.util;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.kscf.app.android.R;
import com.kscf.app.android.app.App;

import java.lang.ref.SoftReference;

/**
 * Created by codeest on 2016/8/4.
 */
public class ToastUtils {

    static SoftReference<ToastUtils> sToastUtils;
    private TextView mTvMsg;

    public static void show(int resId) {
        show(App.getInstance().getString(resId));
    }

    public static void show(String msg) {
        if (sToastUtils == null || sToastUtils.get() == null) {
            sToastUtils = new SoftReference<ToastUtils>(new ToastUtils(App.getInstance()));
        }
        sToastUtils.get().setText(msg);
        sToastUtils.get().getToast(msg).show();
    }



    Context context;
    Toast toast;
    String msg;

    public ToastUtils(Context context) {
        this.context = context;
    }

    public Toast getToast(String msg) {
        if (toast == null) {
            //View contentView = View.inflate(context, R.layout.toast_dialog, null);
            //mTvMsg = (TextView) contentView.findViewById(R.id.tv_msg);
            //toast = new Toast(context);
            //toast.setView(contentView);
            //toast.setGravity(Gravity.CENTER, 0, 0);
            //toast.setDuration(Toast.LENGTH_SHORT);

            toast = Toast.makeText(App.getInstance(), msg, Toast.LENGTH_SHORT);
        }
        //mTvMsg.setText(msg);
        toast.setText(msg);
        return toast;
    }


    public Toast createShort() {
        View contentView = View.inflate(context, R.layout.toast_dialog, null);
        TextView tvMsg = (TextView) contentView.findViewById(R.id.tv_msg);
        toast = new Toast(context);
        toast.setView(contentView);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        tvMsg.setText(msg);
        return toast;
    }

    public void show() {
        if (toast != null) {
            toast.show();
        }
    }

    public void setText(String text) {
        msg = text;
    }
}
