package com.kscf.app.android.util.framing;

import java.lang.ref.SoftReference;

public final class LxEventBus {

    private static SoftReference<LxEventBus> sInstance;

    private volatile com.google.common.eventbus.EventBus mEventBus;

    private LxEventBus() {
        mEventBus = new com.google.common.eventbus.EventBus();
    }

    public static LxEventBus getInstance() {
        if (sInstance == null || sInstance.get() == null) {
            sInstance = new SoftReference<LxEventBus>(new LxEventBus());
        }
        return sInstance.get();
    }


    public com.google.common.eventbus.EventBus get() {
        return mEventBus;
    }
}