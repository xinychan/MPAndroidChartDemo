package com.ubpost.mpandroidchartdemo;

import android.content.Context;
import android.widget.TextView;

import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;

/**
 * 自定义的 MarkerView
 * Created by xinychan on 2017/9/13.
 */

public class CustomMarkerView extends MarkerView {

    private TextView textView;

    /**
     * 自定义构造方法
     */
    public CustomMarkerView(Context context, int layoutResource) {
        super(context, layoutResource);
        textView = (TextView) findViewById(R.id.tv_content);
    }

    /**
     * 每次画 MakerView 时都会触发 Callback 方法，通常会在此方法内更新 View 的內容
     */
    @Override
    public void refreshContent(Entry e, Highlight highlight) {
//        if (e instanceof CandleEntry) {
//
//            CandleEntry ce = (CandleEntry) e;
//
//            textView.setText("" + Utils.formatNumber(ce.getHigh(), 0, true));
//        } else {
//
//            textView.setText("" + Utils.formatNumber(e.getY(), 0, true));
//        }

        textView.setText("" + Utils.formatNumber(e.getY(), 0, true));//显示的是整数
//        textView.setText("" + e.getY());//显示的是double数
        super.refreshContent(e, highlight);//必须实现父类方法，才会展示MakerView
    }

    /**
     * offset 是以点到的那个点(0,0) 中心然后向右下角画出来
     * 所以如果要显示在点上方
     * X=宽度的一半，负数
     * Y=高度的负数
     */
    @Override
    public MPPointF getOffset() {
        MPPointF mpPointF = new MPPointF(-(getWidth() / 2), -getHeight());
        return mpPointF;
    }
}
