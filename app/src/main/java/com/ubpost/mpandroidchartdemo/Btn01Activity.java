package com.ubpost.mpandroidchartdemo;

import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.LargeValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.Utils;

import java.util.ArrayList;

import static android.R.attr.y;

/**
 * 折线图 LineChart 的使用
 * 基础属性的测试代码，参考自官方DEMO
 */
public class Btn01Activity extends AppCompatActivity {

    private Btn01Activity activity;
    private LineChart lineChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_btn01);
        activity = this;
        lineChart = (LineChart) findViewById(R.id.lineChart_Btn01Activity_show);
        setChartOption();
    }

    private void setChartOption() {
        //设置背景颜色，将覆盖整个图表视图。 此外，背景颜色可以在布局文件 .xml 中进行设置
        //注意：设置颜色时要ARGB完整的八位（如 0xff00ff00），否则可能会被视为“设置透明颜色”（如 0xff0000）
        lineChart.setBackgroundColor(ContextCompat.getColor(activity, android.R.color.holo_blue_light));
        //设置统计图标注，会显示在图表的右下角
        lineChart.setContentDescription("折线图示例");
        //自定义统计图标注以及显示样式
        Description description = new Description();
        description.setPosition(100, 100);//标注文字在屏幕上的位置
        description.setText("这是折线图");//标注的描述文字
        description.setTextAlign(Paint.Align.CENTER);//标注文字对齐方式
        description.setTextColor(ContextCompat.getColor(activity, R.color.colorAccent));//标注文字颜色
        description.setTextSize(16);//标注文字大小，以像素为单位，最小6f，最大16f
        description.setTypeface(null);//设置标注文字的 Typeface
        lineChart.setDescription(description);
        //设置当 chart 为空时显示的描述文字
        lineChart.setNoDataText("没有数据");
        //设置当 chart 为空时显示的描述文字颜色
        lineChart.setNoDataTextColor(ContextCompat.getColor(activity, R.color.colorPrimary));
        lineChart.setNoDataTextTypeface(null);//当 chart 为空时显示的描述文字样式
        //如果启用，chart 绘图区后面的背景矩形将绘制
        lineChart.setDrawGridBackground(true);
        //设置网格背景应与绘制的颜色
        lineChart.setGridBackgroundColor(ContextCompat.getColor(activity, R.color.colorPrimary));
        //启用/禁用绘制图表边框（chart周围的线）
        lineChart.setDrawBorders(true);
        //设置 chart 边框线的颜色
        lineChart.setBorderColor(ContextCompat.getColor(activity, android.R.color.holo_red_dark));
        //设置 chart 边框线的宽度，单位 dp
        lineChart.setBorderWidth(5);
        //设置最大可见绘制的 chart count 的数量。 只在 setDrawValues() 设置为 true 时有效
        lineChart.setMaxVisibleValueCount(50);
        setData(45, 100);

    }

    private void setData(int count, float range) {

        //创建 charting.data.Entry 集合
        ArrayList<Entry> values = new ArrayList<Entry>();

        for (int i = 0; i < count; i++) {
            //0 <= Math.random() <= 1；
            float val = (float) (Math.random() * range) + 3;
            //Entry(float x, float y)；仅有位置坐标
            //Entry(float x, float y, Drawable icon)；位置坐标和icon
            //Entry(float x, float y, Object data)；位置坐标和数据对象
            //Entry(float x, float y, Drawable icon, Object data)；位置坐标和icon和数据对象
            values.add(new Entry(i, val, getResources().getDrawable(R.drawable.star)));
        }

        LineDataSet set1;//某一条线的数据集合

        if (lineChart.getData() != null &&
                lineChart.getData().getDataSetCount() > 0) {
            //如果已经有数据集合；则将values赋值给set1；并更新lineChart的显示
            set1 = (LineDataSet) lineChart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            lineChart.getData().notifyDataChanged();
            lineChart.notifyDataSetChanged();
            //lineChart.invalidate();//刷新lineChart
        } else {
            // create a dataset and give it a type
            //没有数据集合，则新建数据集合，values赋值给set1
            set1 = new LineDataSet(values, "DataSet 1");//数据集合 set1 的初始化和标识

            set1.setDrawIcons(false);//数据集合 set1 的是否绘制icon

            // set the line to be drawn like this "- - - - - -"
            set1.enableDashedLine(10f, 5f, 0f);//设置线条为虚线 1.线条宽度2.间隔宽度3.角度
            //HighlightLine，当点击节点时，将会出现与节点水平和垂直的两条线
            set1.enableDashedHighlightLine(10f, 5f, 0f);//高亮虚线的样式
//            set1.setHighLightColor();//高亮线的颜色
//            set1.setHighlightLineWidth();//高亮线的宽度
//            set1.setHighlightEnabled();//高亮是否可点击
            //若要单独定制某一方向上的线，可以先把关闭线条hightlight显示
//            set1.setDrawHighlightIndicators();//是否绘制高亮指示器
//            set1.setDrawHorizontalHighlightIndicator();//是否绘制水平高亮
//            set1.setDrawVerticalHighlightIndicator();//是否绘制垂直高亮
            set1.setColor(Color.BLACK);//折线的颜色
            set1.setLineWidth(1f);//折线的宽度
            set1.setCircleColor(Color.BLACK);//Entry节点的颜色
            set1.setCircleRadius(3f);//Entry节点的大小
            //是否定制节点圆心的颜色，若为false，则节点为单一的同色点，若为true则可以设置节点圆心的颜色
            set1.setDrawCircleHole(false);
            //set1.setCircleColorHole();//设置节点圆心的颜色
            set1.setValueTextSize(9f);//节点显示文字大小
            set1.setValueTextColor(Color.BLACK);//节点显示文字颜色
            set1.setDrawFilled(true);//是否将折线图以下部分用颜色填满；是否开启填充，默认为false
            //设置图例
//            Legend legend = lineChart.getLegend();
//            legend.setForm();//图例样式
//            legend.setFormLineDashEffect();//图例虚线
//            legend.setFormLineWidth();//图例线宽度
//            legend.setFormSize();//图例的大小
//            legend.setFormToTextSpace();//图例的文字间隙
            //set1 的图例设置
//            set1.setForm();//图例样式
            set1.setFormLineWidth(1f);//图例线宽度
            set1.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));//图例虚线
            set1.setFormSize(15.f);//图例大小
            //PercentFormatter：显示成百分比，常用于 PieChart饼图;LargeValueFormatter：超过一千会变成1K
//            set1.setValueFormatter(new LargeValueFormatter());//格式化表中Label的数值

            if (Utils.getSDKInt() >= 18) {
                // fill drawable only supported on api level 18 and above
                Drawable drawable = ContextCompat.getDrawable(this, R.drawable.fade_red);
                set1.setFillDrawable(drawable);//设置填充背景；api18以后
            } else {
                set1.setFillColor(Color.BLACK);//设置填充颜色
                //set1.setFillAlpha();//设置填充区域透明度，默认值为85
            }

            //ILineDataSet 的集合；将set1集合数据添加到dataSets中
            ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
            dataSets.add(set1); // add the datasets

            // create a data object with the datasets
            LineData data = new LineData(dataSets);//创建LineData对象
//            data.setValueTextColor();//数据值的颜色
//            data.setValueTextSize();//数据文字的大小
//            data.setValueFormatter();//数据文字格式
//            data.setValueTypeface();//数据文字属性
//            data.setDrawValues();//是否绘制数据值

            //坐标轴Axis；x轴 只有一个，而y轴分为左右两条轴线
            //GridLine：背景中每一列与X轴垂直的线；AxisLine : 与X轴重合的线；Y轴中同理
            XAxis xAxis = lineChart.getXAxis();
            YAxis yAxisLeft = lineChart.getAxisLeft();
            YAxis yAxisRight = lineChart.getAxisRight();
            //如果不想要出现任何 X 或 Y 轴的东西，可以 setEnabled(false)来关闭轴线
//            xAxis.setEnabled();
//            yAxisLeft.setEnabled();
//            yAxisRight.setEnabled();
            //x轴线
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//默认显示在表上方
//            xAxis.setDrawAxisLine();//是否绘制x轴线
//            xAxis.setAxisLineColor();//x轴线颜色
//            xAxis.setAxisLineWidth();//x轴线宽度
            //x轴线设置成虚线; 1.线条宽度2.间隔宽度3.角度
//            xAxis.setAxisLineDashedLine((new DashPathEffect(new float[]{10f, 5f}, 0f)));
            //x轴线的垂直线
//            xAxis.setDrawGridLines();//是否绘制垂直线
//            xAxis.setGridColor();//垂直线颜色
//            xAxis.setGridLineWidth();//垂直线宽度
//            xAxis.setGridDashedLine();//垂直线设置成虚线
            //x轴线的标签
//            xAxis.setDrawLabels();//是否绘制标签
//            xAxis.setLabelRotationAngle();//label旋转角度
//            xAxis.setTextSize();//设置Label标签的颜色
//            xAxis.setTextSize();//设置Label标签的字体大小
//            xAxis.setTypeface();////设置Label标签的字体
            //x轴线的限制线
//            xAxis.setDrawLimitLinesBehindData();//是否绘制限制线
//            xAxis.addLimitLine(new LimitLine(50f));//添加限制线
//            xAxis.removeLimitLine(new LimitLine(50f));//删除限制线;

            //y轴线
            //y轴有左右两条，大部分时候两边需要同时设定，否则两边显示的数据会不一样，或是 Label 数值线没有对齐的状况
//            yAxisLeft.setAxisMaximum(10f);//最大值
//            yAxisLeft.setAxisMinimum(-10f);//最小值;如果设置的最小值不是负数就看不到效果
//            yAxisLeft.resetAxisMaximum();//使用reset则可以将坐标轴还原
//            yAxisLeft.resetAxisMinimum();//使用reset则可以将坐标轴还原
//            yAxisLeft.setInverted(true);//true就是升序 反之降序排列
//            yAxisLeft.setSpaceTop(100f);//设置最大值至TOP顶端的距离
//            yAxisLeft.setSpaceBottom(100f);//设置最小值到Bottom到底端的距离
//            //设置Y轴的Label显示在图表的内侧还是外侧，默认外侧
//            yAxisLeft.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);
//            //设置Y轴显示的label个数;第一个参数为Label的个数，最多25个，默认为6;第二个参数为 是否均匀显示长度
//            yAxisLeft.setLabelCount(7,true);
//            //设置标签label与坐标轴之间的间距
//            yAxisLeft.setXOffset(40f); //设定 X 轴Offset
//            yAxisLeft.setYOffset(40f);//设定 Y 轴Offset

//            yAxisRight.setAxisMaximum(10f);//最大值
//            yAxisRight.setAxisMinimum(-10f);//最小值;如果设置的最小值不是负数就看不到效果
//            yAxisRight.resetAxisMaximum();//使用reset则可以将坐标轴还原
//            yAxisRight.resetAxisMinimum();//使用reset则可以将坐标轴还原
//            yAxisRight.setInverted(true);//true就是升序 反之降序排列
//            yAxisRight.setSpaceTop(100f);//设置最大值至TOP顶端的距离
//            yAxisRight.setSpaceBottom(100f);//设置最小值到Bottom到底端的距离
//            //设置Y轴的Label显示在图表的内侧还是外侧，默认外侧
//            yAxisRight.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);
//            //设置Y轴显示的label个数;第一个参数为Label的个数，最多25个，默认为6;第二个参数为 是否均匀显示长度
//            yAxisRight.setLabelCount(7,true);
//            //设置标签label与坐标轴之间的间距
//            yAxisRight.setXOffset(40f); //设定 X 轴Offset
//            yAxisRight.setYOffset(40f);//设定 Y 轴Offset

            // set data
            lineChart.setData(data);//lineChart 设置 LineData 展示
        }
    }
}
