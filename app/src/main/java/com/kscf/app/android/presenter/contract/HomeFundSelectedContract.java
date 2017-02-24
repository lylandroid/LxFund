package com.kscf.app.android.presenter.contract;

import com.kscf.app.android.base.BasePresenter;
import com.kscf.app.android.base.view.BaseView;
import com.kscf.app.android.model.bean.HomeFundSelectedBannerBean;
import com.kscf.app.android.model.bean.MainBean;

import java.util.List;

/**
 * 基金精选接口
 * Created by luoyl on 2016/12/26.
 */

public interface HomeFundSelectedContract {

    interface View extends BaseView {
        void showBanner(List<HomeFundSelectedBannerBean> bannerBean);

    }

    interface Presenter extends BasePresenter<View> {
        void httpBanner();
    }
}
