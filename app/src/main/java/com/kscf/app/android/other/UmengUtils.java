package com.kscf.app.android.other;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;

import com.framework.util.L;
import com.framework.util.ToastUtils;
import com.kscf.app.android.app.App;
import com.umeng.analytics.MobclickAgent;

/**
 * Umeng统计
 * Created by luoyl on 2017/1/13.
 */

public class UmengUtils {

    private static String sChannel = "Android";

    public static void init(Application application) {
        //场景类型设置接口,E_UM_NORMAL普通统计场景类型
        MobclickAgent.setScenarioType(application, MobclickAgent.EScenarioType.E_UM_NORMAL);
        String channel = getChannel();
        if (L.isDebug) {
            ToastUtils.show("channel: " + channel);
        }
    }

    public static String getChannel() {
        if (!"Android".equals(sChannel)) {
            return sChannel;
        }
        try {
            Context context = App.getInstance();
            ApplicationInfo appInfo = context.getPackageManager()
                    .getApplicationInfo(context.getPackageName(),
                            PackageManager.GET_META_DATA);
            String channel = appInfo.metaData.getString("UMENG_CHANNEL");
            sChannel = TextUtils.isEmpty(channel) ? sChannel : channel;
            return sChannel;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "Android";
    }
}
