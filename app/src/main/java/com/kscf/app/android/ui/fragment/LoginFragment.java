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
import com.kscf.app.android.ui.activity.DetailsActivity;
import com.kscf.app.android.ui.activity.LoginActivity;
import com.kscf.app.android.ui.activity.QuickAccountActivity;
import com.kscf.app.android.util.CheckUtils;
import com.kscf.app.android.util.LxSPUtils;
import com.kscf.app.android.widget.LoadingPage;

import java.util.Map;

/**
 * Created by luoyl on 2017/1/12.
 */

public class LoginFragment extends BaseFragment<FragmentLoginBinding, LoginFragmentPresenter> implements LoginFragmentContract.View, View.OnClickListener {

    /*是否发送短信验证码*/
    boolean mIsSendSmsCode;

    /*登录方式false:验证码登录，true密码登录*/
    boolean mIsPassLogin;

    /**
     * 倒计时Runnable对象
     */
    ReversTimeRun mReversTimeRun;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_login;
    }


    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    public void initView() {
        mLoadingPage.showPage(LoadingPage.STATE_SUCCEED);
        /*mDataBinding.itemPhone.getTextInputLayout().setCounterEnabled(true);
        mDataBinding.itemPhone.getTextInputLayout().setCounterMaxLength(11);*/

    }

    @Override
    public void initListener() {
        mDataBinding.btnLogin.setOnClickListener(this);
        mDataBinding.tvGetCode.setOnClickListener(this);
        mDataBinding.tvLoginMode.setOnClickListener(this);
        mDataBinding.tvSpannableReadProtocol.setOnClickListener(this);
        mDataBinding.ivCheckAgreement.setOnClickListener(this);

    }

    @Override
    public void initEventAndData() {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_login_mode://选择登录方式
                updateLoginModeUI();
                break;
            case R.id.btn_login:
                if (mIsPassLogin) {
                    passwordLogin();
                } else {
                    codeLogin();
                }
                //testToRiskEvaluation();
                break;
            case R.id.tv_get_code:
                sendSmsCode();
                break;
            case R.id.iv_check_agreement:
                boolean checkBoxIsSelected = mDataBinding.ivCheckAgreement.isSelected();
                mDataBinding.ivCheckAgreement.setSelected(!checkBoxIsSelected);
                break;
            case R.id.tv_spannable_read_protocol:
                ((LoginActivity) mActivity).showHideFragment(RegisterAgreementFragment.newInstance(), LoginFragment.class);
                break;
        }
    }

    /*密码登录*/
    private void passwordLogin() {
        L.i("passwordLogin");
    }

    private void toAccountSettings() {
        ((LoginActivity) mActivity).showHideFragment(AccountSettingsFragment.newInstance(), null);
    }

    //测试方法,跳转到风险测评页面，后面会被删除
    private void testToRiskEvaluation() {
        ((LoginActivity) mActivity).showHideFragment(RiskEvaluationFragment.newInstance(), LoginFragment.class);
    }

    /*验证码登录和密码登录切换*/
    public void updateLoginModeUI() {
        mIsPassLogin = (!mIsPassLogin);
        mDataBinding.itemCode.getEditText().setText("");
        if (mIsPassLogin) {//密码登录
            mDataBinding.llIsShowCode.setVisibility(View.GONE);
            mDataBinding.itemCode.setLeftTxt(R.string.txt_password);
            mDataBinding.itemCode.setInputHintText(R.string.txt_please_input_password);
            mDataBinding.itemCode.setTextInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
            mDataBinding.itemCode.setEditTextMaxLength(20);
        } else {//验证码登录
            mDataBinding.llIsShowCode.setVisibility(View.VISIBLE);
            mDataBinding.itemCode.setLeftTxt(R.string.txt_verification_code);
            mDataBinding.itemCode.setInputHintText(R.string.txt_please_input_verification_code);
            mDataBinding.itemCode.setTextInputType(InputType.TYPE_CLASS_NUMBER);
            mDataBinding.itemCode.setEditTextMaxLength(6);
        }
    }

    /**
     * 调用接口，发送验证码
     */
    public void sendSmsCode() {
        if (mReversTimeRun == null) {
            mReversTimeRun = new ReversTimeRun();
        }
        String phone = mDataBinding.itemPhone.getInputEditText();
        //如果手机号码合法
        if (checkPhoneNumber(phone)) {
            Map<String, Object> param = RetrofitHelper.getHttpParam();
            param.put(LxConstants.mobile, phone);
            mPresenter.sendSmsCode(param);
            mIsSendSmsCode = true;
        }
    }

    /**
     * 验证码方式登陆
     */
    private void codeLogin() {
        L.i("codeLogin");
        String phone = mDataBinding.itemPhone.getInputEditText();
        //验证码
        String code = mDataBinding.itemCode.getInputEditText();


        if (checkPhoneNumber(phone) && isSendSmsCode() && checkSmsCode(code)) {
            if (!mDataBinding.ivCheckAgreement.isSelected()) {
                mDataBinding.tilSpannableReadProtocol.setError("请阅读并勾选用户协议");
                return;
            }
            Map<String, Object> httpParam = RetrofitHelper.getHttpParam();
            httpParam.put(LxConstants.mobile, phone);
            httpParam.put(LxConstants.authcode, code);
            mPresenter.loginOrRegister(httpParam);
        }
    }


    @Override
    public void onLoginSuccess(BaseBean<LoginOrRegisterBean> baseBean) {
        if (baseBean.success) {
            L.i("onLoginSuccess 登录成功");
            ToastUtils.show(baseBean.message);
            LxSPUtils.putToken(baseBean.body.token);
            openAccountStepPage();
            mActivity.onBackPressed();
        } else {
            ToastUtils.show(baseBean.message);
        }

    }

    @Override
    public void onGetCodeSuccess(BaseBean baseBean) {
        if (baseBean != null && baseBean.success) {
            //短信验证码发送成功后开始倒计时
            sendSmsCodeReverseTime();
            ToastUtils.show(R.string.txt_sms_code_send_success);
        } else {
            ToastUtils.show(R.string.txt_sms_code_send_err);
        }
    }

    @Override
    public void onOpenAccountStepSuccess(BaseBean<OpenAccountStepBean> baseBean) {
        switch (baseBean.body.status) {
            case 0:

                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
        }
    }

    /**
     * 打开开户步骤页面（登录成功后，调用开户步骤接口,查看当前在步骤几）
     */
    public void openAccountStepPage() {
        Intent intent = new Intent(mActivity, QuickAccountActivity.class);
        intent.putExtra(LxConstants.fragmentHashCodeKey, QuickAccountFragment01.class.hashCode());
        App.getInstance().startTargetActivity(mActivity, intent, true);
    }


    public boolean isSendSmsCode() {
        //如果没有发送验证码，提示用户发验证码
        if (!mIsSendSmsCode) {
            ToastUtils.show(R.string.txt_please_send_sms_code);
        }
        return mIsSendSmsCode;
    }

    /**
     * 验证手机号码是否正确
     *
     * @param phone
     * @return
     */
    public boolean checkPhoneNumber(String phone) {
        boolean result = true;
        //手机号码是否输入
        if (TextUtils.isEmpty(phone)) {
            ToastUtils.show(R.string.txt_please_input_phone_number);
            //mDataBinding.itemPhone.setTextInputLayoutError(R.string.txt_please_input_phone_number);
            result = false;
        } else if (!CheckUtils.checkPhoneNumber(phone)) {
            //验证手机号码格式是否正确
            ToastUtils.show(R.string.txt_phone_number_format_err);
            //mDataBinding.itemPhone.setTextInputLayoutError(R.string.txt_phone_number_format_err);
            result = false;
        }
        return result;
    }

    public boolean checkSmsCode(String code) {
        boolean result = true;
        if (!mIsSendSmsCode) {
            //mDataBinding.itemCode.setTextInputLayoutError(R.string.txt_please_send_sms_code);
            ToastUtils.show(R.string.txt_please_send_sms_code);
            result = false;
        } else if (TextUtils.isEmpty(code)) {
            //请输入短信验证码
            ToastUtils.show(R.string.txt_please_input_sms_code);
            //mDataBinding.itemCode.setTextInputLayoutError(R.string.txt_please_input_sms_code);
            result = false;
        }
        return result;
    }


    public class ReversTimeRun implements Runnable {
        //记录倒序时间
        public int reversTime = 60;

        @Override
        public void run() {
            updateCodeUi(--reversTime);
            App.getInstance().removeCallbacks(this);
            App.getInstance().postDelayed(this, 1000);
        }
    }

    /**
     * 发送短信验证码倒计时
     */
    public void sendSmsCodeReverseTime() {
        mDataBinding.tvGetCode.setEnabled(false);
        mReversTimeRun.reversTime = 60;
        App.getInstance().postDelayed(mReversTimeRun, 800);
    }

    /**
     * 更新验证码按钮UI
     *
     * @param time
     */
    public void updateCodeUi(int time) {
        if (time > 0) {
            mDataBinding.tvGetCode.setText(String.valueOf(time));
        } else {
            mDataBinding.tvGetCode.setText(R.string.txt_get_verification_code);
            mDataBinding.tvGetCode.setEnabled(true);
            App.getInstance().removeCallbacks(mReversTimeRun);
        }
    }


    @Override
    public void free() {
        mDataBinding.btnLogin.setOnClickListener(null);
        mDataBinding.tvGetCode.setOnClickListener(null);
        App.getInstance().removeCallbacks(mReversTimeRun);
        mReversTimeRun = null;
        super.free();
    }


}
