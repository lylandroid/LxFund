package com.kscf.app.android.model.bean.base;

import android.content.Intent;
import android.text.TextUtils;
import android.util.SparseArray;

import com.framework.util.ToastUtils;
import com.kscf.app.android.R;
import com.kscf.app.android.app.App;
import com.kscf.app.android.app.LxConstants;
import com.kscf.app.android.base.BaseActivity;
import com.kscf.app.android.ui.activity.LoginActivity;
import com.kscf.app.android.ui.fragment.LoginFragment;

/**
 * 响应状态吗类
 *
 * @author luoyl
 * @created 2017/3/29
 */

public class ResponseStateCode {

    private static ResponseStateCode sInstance;
    private SparseArray<Msg> mCodeArray;

    public interface Code {
        int UNKNOWN_ERR = -1;//未知错误
        int SUCCESS = 0;//成功
        int INVALID_SIGNATURE = 402001; // 签名错误
        int INVALID_TOKEN = 402002; // 无效token
        int EXPIRED_TOKEN = 402003; // 过期token
        int INTERNAL_ERROR = 402004; // 内部错误
        int INVALID_VERIFYCODE = 402005; // 验证码无效
        int BAD_FORMAT = 402006; // 格式错误,如手机号格式错误
        int ALREADY_EXIST = 402007; // 已经存在,如手机号已注册
        int NOT_SUPPORTED = 402008; // 不支持
        int ENTITY_NOTEXIST = 402009; // 实体不存在

        //登录
        int LOGIN_SUCCESS = 200000;//"登录成功"
        int REGISTER_SUCCESS = 200001;//,"注册成功"
        int LOGIN_FAIL = 500000;//登录失败
        int REGISTER_FAIL = 500001;//注册失败"
        int INVALID_CELL_NO = 500002;//手机号码不正确
        int VERIFY_CODE_EXPIRED = 500003;//验证码已过期，请重新获取
        int INVALID_VERIFY_CODE = 500004;//您输入的验证码不正确！
        int SMS_RETURN_SEND_FAIL = 500005;//短信发送服务返回发送失败
        int LOGIN_STATE_EXPIRED = 500006;//登录已失效，请重新登录后重试

        //银行卡鉴权
        int INVALID_ID_CARD = 500010;//无效的身份证号码
        int INVALID_AGE = 500011;//年龄未满18岁或大于70岁无法开户
        int AUTH_BANK_CARD_FAIL = 500012;//您输入的银行卡号、身份信息、手机号码与银行预留不匹配


    }


    public ResponseStateCode() {
        mCodeArray = new SparseArray<>(30);
        mCodeArray.put(Code.UNKNOWN_ERR, new Msg(false, R.string.txt_unknown_err));//未知错误
        mCodeArray.put(Code.SUCCESS, new Msg(true, R.string.txt_success));//成功
        mCodeArray.put(Code.INVALID_SIGNATURE, new Msg(false, R.string.txt_autograph_error));//签名错误
        mCodeArray.put(Code.INVALID_TOKEN, new Msg(false, LoginActivity.class.getName(), LoginFragment.class.getName(), R.string.txt_invalid_token));//无效token
        mCodeArray.put(Code.EXPIRED_TOKEN, new Msg(false, LoginActivity.class.getName(), LoginFragment.class.getName(), R.string.txt_overdue_token));//过期token
        mCodeArray.put(Code.INTERNAL_ERROR, new Msg(false, R.string.txt_inner_err));//内部错误
        mCodeArray.put(Code.INVALID_VERIFYCODE, new Msg(false, R.string.txt_verification_code_overdue_re_get));//验证码无效
        mCodeArray.put(Code.BAD_FORMAT, new Msg(false, R.string.txt_phone_number_format_err));//格式错误,如手机号格式错误
        mCodeArray.put(Code.ALREADY_EXIST, new Msg(false, R.string.txt_phone_number_already_register));//已经存在,如手机号已注册
        mCodeArray.put(Code.NOT_SUPPORTED, new Msg(false, R.string.txt_no_support));//不支持
        mCodeArray.put(Code.ENTITY_NOTEXIST, new Msg(false, R.string.txt_entity_no_exist));//实体不存在

        //登录
        mCodeArray.put(Code.LOGIN_SUCCESS, new Msg(true, R.string.txt_login_success));//登录成功
        mCodeArray.put(Code.REGISTER_SUCCESS, new Msg(true, R.string.txt_register_success));//登录成功
        mCodeArray.put(Code.LOGIN_FAIL, new Msg(false, R.string.txt_login_fail));//登录失败
        mCodeArray.put(Code.REGISTER_FAIL, new Msg(false, R.string.txt_register_fail));//注册失败
        mCodeArray.put(Code.INVALID_CELL_NO, new Msg(false, R.string.txt_phone_number_format_err));//手机号码不正确
        mCodeArray.put(Code.VERIFY_CODE_EXPIRED, new Msg(false, R.string.txt_verification_code_overdue_re_get));//验证码已过期，请重新获取！
        mCodeArray.put(Code.INVALID_VERIFY_CODE, new Msg(false, R.string.txt_you_input_verification_code_err));//您输入的验证码不正确
        mCodeArray.put(Code.SMS_RETURN_SEND_FAIL, new Msg(false, R.string.txt_sms_code_send_err));//短信发送服务返回发送失败
        mCodeArray.put(Code.LOGIN_STATE_EXPIRED, new Msg(false, LoginActivity.class.getName(), LoginFragment.class.getName(), R.string.txt_login_invalid_re_login));//登录已失效，请重新登录后重试

        //银行卡鉴权
        mCodeArray.put(Code.INVALID_ID_CARD, new Msg(false, R.string.txt_invalid_id_card_number));//无效的身份证号码
        mCodeArray.put(Code.INVALID_AGE, new Msg(false, R.string.txt_account_age_scope));//年龄未满18岁或大于70岁无法开户
        mCodeArray.put(Code.AUTH_BANK_CARD_FAIL, new Msg(false, R.string.txt_you_input_info_bank_reserve_mismatch));//您输入的银行卡号、身份信息、手机号码与银行预留不匹配

    }

    private static ResponseStateCode getInstance() {
        if (sInstance == null) {
            sInstance = new ResponseStateCode();
        }
        return sInstance;
    }

    public static Msg getStateMsg(int code) {
        Msg msg = getInstance().mCodeArray.get(code);
        if (msg == null) {
            return getInstance().mCodeArray.get(Code.UNKNOWN_ERR);
        }
        return msg;
    }

    public static void toAction(int code, BaseActivity activity, boolean isNeedLogin) {
        Msg msg = getStateMsg(code);
        if (msg.isAction()) {
            Intent intent = new Intent();
            intent.setClassName(activity, msg.activityClazzName);
            intent.putExtra(LxConstants.fragmentClazzNameKey, msg.fragmentClazzName);
            App.getInstance().startTargetActivity(activity, intent, isNeedLogin);
        }
        ToastUtils.show(msg.msgResId);

    }


    public static class Msg {
        //是否成功
        public boolean isSuccess;
        //错误提示信息的资源id
        public int msgResId;
        //是否需要跳转（比如登录无效，重新跳转到登录页面）
        public String activityClazzName;

        public String fragmentClazzName;

        public Msg(boolean isSuccess, int msgResId) {
            this.isSuccess = isSuccess;
            this.msgResId = msgResId;
        }

        public Msg(boolean isSuccess, String activityClazzName, String fragmentClazzName, int msgResId) {
            this.isSuccess = isSuccess;
            this.msgResId = msgResId;
            this.activityClazzName = activityClazzName;
            this.fragmentClazzName = fragmentClazzName;
        }

        /**
         * 是否需要跳转
         *
         * @return
         */
        public boolean isAction() {
            return !TextUtils.isEmpty(fragmentClazzName);
        }
    }


}
