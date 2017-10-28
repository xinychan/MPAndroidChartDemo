package com.ubpost.mpandroidchartdemo;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;

import static com.ubpost.mpandroidchartdemo.R.id.btn_Btn09Activity_clear;
import static com.ubpost.mpandroidchartdemo.R.id.btn_Btn09Activity_refresh;

/**
 * 条形图 BarChart 的使用
 */
public class Btn02Activity extends AppCompatActivity {

    private Btn02Activity activity;
    private BarChart barChart;
    private Button btn_Btn02Activity_refresh;
    private Button btn_Btn02Activity_clear;
    private CustomMarkerView customMarkerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_btn02);
        activity = this;
        barChart = (BarChart) findViewById(R.id.barChart_Btn02Activity_show);
        btn_Btn02Activity_refresh = (Button) findViewById(R.id.btn_Btn02Activity_refresh);
        btn_Btn02Activity_clear = (Button) findViewById(R.id.btn_Btn02Activity_clear);
        customMarkerView = new CustomMarkerView(activity,R.layout.custom_marker_view_layout);
//        lineChart.setDrawMarkers(true);//只要设置了setMarker，则认为开启了setDrawMarkers
        customMarkerView.setChartView(barChart);//该代码没发现实际作用
        barChart.setMarker(customMarkerView);//设置MarkerView，点击节点时展示
        setChartOption();
        btn_Btn02Activity_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setData();
            }
        });
        btn_Btn02Activity_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearData();
            }
        });
    }

    private void setChartOption() {
        //设置背景颜色，将覆盖整个图表视图
        barChart.setBackgroundColor(ContextCompat.getColor(activity, android.R.color.holo_green_light));
        //设置统计图标注，会显示在图表的右下角
        barChart.setContentDescription("条形图示例");
        //图标无数据时的展示
        barChart.setNoDataText("条形图无数据");
        barChart.setNoDataTextColor(ContextCompat.getColor(activity, R.color.colorPrimary));
        //如果启用，chart 绘图区后面的背景矩形将绘制
        barChart.setDrawGridBackground(false);
        barChart.setGridBackgroundColor(ContextCompat.getColor(activity, android.R.color.white));
        //启用/禁用绘制图表边框
        barChart.setDrawBorders(true);
        barChart.setBorderWidth(2f);
        barChart.setBorderColor(ContextCompat.getColor(activity, android.R.color.holo_orange_light));

        //坐标轴Axis；x轴 只有一个，而y轴分为左右两条轴线
        //GridLine：背景中每一列与X轴垂直的线；AxisLine : 与X轴重合的线；Y轴中同理
        XAxis xAxis = barChart.getXAxis();
        YAxis yAxisLeft = barChart.getAxisLeft();
        YAxis yAxisRight = barChart.getAxisRight();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //如果不想要出现任何 X 或 Y 轴的东西，可以 setEnabled(false)来关闭轴线
//        xAxis.setEnabled(false);
//        yAxisLeft.setEnabled(false);
//        yAxisRight.setEnabled(false);

        setData();

    }

    /**
     * 设置数据
     */
    private void setData() {

        //在清除了列表后，不显示MarkerView了，这里要再次开启
        barChart.setDrawMarkers(true);

        ArrayList<BarEntry> list1 = new ArrayList<>(50);
        for (int i = 0; i < 30; i++) {
            list1.add(new BarEntry(i, i));
        }

        ArrayList<BarEntry> list2 = new ArrayList<>(50);
        for (int i = 0; i < 30; i++) {
            float y = (float) (Math.random() * 30);
            list2.add(new BarEntry(i, y));
        }

        ArrayList<BarEntry> list3 = new ArrayList<>(50);
        for (int i = 0; i < 30; i++) {
            float y = (float) (Math.random() * 30);
            list3.add(new BarEntry(i, y));
        }

        BarDataSet set1;
        BarDataSet set2;

        if (barChart.getData() != null &&
                barChart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) barChart.getData().getDataSetByIndex(0);
            set2 = (BarDataSet) barChart.getData().getDataSetByIndex(1);
            set1.setValues(list2);
            set2.setValues(list3);
            barChart.getData().notifyDataChanged();
            barChart.notifyDataSetChanged();
            barChart.invalidate();
        } else {
            set1 = new BarDataSet(list1, "The year 2017");
            set2 = new BarDataSet(list2, "The year 2018");

//            set1.setDrawIcons(false);
//            set1.setColors(ColorTemplate.MATERIAL_COLORS);//多彩颜色
            set1.setColors(ContextCompat.getColor(activity, android.R.color.holo_red_light));
            set2.setColors(ContextCompat.getColor(activity, android.R.color.holo_blue_bright));

            //设置 dataSets 数据源
            ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
            dataSets.add(set1);
            dataSets.add(set2);

            //设置 BarData 以及 Bar提示文字的显示
            BarData data = new BarData(dataSets);
            data.setDrawValues(true);
            data.setValueTextSize(10f);
            data.setValueTextColor(ContextCompat.getColor(activity, android.R.color.holo_red_light));
            data.setBarWidth(1f);//条形的宽度
            //setData
            barChart.setData(data);
            barChart.invalidate();
        }

    }

    /**
     * 清空数据
     */
    private void clearData() {
        //清空数据
        //清除列表时，要关闭MarkerView的显示，否则MarkerView中要显示的数据为null，空指针异常
        barChart.setDrawMarkers(false);
        barChart.getData().clearValues();//内部执行了notifyDataChanged
        barChart.notifyDataSetChanged();
        barChart.invalidate();
    }

}
