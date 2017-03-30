package com.kscf.app.android.ui.fragment.setting;

import android.view.View;

import com.kscf.app.android.R;
import com.kscf.app.android.base.BaseFragment;
import com.kscf.app.android.databinding.FragmentMoreInformationBinding;
import com.kscf.app.android.presenter.MoreInformationFragmentPresenter;
import com.kscf.app.android.presenter.contract.MoreInformationFragmentContract;
import com.kscf.app.android.ui.activity.DetailsActivity;
import com.kscf.app.android.widget.LoadingPage;

/**
 * 更多信息Fragment
 * Created by luoyl on 2017/1/12.
 */

public class MoreInformationFragment extends BaseFragment<FragmentMoreInformationBinding, MoreInformationFragmentPresenter> implements MoreInformationFragmentContract.View, View.OnClickListener {

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_more_information;
    }


    public static MoreInformationFragment newInstance() {
        return new MoreInformationFragment();
    }

    @Override
    public void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    public void initView() {
        mLoadingPage.showPage(LoadingPage.STATE_SUCCEED);
    }

    @Override
    public void initListener() {
        mDataBinding.itemMail.setOnClickListener(this);

    }

    @Override
    public void initEventAndData() {


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.item_mail:
                mActivity.addFragmentToActivity(mActivity,DetailsActivity.class, SaveEmailFragment.class.getName(), true);
                break;
        }
    }

    /*private void toAccountSettings() {
        ((LoginActivity) mActivity).showHideFragment(AccountSettingsFragment.newInstance(), null);
    }*/

    @Override
    public void free() {
        super.free();
    }


}
