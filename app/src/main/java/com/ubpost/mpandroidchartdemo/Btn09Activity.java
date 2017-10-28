package com.ubpost.mpandroidchartdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

/**
 * 折线图 LineChart 的使用
 */
public class Btn09Activity extends AppCompatActivity {

    private Btn09Activity activity;
    private LineChart lineChart;
    private Button btn_Btn09Activity_refresh;
    private Button btn_Btn09Activity_clear;
    private CustomMarkerView customMarkerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_btn09);
        activity = this;
        lineChart = (LineChart) findViewById(R.id.lineChart_Btn09Activity_show);
        btn_Btn09Activity_refresh = (Button) findViewById(R.id.btn_Btn09Activity_refresh);
        btn_Btn09Activity_clear = (Button) findViewById(R.id.btn_Btn09Activity_clear);
        customMarkerView = new CustomMarkerView(activity,R.layout.custom_marker_view_layout);
//        lineChart.setDrawMarkers(true);//只要设置了setMarker，则认为开启了setDrawMarkers
        lineChart.setMarker(customMarkerView);//设置MarkerView，点击节点时展示
        setChartOption();
        btn_Btn09Activity_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setData2();
            }
        });
        btn_Btn09Activity_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearData();
            }
        });
    }

    private void setChartOption() {
        //设置背景颜色，将覆盖整个图表视图
        lineChart.setBackgroundColor(ContextCompat.getColor(activity, android.R.color.holo_green_light));
        //设置统计图标注，会显示在图表的右下角
        lineChart.setContentDescription("折线图示例");
        //图标无数据时的展示
        lineChart.setNoDataText("折线图无数据");
        lineChart.setNoDataTextColor(ContextCompat.getColor(activity, R.color.colorPrimary));
        //如果启用，chart 绘图区后面的背景矩形将绘制
        lineChart.setDrawGridBackground(false);
        lineChart.setGridBackgroundColor(ContextCompat.getColor(activity, android.R.color.white));
        //启用/禁用绘制图表边框
        lineChart.setDrawBorders(true);
        lineChart.setBorderWidth(2f);
        lineChart.setBorderColor(ContextCompat.getColor(activity, android.R.color.holo_orange_light));
        //设置最大可见绘制的 chart count 的数量；该方法必须要有，否则报错
        //lineChart.setMaxVisibleValueCount(10);//参照DEMO的写法，该方法不写也不会有问题，方法具体作用未知

        //坐标轴Axis；x轴 只有一个，而y轴分为左右两条轴线
        //GridLine：背景中每一列与X轴垂直的线；AxisLine : 与X轴重合的线；Y轴中同理
        XAxis xAxis = lineChart.getXAxis();
        YAxis yAxisLeft = lineChart.getAxisLeft();
        YAxis yAxisRight = lineChart.getAxisRight();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //如果不想要出现任何 X 或 Y 轴的东西，可以 setEnabled(false)来关闭轴线
//        xAxis.setEnabled(false);
//        yAxisLeft.setEnabled(false);
//        yAxisRight.setEnabled(false);

        setData2();
    }

    /**
     * 设置数据
     * 官方DEMO代码
     */
    private void setData(int count, float range) {

        ArrayList<Entry> list1 = new ArrayList<>(50);
        for (int i = 0; i < 30; i++) {
            float mult = range / 2f;
            float val = (float) (Math.random() * mult) + 50;
            list1.add(new Entry(i, val));
        }

        ArrayList<Entry> list2 = new ArrayList<Entry>();

        for (int i = 0; i < 30; i++) {
            float mult = range;
            float val = (float) (Math.random() * mult) + 30;
            list2.add(new Entry(i, val));
        }

        ArrayList<Entry> list3 = new ArrayList<Entry>();

        for (int i = 0; i < 30; i++) {
            float mult = range;
            float val = (float) (Math.random() * mult) + 10;
            list3.add(new Entry(i, val));
        }

        LineDataSet set1, set2, set3;

        if (lineChart.getData() != null &&
                lineChart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) lineChart.getData().getDataSetByIndex(0);
            set2 = (LineDataSet) lineChart.getData().getDataSetByIndex(1);
            set3 = (LineDataSet) lineChart.getData().getDataSetByIndex(2);
            set1.setValues(list1);
            set2.setValues(list2);
            set3.setValues(list3);
            lineChart.getData().notifyDataChanged();
            lineChart.notifyDataSetChanged();
            lineChart.invalidate();//必须要执行此方法，才会重新绘制图表
        } else {
            // create a dataset and give it a type
            set1 = new LineDataSet(list1, "DataSet 1");

            set1.setAxisDependency(YAxis.AxisDependency.LEFT);
            set1.setColor(ColorTemplate.getHoloBlue());
            set1.setCircleColor(ColorTemplate.getHoloBlue());
            set1.setLineWidth(2f);
            set1.setCircleRadius(3f);
            set1.setFillAlpha(65);
            set1.setFillColor(ColorTemplate.getHoloBlue());
            set1.setHighLightColor(ColorTemplate.getHoloBlue());
            set1.setDrawCircleHole(false);

            // create a dataset and give it a type
            set2 = new LineDataSet(list2, "DataSet 2");
            set2.setAxisDependency(YAxis.AxisDependency.RIGHT);
            set2.setColor(Color.RED);
            set2.setCircleColor(Color.RED);
            set2.setLineWidth(2f);
            set2.setCircleRadius(3f);
            set2.setFillAlpha(65);
            set2.setFillColor(Color.RED);
            set2.setDrawCircleHole(false);
            set2.setHighLightColor(Color.RED);

            set3 = new LineDataSet(list3, "DataSet 3");
            set3.setAxisDependency(YAxis.AxisDependency.RIGHT);
            set3.setColor(Color.YELLOW);
            set3.setCircleColor(Color.YELLOW);
            set3.setLineWidth(2f);
            set3.setCircleRadius(3f);
            set3.setFillAlpha(65);
            set3.setFillColor(ColorTemplate.colorWithAlpha(Color.YELLOW, 200));
            set3.setDrawCircleHole(false);
            set3.setHighLightColor(Color.YELLOW);

            // create a data object with the datasets
            LineData data = new LineData(set1, set2, set3);
            data.setValueTextColor(Color.BLACK);
            data.setValueTextSize(9f);

            // set data
            lineChart.setData(data);
        }

    }

    /**
     * 设置数据
     */
    private void setData2() {

        //在清除了列表后，不显示MarkerView了，这里要再次开启
        lineChart.setDrawMarkers(true);

        ArrayList<Entry> list1 = new ArrayList<>(50);
        for (int i = 0; i < 30; i++) {
            list1.add(new Entry(i, i));
        }

        ArrayList<Entry> list2 = new ArrayList<Entry>();

        for (int i = 0; i < 30; i++) {
            float y = (float) (Math.random() * 30);
            list2.add(new Entry(i, y));
        }

        ArrayList<Entry> list3 = new ArrayList<Entry>();

        for (int i = 0; i < 30; i++) {
            float y = (float) (Math.random() * 30);
            list3.add(new Entry(i, y));
        }

        ArrayList<Entry> list4 = new ArrayList<Entry>();

        for (int i = 0; i < 30; i++) {
            float y = (float) (Math.random() * 30);
            list4.add(new Entry(i, y));
        }

        LineDataSet set1;
        LineDataSet set2;

        if (lineChart.getData() != null &&
                lineChart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) lineChart.getData().getDataSetByIndex(0);
            set2 = (LineDataSet) lineChart.getData().getDataSetByIndex(1);
            set1.setValues(list3);
            set2.setValues(list4);
            lineChart.getData().notifyDataChanged();
            lineChart.notifyDataSetChanged();
            lineChart.invalidate();//必须要执行此方法，才会重新绘制图表
        } else {
            // create a dataset and give it a type
            set1 = new LineDataSet(list1, "DataSet 1");

            set1.setAxisDependency(YAxis.AxisDependency.LEFT);
            set1.setLineWidth(1f);
            set1.setColor(Color.BLUE);
            set1.setCircleRadius(3f);
            set1.setCircleColor(Color.BLUE);
            set1.setHighLightColor(Color.BLUE);
            set1.setDrawCircleHole(false);
//            set1.setFillAlpha(65);
//            set1.setFillColor(Color.BLUE);

            // create a dataset and give it a type
            set2 = new LineDataSet(list2, "DataSet 2");
            set2.setAxisDependency(YAxis.AxisDependency.RIGHT);
            set2.setLineWidth(1f);
            set2.setColor(Color.RED);
            set2.setCircleRadius(3f);
            set2.setCircleColor(Color.RED);
            set2.setDrawCircleHole(false);
            set2.setHighLightColor(Color.RED);
//            set2.setFillAlpha(65);
//            set2.setFillColor(Color.RED);

            //ILineDataSet 的集合；将集合数据添加到dataSets中
            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);
            dataSets.add(set2);
            //创建LineData对象
//            LineData data = new LineData(dataSets);
            LineData data = new LineData(set1, set2);//与上面效果一致
            data.setDrawValues(true);
            data.setValueTextColor(Color.WHITE);
            data.setValueTextSize(16f);
            //setData
            lineChart.setData(data);
            lineChart.invalidate();
        }

    }

    /**
     * 清空数据
     */
    private void clearData() {
//        if (lineChart.getData() != null && lineChart.getData().getDataSetCount() > 0) {
//            int count = lineChart.getData().getDataSetCount();
//            Log.d("mytag", "int count == " + count);
//            //这里没有起到删除所有data的作用
//            for (int i = 0; i < count; i++) {
//                lineChart.getData().removeDataSet(i);
//                Log.d("mytag", "getData().removeDataSet(i) = " + i);
//            }
//            Log.d("mytag", "lineChart.getData().getDataSetCount() == " + lineChart.getData().getDataSetCount());
//            lineChart.getData().notifyDataChanged();
//            lineChart.notifyDataSetChanged();
//            lineChart.invalidate();
//        }

        //清空数据
        Log.d("mytag", "lineChart.getData().getDataSetCount() == " + lineChart.getData().getDataSetCount());
        //清除列表时，要关闭MarkerView的显示，否则MarkerView中要显示的数据为null，空指针异常
        lineChart.setDrawMarkers(false);
        lineChart.getData().clearValues();//内部执行了notifyDataChanged
        lineChart.notifyDataSetChanged();
        lineChart.invalidate();
    }

}
