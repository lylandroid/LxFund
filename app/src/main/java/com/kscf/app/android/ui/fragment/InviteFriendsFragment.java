package com.kscf.app.android.ui.fragment;

import android.view.View;

import com.framework.base.adapter.DataBindingViewPagerAdapter;
import com.kscf.app.android.R;
import com.kscf.app.android.app.App;
import com.kscf.app.android.base.BaseFragment;
import com.kscf.app.android.databinding.FragmentInviteFriendsBinding;
import com.kscf.app.android.databinding.ItemPageViewpagerFundPositionBinding;
import com.kscf.app.android.presenter.InviteFriendsFragmentPresenter;
import com.kscf.app.android.presenter.contract.InviteFriendsFragmentContract;
import com.kscf.app.android.widget.LoadingPage;

import java.util.ArrayList;
import java.util.List;

/**
 * 邀请好友Fragment
 * Created by luoyl on 2017/3/22.
 */

public class InviteFriendsFragment extends BaseFragment<FragmentInviteFriendsBinding, InviteFriendsFragmentPresenter> implements InviteFriendsFragmentContract.View, View.OnClickListener {

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_invite_friends;
    }


    public static InviteFriendsFragment newInstance() {
        return new InviteFriendsFragment();
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

    }

    @Override
    public void initEventAndData() {
        List<String> pageDatas = new ArrayList<>();
        pageDatas.add("");
        pageDatas.add("");

        List<Integer> pageResIds = new ArrayList<>();
        pageResIds.add(R.layout.item_viewpager_invite_friends);
        pageResIds.add(R.layout.item_viewpager_invite_friends_my);

        InviteFriendsPagerAdapter adapter = new InviteFriendsPagerAdapter(pageResIds, pageDatas);
        mDataBinding.viewPager.setAdapter(adapter);
        mDataBinding.tabLayout.setupWithViewPager(mDataBinding.viewPager);
    }

    @Override
    public void onClick(View v) {
        /*switch (v.getId()) {
            case R.id.iv_scan:
                startActivity(new Intent(mActivity, CaptureActivity.class));
                break;
        }*/
    }

    /*private void toAccountSettings() {
        ((LoginActivity) mActivity).showHideFragment(AccountSettingsFragment.newInstance(), null);
    }*/

    @Override
    public void free() {
        super.free();
    }

    /**
     * 泛型List<String>：viewpager中RecyclerView条目数据
     */
    class InviteFriendsPagerAdapter extends DataBindingViewPagerAdapter<ItemPageViewpagerFundPositionBinding, String> {

        public InviteFriendsPagerAdapter(List<Integer> pageResIds, List<String> pageDatas) {
            super(pageResIds, null, pageDatas);
            mTitles.add(App.getInstance().getResources().getString(R.string.txt_position));
            mTitles.add(App.getInstance().getResources().getString(R.string.txt_on_the_way));
        }


        @Override
        public void bindRealData(int position, View rootView, String s) {

        }
    }


}
