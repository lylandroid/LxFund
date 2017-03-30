package com.kscf.app.android.presenter;

import com.kscf.app.android.base.RxPresenter;
import com.kscf.app.android.presenter.contract.SaveEmailFragmentContract;

import javax.inject.Inject;

/**
 * 保存电子邮箱Presenter
 * Created by luoyl on 2017/3/27.
 */

public class SaveEmailFragmentPresenter extends RxPresenter<SaveEmailFragmentContract.View> implements SaveEmailFragmentContract.Presenter {

    @Inject
    public SaveEmailFragmentPresenter() {
    }

    @Override
    public void start() {
    }

}
