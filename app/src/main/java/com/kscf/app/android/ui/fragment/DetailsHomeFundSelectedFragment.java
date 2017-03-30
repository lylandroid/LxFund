package com.kscf.app.android.ui.fragment;

import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.widget.RadioGroup;

import com.framework.base.adapter.DataBindingRecyclerAdapter;
import com.framework.util.ToastUtils;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
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
 * 基金精选详情Fragment
 * Created by luoyl on 2017/1/12.
 */

public class DetailsHomeFundSelectedFragment extends BaseFragment<FragmentDetailsHomeFundSelectedBinding, DetailsHomeFundSelectedFragmentPresenter>
        implements DetailsHomeFundSelectedFragmentContract.View, View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    protected String[] mMonths = new String[]{
            "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Okt", "Nov", "Dec"
    };

    protected String[] mParties = new String[]{
            "Party A", "Party B", "Party C", "Party D", "Party E", "Party F", "Party G", "Party H",
            "Party I", "Party J", "Party K", "Party L", "Party M", "Party N", "Party O", "Party P",
            "Party Q", "Party R", "Party S", "Party T", "Party U", "Party V", "Party W", "Party X",
            "Party Y", "Party Z"
    };

    private String[] mOpenAndUnOpen = new String[2];

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
        mLoadingPage.showPage(LoadingPage.STATE_SUCCEED);
    }

    @Override
    public void initListener() {
        mDataBinding.includeRadioGroup.radioGroup.setOnCheckedChangeListener(this);
        mDataBinding.itemOpen.setOnClickListener(this);

    }

    @Override
    public void initEventAndData() {
        mOpenAndUnOpen[0] = getResources().getString(R.string.txt_open);
        mOpenAndUnOpen[1] = getResources().getString(R.string.txt_un_open);
        /*折线图*/
        brokenLineChartSetting();
        brokenLineChartData();
         /*业绩排名数据*/
        initPerformanceRankingData();

        /*饼图数据*/
        pieChartSetting();

        List<Integer> datas = new ArrayList<>();
        datas.add(1);
        datas.add(1);
        datas.add(1);
        datas.add(1);
        datas.add(1);
        datas.add(1);

        /*股票成分*/
        mDataBinding.includeRecycler.recyclerViewStock.setAdapter(new DataBindingRecyclerAdapter(mDataBinding.includeRecycler.recyclerViewStock
                , R.layout.item_recycler_details_fund_selected_stock
                , datas));
        /*债券成分*/
        mDataBinding.includeRecycler.recyclerViewBond.setAdapter(new DataBindingRecyclerAdapter(mDataBinding.includeRecycler.recyclerViewBond
                , R.layout.item_recycler_details_fund_selected_stock
                , datas));
        /*重仓债券*/
        mDataBinding.includeRecycler.recyclerViewHolding.setAdapter(new DataBindingRecyclerAdapter(mDataBinding.includeRecycler.recyclerViewHolding
                , R.layout.item_recycler_details_fund_selected_hanking
                , datas));


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.item_open:
                boolean isSelected = !mDataBinding.tvOpen.isSelected();
                mDataBinding.tvOpen.setSelected(isSelected);
                mDataBinding.tvOpen.setText(isSelected ? mOpenAndUnOpen[1] : mOpenAndUnOpen[0]);
                break;
        }
    }

    private void selectOnClickRadioButton(int checkedId, boolean isSelected) {
        switch (checkedId) {
            case R.id.rb_near_7_day:
                mDataBinding.includeRadioGroup.rbNear7Day.setSelected(isSelected);
                break;
            case R.id.rb_near_3_month:
                mDataBinding.includeRadioGroup.rbNear3Month.setSelected(isSelected);
                break;
            case R.id.rb_near_1_month:
                mDataBinding.includeRadioGroup.rbNear1Month.setSelected(isSelected);
                break;
            case R.id.rb_near_1_year:
                mDataBinding.includeRadioGroup.rbNear1Year.setSelected(isSelected);
                break;
            case R.id.rb_today:
                mDataBinding.includeRadioGroup.rbToday.setSelected(isSelected);
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


    /**
     * 初始化RecyclerView业绩排名数据
     */
    private void initPerformanceRankingData() {
        List<String> datas = new ArrayList<>();
        datas.add("1");
        datas.add("1");
        datas.add("1");
        mDataBinding.recyclerViewPerformanceRanking.setAdapter(
                new DataBindingRecyclerAdapter(mDataBinding.recyclerViewPerformanceRanking
                        , R.layout.item_recycler_details_fund_selected_performance_ranking
                        , datas));
    }

    /**
     * 饼状图设置
     */
    private void pieChartSetting() {
        PieChart pieChart = mDataBinding.pieChart;
        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5, 10, 5, 5);

        pieChart.setDragDecelerationFrictionCoef(0.95f);

        //pieChart.setCenterTextTypeface(mTfLight);
        //pieChart.setCenterText(generateCenterSpannableText());

        pieChart.setDrawHoleEnabled(false);
        pieChart.setHoleColor(Color.WHITE);


        pieChart.setTransparentCircleColor(Color.WHITE);
        pieChart.setTransparentCircleAlpha(10);
        //pieChartrt.setTransparentCircleAlpha(10);

        //pieChartrt.setHoleRadius(58f);
        //pieChartrt.setTransparentCircleRadius(61f);
        pieChart.setHoleRadius(8f);
        pieChart.setTransparentCircleRadius(8f);
        //设置饼状图中间的文字是否显示
        pieChart.setDrawEntryLabels(false);

        pieChart.setRotationAngle(0);
        //pieChartble rotation of the chart by touch
        pieChart.setRotationEnabled(true);
        pieChart.setHighlightPerTapEnabled(true);


        // mChart.setUnit(" €");
        // mChart.setDrawUnitsInChart(true);

        // add a selection listener
        //pieChart.setOnChartValueSelectedListener(this);

        Legend mLegend = pieChart.getLegend();  //设置比例图
        //mLegend.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);  //最右边显示
        mLegend.setOrientation(Legend.LegendOrientation.VERTICAL);//饼状图和文字描述方向
        mLegend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);//文字描述方向
        //mLegend.setDirection(Legend.LegendDirection.RIGHT_TO_LEFT);//文字和点方向
        mLegend.setVerticalAlignment(Legend.LegendVerticalAlignment.CENTER);//文字描述是否居中

        setPieChartData(4, 100);

        //mChart.animateY(0, Easing.EasingOption.EaseInOutQuad);
        pieChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);
    }

    private void setPieChartData(int count, float range) {
        PieChart pieChart = mDataBinding.pieChart;
        float mult = range;

        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
        for (int i = 0; i < count; i++) {
            entries.add(new PieEntry((float) ((Math.random() * mult) + mult / 5), mParties[i % mParties.length]));
        }

        PieDataSet dataSet = new PieDataSet(entries, "Election Results");
        //设置个饼状图之间的距离
        dataSet.setSliceSpace(1f);
        dataSet.setSelectionShift(5f);

        // add a lot of colors

        ArrayList<Integer> colors = new ArrayList<Integer>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(colors);
        //dataSet.setSelectionShift(0f);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(12f);
        data.setValueTextColor(Color.WHITE);
        data.setValueTypeface(Typeface.DEFAULT);
        pieChart.setData(data);

        // undo all highlights
        pieChart.highlightValues(null);

        pieChart.invalidate();
    }


    /**
     * 折线设置
     */
    private void brokenLineChartSetting() {
        LineChartHelper.setup(mDataBinding.lineChart);

        mDataBinding.lineChart.setExtraBottomOffset(5f);


        mDataBinding.lineChart.getAxisLeft().setDrawGridLines(false);
        mDataBinding.lineChart.getXAxis().setDrawGridLines(false);
        mDataBinding.lineChart.getXAxis().setLabelCount(5);
        mDataBinding.lineChart.getXAxis().setGranularity(1f);

        //设置图表内格子外的边框是否显示
        mDataBinding.lineChart.setDrawBorders(false);

    }

    /**
     * 折线图数据
     */
    public void brokenLineChartData() {
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
    public void free() {
        mDataBinding.lineChart.clear();
        mDataBinding.pieChart.clear();
        super.free();
    }
}
