package com.kscf.app.android.ui.fragment;

import android.text.InputType;
import android.text.TextUtils;
import android.view.View;

import com.framework.util.L;
import com.framework.util.ToastUtils;
import com.kscf.app.android.R;
import com.kscf.app.android.app.App;
import com.kscf.app.android.base.BaseFragment;
import com.kscf.app.android.databinding.FragmentLoginBinding;
import com.kscf.app.android.model.bean.GetOpenAccountStepBean;
import com.kscf.app.android.model.bean.LoginOrRegisterBean;
import com.kscf.app.android.model.bean.SendSmsBean;
import com.kscf.app.android.model.bean.base.BaseResponseBean;
import com.kscf.app.android.model.bean.base.ResponseStateCode;
import com.kscf.app.android.model.bean.response.GetOpenAccountStepResponseBean;
import com.kscf.app.android.model.bean.response.LoginOrRegisterResponseBean;
import com.kscf.app.android.presenter.LoginFragmentPresenter;
import com.kscf.app.android.presenter.contract.LoginFragmentContract;
import com.kscf.app.android.ui.activity.LoginActivity;
import com.kscf.app.android.ui.activity.MainActivity;
import com.kscf.app.android.ui.activity.QuickAccountActivity;
import com.kscf.app.android.util.CheckUtils;
import com.kscf.app.android.util.LxSPUtils;
import com.kscf.app.android.util.framing.LxRxBus;
import com.kscf.app.android.widget.LoadingPage;

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

    int[] mSendSmsButtonColors;

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
        mSendSmsButtonColors = new int[]{getResources().getColor(R.color.txt_btn_verification_code_selector)
                , getResources().getColor(R.color.txt_subtitle_color)};

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

    /*private void toAccountSettings() {
        ((LoginActivity) mActivity).showHideFragment(AccountSettingsFragment.newInstance(), null);
    }

    //测试方法,跳转到风险测评页面，后面会被删除
    private void testToRiskEvaluation() {
        ((LoginActivity) mActivity).showHideFragment(RiskEvaluationFragment.newInstance(), LoginFragment.class);
    }*/

    /*验证码登录和密码登录切换*/
    public void updateLoginModeUI() {
        mIsPassLogin = (!mIsPassLogin);
        mDataBinding.itemCode.getEditText().setText("");
        if (mIsPassLogin) {//密码登录
            mDataBinding.llIsShowCode.setVisibility(View.GONE);
            mDataBinding.itemCode.setLeftTxt(R.string.txt_password);
            mDataBinding.itemCode.setInputHintText(R.string.txt_please_input_password);
            mDataBinding.itemCode.setTextInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
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
            SendSmsBean sendSmsBean = new SendSmsBean(phone);
            mPresenter.sendSmsCode(sendSmsBean);
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
            LoginOrRegisterBean loginOrRegisterBean = new LoginOrRegisterBean(phone, code);
            mPresenter.loginOrRegister(loginOrRegisterBean);
        }
    }


    @Override
    public void onLoginSuccess(BaseResponseBean<LoginOrRegisterResponseBean> baseBean) {
        if (baseBean.isDataNotNull()) {
            ToastUtils.show(R.string.txt_login_success);
            String token = baseBean.data.get(0).token;
            String userId = baseBean.data.get(0).userId;
            LxSPUtils.putToken(token);
            LxSPUtils.putUserId(userId);
            openAccountStepPage(token);
            LxRxBus.getInstance().get().post(MainActivity.sRxBusLoginToHomeMyAccountTag, String.valueOf(2));
            if (L.isDebug) {
                ToastUtils.show("token: " + token + "\n userId:" + userId);
            }
            //mActivity.onBackPressed();
        } else {
            ToastUtils.show(R.string.txt_login_fail);
        }

    }

    @Override
    public void onSmsCodeSuccess(BaseResponseBean responseBean) {
        if (responseBean.getMsg().isSuccess) {
            //短信验证码发送成功后开始倒计时
            sendSmsCodeReverseTime();
            ToastUtils.show(R.string.txt_sms_code_send_success);
        } else {
            ToastUtils.show(R.string.txt_sms_code_send_err);
        }
    }


    /**
     * 打开开户步骤页面第几步
     */
    @Override
    public void onOpenAccountStepSuccess(BaseResponseBean<GetOpenAccountStepResponseBean> baseResponseBean) {
        //保存开户步骤
        LxSPUtils.putOpenAccountStep(baseResponseBean.data.get(0).code);
        switch (baseResponseBean.data.get(0).code) {
            case 1://还没开始开户
                mActivity.addFragmentToActivity(mActivity, QuickAccountActivity.class, QuickAccountFragment01.class.getName(), true);
                break;
            case 2://"已开户还未设置交易密码!
                mActivity.addFragmentToActivity(mActivity, QuickAccountActivity.class, QuickAccountFragment02.class.getName(), true);
                break;
            case 3://开过户但风险测评还没完成
                mActivity.addFragmentToActivity(mActivity, QuickAccountActivity.class, QuickAccountFragment03.class.getName(), true);
                break;
        }
    }

    /**
     * 打开开户步骤页面（登录成功后，调用开户步骤接口,查看当前在步骤几）
     */
    public void openAccountStepPage(String token) {
        //获取开户步骤值，
        // 0：开过户也测评过！
        // 1：还没开始开户!"
        // 2：已开户还未设置交易密码!
        // 3：开过户但风险测评还没完成
        int openAccountStep = LxSPUtils.getOpenAccountStep();
        if (openAccountStep != 0) {
            mPresenter.getOpenAccountStep(mActivity, new GetOpenAccountStepBean(token));
        }
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
            mDataBinding.tvGetCode.setText(time + "s");
            mDataBinding.tvGetCode.setTextColor(mSendSmsButtonColors[1]);
        } else {
            mDataBinding.tvGetCode.setText(R.string.txt_get_verification_code);
            mDataBinding.tvGetCode.setTextColor(mSendSmsButtonColors[0]);
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
