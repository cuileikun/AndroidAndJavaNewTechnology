package com.example.cuileikun.androidandjava.activity.androidbase;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.example.cuileikun.androidandjava.R;
import com.qk.applibrary.activity.QkActivity;

/**
 * 作者：popular cui
 * 时间：2017/4/17 16:28
 * 功能:androidbase 02
 */
public class AndroidBaseSecondActivity extends QkActivity implements View.OnClickListener {

    private Button btn_log;
    private Button btn_user_login_data;
    private Button btn_file_permission;
    private Button btn_user_sdcard_login;
    private Button btn_get_sdcard_size;
    private Button btn_user_sp_login;
    private Button btn_do_xml;

    @Override
    public void initViews() {
        btn_log = (Button) findViewById(R.id.btn_log);
        btn_user_login_data = (Button) findViewById(R.id.btn_user_login_data);
        btn_file_permission = (Button) findViewById(R.id.btn_file_permission);
        btn_user_sdcard_login = (Button) findViewById(R.id.btn_user_sdcard_login);
        btn_get_sdcard_size = (Button) findViewById(R.id.btn_get_sdcard_size);
        btn_user_sp_login = (Button) findViewById(R.id.btn_user_sp_login);
        btn_do_xml = (Button) findViewById(R.id.btn_do_xml);
    }

    @Override
    public void addListeners() {
        btn_log.setOnClickListener(AndroidBaseSecondActivity.this);
        btn_user_login_data.setOnClickListener(AndroidBaseSecondActivity.this);
        btn_file_permission.setOnClickListener(AndroidBaseSecondActivity.this);
        btn_user_sdcard_login.setOnClickListener(AndroidBaseSecondActivity.this);
        btn_get_sdcard_size.setOnClickListener(AndroidBaseSecondActivity.this);
        btn_user_sp_login.setOnClickListener(AndroidBaseSecondActivity.this);
        btn_do_xml.setOnClickListener(AndroidBaseSecondActivity.this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_log:
                startActivity(new Intent(AndroidBaseSecondActivity.this,LogActivity.class));
                break;
            case R.id.btn_user_login_data:
                startActivity(new Intent(AndroidBaseSecondActivity.this,UserLoginDataActivity.class));
                break;
            case R.id.btn_file_permission:
                startActivity(new Intent(AndroidBaseSecondActivity.this,FilePermissionActivity.class));
                break;
            case R.id.btn_user_sdcard_login:
                startActivity(new Intent(AndroidBaseSecondActivity.this,UserLoginSdcardActivity.class));
                break;
            case R.id.btn_get_sdcard_size:
                startActivity(new Intent(AndroidBaseSecondActivity.this,GetSdcardSizeActivity.class));
                break;
            case R.id.btn_user_sp_login:
                startActivity(new Intent(AndroidBaseSecondActivity.this,UserLoginSpActivity.class));
                break;
            case R.id.btn_do_xml:
                startActivity(new Intent(AndroidBaseSecondActivity.this,DoXmlActivity.class));
                break;

        }


    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_android_base_second;
    }


}
