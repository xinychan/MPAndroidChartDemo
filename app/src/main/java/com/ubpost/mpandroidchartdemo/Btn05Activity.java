package com.ubpost.mpandroidchartdemo;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.github.mikephil.charting.charts.ScatterChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.ScatterData;
import com.github.mikephil.charting.data.ScatterDataSet;
import com.github.mikephil.charting.interfaces.datasets.IScatterDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

/**
 * 散射图 ScatterChart 使用
 */
public class Btn05Activity extends AppCompatActivity {

    private Btn05Activity activity;
    private ScatterChart scatterChart;
    private Button btn_Btn05Activity_refresh;
    private Button btn_Btn05Activity_clear;
    private CustomMarkerView customMarkerView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_btn05);
        activity = this;
        scatterChart = (ScatterChart) findViewById(R.id.scatterChart_Btn05Activity_show);
        btn_Btn05Activity_refresh = (Button) findViewById(R.id.btn_Btn05Activity_refresh);
        btn_Btn05Activity_clear = (Button) findViewById(R.id.btn_Btn05Activity_clear);
        customMarkerView = new CustomMarkerView(activity,R.layout.custom_marker_view_layout);
        scatterChart.setMarker(customMarkerView);//设置MarkerView，点击节点时展示
        setChartOption();
    }

    private void setChartOption(){
        //设置背景颜色，将覆盖整个图表视图
        scatterChart.setBackgroundColor(ContextCompat.getColor(activity, android.R.color.holo_green_light));
        //设置统计图标注，会显示在图表的右下角
        scatterChart.setContentDescription("示例");
        scatterChart.getDescription().setEnabled(false);
        //图标无数据时的展示
        scatterChart.setNoDataText("散射图无数据");
        scatterChart.setNoDataTextColor(ContextCompat.getColor(activity, R.color.colorPrimary));
        //如果启用，chart 绘图区后面的背景矩形将绘制
        scatterChart.setDrawGridBackground(false);
        scatterChart.setGridBackgroundColor(ContextCompat.getColor(activity, android.R.color.white));
        //启用/禁用绘制图表边框
        scatterChart.setDrawBorders(true);
        scatterChart.setBorderWidth(2f);
        scatterChart.setBorderColor(ContextCompat.getColor(activity, android.R.color.holo_orange_light));
        //设置最大可见绘制的 chart count 的数量；该方法必须要有，否则报错
        //scatterChart.setMaxVisibleValueCount(10);//参照DEMO的写法，该方法不写也不会有问题，方法具体作用未知
        scatterChart.setTouchEnabled(true);//是否可触摸
        scatterChart.setDragEnabled(true);//是否可拖拽
        scatterChart.setScaleEnabled(true);//是否可伸缩
        //如果设置为true,x和y轴可以同时用两手指伸缩；false则x/y轴单独伸缩
        scatterChart.setPinchZoom(true);
        //设置高亮位置与被点击处的最大距离屏幕；
        //数字越小，则误差越精密，越难点击到高亮位置；数字越大，则误差越大，越容易点击出高亮
        scatterChart.setMaxHighlightDistance(20f);


        //坐标轴Axis；x轴 只有一个，而y轴分为左右两条轴线
        //GridLine：背景中每一列与X轴垂直的线；AxisLine : 与X轴重合的线；Y轴中同理
        XAxis xAxis = scatterChart.getXAxis();
        YAxis yAxisLeft = scatterChart.getAxisLeft();
        YAxis yAxisRight = scatterChart.getAxisRight();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //如果不想要出现任何 X 或 Y 轴的东西，可以 setEnabled(false)来关闭轴线
//        xAxis.setEnabled(false);
//        yAxisLeft.setEnabled(false);
//        yAxisRight.setEnabled(false);

        setData();
    }

    private void setData(){

        ArrayList<Entry> yVals1 = new ArrayList<Entry>();
        ArrayList<Entry> yVals2 = new ArrayList<Entry>();
        ArrayList<Entry> yVals3 = new ArrayList<Entry>();

        for (int i = 0; i < 10; i++) {
            float val = (float) (Math.random() * 10) + 3;
            yVals1.add(new Entry(i, val));
        }

        for (int i = 0; i < 10; i++) {
            float val = (float) (Math.random() * 16) + 6;
            yVals2.add(new Entry(i+.033f, val));
        }

        for (int i = 0; i < 10; i++) {
            float val = (float) (Math.random() * 13) + 3;
            yVals3.add(new Entry(i+0.66f, val));
        }

        // create a dataset and give it a type
        ScatterDataSet set1 = new ScatterDataSet(yVals1, "DS 1");
        set1.setScatterShape(ScatterChart.ScatterShape.SQUARE);//散射点形状
        set1.setColor(ColorTemplate.COLORFUL_COLORS[0]);//散射点颜色
        ScatterDataSet set2 = new ScatterDataSet(yVals2, "DS 2");
        set2.setScatterShape(ScatterChart.ScatterShape.CIRCLE);//散射点形状
        set2.setScatterShapeHoleColor(Color.RED);//散射点中心圆的颜色
        set2.setScatterShapeHoleRadius(5f);//散射点中心圆半径
        set2.setColor(Color.BLUE);//散射点颜色，有中心圆则是中心圆圆环的颜色
        ScatterDataSet set3 = new ScatterDataSet(yVals3, "DS 3");
        set3.setShapeRenderer(new CustomScatterShapeRenderer());//自定义形状
        set3.setColor(ColorTemplate.COLORFUL_COLORS[2]);//散射点颜色

        set1.setScatterShapeSize(8f);//散射点大小
        set2.setScatterShapeSize(8f);//散射点大小
        set3.setScatterShapeSize(18f);//散射点大小

        ArrayList<IScatterDataSet> dataSets = new ArrayList<IScatterDataSet>();
        dataSets.add(set1); // add the datasets
        dataSets.add(set2);
        dataSets.add(set3);

        // create a data object with the datasets
        ScatterData data = new ScatterData(dataSets);
        data.setDrawValues(true);//是否绘制值
        data.setValueTextColor(Color.WHITE);//值字体颜色
        data.setValueTextSize(10f);//值字体大小

        scatterChart.setData(data);
        scatterChart.invalidate();
    }

}
