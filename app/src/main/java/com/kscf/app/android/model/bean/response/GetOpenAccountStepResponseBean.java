package com.kscf.app.android.model.bean.response;

/**
 * 开户步骤
 * Created by luoyl on 2017/2/9.
 */

public class GetOpenAccountStepResponseBean {


    /**
     * code : 1
     * desc : 还没开始开户!
     * preDetail : {}
     * <p>
     * ACCOUNT_OPEN_RISK_TEST_FINISHED("0","开过户也测评过！"),
     * ACCOUNT_NOT_OPEN("1","还没开始开户!"),
     * ACCOUNT_OPEN_TRADE_PWD_NOT_SET("2","已开户还未设置交易密码!"),
     * ACCOUNT_OPEN_RISK_TEST_NOT_FINISHED("3","开过户但风险测评还没完成！");
     */

    public int code;
    public String desc;
    public PreDetailBean preDetail;

    public static class PreDetailBean {
    }
}
