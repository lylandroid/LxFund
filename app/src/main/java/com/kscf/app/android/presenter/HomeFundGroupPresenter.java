package com.kscf.app.android.presenter;

import com.framework.http.HttpSubscriber;
import com.framework.http.RetrofitHelper;
import com.framework.util.L;
import com.kscf.app.android.base.RxPresenter;
import com.kscf.app.android.model.bean.HomeFundSelectedBannerBean;
import com.kscf.app.android.presenter.contract.HomeFundGroupContract;

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
    public HomeFundGroupPresenter() {
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
       // addSubscriber(mRetrofitHelper.sendNet(HttpMethod.GET,Apis.api_fund_selected_banner, null, paramMap, httpSubscriber));
    }
}
