package com.ubpost.mpandroidchartdemo;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;

import java.util.ArrayList;

/**
 * 雷达/网状图 RadarChart 使用
 */
public class Btn08Activity extends AppCompatActivity {

    private Btn08Activity activity;
    private RadarChart radarChart;
    private Button btn_Btn08Activity_refresh;
    private Button btn_Btn08Activity_clear;
    private CustomMarkerView customMarkerView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_btn08);
        activity = this;
        radarChart = (RadarChart) findViewById(R.id.radarChart_Btn08Activity_show);
        btn_Btn08Activity_refresh = (Button) findViewById(R.id.btn_Btn08Activity_refresh);
        btn_Btn08Activity_clear = (Button) findViewById(R.id.btn_Btn08Activity_clear);
        customMarkerView = new CustomMarkerView(activity,R.layout.custom_marker_view_layout);
//        radarChart.setMarker(customMarkerView);//设置MarkerView，点击节点时展示
        setChartOption();
    }

    private void setChartOption(){
        //设置背景颜色，将覆盖整个图表视图
        radarChart.setBackgroundColor(ContextCompat.getColor(activity, android.R.color.holo_green_light));
        //设置统计图标注，会显示在图表的右下角
        radarChart.setContentDescription("雷达图示例");
        radarChart.getDescription().setEnabled(false);
        //图标无数据时的展示
        radarChart.setNoDataText("雷达图无数据");
        radarChart.setNoDataTextColor(ContextCompat.getColor(activity, R.color.colorPrimary));

        radarChart.setTouchEnabled(true);//是否可触摸
        //设置高亮位置与被点击处的最大距离屏幕；
        //数字越小，则误差越精密，越难点击到高亮位置；数字越大，则误差越大，越容易点击出高亮
        radarChart.setMaxHighlightDistance(20f);

        radarChart.setWebLineWidth(2f);//网线宽度
        radarChart.setWebColor(Color.RED);//网线颜色
        radarChart.setWebLineWidthInner(2f);//内网线的宽度
        radarChart.setWebColorInner(Color.BLUE);//内网线的颜色
        radarChart.setWebAlpha(100);//网线透明度

        //x/y轴上的动画
        radarChart.animateXY(
                1400, 1400,
                Easing.EasingOption.EaseInOutQuad,
                Easing.EasingOption.EaseInOutQuad);

        //坐标轴Axis； 雷达图中 x/y轴 只有一个
        XAxis xAxis = radarChart.getXAxis();
        YAxis yAxis = radarChart.getYAxis();
//        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//        yAxis.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);
        //如果不想要出现任何 X 或 Y 轴的东西，可以 setEnabled(false)来关闭轴线
//        xAxis.setEnabled(false);
//        yAxis.setEnabled(false);

        setData();
    }

    private void setData(){

        float mult = 80;
        float min = 20;
        int cnt = 7;//n个方向（n个纬度的判断）

        ArrayList<RadarEntry> entries1 = new ArrayList<RadarEntry>();
        ArrayList<RadarEntry> entries2 = new ArrayList<RadarEntry>();

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
        for (int i = 0; i < cnt; i++) {
            //拿到某个纬度中的值
            float val1 = (float) (Math.random() * mult) + min;
            entries1.add(new RadarEntry(val1));

            float val2 = (float) (Math.random() * mult) + min;
            entries2.add(new RadarEntry(val2));
        }

        RadarDataSet set1 = new RadarDataSet(entries1, "Last Week");
        set1.setColor(Color.RED);//节点之间连接线的颜色
        set1.setFillColor(Color.YELLOW);//面积中的填充颜色
        set1.setDrawFilled(true);//是否填充
        set1.setFillAlpha(80);//透明度
        set1.setLineWidth(2f);//连接线的宽度
        set1.setDrawHighlightCircleEnabled(true);//是否展示高亮圆点
        set1.setDrawHighlightIndicators(true);//高亮指示器，是否会展示x/y轴的高亮线
        set1.setHighlightLineWidth(2f);//高亮线的宽度

        RadarDataSet set2 = new RadarDataSet(entries2, "This Week");
        set2.setColor(Color.BLUE);//节点之间连接线的颜色
        set2.setFillColor(Color.GREEN);//面积中的填充颜色
        set2.setDrawFilled(true);//是否填充
        set2.setFillAlpha(80);//是否填充
        set2.setLineWidth(2f);//连接线的宽度
        set2.setDrawHighlightCircleEnabled(true);//连接线的宽度
        set2.setDrawHighlightIndicators(false);//高亮指示器，是否展示x/y轴的高亮线

        ArrayList<IRadarDataSet> sets = new ArrayList<IRadarDataSet>();
        sets.add(set1);
        sets.add(set2);

        RadarData data = new RadarData(sets);
        data.setValueTextSize(8f);
        data.setDrawValues(true);
        data.setValueTextColor(Color.WHITE);

        radarChart.setData(data);
        radarChart.invalidate();
        
    }
}
