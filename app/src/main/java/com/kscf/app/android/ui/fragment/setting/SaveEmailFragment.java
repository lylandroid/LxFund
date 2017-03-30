package com.kscf.app.android.ui.fragment.setting;

import android.text.TextUtils;
import android.view.View;

import com.framework.base.adapter.DataBindingRecyclerAdapter;
import com.framework.util.ToastUtils;
import com.kscf.app.android.R;
import com.kscf.app.android.base.BaseFragment;
import com.kscf.app.android.databinding.FragmentMessageBinding;
import com.kscf.app.android.databinding.FragmentSaveEmailBinding;
import com.kscf.app.android.presenter.MessageFragmentPresenter;
import com.kscf.app.android.presenter.SaveEmailFragmentPresenter;
import com.kscf.app.android.presenter.contract.MessageFragmentContract;
import com.kscf.app.android.util.CheckUtils;
import com.kscf.app.android.widget.LoadingPage;

import java.util.ArrayList;
import java.util.List;

/**
 * 保存邮箱地址Fragment
 * Created by luoyl on 2017/3/27.
 */

public class SaveEmailFragment extends BaseFragment<FragmentSaveEmailBinding, SaveEmailFragmentPresenter> implements MessageFragmentContract.View, View.OnClickListener {

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_save_email;
    }


    public static SaveEmailFragment newInstance() {
        return new SaveEmailFragment();
    }

    @Override
    public void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    public void initView() {
        mLoadingPage.showPage(LoadingPage.STATE_SUCCEED);
    }

    @Override
    public void initListener() {
        mDataBinding.btnConfirm.setOnClickListener(this);

    }

    @Override
    public void initEventAndData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_confirm:
                saveEmail();
                break;
        }
    }

    private void saveEmail() {
        String emailStr = mDataBinding.itemEmail.getInputEditText();
        if (checkEmail(emailStr)) {
            ToastUtils.show("邮箱格式正确");
        }

    }

    private boolean checkEmail(String email) {
        boolean result = false;
        if (TextUtils.isEmpty(email)) {
            ToastUtils.show(R.string.txt_please_input_email);
        } else if (!CheckUtils.checkEmail(email)) {
            ToastUtils.show(R.string.txt_please_input_correct_email);
        } else {
            result = true;
        }
        return result;
    }

    /*private void toAccountSettings() {
        ((LoginActivity) mActivity).showHideFragment(AccountSettingsFragment.newInstance(), null);
    }*/

    @Override
    public void free() {
        super.free();
    }


}
