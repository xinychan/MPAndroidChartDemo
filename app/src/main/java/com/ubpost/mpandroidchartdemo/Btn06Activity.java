package com.ubpost.mpandroidchartdemo;

import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.github.mikephil.charting.charts.CandleStickChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CandleDataSet;
import com.github.mikephil.charting.data.CandleEntry;

import java.util.ArrayList;

/**
 * K 线图 CandleStickChart 的使用
 */
public class Btn06Activity extends AppCompatActivity {

    private Btn06Activity activity;
    private CandleStickChart candleStickChart;
    private Button btn_Btn06Activity_refresh;
    private Button btn_Btn06Activity_clear;
    private CustomMarkerView customMarkerView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_btn06);
        activity = this;
        candleStickChart = (CandleStickChart) findViewById(R.id.candleStickChart_Btn06Activity_show);
        btn_Btn06Activity_refresh = (Button) findViewById(R.id.btn_Btn06Activity_refresh);
        btn_Btn06Activity_clear = (Button) findViewById(R.id.btn_Btn06Activity_clear);
        customMarkerView = new CustomMarkerView(activity,R.layout.custom_marker_view_layout);
        candleStickChart.setMarker(customMarkerView);//设置MarkerView，点击节点时展示
        setChartOption();
    }
    
    private void setChartOption(){
        //设置背景颜色，将覆盖整个图表视图
        candleStickChart.setBackgroundColor(ContextCompat.getColor(activity, android.R.color.holo_green_light));
        //设置统计图标注，会显示在图表的右下角
        candleStickChart.setContentDescription("K 线图示例");
        candleStickChart.getDescription().setEnabled(false);
        //图标无数据时的展示
        candleStickChart.setNoDataText("K 线图无数据");
        candleStickChart.setNoDataTextColor(ContextCompat.getColor(activity, R.color.colorPrimary));
        //如果启用，chart 绘图区后面的背景矩形将绘制
        candleStickChart.setDrawGridBackground(false);
        candleStickChart.setGridBackgroundColor(ContextCompat.getColor(activity, android.R.color.white));
        //启用/禁用绘制图表边框
        candleStickChart.setDrawBorders(true);
        candleStickChart.setBorderWidth(2f);
        candleStickChart.setBorderColor(ContextCompat.getColor(activity, android.R.color.holo_orange_light));
        //设置最大可见绘制的 chart count 的数量；该方法必须要有，否则报错
        //candleStickChart.setMaxVisibleValueCount(10);//参照DEMO的写法，该方法不写也不会有问题，方法具体作用未知

        candleStickChart.setTouchEnabled(true);//是否可触摸
        candleStickChart.setDragEnabled(true);//是否可拖拽
        candleStickChart.setScaleEnabled(true);//是否可伸缩
        //如果设置为true,x和y轴可以同时用两手指伸缩；false则x/y轴单独伸缩
        candleStickChart.setPinchZoom(true);
        //设置高亮位置与被点击处的最大距离屏幕；
        //数字越小，则误差越精密，越难点击到高亮位置；数字越大，则误差越大，越容易点击出高亮
        candleStickChart.setMaxHighlightDistance(50f);
        
        //坐标轴Axis；x轴 只有一个，而y轴分为左右两条轴线
        //GridLine：背景中每一列与X轴垂直的线；AxisLine : 与X轴重合的线；Y轴中同理
        XAxis xAxis = candleStickChart.getXAxis();
        YAxis yAxisLeft = candleStickChart.getAxisLeft();
        YAxis yAxisRight = candleStickChart.getAxisRight();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //如果不想要出现任何 X 或 Y 轴的东西，可以 setEnabled(false)来关闭轴线
//        xAxis.setEnabled(false);
//        yAxisLeft.setEnabled(false);
//        yAxisRight.setEnabled(false);

        setData();
        
    }

    private void setData(){

        //表格复位
        candleStickChart.resetTracking();

        ArrayList<CandleEntry> yVals1 = new ArrayList<CandleEntry>();

        for (int i = 0; i < 10; i++) {
            float mult = (i + 1);
            float val = (float) (Math.random() * 40) + mult;

            float high = (float) (Math.random() * 9) + 8f;
            float low = (float) (Math.random() * 9) + 8f;

            float open = (float) (Math.random() * 6) + 1f;
            float close = (float) (Math.random() * 6) + 1f;

            boolean even = i % 2 == 0;
            //CandleEntry(float x, float shadowH, float shadowL, float open, float close)
            //x轴位置，最高价，最低价，开盘价，收盘价
            //开盘价<收盘价则是阳线，意味价格上涨，否则阴线
            yVals1.add(new CandleEntry(
                    i, val + high,
                    val - low,
                    even ? val + open : val - open,
                    even ? val - close : val + close,
                    getResources().getDrawable(R.drawable.star)
            ));
        }

        CandleDataSet set1 = new CandleDataSet(yVals1, "Data Set");

        set1.setDrawIcons(false);
        set1.setAxisDependency(YAxis.AxisDependency.LEFT);
//        set1.setColor(Color.rgb(80, 80, 80));
        set1.setShadowColor(Color.DKGRAY);//阴影线颜色（上影/下影线）
        set1.setShadowWidth(0.7f);//阴影线宽度
        set1.setDecreasingColor(Color.RED);//阴线颜色
        set1.setDecreasingPaintStyle(Paint.Style.FILL);//阴线填充类型
        set1.setIncreasingColor(Color.rgb(122, 242, 84));//阳线颜色
        set1.setIncreasingPaintStyle(Paint.Style.STROKE);//阳线填充类型
        set1.setNeutralColor(Color.BLUE);//中性线颜色，则收盘价=开盘价
        //set1.setHighlightLineWidth(1f);

        CandleData data = new CandleData(set1);

        candleStickChart.setData(data);
        candleStickChart.invalidate();
        
    }
    
}
