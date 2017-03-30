package com.kscf.app.android.ui.fragment;

import com.framework.view.webview.CustomWebView;
import com.kscf.app.android.R;
import com.kscf.app.android.base.BaseFragment;
import com.kscf.app.android.databinding.FragmentRegisterAgreementBinding;
import com.kscf.app.android.presenter.NullPresenter;
import com.kscf.app.android.widget.LoadingPage;

/**
 * 注册协议Fragment
 * Created by luoyl on 2017/3/1.
 */

public class RegisterAgreementFragment extends BaseFragment<FragmentRegisterAgreementBinding, NullPresenter> {


    @Override
    public int getLayoutResId() {
        return R.layout.fragment_register_agreement;
    }


    public static RegisterAgreementFragment newInstance() {
        return new RegisterAgreementFragment();
    }

    @Override
    public void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    public void initView() {
        mLoadingPage.showPage(LoadingPage.STATE_SUCCEED);
        mDataBinding.webview.handlerBack(mDataBinding.toolbar);
//        CustomWebView customWebView = new CustomWebView(mActivity, mDataBinding.webViewControl);
//        customWebView.loadUrl("https://www.baidu.com/");
        mDataBinding.webview.loadUrl("https://www.baidu.com/");

    }

    @Override
    public void initListener() {


    }

    @Override
    public void initEventAndData() {
    }

    @Override
    public void free() {
        mDataBinding.webViewControl.removeView(mDataBinding.webview);
        super.free();
    }
}
