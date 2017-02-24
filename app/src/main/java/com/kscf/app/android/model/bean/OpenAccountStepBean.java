package com.kscf.app.android.model.bean;

import com.google.gson.annotations.SerializedName;

/**
 * 开户步骤
 * Created by luoyl on 2017/2/9.
 */

public class OpenAccountStepBean {

    /**
     * status : 3
     * desc : 还没开始测评！
     * detail : {"identityno":"340824199003093248","mobile":"13819999997","bankserial":"012","identitytype":"0","customername":"例看","bankacco":"6230205698269856"}
     */

    //@SerializedName("3")
    //public String value3;
    public int status;
    public String desc;
    public DetailBean detail;

    public static class DetailBean {
        /**
         * identityno : 340824199003093248
         * mobile : 13819999997
         * bankserial : 012
         * identitytype : 0
         * customername : 例看
         * bankacco : 6230205698269856
         */

        public String identityno;
        public String mobile;
        public String bankserial;
        public String identitytype;
        public String customername;
        public String bankacco;
    }
}
