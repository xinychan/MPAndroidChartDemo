package com.ubpost.mpandroidchartdemo;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;

import java.util.ArrayList;

import static com.ubpost.mpandroidchartdemo.R.id.btn_Btn09Activity_clear;
import static com.ubpost.mpandroidchartdemo.R.id.btn_Btn09Activity_refresh;

/**
 * 圆饼图 PieChart 使用
 */
public class Btn04Activity extends AppCompatActivity {

    private Btn04Activity activity;
    private PieChart pieChart;
    private Button btn_Btn04Activity_refresh;
    private Button btn_Btn04Activity_clear;
    private CustomMarkerView customMarkerView;

    protected String[] mParties = new String[] {
            "Party A", "Party B", "Party C", "Party D", "Party E", "Party F", "Party G", "Party H",
            "Party I", "Party J", "Party K", "Party L", "Party M", "Party N", "Party O", "Party P",
            "Party Q", "Party R", "Party S", "Party T", "Party U", "Party V", "Party W", "Party X",
            "Party Y", "Party Z"
    };
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_btn04);
        activity = this;
        pieChart = (PieChart) findViewById(R.id.pieChart_Btn04Activity_show);
        btn_Btn04Activity_refresh = (Button) findViewById(R.id.btn_Btn04Activity_refresh);
        btn_Btn04Activity_clear = (Button) findViewById(R.id.btn_Btn04Activity_clear);
        customMarkerView = new CustomMarkerView(activity,R.layout.custom_marker_view_layout);
        pieChart.setMarker(customMarkerView);//设置MarkerView，点击节点时展示
        setChartOption();
    }

    private void setChartOption(){
        //设置背景颜色，将覆盖整个图表视图
        pieChart.setBackgroundColor(ContextCompat.getColor(activity, android.R.color.white));
        //图标无数据时的展示
        pieChart.setNoDataText("圆饼图无数据");
        pieChart.setNoDataTextColor(ContextCompat.getColor(activity, R.color.colorPrimary));

        //如果开启，PieChart的百分比不是它的原始值，值为IValueFormatter格式提供的百分比
        pieChart.setUsePercentValues(true);
        //是否展示内容描述；描述在右下角展示
        pieChart.getDescription().setEnabled(false);
        //给图表左上右下设置额外补偿；即图表与整个背景之间的间距
        pieChart.setExtraOffsets(5, 5, 5, 5);

        //减速摩擦系数，值在0-1之间，值越大，速度降低会缓慢
        pieChart.setDragDecelerationFrictionCoef(0.9f);

        //是否绘制中心圆环；不绘制则文字不展示
        pieChart.setDrawCenterText(true);
        //中心圆环的文字；文字显示返回不会超出中心圆环
        pieChart.setCenterText(generateCenterSpannableText());
        //是否展示中心圆环；展示则中心为圆圈；不展示则中间无圆圈占用
        pieChart.setDrawHoleEnabled(true);
        //中心圆环处的背景颜色
        pieChart.setHoleColor(Color.YELLOW);
        //中心圆环处的半径
        pieChart.setHoleRadius(55f);
        //中心透明圆环的颜色
        pieChart.setTransparentCircleColor(Color.RED);
        //中心透明圆环的半径
        pieChart.setTransparentCircleRadius(60f);
        //中心透明圆环的透明度；数值越大越不透明
        pieChart.setTransparentCircleAlpha(80);

        //设置一个抵消RadarChart的旋转度。默认270 f
        pieChart.setRotationAngle(180);//意味这从圆饼图哪个位置开始伸展
        //是否允许圆饼图旋转
        pieChart.setRotationEnabled(true);
        //点击后是否高亮
        pieChart.setHighlightPerTapEnabled(true);

//         pieChart.setUnit(" €");
//         pieChart.setDrawUnitsInChart(true);

        setData(4, 100);

        //设置Y轴的动画；参数：动画时间，动画效果
        pieChart.animateY(1000, Easing.EasingOption.EaseInOutQuad);
        //设置X轴的动画；参数：动画时间，动画效果
//        pieChart.animateX(1400, Easing.EasingOption.EaseInOutQuad);
        // mChart.spin(2000, 0, 360);

        //图例的展示
        Legend l = pieChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);//图例展示的垂直位置
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);//图例展示的水平位置
        l.setOrientation(Legend.LegendOrientation.VERTICAL);//图例排序的方向
        l.setDrawInside(false);//图例是绘制在图表外部，还是在图表内部
        l.setXEntrySpace(10f);//图例之间X轴的间隔
        l.setYEntrySpace(10f);//图例之间Y轴的间隔
        l.setYOffset(0f);//图例Y轴的偏移量
        l.setXOffset(10f);//图例X轴的偏移量

        //标签的颜色和标签字体的大小；这里的标签值是mParties中对应的名称值
        pieChart.setEntryLabelColor(Color.RED);
        pieChart.setEntryLabelTextSize(12f);

    }

    private void setData(int count, float range) {

        float mult = range;

        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
//        for (int i = 0; i < count ; i++) {
//            entries.add(new PieEntry((float) ((Math.random() * mult) + mult / 5),
//                    mParties[i % mParties.length],
//                    getResources().getDrawable(R.drawable.star)));
//        }

        for (int i = 0; i < count ; i++) {
            //count = 4，只用到了0-3，所占百分比分别为0，16.7，33.3,50；
            PieEntry pieEntry = new PieEntry(i,mParties[i % mParties.length],getResources().getDrawable(R.drawable.star));
            entries.add(pieEntry);
        }

        PieDataSet dataSet = new PieDataSet(entries, "Election Results");

        //是否绘制Icon
        dataSet.setDrawIcons(false);
        //绘制Icon的偏移量
        dataSet.setIconsOffset(new MPPointF(0, 0));

        //每份饼之间的空隙
        dataSet.setSliceSpace(3f);
        //点击后，高亮突出的距离
        dataSet.setSelectionShift(10f);

        //给 dataSet 的饼添加不同的显示颜色
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
        //设置显示颜色
        dataSet.setColors(colors);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());//百分比格式
        data.setValueTextSize(20f);//百分比值的字体大小
        data.setValueTextColor(Color.RED);//百分比值的字体颜色
        pieChart.setData(data);

        // undo all highlights
        pieChart.highlightValues(null);//设置高亮值

        pieChart.invalidate();
    }

    /**
     * 中心空白处内容
     */
    private SpannableString generateCenterSpannableText() {

        SpannableString s = new SpannableString("MPAndroidChart\ndeveloped by Philipp Jahoda");
        //以下是给文字设置样式
        //setSpan(Object what, int start, int end, int flags)
        //参数含义：文字样式，从第几个开始，到哪一个结束，标记
        s.setSpan(new RelativeSizeSpan(1.7f), 0, 14, 0);//前面14个字符串放大1.7倍
        s.setSpan(new StyleSpan(Typeface.NORMAL), 14, s.length() - 15, 0);//从第14个开始到倒数15个字符设置字体
        s.setSpan(new ForegroundColorSpan(Color.GRAY), 14, s.length() - 15, 0);//从第14个开始到倒数15个字符设置颜色
        s.setSpan(new RelativeSizeSpan(.8f), 14, s.length() - 15, 0);//从第14个开始到倒数15个字符缩小0.8倍
        s.setSpan(new StyleSpan(Typeface.ITALIC), s.length() - 14, s.length(), 0);//最后14个字符设置字体
        s.setSpan(new ForegroundColorSpan(ColorTemplate.getHoloBlue()), s.length() - 14, s.length(), 0);//最后14个字符设置颜色
        return s;
    }

}
