package com.kscf.app.android.presenter;

import com.framework.http.HttpSubscriber;
import com.framework.util.ToastUtils;
import com.kscf.app.android.R;
import com.kscf.app.android.base.RxPresenter;
import com.kscf.app.android.model.bean.base.BaseBean;
import com.kscf.app.android.model.bean.SendSmsBean;
import com.kscf.app.android.presenter.contract.RiskEvaluationFragmentContract;

import java.util.Map;

import javax.inject.Inject;

/**
 * 风险测评Presenter
 * Created by luoyl on 2017/1/22.
 */

public class RiskEvaluationFragmentPresenter extends RxPresenter<RiskEvaluationFragmentContract.View> implements RiskEvaluationFragmentContract.Presenter {

    @Inject
    public RiskEvaluationFragmentPresenter() {
    }

    @Override
    public void start() {
    }


    public void loginOrRegister(Map<String, Object> param) {
        HttpSubscriber<SendSmsBean> httpSubscriber = new HttpSubscriber<SendSmsBean>() {
            @Override
            public void onSuccess(SendSmsBean loginOrRegisterBean) {
                if (mIsDestroy) {
                    return;
                }
               /* if (loginOrRegisterBean.success) {
                   // mView.onLoginSuccess(loginOrRegisterBean);
                } else {

                }*/
            }

            @Override
            public void onError(Throwable e) {
                if (mIsDestroy) {
                    return;
                }
                super.onError(e);
                RiskEvaluationFragmentPresenter.this.onHttpErr(1, 0, "", e);
                ToastUtils.show(R.string.txt_phone_number_format_err);
            }
        };
        ///addSubscriber(mRetrofitHelper.sendNet(HttpMethod.GET,Apis.testApi, null, param, httpSubscriber));
    }


    public void sendSmsCode(Map<String, Object> param) {
        HttpSubscriber<BaseBean> httpSubscriber = new HttpSubscriber<BaseBean>() {
            @Override
            public void onSuccess(BaseBean baseBean) {
                if (baseBean.success) {
                    ToastUtils.show(R.string.txt_sms_code_send_success);
                } else {
                    ToastUtils.show(R.string.txt_sms_code_send_err);
                }


            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                RiskEvaluationFragmentPresenter.this.onHttpErr(0, 0, "", e);
                ToastUtils.show(R.string.txt_sms_code_send_err);
            }
        };
        //addSubscriber(mRetrofitHelper.sendNet(HttpMethod.POST,Apis.loginSendSmsCodeApi, null, param, httpSubscriber));
    }
}
