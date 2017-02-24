package com.kscf.app.android.presenter;

import com.kscf.app.android.app.Apis;
import com.kscf.app.android.base.RxPresenter;
import com.kscf.app.android.model.bean.HomeFundSelectedBannerBean;
import com.kscf.app.android.model.http.HttpMethod;
import com.kscf.app.android.model.http.HttpSubscriber;
import com.kscf.app.android.model.http.NetCount;
import com.kscf.app.android.model.http.RetrofitHelper;
import com.kscf.app.android.presenter.contract.HomeFundGroupContract;
import com.framework.util.L;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

/**
 * 基金组合Presenter
 * Created by luoyl on 2016/12/26.
 */

public class HomeFundGroupPresenter extends RxPresenter<HomeFundGroupContract.View> implements HomeFundGroupContract.Presenter {

    @Inject
    public HomeFundGroupPresenter(RetrofitHelper retrofitHelper) {
        mRetrofitHelper = retrofitHelper;
    }

    @Override
    public void start() {
        httpBanner();
    }

    @Override
    public void httpBanner() {
        HttpSubscriber<List<HomeFundSelectedBannerBean>> httpSubscriber = new HttpSubscriber<List<HomeFundSelectedBannerBean>>() {
            @Override
            public void onSuccess(List<HomeFundSelectedBannerBean> bannerBean) {
                L.i("bannerBean onSuccess: " + bannerBean);
                mView.showBanner(bannerBean);
                //mView.showContent(mainBean);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }
        };

        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("type", "1");
        addSubscriber(mRetrofitHelper.sendNet(HttpMethod.GET,Apis.api_fund_selected_banner, null, paramMap, httpSubscriber));
    }
}
