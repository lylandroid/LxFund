package com.kscf.app.android.model.bean.base;

import java.util.List;

/**
 * Created by Administrator on 2017/3/24.
 */

public class BaseResponseBean<ItemData>{
    public int code = -1;
    public String msg;
    public int total;
    public int totalpage;
    public List<ItemData> data;

    //public Object singleData;

    public boolean isDataNotNull() {
        return data != null && data.size() > 0;
    }

    public ResponseStateCode.Msg getMsg(){
        return ResponseStateCode.getStateMsg(code);
    }
}
