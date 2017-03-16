package com.kscf.app.android.other;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;

import com.framework.util.L;
import com.framework.util.ToastUtils;
import com.growingio.android.sdk.collection.Configuration;
import com.growingio.android.sdk.collection.GrowingIO;

/**
 * Created by luoyl on 2017/1/13.
 */

public class GrowingioUtils {

    private static String sChannel = "Android";

    public static void init(Application application) {
        String channel = getChannel(application);
        GrowingIO.startWithConfiguration(application, new Configuration()
                .useID()
                .trackAllFragments()
                .setChannel(channel));
        if (L.isDebug) {
            ToastUtils.show("channel: " + channel);
        }
    }

    public static String getChannel(Context context) {
        if (!"Android".equals(sChannel)) {
            return sChannel;
        }
        try {

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
