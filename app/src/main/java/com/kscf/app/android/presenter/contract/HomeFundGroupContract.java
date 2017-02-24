package com.kscf.app.android.presenter.contract;

import com.kscf.app.android.base.BasePresenter;
import com.kscf.app.android.base.view.BaseView;
import com.kscf.app.android.model.bean.HomeFundSelectedBannerBean;

import java.util.List;

/**
 * 基金组合接口
 * Created by luoyl on 2016/12/26.
 */

public interface HomeFundGroupContract {

    interface View extends BaseView {
        void showBanner(List<HomeFundSelectedBannerBean> bannerBean);

    }

    interface Presenter extends BasePresenter<View> {
        void httpBanner();
    }
}
