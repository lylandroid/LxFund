package com.kscf.app.android.presenter;

import com.framework.http.RetrofitHelper;
import com.kscf.app.android.base.RxPresenter;
import com.kscf.app.android.presenter.contract.InviteFriendsFragmentContract;

import javax.inject.Inject;

/**
 * Created by luoyl on 2017/3/22.
 * 邀请好友Presenter
 */

public class InviteFriendsFragmentPresenter extends RxPresenter<InviteFriendsFragmentContract.View> implements InviteFriendsFragmentContract.Presenter {

    @Inject
    public InviteFriendsFragmentPresenter() {
    }

    @Override
    public void start() {
    }

}
