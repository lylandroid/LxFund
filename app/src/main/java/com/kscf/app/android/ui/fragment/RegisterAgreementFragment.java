package com.kscf.app.android.ui.fragment;

import android.content.Intent;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;

import com.framework.util.L;
import com.framework.util.ToastUtils;
import com.kscf.app.android.R;
import com.kscf.app.android.app.App;
import com.kscf.app.android.app.LxConstants;
import com.kscf.app.android.base.BaseFragment;
import com.kscf.app.android.databinding.FragmentLoginBinding;
import com.kscf.app.android.model.bean.BaseBean;
import com.kscf.app.android.model.bean.LoginOrRegisterBean;
import com.kscf.app.android.model.bean.OpenAccountStepBean;
import com.kscf.app.android.model.http.RetrofitHelper;
import com.kscf.app.android.presenter.LoginFragmentPresenter;
import com.kscf.app.android.presenter.contract.LoginFragmentContract;
import com.kscf.app.android.ui.activity.LoginActivity;
import com.kscf.app.android.ui.activity.QuickAccountActivity;
import com.kscf.app.android.util.CheckUtils;
import com.kscf.app.android.util.LxSPUtils;
import com.kscf.app.android.widget.LoadingPage;

import java.util.Map;

/**
 * 注册协议Fragment
 * Created by luoyl on 2017/3/1.
 */

public class RegisterAgreementFragment extends BaseFragment {


    @Override
    public int getLayoutResId() {
        return R.layout.fragment_register_agreement;
    }


    public static RegisterAgreementFragment newInstance() {
        return new RegisterAgreementFragment();
    }

    @Override
    public void initInject() {
        //getFragmentComponent().inject(this);
    }

    @Override
    public void initView() {
        mLoadingPage.showPage(LoadingPage.STATE_SUCCEED);

    }

    @Override
    public void initListener() {


    }

    @Override
    public void initEventAndData() {
    }


}
