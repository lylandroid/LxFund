package com.kscf.app.android.presenter;

import com.kscf.app.android.base.RxPresenter;
import com.kscf.app.android.model.http.RetrofitHelper;
import com.kscf.app.android.presenter.contract.HomeFundMyAccountContract;

import javax.inject.Inject;

/**
 * 我的基金Presenter
 * Created by luoyl on 2016/12/26.
 */

public class HomeFundMyAccountPresenter extends RxPresenter<HomeFundMyAccountContract.View> implements HomeFundMyAccountContract.Presenter {

    @Inject
    public HomeFundMyAccountPresenter(RetrofitHelper retrofitHelper) {
        mRetrofitHelper = retrofitHelper;
    }

    @Override
    public void start() {
        //httpBanner();
    }

//    @Override
//    public void httpBanner() {
//        HttpSubscriber<List<HomeFundSelectedBannerBean>> httpSubscriber = new HttpSubscriber<List<HomeFundSelectedBannerBean>>() {
//            @Override
//            public void onSuccess(List<HomeFundSelectedBannerBean> bannerBean) {
//                L.i("bannerBean onSuccess: " + bannerBean);
//                mView.showBanner(bannerBean);
//                //mView.showContent(mainBean);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                super.onError(e);
//            }
//        };
//
//        Map<String,Object> paramMap = new HashMap<>();
//        paramMap.put("type", "1");
//        addSubscriber(mRetrofitHelper.getAsync(Apis.api_fund_selected_banner, null, paramMap, httpSubscriber));
//    }
}
