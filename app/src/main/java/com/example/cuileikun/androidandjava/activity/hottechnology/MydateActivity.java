package com.example.cuileikun.androidandjava.activity.hottechnology;

import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cuileikun.androidandjava.R;
import com.example.cuileikun.androidandjava.view.ChangeDatePopwindow;
import com.qk.applibrary.activity.QkActivity;

/**
 * 作者：popular cui
 * 时间：2017/3/28 13:31
 * 功能:年月日三级联动控件
 * csdn下载地址：http://download.csdn.net/detail/hubeiqiyuan/9598546
 */
public class MydateActivity extends QkActivity {

    private TextView selectDate;
    private TextView tv_date;


    @Override
    public int getLayoutId() {
        return R.layout.activity_mydate;
    }

    @Override
    public void initViews() {
        selectDate= (TextView) findViewById(R.id.selectDate);
        tv_date = (TextView) findViewById(R.id.tv_date);
    }


    public void selectDate(View v){
        if (v.getId()==R.id.selectDate){
            selectDate();
        }

    }


    private String[] selectDate() {
        final String[] str = new String[10];
        ChangeDatePopwindow mChangeBirthDialog = new ChangeDatePopwindow(
                this);
        mChangeBirthDialog.setDate("2016", "1", "1");
        mChangeBirthDialog.showAtLocation(selectDate, Gravity.BOTTOM, 0, 0);
        mChangeBirthDialog.setBirthdayListener(new ChangeDatePopwindow.OnBirthListener() {

            @Override
            public void onClick(String year, String month, String day) {
                // TODO Auto-generated method stub
                Toast.makeText(MydateActivity.this,year + "-" + month + "-" + day,Toast.LENGTH_LONG).show();
                StringBuilder sb = new StringBuilder();
                sb.append(year.substring(0, year.length() - 1)).append("-").append(month.substring(0, day.length() - 1)).append("-").append(day);
                str[0] = year + "-" + month + "-" + day;
                str[1] = sb.toString();
//                selectDate.setText(year + "-" + month + "-" + day);
                tv_date.setText(year + "-" + month + "-" + day);

            }
        });
        return str;
    }
}
