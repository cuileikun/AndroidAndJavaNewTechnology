package com.example.cuileikun.androidandjava.activity.hottechnology;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.cuileikun.androidandjava.R;

import me.leefeng.citypicker.CityPicker;
import me.leefeng.citypicker.CityPickerListener;

/**
 * 作者：popular cui
 * 时间：2017/3/28 10:52
 * 功能:github开源项目省市县三级联动  https://github.com/cuileikun/CityPickerWebView
 */
public class CityPickerActivity extends AppCompatActivity implements CityPickerListener {
    private TextView textView;
    private CityPicker cityPicker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_picker_webview);
        cityPicker = new CityPicker(CityPickerActivity.this, this);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cityPicker.show();
            }
        });
        textView = (TextView) findViewById(R.id.textView);
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cityPicker.close();
            }
        });

    }


    @Override
    public void getCity(final String s) {
        textView.setText(s);
    }

    @Override
    public void onBackPressed() {
        if (cityPicker.isShow()){
            cityPicker.close();
            return;
        }
        super.onBackPressed();
    }
}
