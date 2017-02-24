package com.kscf.app.android.ui.fragment;

import android.view.View;

import com.framework.dialog.address.AddressPicker;
import com.framework.dialog.address.bean.City;
import com.framework.dialog.address.bean.County;
import com.framework.dialog.address.bean.Province;
import com.framework.util.L;
import com.framework.util.ToastUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kscf.app.android.R;
import com.kscf.app.android.base.BaseFragment;
import com.kscf.app.android.databinding.FragmentContactAddressBinding;
import com.kscf.app.android.presenter.ContactAddressFragmentPresenter;
import com.kscf.app.android.presenter.contract.ContactAddressFragmentContract;
import com.kscf.app.android.util.FileUtils;
import com.kscf.app.android.widget.LoadingPage;

import java.util.ArrayList;

/**
 * Created by luoyl on 2017/1/24.
 * 联系方式Fragment
 */

public class ContactAddressFragment extends BaseFragment<FragmentContactAddressBinding, ContactAddressFragmentPresenter> implements ContactAddressFragmentContract.View, View.OnClickListener, AddressPicker.OnAddressPickListener {

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_contact_address;
    }


    public static ContactAddressFragment newInstance() {
        return new ContactAddressFragment();
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
        mDataBinding.itemProvinceCityArea.setOnClickListener(this);

    }

    @Override
    public void initEventAndData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.item_province_city_area:
                showAddressDialog();
                break;
        }
    }

    public void showAddressDialog() {
        try {
            String addressJson = FileUtils.readInStreamString(mActivity.getAssets().open("city.json"));
            ArrayList<Province> provinceList = new Gson().fromJson(addressJson, new TypeToken<ArrayList<Province>>() {
            }.getType());
            AddressPicker picker = new AddressPicker(mActivity, provinceList);
            picker.setHideProvince(false);
            picker.setHideCounty(false);
            if (false) {
                picker.setColumnWeight(1 / 3.0, 2 / 3.0);//将屏幕分为3份，省级和地级的比例为1:2
            } else {
                picker.setColumnWeight(2 / 8.0, 3 / 8.0, 3 / 8.0);//省级、地级和县级的比例为2:3:3
            }
            //picker.setSelectedItem("贵州", "毕节", "纳雍");
            picker.setSelectedItem("四川", "南充", "仪陇");
            picker.setOnAddressPickListener(this);
            picker.show();
        } catch (Exception e) {

        }
    }

    /**
     * 地址dialog
     *
     * @param province the province
     * @param city     the city
     * @param county   the county ，if {@code hideCounty} is true，this is null
     */
    @Override
    public void onAddressPicked(Province province, City city, County county) {
        L.i("" + province + "　" + city + " " + county);
        ToastUtils.show("" + province + "　" + city + " " + county);
    }


    @Override
    public void free() {
        super.free();
    }


}
