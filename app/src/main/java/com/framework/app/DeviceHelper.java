package com.framework.app;

import com.framework.util.DeviceUtils;
import com.framework.util.LocationUtils;
import com.framework.util.NetworkUtils;
import com.framework.util.ScreenUtil;

import java.util.UUID;

/**
 * Created by Administrator on 2017/3/24.
 */

public class DeviceHelper {


    public static class DeviceInfo {

        //设备ID
        public String deviceId;
        //设备品牌
        public String deviceName;
        // 纬度
        public double latitude = 0.0;
        // 经度
        public double longitude = 0.0;
        public String imei;

        public int screenWidth;
        public int screenHeight;

        //网络类型
        public String netType;
        public String uuId;

        public String appVersionName;

        public DeviceInfo() {
            deviceId = DeviceUtils.getDeviceId();
            latitude = LocationUtils.getLatitude();
            longitude = LocationUtils.getLongitude();
            screenWidth = ScreenUtil.getScreenWidth();
            screenHeight = ScreenUtil.getScreenHeight();
            netType = NetworkUtils.getNetworkType().name();
            uuId = DeviceUtils.getUUID();
            appVersionName = DeviceUtils.getVersionName();
            deviceName = DeviceUtils.getDeviceName();

        }
    }
}
