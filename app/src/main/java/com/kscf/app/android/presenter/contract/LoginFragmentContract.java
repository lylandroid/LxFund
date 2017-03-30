package com.kscf.app.android.presenter.contract;

import com.kscf.app.android.base.BaseActivity;
import com.kscf.app.android.base.BasePresenter;
import com.kscf.app.android.base.view.BaseView;
import com.kscf.app.android.model.bean.LoginOrRegisterBean;
import com.kscf.app.android.model.bean.base.BaseBean;
import com.kscf.app.android.model.bean.base.BaseResponseBean;
import com.kscf.app.android.model.bean.SendSmsBean;
import com.kscf.app.android.model.bean.GetOpenAccountStepBean;
import com.kscf.app.android.model.bean.response.GetOpenAccountStepResponseBean;
import com.kscf.app.android.model.bean.response.LoginOrRegisterResponseBean;

import java.util.Map;

/**
 * 登录Fragment接口
 * Created by luoyl on 2016/12/26.
 */

public interface LoginFragmentContract {

    interface View extends BaseView {
        void onLoginSuccess(BaseResponseBean<LoginOrRegisterResponseBean> baseBean);

        void onSmsCodeSuccess(BaseResponseBean baseResponseBean);

        /*开户步骤回调api*/
        void onOpenAccountStepSuccess(BaseResponseBean<GetOpenAccountStepResponseBean> baseBean);
    }

    interface Presenter extends BasePresenter<View> {
        void loginOrRegister(LoginOrRegisterBean loginOrRegisterBean);

        void sendSmsCode(SendSmsBean sendSmsBean);

        /**
         * 获取开户步骤
         */
        void getOpenAccountStep(BaseActivity activity, GetOpenAccountStepBean bean);
    }
}
