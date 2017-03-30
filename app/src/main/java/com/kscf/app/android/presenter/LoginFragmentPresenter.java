package com.kscf.app.android.presenter;

import com.framework.http.HttpSubscriber;
import com.framework.util.ToastUtils;
import com.kscf.app.android.R;
import com.kscf.app.android.base.BaseActivity;
import com.kscf.app.android.base.RxPresenter;
import com.kscf.app.android.model.bean.GetOpenAccountStepBean;
import com.kscf.app.android.model.bean.LoginOrRegisterBean;
import com.kscf.app.android.model.bean.SendSmsBean;
import com.kscf.app.android.model.bean.base.BaseResponseBean;
import com.kscf.app.android.model.bean.base.ResponseStateCode;
import com.kscf.app.android.model.bean.response.GetOpenAccountStepResponseBean;
import com.kscf.app.android.model.bean.response.LoginOrRegisterResponseBean;
import com.kscf.app.android.model.http.ApiHelper;
import com.kscf.app.android.presenter.contract.LoginFragmentContract;

import javax.inject.Inject;

import rx.Subscription;

/**
 * Created by luoyl on 2016/12/26.
 * 登录Presenter
 */

public class LoginFragmentPresenter extends RxPresenter<LoginFragmentContract.View> implements LoginFragmentContract.Presenter {

    @Inject
    public LoginFragmentPresenter() {
    }

    @Override
    public void start() {
    }

    @Override
    public void loginOrRegister(LoginOrRegisterBean loginOrRegisterBean) {
        HttpSubscriber<BaseResponseBean<LoginOrRegisterResponseBean>> httpSubscriber = new HttpSubscriber<BaseResponseBean<LoginOrRegisterResponseBean>>() {
            @Override
            public void onSuccess(BaseResponseBean<LoginOrRegisterResponseBean> baseBean) {
                if (mIsDestroy) {
                    return;
                }
                ResponseStateCode.Msg msg = baseBean.getMsg();
                if(msg.isSuccess){
                    mView.onLoginSuccess(baseBean);
                }else{
                    ToastUtils.show(msg.msgResId);
                }
               /* if (baseBean.isDataSuccess()) {
                    mView.onLoginSuccess(baseBean);
                } else {
                    String errMsg = ResUtils.getString(R.string.txt_login_register_err);
                    switch (baseBean.code) {
                        case 500002://手机号码不正确
                            errMsg = ResUtils.getString(R.string.txt_phone_number_format_err);
                            break;
                        case 500003://验证码已过期，请重新获取！
                            errMsg = ResUtils.getString(R.string.txt_verification_code_overdue_re_get);
                            break;
                        case 500004://"您输入的验证码不正确！
                            errMsg = ResUtils.getString(R.string.txt_you_input_verification_code_err);
                            break;
                        case 500005://"短信发送服务返回发送失败"
                            errMsg = ResUtils.getString(R.string.txt_sms_code_send_err);
                            break;
                        case 500000://登录失败
                            errMsg = ResUtils.getString(R.string.txt_login_fail);
                            break;
                        case 500001://注册失败
                            errMsg = ResUtils.getString(R.string.txt_register_fail);
                            break;

                    }
                    ToastUtils.show(errMsg);
                }*/

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
        Subscription subscription = ApiHelper.loginOrRegister(loginOrRegisterBean, httpSubscriber);
        addSubscriber(subscription);
    }

    @Override
    public void sendSmsCode(final SendSmsBean sendSmsBean) {
        Subscription subscription = ApiHelper.sendSmsVerificationCode(sendSmsBean, new HttpSubscriber<BaseResponseBean>() {
            @Override
            public void onSuccess(BaseResponseBean responseBean) {
                if (mIsDestroy) {
                    return;
                }
                mView.onSmsCodeSuccess(responseBean);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                ToastUtils.show(R.string.txt_sms_code_send_err);

            }
        });
        addSubscriber(subscription);
    }

    @Override
    public void getOpenAccountStep(final BaseActivity activity, GetOpenAccountStepBean bean) {

        HttpSubscriber<BaseResponseBean<GetOpenAccountStepResponseBean>> httpSubscriber = new HttpSubscriber<BaseResponseBean<GetOpenAccountStepResponseBean>>() {
            @Override
            public void onSuccess(BaseResponseBean<GetOpenAccountStepResponseBean> baseBean) {
                if (!mIsDestroy && baseBean.getMsg().isSuccess)
                    mView.onOpenAccountStepSuccess(baseBean);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                LoginFragmentPresenter.this.onHttpErr(0, 0, "", e);
            }

            @Override
            public void onCompleted() {
                if (activity != null) {
                    activity.onBackPressed();
                }
            }
        };
        addSubscriber(ApiHelper.getOpenAccountStep(bean, httpSubscriber));

    }
}
