package com.kscf.app.android.presenter;

import com.kscf.app.android.app.Apis;
import com.kscf.app.android.base.RxPresenter;
import com.kscf.app.android.model.bean.MainBean;
import com.kscf.app.android.model.http.HttpMethod;
import com.kscf.app.android.model.http.HttpSubscriber;
import com.kscf.app.android.model.http.RetrofitHelper;
import com.kscf.app.android.presenter.contract.MainContract;
import com.framework.util.L;

import javax.inject.Inject;

/**
 * Created by luoyl on 2016/12/26.
 */

public class MainPresenter extends RxPresenter<MainContract.View> implements MainContract.Presenter {

    @Inject
    public MainPresenter(RetrofitHelper retrofitHelper) {
        mRetrofitHelper = retrofitHelper;
    }

    @Override
    public void start() {
        HttpSubscriber<MainBean> httpSubscriber = new HttpSubscriber<MainBean>() {
            @Override
            public void onSuccess(MainBean mainBean) {
                L.i("MainPresenter onSuccess: " + mainBean);
                mView.showContent(mainBean);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }
        };

        addSubscriber(mRetrofitHelper.sendNet(HttpMethod.GET,Apis.testApi, null, null, httpSubscriber));
    }
}
