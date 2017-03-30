package com.framework.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import com.framework.app.CommApp;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.StringReader;
import java.util.UUID;

/**
 * author: luoyl
 * time  : 2017/3/25
 * desc  : 设备相关的工具类
 */
public class DeviceUtils {

    private DeviceUtils() {
        throw new UnsupportedOperationException("u can't fuck me...");
    }

    /**
     * 获取设备MAC地址
     * <p>需添加权限 android.permission.ACCESS_WIFI_STATE</p>
     *
     * @param context 上下文
     * @return MAC地址
     */
    public static String getMacAddress(Context context) {
        WifiManager wifi = (WifiManager) context
                .getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();
        String macAddress = info.getMacAddress().replace(":", "");
        return macAddress == null ? "" : macAddress;
    }

    /**
     * 获取设备MAC地址
     * <p>需添加权限 android.permission.ACCESS_WIFI_STATE</p>
     *
     * @return MAC地址
     */
    public static String getMacAddress() {
        String macAddress = null;
        try {
            Process pp = Runtime.getRuntime().exec("cat/sys/class/net/wlan0/address");
            InputStreamReader ir = new InputStreamReader(pp.getInputStream());
            LineNumberReader reader = new LineNumberReader(ir);
            macAddress = reader.readLine().replace(":", "");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return macAddress == null ? "" : macAddress;
    }

    /**
     * 获取设备厂商，如Xiaomi
     *
     * @return 设备厂商
     */
    public static String getManufacturer() {
        String MANUFACTURER = Build.MANUFACTURER;
        return MANUFACTURER;
    }

    /**
     * 获取设备型号，如MI2SC
     *
     * @return 设备型号
     */
    public static String getModel() {
        String model = Build.MODEL;
        if (model != null) {
            model = model.trim().replaceAll("\\s*", "");
        } else {
            model = "";
        }
        return model;
    }

    /**
     * 获取设备id
     *
     * @return
     */
    public static String getDeviceId() {
        TelephonyManager tm = (TelephonyManager) CommApp.getApp().getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getDeviceId();
    }

    /**
     * 获取设备品牌
     *
     * @return
     */
    public static String getDeviceName() {
        return android.os.Build.BRAND;
    }

    /**
     * 设备的唯一标识。由设备的多个信息拼接合成。
     *
     * @return
     */
    public static String getDeviceOnlyIdentification() {
        return android.os.Build.FINGERPRINT;
    }

    public static String getSystemVersionName() {
        return android.os.Build.VERSION.RELEASE;
    }


    /**
     * TODO 序列号
     * 从Android 2.3 (“Gingerbread”)开始可用，可以通过android.os.Build.SERIAL获取，对于没有通话功能的设备，它会
     * 返回一个唯一的device ID
     *
     * @return
     */
    public static String getSerial() {
        try {
            String str = android.os.Build.class.getField("SERIAL").get(null).toString();
            return str;
        } catch (IllegalAccessException | IllegalArgumentException
                | NoSuchFieldException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getSimSerialNumber() {
        android.telephony.TelephonyManager tm = (android.telephony.TelephonyManager) CommApp.getApp().getSystemService(Context.TELEPHONY_SERVICE);
        String simSerialNum = tm.getSimSerialNumber();
        if (TextUtils.isEmpty(simSerialNum)) {
            simSerialNum = getSerial();
        }
        return simSerialNum;
    }

    public static String getUUID() {
        return UUID.fromString("LS_ANDROID_UUID").toString();
    }


    /**
     * 获取设备的IMEI
     * IMEI
     * 方式：TelephonyManager.getDeviceId():
     * 问题
     * 范围：网上说“只能支持拥有通话功能的设备，对于平板不可以”，但是我测试了型号FDR-A01w平板确实拿到的是null,
     * 而 型号S7-601的平板却能拿到。
     * 持久性：返厂，数据擦除的时候不彻底，保留了原来的标识。
     * 权限：需要权限：android.permission.READ_PHONE_STATE
     * bug: 有些厂家的实现有bug，返回一些不可用的数据
     *
     * @return
     */
    public String getLocalIMEI(Context context) {
        TelephonyManager tm = null;
        try {
            tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            if (null != tm) {
                return tm.getDeviceId();
            }
        } catch (Exception ex) {
            L.e(ex);
        } finally {
            tm = null;
        }
        return null;
    }


    public static String getVersionName() {
        // 获取packagemanager的实例
        PackageManager packageManager = CommApp.getApp().getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = null;
        try {
            packInfo = packageManager.getPackageInfo(CommApp.getApp().getPackageName(), 0);
            String version = packInfo.versionName;
            return version;
        } catch (PackageManager.NameNotFoundException e) {
            L.e(e);
        }
        return "";
    }
}