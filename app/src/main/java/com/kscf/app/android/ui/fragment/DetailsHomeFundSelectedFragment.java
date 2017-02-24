package com.kscf.app.android.ui.fragment;

import android.graphics.Paint;
import android.view.View;
import android.widget.RadioGroup;

import com.framework.util.ToastUtils;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.kscf.app.android.R;
import com.kscf.app.android.base.BaseFragment;
import com.kscf.app.android.databinding.FragmentDetailsHomeFundSelectedBinding;
import com.kscf.app.android.presenter.DetailsHomeFundSelectedFragmentPresenter;
import com.kscf.app.android.presenter.contract.DetailsHomeFundSelectedFragmentContract;
import com.kscf.app.android.util.LineChartHelper;
import com.kscf.app.android.widget.LoadingPage;

import java.util.ArrayList;
import java.util.List;

/**
 * 消息Fragment
 * Created by luoyl on 2017/1/12.
 */

public class DetailsHomeFundSelectedFragment extends BaseFragment<FragmentDetailsHomeFundSelectedBinding, DetailsHomeFundSelectedFragmentPresenter>
        implements DetailsHomeFundSelectedFragmentContract.View, View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_details_home_fund_selected;
    }


    public static DetailsHomeFundSelectedFragment newInstance() {
        return new DetailsHomeFundSelectedFragment();
    }

    @Override
    public void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    public void initView() {
        mDataBinding.tvMoney.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        mLoadingPage.showPage(LoadingPage.STATE_SUCCEED);
    }

    @Override
    public void initListener() {
        mDataBinding.includeItem.radioGroup.setOnCheckedChangeListener(this);

    }

    @Override
    public void initEventAndData() {
        lineChartSetting();

        LineChart lineChart = mDataBinding.lineChart;

        List<Entry> valsComp1 = new ArrayList<Entry>();
        Entry c1e1 = new Entry(1484496000000f, 0f); // 0 == quarter 1
        valsComp1.add(c1e1);
        Entry c1e2 = new Entry(1484582400000f, 0.5f); // 1 == quarter 2 ...
        valsComp1.add(c1e2);
        Entry c1e3 = new Entry(1484668800000f, 1.79f); // 1 == quarter 2 ...
        valsComp1.add(c1e3);
        Entry c1e4 = new Entry(1484755200000f, 1.19f); // 1 == quarter 2 ...
        valsComp1.add(c1e4);
        Entry c1e5 = new Entry(1484841600000f, 0.69f); // 1 == quarter 2 ...
        valsComp1.add(c1e5);
        Entry c1e6 = new Entry(1485100800000f, 1.19f); // 1 == quarter 2 ...
        valsComp1.add(c1e6);
        Entry c1e7 = new Entry(1486051200000f, 1.79f); // 1 == quarter 2 ...
        valsComp1.add(c1e7);
        Entry c1e8 = new Entry(1486310400000f, 1.59f); // 1 == quarter 2 ...
        valsComp1.add(c1e8);

        // and so on ...

        LineDataSet setComp1 = new LineDataSet(valsComp1, "累计收益");
        //是否显示圆点
        setComp1.setDrawCircles(false);
        //圆角
        setComp1.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        setComp1.setAxisDependency(YAxis.AxisDependency.LEFT);

        // use the interface ILineDataSet
        List<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        dataSets.add(setComp1);

        LineData data = new LineData(dataSets);
        lineChart.setGridBackgroundColor(getResources().getColor(R.color.line_dialog_color));
        lineChart.setData(data);
        lineChart.invalidate(); // refresh


    }

    @Override
    public void onClick(View v) {
      /*  switch (v.getId()) {
            case R.id.rb_near_7_day:
            case R.id.rb_near_3_month:
            case R.id.rb_near_1_month:
            case R.id.rb_near_1_year:
            case R.id.rb_today:
                selectOnClickRadioButton(v);
                break;
        }*/
    }

    private void selectOnClickRadioButton(int checkedId, boolean isSelected) {
        switch (checkedId) {
            case R.id.rb_near_7_day:
                mDataBinding.includeItem.rbNear7Day.setSelected(isSelected);
                break;
            case R.id.rb_near_3_month:
                mDataBinding.includeItem.rbNear3Month.setSelected(isSelected);
                break;
            case R.id.rb_near_1_month:
                mDataBinding.includeItem.rbNear1Month.setSelected(isSelected);
                break;
            case R.id.rb_near_1_year:
                mDataBinding.includeItem.rbNear1Year.setSelected(isSelected);
                break;
            case R.id.rb_today:
                mDataBinding.includeItem.rbToday.setSelected(isSelected);
                break;
        }
    }

    private int mPreCheckedId = R.id.rb_near_7_day;

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        ToastUtils.show("onCheckedChanged");
        selectOnClickRadioButton(mPreCheckedId, false);
        selectOnClickRadioButton(checkedId, true);
        mPreCheckedId = checkedId;

    }

    /*private void toAccountSettings() {
        ((LoginActivity) mActivity).showHideFragment(AccountSettingsFragment.newInstance(), null);
    }*/


    public void lineChartSetting() {
        LineChartHelper.setup(mDataBinding.lineChart);

        mDataBinding.lineChart.setExtraBottomOffset(5f);


        mDataBinding.lineChart.getAxisLeft().setDrawGridLines(false);
        mDataBinding.lineChart.getXAxis().setDrawGridLines(false);
        mDataBinding.lineChart.getXAxis().setLabelCount(5);
        mDataBinding.lineChart.getXAxis().setGranularity(1f);

        //设置图表内格子外的边框是否显示
        mDataBinding.lineChart.setDrawBorders(false);
    }

}
