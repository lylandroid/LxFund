package com.kscf.app.android.presenter;

import com.kscf.app.android.R;
import com.kscf.app.android.app.Apis;
import com.kscf.app.android.base.RxPresenter;
import com.kscf.app.android.model.bean.BaseBean;
import com.kscf.app.android.model.bean.LoginOrRegisterBean;
import com.kscf.app.android.model.bean.OpenAccountStepBean;
import com.kscf.app.android.model.http.HttpMethod;
import com.kscf.app.android.model.http.HttpSubscriber;
import com.kscf.app.android.model.http.RetrofitHelper;
import com.kscf.app.android.presenter.contract.LoginFragmentContract;
import com.framework.util.ToastUtils;

import java.util.Map;

import javax.inject.Inject;

/**
 * Created by luoyl on 2016/12/26.
 * 登录Presenter
 */

public class LoginFragmentPresenter extends RxPresenter<LoginFragmentContract.View> implements LoginFragmentContract.Presenter {

    @Inject
    public LoginFragmentPresenter(RetrofitHelper retrofitHelper) {
        mRetrofitHelper = retrofitHelper;
    }

    @Override
    public void start() {
    }

    @Override
    public void loginOrRegister(Map<String, Object> param) {
        HttpSubscriber<BaseBean<LoginOrRegisterBean>> httpSubscriber = new HttpSubscriber<BaseBean<LoginOrRegisterBean>>() {
            @Override
            public void onSuccess(BaseBean<LoginOrRegisterBean> loginOrRegisterBean) {
                if (mIsDestroy) {
                    return;
                }
                mView.onLoginSuccess(loginOrRegisterBean);
            }

            @Override
            public void onError(Throwable e) {
                if (mIsDestroy) {
                    return;
                }
                super.onError(e);
                LoginFragmentPresenter.this.onHttpErr(1, 0, "", e);
                ToastUtils.show(R.string.txt_login_register_err);
            }
        };
        addSubscriber(mRetrofitHelper.sendNet(HttpMethod.POST,Apis.loginOrRegisterApi, null, param, httpSubscriber));
    }

    @Override
    public void sendSmsCode(Map<String, Object> param) {
        HttpSubscriber<BaseBean> httpSubscriber = new HttpSubscriber<BaseBean>() {
            @Override
            public void onSuccess(BaseBean baseBean) {
                if (!mIsDestroy)
                    mView.onGetCodeSuccess(baseBean);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                LoginFragmentPresenter.this.onHttpErr(0, 0, "", e);
                ToastUtils.show(R.string.txt_sms_code_send_err);
            }
        };
        addSubscriber(mRetrofitHelper.sendNet(HttpMethod.POST,Apis.loginSendSmsCodeApi, null, param, httpSubscriber));
    }

    @Override
    public void getOpenAccountStep(Map<String, Object> param) {

        HttpSubscriber<BaseBean<OpenAccountStepBean>> httpSubscriber = new HttpSubscriber<BaseBean<OpenAccountStepBean>>() {
            @Override
            public void onSuccess(BaseBean<OpenAccountStepBean> baseBean) {
                if (!mIsDestroy && baseBean.body != null)
                    mView.onOpenAccountStepSuccess(baseBean);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                LoginFragmentPresenter.this.onHttpErr(0, 0, "", e);
            }
        };
        addSubscriber(mRetrofitHelper.sendNet(HttpMethod.POST,Apis.openAccountStep, null, param, httpSubscriber));

    }
}
