package com.kscf.app.android.presenter;

import com.kscf.app.android.base.RxPresenter;
import com.kscf.app.android.presenter.contract.IWantFixedInvestmentFragmentContract;

import javax.inject.Inject;

/**
 * Created by luoyl on 2017/3/27.
 * 我的定投Presenter
 */

public class IWantFixedInvestmentFragmentPresenter extends RxPresenter<IWantFixedInvestmentFragmentContract.View> implements IWantFixedInvestmentFragmentContract.Presenter {

    @Inject
    public IWantFixedInvestmentFragmentPresenter() {
    }

    @Override
    public void start() {
    }

}
