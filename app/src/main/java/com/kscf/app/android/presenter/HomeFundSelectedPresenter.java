package com.kscf.app.android.presenter;

import com.kscf.app.android.R;
import com.kscf.app.android.app.Apis;
import com.kscf.app.android.base.RxPresenter;
import com.kscf.app.android.model.bean.HomeFundSelectedBannerBean;
import com.kscf.app.android.model.http.HttpSubscriber;
import com.kscf.app.android.model.http.RetrofitHelper;
import com.kscf.app.android.presenter.contract.HomeFundSelectedContract;
import com.framework.util.L;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

/**
 * 基金精选Presenter
 * Created by luoyl on 2016/12/26.
 */

public class HomeFundSelectedPresenter extends RxPresenter<HomeFundSelectedContract.View> implements HomeFundSelectedContract.Presenter {

    @Inject
    public HomeFundSelectedPresenter(RetrofitHelper retrofitHelper) {
        mRetrofitHelper = retrofitHelper;
    }

    @Override
    public void start() {
        httpBanner();
    }

    @Override
    public void httpBanner() {
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
        /*List<HomeFundSelectedBannerBean> bannerBeans = new ArrayList<>(1);
        HomeFundSelectedBannerBean bannerBean = new HomeFundSelectedBannerBean();
        bannerBean.imgResId = R.drawable.index_banner;
        bannerBeans.add(bannerBean);
        mView.showBanner(bannerBeans);*/
    }
}
