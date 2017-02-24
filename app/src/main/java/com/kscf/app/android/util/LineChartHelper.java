package com.kscf.app.android.util;

import android.graphics.Color;
import android.graphics.Typeface;

import com.github.mikephil.charting.charts.BarLineChartBase;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by luoyl on 2017/2/16.
 */

public class LineChartHelper {

    public static Typeface setup(Chart<?> chart) {

        //mTf = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");
        Typeface mTf = Typeface.SERIF;

        // no description text
        //chart.getDescription().setEnabled(false);

        // enable touch gestures
        chart.setTouchEnabled(true);

        if (chart instanceof BarLineChartBase) {

            BarLineChartBase mChart = (BarLineChartBase) chart;

            mChart.setDrawGridBackground(false);
            mChart.setDescription(null);

            // enable scaling and dragging
            mChart.setDragEnabled(true);
            //mChart.setScaleEnabled(true);
            mChart.setScaleXEnabled(true);
            mChart.setScaleYEnabled(false);

            // if disabled, scaling can be done on x- and y-axis separately
            mChart.setPinchZoom(false);

            YAxis leftAxis = mChart.getAxisLeft();
            leftAxis.removeAllLimitLines(); // reset all limit lines to avoid overlapping lines
            leftAxis.setTypeface(mTf);
            leftAxis.setTextSize(8f);
            leftAxis.setTextColor(Color.DKGRAY);
            leftAxis.setValueFormatter(new PercentFormatter());

            XAxis xAxis = mChart.getXAxis();
            xAxis.setTypeface(mTf);
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            xAxis.setTextSize(8f);
            xAxis.setTextColor(Color.DKGRAY);

            xAxis.setValueFormatter(new IAxisValueFormatter() {

                private SimpleDateFormat mFormat = new SimpleDateFormat(/*"dd MMM HH:mm"*/"yyyy-MM-dd");

                @Override
                public String getFormattedValue(float value, AxisBase axis) {
                    return mFormat.format(new Date((long) value));
                }
            });

            mChart.getAxisRight().setEnabled(false);
        }
        return mTf;
    }
}
