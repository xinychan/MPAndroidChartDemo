package com.ubpost.mpandroidchartdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.BubbleData;
import com.github.mikephil.charting.data.BubbleDataSet;
import com.github.mikephil.charting.data.BubbleEntry;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CandleDataSet;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.ScatterData;
import com.github.mikephil.charting.data.ScatterDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

/**
 * 组合图表 Combined-Chart 的使用
 */
public class Btn03Activity extends AppCompatActivity {

    private Btn03Activity activity;
    private CombinedChart combinedChart;
    private Button btn_Btn03Activity_refresh;
    private Button btn_Btn03Activity_clear;
    private CustomMarkerView customMarkerView;

    private final int itemcount = 12;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_btn03);
        activity = this;
        combinedChart = (CombinedChart) findViewById(R.id.combinedChart_Btn03Activity_show);
        btn_Btn03Activity_refresh = (Button) findViewById(R.id.btn_Btn03Activity_refresh);
        btn_Btn03Activity_clear = (Button) findViewById(R.id.btn_Btn03Activity_clear);
        customMarkerView = new CustomMarkerView(activity,R.layout.custom_marker_view_layout);
//        lineChart.setDrawMarkers(true);//只要设置了setMarker，则认为开启了setDrawMarkers
        combinedChart.setMarker(customMarkerView);//设置MarkerView，点击节点时展示
        setChartOption();

    }

    private void setChartOption(){

        //设置背景颜色，将覆盖整个图表视图
        combinedChart.setBackgroundColor(ContextCompat.getColor(activity, android.R.color.holo_green_light));
        //设置统计图标注，会显示在图表的右下角
        combinedChart.setContentDescription("条形图示例");
        //图标无数据时的展示
        combinedChart.setNoDataText("组合图无数据");
        combinedChart.setNoDataTextColor(ContextCompat.getColor(activity, R.color.colorPrimary));
        //如果启用，chart 绘图区后面的背景矩形将绘制
        combinedChart.setDrawGridBackground(false);
        combinedChart.setGridBackgroundColor(ContextCompat.getColor(activity, android.R.color.white));
        //启用/禁用绘制图表边框
        combinedChart.setDrawBorders(true);
        combinedChart.setBorderWidth(2f);
        combinedChart.setBorderColor(ContextCompat.getColor(activity, android.R.color.holo_orange_light));

        //坐标轴Axis；x轴 只有一个，而y轴分为左右两条轴线
        //GridLine：背景中每一列与X轴垂直的线；AxisLine : 与X轴重合的线；Y轴中同理
        XAxis xAxis = combinedChart.getXAxis();
        YAxis yAxisLeft = combinedChart.getAxisLeft();
        YAxis yAxisRight = combinedChart.getAxisRight();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //如果不想要出现任何 X 或 Y 轴的东西，可以 setEnabled(false)来关闭轴线
//        xAxis.setEnabled(false);
//        yAxisLeft.setEnabled(false);
//        yAxisRight.setEnabled(false);

        // draw bars behind lines
//        combinedChart.setDrawOrder(
//                new CombinedChart.DrawOrder[]{CombinedChart.DrawOrder.BAR,CombinedChart.DrawOrder.BUBBLE,
//                        CombinedChart.DrawOrder.CANDLE,CombinedChart.DrawOrder.LINE,CombinedChart.DrawOrder.SCATTER});

        setData();

    }

    private void setData() {
        //设置数据；这个是综合表格，可以添加多个类型的数据
        CombinedData data = new CombinedData();
        data.setData(generateLineData());//直线图
        data.setData(generateBarData());//条形图
        data.setData(generateBubbleData());//泡泡表
        data.setData(generateScatterData());//散射图表
        data.setData(generateCandleData());//K 线图标
        data.setDrawValues(true);
        data.setValueTextSize(10f);
        data.setValueTextColor(ContextCompat.getColor(activity, android.R.color.holo_red_light));
        //setData
        combinedChart.setData(data);
        combinedChart.invalidate();
    }

    /**
     * 直线图
     */
    private LineData generateLineData() {

        LineData d = new LineData();

        ArrayList<Entry> entries = new ArrayList<Entry>();

        for (int index = 0; index < itemcount; index++){
            entries.add(new Entry(index + 0.5f, getRandom(15, 5)));
        }

//        for (int index = 0; index < itemcount; index++){
//            entries.add(new Entry(index,index));
//        }

        LineDataSet set = new LineDataSet(entries, "Line DataSet");
        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        set.setLineWidth(2.5f);
        set.setColor(Color.rgb(240, 238, 70));
        set.setCircleRadius(5f);
        set.setCircleColor(Color.rgb(240, 238, 70));
        set.setHighLightColor(Color.rgb(240, 238, 70));
        set.setDrawCircleHole(false);
//        set.setFillColor(Color.rgb(240, 238, 70));
        //显示模式是直线还是曲线
//        set.setMode(LineDataSet.Mode.LINEAR);//普通直线，默认模式
//        set.setMode(LineDataSet.Mode.STEPPED);//直角折线
        set.setMode(LineDataSet.Mode.CUBIC_BEZIER);//立方贝塞尔曲线
//        set.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);//水平贝塞尔曲线
        set.setDrawValues(true);
        set.setValueTextSize(10f);
        set.setValueTextColor(Color.rgb(240, 238, 70));

        d.addDataSet(set);

        return d;
    }

    /**
     * 条形图
     */
    private BarData generateBarData() {

        ArrayList<BarEntry> entries1 = new ArrayList<BarEntry>();
        ArrayList<BarEntry> entries2 = new ArrayList<BarEntry>();

        for (int index = 0; index < itemcount; index++) {
            entries1.add(new BarEntry(0, getRandom(25, 25)));

            // stacked
            entries2.add(new BarEntry(0, new float[]{getRandom(13, 12), getRandom(13, 12)}));
        }

        //一个Bar条上只展示一条数据
        BarDataSet set1 = new BarDataSet(entries1, "Bar 1");
        set1.setColor(Color.rgb(60, 220, 78));
        set1.setValueTextColor(Color.rgb(60, 220, 78));
        set1.setValueTextSize(10f);
        set1.setAxisDependency(YAxis.AxisDependency.LEFT);
        //两条数据展现在同一个Bar上
        BarDataSet set2 = new BarDataSet(entries2, "");
        set2.setStackLabels(new String[]{"Stack 1", "Stack 2"});
        set2.setColors(new int[]{Color.rgb(61, 165, 255), Color.rgb(23, 197, 255)});
        set2.setValueTextColor(Color.rgb(61, 165, 255));
        set2.setValueTextSize(10f);
        set2.setAxisDependency(YAxis.AxisDependency.LEFT);

        float groupSpace = 0.2f;//坐标轴与条形之间的距离
        float barSpace = 0.1f; // x2 dataset；条形之间的距离
        float barWidth = 0.3f; // x2 dataset；条形的宽度
        // (0.45 + 0.02) * 2 + 0.06 = 1.00 -> interval per "group"

        BarData d = new BarData(set1, set2);
        d.setBarWidth(barWidth);

        // make this BarData object grouped
        //必须将列表数据 BarData 放到一个集团中，才会分成多条 Bar 显示
        d.groupBars(0, groupSpace, barSpace); // start at x = 0

        return d;
    }

    /**
     * 散射图表
     */
    protected ScatterData generateScatterData() {

        ScatterData d = new ScatterData();

        ArrayList<Entry> entries = new ArrayList<Entry>();

//        for (float index = 0; index < itemcount; index += 0.5f){
//            entries.add(new Entry(index + 0.25f, getRandom(10, 55)));
//        }

        for (float index = 0; index < itemcount; index++){
            entries.add(new Entry(index, index));
        }

        ScatterDataSet set = new ScatterDataSet(entries, "Scatter DataSet");
        set.setColors(ColorTemplate.MATERIAL_COLORS);
        set.setScatterShapeSize(20f);//散射点大小（散射点默认正方形）
//        set.setScatterShape();//散射点形状
//        set.setColors(Color.RED);//散射点颜色
        set.setHighlightLineWidth(5f);//高亮线宽度
        set.setHighLightColor(Color.YELLOW);//高亮线颜色
        set.setDrawValues(true);//是否绘制值
        set.setValueTextSize(10f);//值字体大小
        set.setValueTextColor(Color.WHITE);//值字体颜色
        d.addDataSet(set);

        return d;
    }

    /**
     * K 线图表
     */
    protected CandleData generateCandleData() {

        CandleData d = new CandleData();

        ArrayList<CandleEntry> entries = new ArrayList<CandleEntry>();

//        for (int index = 0; index < itemcount; index += 2){
//            entries.add(new CandleEntry(index + 1f, 90, 70, 85, 75f));
//        }

        for (int index = 0; index < itemcount; index++){
            //CandleEntry(float x, float shadowH, float shadowL, float open, float close)
            //x轴位置，最高价，最低价，开盘价，收盘价
            entries.add(new CandleEntry(index, 90, 70, 85, 75f));
        }

        CandleDataSet set = new CandleDataSet(entries, "Candle DataSet");
//        set.setDecreasingColor(Color.rgb(142, 150, 175));
//        set.setShadowColor(Color.DKGRAY);
        set.setShadowColor(Color.RED);//阴影线颜色（上影/下影线）
        set.setDecreasingColor(Color.BLUE);//阴线颜色
//        set.setIncreasingColor();//阳线颜色
        set.setBarSpace(0.3f);//条的宽度
        set.setValueTextSize(10f);
        set.setValueTextColor(Color.RED);
        set.setDrawValues(true);
        d.addDataSet(set);

        return d;
    }

    /**
     * 泡泡图表
     */
    protected BubbleData generateBubbleData() {

        BubbleData bd = new BubbleData();

        ArrayList<BubbleEntry> entries = new ArrayList<BubbleEntry>();

//        for (int index = 0; index < itemcount; index++) {
//            float y = getRandom(10, 105);
//            float size = getRandom(100, 105);
//            entries.add(new BubbleEntry(index + 0.5f, y, size));
//        }

        for (int index = 0; index < itemcount; index++) {
            float size = 100;
            //圆心x/y，和size值（即展示的值，也是泡泡的半径）
            entries.add(new BubbleEntry(index, index, size));
        }

        BubbleDataSet set = new BubbleDataSet(entries, "Bubble DataSet");
        set.setColors(ColorTemplate.VORDIPLOM_COLORS);//泡泡的颜色
//        set.setColors(Color.BLUE);
        set.setValueTextSize(10f);//size值的字体大小
        set.setValueTextColor(Color.RED);//size值的字体颜色
        set.setHighlightCircleWidth(10f);//高亮圆环的半径
//        set.setHighLightColor(Color.RED);//高亮圆环的颜色，不依此变化，而是被点击的圆颜色加深
        set.setDrawValues(true);
        bd.addDataSet(set);

        return bd;
    }

    /**
     * 获得随机数
     */
    private float getRandom(float range, float startsfrom) {
        return (float) (Math.random() * range) + startsfrom;
    }
}
