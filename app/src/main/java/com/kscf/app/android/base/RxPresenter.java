package com.kscf.app.android.base;

import com.kscf.app.android.base.view.BaseView;
import com.kscf.app.android.model.http.HttpSubscriber;
import com.kscf.app.android.model.http.RetrofitHelper;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * MVP P层RxJava封装，
 * Created by luoyl on 2016/12/4.
 */

public abstract class RxPresenter<V extends BaseView> implements BasePresenter<V> {

    protected V mView;

    protected CompositeSubscription mCompositeSubscription;

    protected HttpSubscriber mSubscriber;

    protected RetrofitHelper mRetrofitHelper;

    protected boolean mIsDestroy;


    protected void addSubscriber(Subscription subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }


    @Override
    public void attachView(V v) {
        mView = v;
    }

    @Override
    public void detachView() {
        mIsDestroy = true;
        mView = null;
        if (mCompositeSubscription != null) {
            //mCompositeSubscription.unsubscribe();
            mCompositeSubscription.clear();
        }
        mCompositeSubscription = null;
        //mRetrofitHelper = null;
        mSubscriber = null;
    }

    public void onHttpErr(int reqCode, int respCode, String errMsg, Throwable e) {
        if (mView != null) {
            mView.onHttpErr(reqCode, respCode, errMsg, e);
        }

    }
}
