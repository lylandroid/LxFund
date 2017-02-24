package com.kscf.app.android.ui;

import com.kscf.app.android.R;
import com.kscf.app.android.ui.fragment.HomeFundSelectedFragment;
import com.kscf.app.android.ui.fragment.HomeFundGroupFragment;
import com.kscf.app.android.ui.fragment.HomeMyAccountFragment;

public enum HomeTabs {
    FUND_SELECTED(0, R.string.fundSelected, R.drawable.home_tabhost_selected_selector, HomeFundSelectedFragment.class),
    FUND_GROUP(1, R.string.fundGroup, R.drawable.home_tabhost_group_selector, HomeFundGroupFragment.class),
    //FUND_GROUP2(R.string.fundGroup, R.drawable.home_tabhost_group_selector, HomeFundGroupFragment.class),
    MY_ACCOUNT(2, R.string.myAccount, R.drawable.home_tabhost_my_account_selector, HomeMyAccountFragment.class);


    public int index;
    public int txtResId;
    public int iconSelectorId;
    public Class<?> fragmentClazz;


    HomeTabs(int index, int txtResId, int iconSelectorId, Class<?> fragmentClazz) {
        this.index = index;
        this.txtResId = txtResId;
        this.iconSelectorId = iconSelectorId;
        this.fragmentClazz = fragmentClazz;
    }

}