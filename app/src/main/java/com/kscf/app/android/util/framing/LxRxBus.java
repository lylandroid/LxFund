package com.kscf.app.android.util.framing;

import com.hwangjr.rxbus.Bus;

public final class LxRxBus {

    private static LxRxBus sInstance;

    private volatile Bus mBus;

    private LxRxBus() {
        mBus = new Bus();
    }

    public static LxRxBus getInstance() {
        if (sInstance == null) {
            synchronized (LxRxBus.class) {
                if (sInstance == null) {
                    sInstance = new LxRxBus();
                }
            }

        }
        return sInstance;
    }


    public Bus get() {
        return mBus;
    }

    public static void clear() {
        sInstance = null;
    }
}