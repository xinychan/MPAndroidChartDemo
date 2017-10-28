package com.ubpost.mpandroidchartdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.github.mikephil.charting.charts.BubbleChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BubbleData;
import com.github.mikephil.charting.data.BubbleDataSet;
import com.github.mikephil.charting.data.BubbleEntry;
import com.github.mikephil.charting.interfaces.datasets.IBubbleDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;

import java.util.ArrayList;

import static android.R.attr.entries;

/**
 * 泡泡图 BubbleChart 使用
 */
public class Btn07Activity extends AppCompatActivity {

    private Btn07Activity activity;
    private BubbleChart bubbleChart;
    private Button btn_Btn07Activity_refresh;
    private Button btn_Btn07Activity_clear;
    private CustomMarkerView customMarkerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_btn07);
        activity = this;
        bubbleChart = (BubbleChart) findViewById(R.id.bubbleChart_Btn07Activity_show);
        btn_Btn07Activity_refresh = (Button) findViewById(R.id.btn_Btn07Activity_refresh);
        btn_Btn07Activity_clear = (Button) findViewById(R.id.btn_Btn07Activity_clear);
        customMarkerView = new CustomMarkerView(activity,R.layout.custom_marker_view_layout);
//        bubbleChart.setMarker(customMarkerView);//设置MarkerView，点击节点时展示
        setChartOption();
    }
    
    private void setChartOption(){
        //设置背景颜色，将覆盖整个图表视图
        bubbleChart.setBackgroundColor(ContextCompat.getColor(activity, android.R.color.holo_green_light));
        //设置统计图标注，会显示在图表的右下角
        bubbleChart.setContentDescription("泡泡图示例");
        bubbleChart.getDescription().setEnabled(false);
        //图标无数据时的展示
        bubbleChart.setNoDataText("泡泡图无数据");
        bubbleChart.setNoDataTextColor(ContextCompat.getColor(activity, R.color.colorPrimary));
        //如果启用，chart 绘图区后面的背景矩形将绘制
        bubbleChart.setDrawGridBackground(false);
        bubbleChart.setGridBackgroundColor(ContextCompat.getColor(activity, android.R.color.white));
        //启用/禁用绘制图表边框
        bubbleChart.setDrawBorders(true);
        bubbleChart.setBorderWidth(2f);
        bubbleChart.setBorderColor(ContextCompat.getColor(activity, android.R.color.holo_orange_light));
        //设置最大可见绘制的 chart count 的数量；该方法必须要有，否则报错
        //bubbleChart.setMaxVisibleValueCount(10);//参照DEMO的写法，该方法不写也不会有问题，方法具体作用未知

        bubbleChart.setTouchEnabled(true);//是否可触摸
        bubbleChart.setDragEnabled(true);//是否可拖拽
        bubbleChart.setScaleEnabled(true);//是否可伸缩
        //如果设置为true,x和y轴可以同时用两手指伸缩；false则x/y轴单独伸缩
        bubbleChart.setPinchZoom(true);
        //设置高亮位置与被点击处的最大距离屏幕；
        //数字越小，则误差越精密，越难点击到高亮位置；数字越大，则误差越大，越容易点击出高亮
        bubbleChart.setMaxHighlightDistance(20f);

        //坐标轴Axis；x轴 只有一个，而y轴分为左右两条轴线
        //GridLine：背景中每一列与X轴垂直的线；AxisLine : 与X轴重合的线；Y轴中同理
        XAxis xAxis = bubbleChart.getXAxis();
        YAxis yAxisLeft = bubbleChart.getAxisLeft();
        YAxis yAxisRight = bubbleChart.getAxisRight();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //如果不想要出现任何 X 或 Y 轴的东西，可以 setEnabled(false)来关闭轴线
//        xAxis.setEnabled(false);
//        yAxisLeft.setEnabled(false);
//        yAxisRight.setEnabled(false);

        setData();
    }

    private void setData(){

        ArrayList<BubbleEntry> yVals1 = new ArrayList<BubbleEntry>();
        ArrayList<BubbleEntry> yVals2 = new ArrayList<BubbleEntry>();
        ArrayList<BubbleEntry> yVals3 = new ArrayList<BubbleEntry>();

        for (int i = 0; i < 12; i++) {
            float val = (float) (Math.random() * 30);
            float size = (float) (Math.random() * 30);
            //圆心x/y，和size值（即展示的值，也是泡泡的半径）
            yVals1.add(new BubbleEntry(i, val, size, getResources().getDrawable(R.drawable.star)));
        }

        for (int i = 0; i < 12; i++) {
            float val = (float) (Math.random() * 30);
            float size = (float) (Math.random() * 30);
            //圆心x/y，和size值（即展示的值，也是泡泡的半径）
            yVals2.add(new BubbleEntry(i, val, size, getResources().getDrawable(R.drawable.star)));
        }

        for (int i = 0; i < 12; i++) {
            float val = (float) (Math.random() * 30);
            float size = (float) (Math.random() * 30);
            //圆心x/y，和size值（即展示的值，也是泡泡的半径）
            yVals3.add(new BubbleEntry(i, val, size));
        }

        // create a dataset and give it a type
        BubbleDataSet set1 = new BubbleDataSet(yVals1, "DS 1");
        set1.setDrawIcons(false);//是否展示Icon
        set1.setColor(Color.RED, 50);//泡泡的颜色
        set1.setDrawValues(true);//是否展示值

        BubbleDataSet set2 = new BubbleDataSet(yVals2, "DS 2");
        set2.setDrawIcons(false);//是否展示Icon
        set2.setIconsOffset(new MPPointF(0, 15));//Icon的偏移量
        set2.setColor(Color.YELLOW, 50);//泡泡的颜色
        set2.setDrawValues(true);//是否展示值

        BubbleDataSet set3 = new BubbleDataSet(yVals3, "DS 3");
        set3.setColor(Color.BLUE, 50);//泡泡的颜色
        set3.setDrawValues(true);//是否展示值

        ArrayList<IBubbleDataSet> dataSets = new ArrayList<IBubbleDataSet>();
        dataSets.add(set1); // add the datasets
        dataSets.add(set2);
        dataSets.add(set3);

        // create a data object with the datasets
        BubbleData data = new BubbleData(dataSets);
        data.setDrawValues(true);//是否展示值
        data.setValueTextSize(8f);//值的字体大小
        data.setValueTextColor(Color.WHITE);//值的字体颜色
        data.setHighlightCircleWidth(1.5f);//高亮圆环的半径

        bubbleChart.setData(data);
        bubbleChart.invalidate();
    }
    
}
