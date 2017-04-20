package com.example.cuileikun.androidandjava.activity.androidbase;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.example.cuileikun.androidandjava.R;
import com.qk.applibrary.activity.QkActivity;

/**
 * 作者：popular cui
 * 时间：2017/4/18 10:34
 * 功能:androidbase03
 */
public class AndroidBaseThirdActivity extends QkActivity implements View.OnClickListener {

    public static AndroidBaseThirdActivity mInstance = null;
    private Context mContext;
    private Button btn_sqlite;
    private Button btn_listview;
    private Button btn_btn_contact;
    private Button btn_arrayAdapter;

    @Override
    public void initViews() {
        mInstance = this;
        mContext = this;
        btn_sqlite = (Button) findViewById(R.id.btn_sqlite);
        btn_listview = (Button) findViewById(R.id.btn_listview);
        btn_btn_contact = (Button) findViewById(R.id.btn_btn_contact);
        btn_arrayAdapter = (Button) findViewById(R.id.btn_arrayAdapter);
    }

    @Override
    public void addListeners() {
        btn_sqlite.setOnClickListener(AndroidBaseThirdActivity.this);
        btn_listview.setOnClickListener(AndroidBaseThirdActivity.this);
        btn_btn_contact.setOnClickListener(AndroidBaseThirdActivity.this);
        btn_arrayAdapter.setOnClickListener(AndroidBaseThirdActivity.this);

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_android_base_third;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btn_sqlite:
                startActivity(new Intent(AndroidBaseThirdActivity.this,SqliteActivity.class));
                break;
            case R.id.btn_listview:
                startActivity(new Intent(AndroidBaseThirdActivity.this,ListviewActivity.class));
                break;
            case R.id.btn_btn_contact:
                startActivity(new Intent(AndroidBaseThirdActivity.this,ContactActivity.class));
                break;

            case R.id.btn_arrayAdapter:
                startActivity(new Intent(AndroidBaseThirdActivity.this,ArrayAdapterActivity.class));
                break;

        }

    }


}
