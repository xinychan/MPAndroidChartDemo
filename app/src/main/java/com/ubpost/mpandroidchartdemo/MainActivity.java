package com.ubpost.mpandroidchartdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * 设置不同的图表
 * 1. 折线图 LineChart
 * 2. 条形图 BarChart
 * 3. 条形折线图 Combined-Chart
 * 4. 圆饼图 PieChart
 * 5. 散射图 ScatterChart
 * 6. K线图 CandleStickChart
 * 7. 泡泡图 BubbleChart
 * 8. 雷达/网状图 RadarChart
 */

/**
 * MPAndroidChart 的使用
 * 1-项目的build.gradle引入maven 以及 app的build.gradle添加依赖
 * 2-参照官方 demo 代码测试
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private MainActivity activity;
    private Button btn01;
    private Button btn02;
    private Button btn03;
    private Button btn04;
    private Button btn05;
    private Button btn06;
    private Button btn07;
    private Button btn08;
    private Button btn09;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activity = this;
        initView();
        setOnClick();
    }

    private void initView() {
        btn01 = (Button) findViewById(R.id.btn_MainActivity_btn01);
        btn02 = (Button) findViewById(R.id.btn_MainActivity_btn02);
        btn03 = (Button) findViewById(R.id.btn_MainActivity_btn03);
        btn04 = (Button) findViewById(R.id.btn_MainActivity_btn04);
        btn05 = (Button) findViewById(R.id.btn_MainActivity_btn05);
        btn06 = (Button) findViewById(R.id.btn_MainActivity_btn06);
        btn07 = (Button) findViewById(R.id.btn_MainActivity_btn07);
        btn08 = (Button) findViewById(R.id.btn_MainActivity_btn08);
        btn09 = (Button) findViewById(R.id.btn_MainActivity_btn09);
    }

    private void setOnClick() {
        btn01.setOnClickListener(this);
        btn02.setOnClickListener(this);
        btn03.setOnClickListener(this);
        btn04.setOnClickListener(this);
        btn05.setOnClickListener(this);
        btn06.setOnClickListener(this);
        btn07.setOnClickListener(this);
        btn08.setOnClickListener(this);
        btn09.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_MainActivity_btn01:
                intoNextActivity(Btn01Activity.class);
                break;
            case R.id.btn_MainActivity_btn02:
                intoNextActivity(Btn02Activity.class);
                break;
            case R.id.btn_MainActivity_btn03:
                intoNextActivity(Btn03Activity.class);
                break;
            case R.id.btn_MainActivity_btn04:
                intoNextActivity(Btn04Activity.class);
                break;
            case R.id.btn_MainActivity_btn05:
                intoNextActivity(Btn05Activity.class);
                break;
            case R.id.btn_MainActivity_btn06:
                intoNextActivity(Btn06Activity.class);
                break;
            case R.id.btn_MainActivity_btn07:
                intoNextActivity(Btn07Activity.class);
                break;
            case R.id.btn_MainActivity_btn08:
                intoNextActivity(Btn08Activity.class);
                break;
            case R.id.btn_MainActivity_btn09:
                intoNextActivity(Btn09Activity.class);
                break;
        }
    }

    private void intoNextActivity(Class<?> cls) {
        Intent intent = new Intent();
        intent.setClass(activity, cls);
        startActivity(intent);
    }
}
