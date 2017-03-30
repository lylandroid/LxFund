package com.kscf.app.android.presenter;

import com.framework.http.RetrofitHelper;
import com.kscf.app.android.base.RxPresenter;
import com.kscf.app.android.presenter.contract.ContactAddressFragmentContract;

import javax.inject.Inject;

/**
 * Created by luoyl on 2017/1/24.
 * 联系地址Presenter
 */

public class ContactAddressFragmentPresenter extends RxPresenter<ContactAddressFragmentContract.View> implements ContactAddressFragmentContract.Presenter {

    @Inject
    public ContactAddressFragmentPresenter() {
    }

    @Override
    public void start() {
    }


}
