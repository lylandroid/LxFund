package com.kscf.app.android.ui.dialog;

import android.databinding.DataBindingUtil;
import android.support.v4.app.FragmentManager;
import android.view.View;

import com.framework.jungly.gridpasswordview.GridPasswordView;
import com.kscf.app.android.R;
import com.kscf.app.android.databinding.DialogPayPasswordBinding;
import com.framework.util.L;

import me.shaohui.bottomdialog.BaseBottomDialog;

/**
 * Created by luoyl on 2017/1/11.
 */

public class PayPasswordFragmentDialog extends BaseBottomDialog {


    private DialogPayPasswordBinding mDataBinding;

    @Override
    public int getLayoutRes() {
        return R.layout.dialog_pay_password;
    }

    @Override
    public void bindView(View v) {
        mDataBinding = DataBindingUtil.bind(v);
        mDataBinding.setPayDialogFragment(this);
        setCancelable(false);
        //setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失

        mDataBinding.gridPasswordView.setOnPasswordChangedListener(new GridPasswordView.OnPasswordChangedListener() {
            @Override
            public void onTextChanged(String psw) {
                L.i("onTextChanged: " + psw);
            }

            @Override
            public void onInputFinish(String psw) {
                L.e("onInputFinish: " + psw);
            }
        });


    }

    /**
     * 键盘数字点击事件
     */
    public void keyboardClick(int id) {
        switch (id) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                mDataBinding.gridPasswordView.setText(String.valueOf(id));
                break;
            case 10:
                break;
            case 12:
                mDataBinding.gridPasswordView.deleteClick();
                break;
            case -1:
                dismiss();
                break;
        }

    }

    @Override
    public void show(FragmentManager fragmentManager) {
        if (!isAdded()) {
            super.show(fragmentManager);
        }
    }

    @Override
    public void onDestroy() {
        L.e("dialog onDestroy");
        if (mDataBinding != null) {
            mDataBinding.unbind();
            mDataBinding = null;
        }
        super.onDestroy();
    }
}
