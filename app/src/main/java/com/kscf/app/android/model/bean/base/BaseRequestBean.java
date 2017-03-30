package com.kscf.app.android.model.bean.base;

import android.text.TextUtils;

import com.framework.util.DeviceUtils;
import com.framework.util.L;
import com.framework.util.LocationUtils;
import com.framework.util.NetworkUtils;
import com.framework.util.ScreenUtil;
import com.google.gson.Gson;
import com.kscf.app.android.app.App;
import com.kscf.app.android.app.LxConstants;
import com.kscf.app.android.other.UmengUtils;
import com.kscf.app.android.util.LxSPUtils;
import com.kscf.app.android.util.encrypt.DesUtils;
import com.kscf.app.android.util.encrypt.SignUtils;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/21.
 */

public class BaseRequestBean {

    private static BaseRequestBean sInstance;

    private RequestBody mRequestBody;


    public BaseRequestBean() {
        mRequestBody = new RequestBody();
    }

    public static class RequestBody {
        // 以下放到request body
        // ###app相关信息
        public String appid;
        //app版本
        public String appv;
        // native or h5
        public String appm;
        //分发渠道:appstore,wandoujia等
        public String appch;

        // ###设备相关信息
        public String did;// 设备id
        public String dbr; // 设备品牌
        public String dmd; // 设备型号
        public String dos; // 设备os
        public String dscr; // 设备屏幕大小
        public String dnet; // 设备网络类型


        // ###地理信息
        //public String area; // province_city_zone_addrid_projid,如100_12_98_55666667_8877888,没有设置0
        public double lng; // 经度
        public double lat; // 纬度
        // ###重要字段
        //public String body; // json串,作为参数,加密
        public String ts; // 时间戳,全数字
        public String token; // token
        public String sign; // 签名

        public RequestBody() {
            appid = LxConstants.HTTP.APP_ID;

            appv = DeviceUtils.getVersionName();//"2.1";
            appm = "native";
            appch = UmengUtils.getChannel();
            //area = "0_0_0_0_0";
            //ts = "1490256381237";
            did = DeviceUtils.getDeviceId();//设备id
            dbr = DeviceUtils.getDeviceName();//设备品牌
            dmd = DeviceUtils.getModel();//设备型号
            //dos = DeviceUtils.getDeviceId() // 设备os
            int[] screenSize = ScreenUtil.getScreenSize(App.getApp());
            dscr = new StringBuilder().append("width=").append(String.valueOf(screenSize[0]))
                    .append(",height=").append(String.valueOf(screenSize[1])).toString();
            dnet = NetworkUtils.getNetworkType().name();
            lng = LocationUtils.getLongitude();
            lat = LocationUtils.getLatitude();
        }
    }

    public static BaseRequestBean getInstance() {
        if (sInstance == null) {
            sInstance = new BaseRequestBean();
        }
        sInstance.mRequestBody.token = LxSPUtils.getToken();//token有过期时间
        sInstance.mRequestBody.ts = String.valueOf(System.currentTimeMillis());
        return sInstance;
    }

    public String toJson(Object bean) {
        return new Gson().toJson(bean);
    }

    public Map<String, String> toMap(String json) {
        return new Gson().fromJson(json, HashMap.class);
    }

    /**
     * 转换为加密的Map
     *
     * @param bean
     * @return
     */
    public Map<String, String> toBuildMap(Object bean) {
        Map<String, String> paramMap = toMap(bean);
        paramMap = SignUtils.buildRequestPara(paramMap);
        return paramMap;
    }


    public Map<String, String> toMap(Object bean) {
        String body = null;
        try {
            body = DesUtils.encrypt(
                    URLEncoder.encode(toJson(bean), "UTF-8")
                    , LxConstants.HTTP.VSTONE_DES);

        } catch (Throwable e) {
            L.e(e);
        }
        Map<String, String> map = toMap(toJson(getRequestBody()));
        map.put("body", body);
        return map;
    }

    public RequestBody getRequestBody() {
        return mRequestBody;
    }
}
