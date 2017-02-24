package com.kscf.app.android.base;

import com.kscf.app.android.base.view.BaseView;

/**
 * Created by luoyl on 2016/12/3.
 */

public interface BasePresenter<V extends BaseView> {

    void attachView(V v);


    void detachView();

    void start();
}
