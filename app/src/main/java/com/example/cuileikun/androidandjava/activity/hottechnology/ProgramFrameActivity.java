package com.example.cuileikun.androidandjava.activity.hottechnology;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.cuileikun.androidandjava.R;
import com.qk.applibrary.activity.QkActivity;

/**
 * 作者：popular cui
 * 时间：2017/1/16 14:45
 * 功能:常用框架 包括界面框架+网络请求框架+刷新框架
 */
public class ProgramFrameActivity extends QkActivity implements View.OnClickListener {
    public static ProgramFrameActivity mInstance = null;
    private Context mContext;
    private RelativeLayout interface_frame_rl;//1,界面框架
    private RelativeLayout network_frame_rl;//3，网络请求框架
    private RelativeLayout image_processing_framework_rl;//图片处理框架

    @Override
    public int getLayoutId() {
        return R.layout.activity_program_frame;

    }

    @Override
    public void initViews() {
        interface_frame_rl = (RelativeLayout) findViewById(R.id.interface_frame_rl);
        network_frame_rl = (RelativeLayout) findViewById(R.id.network_frame_rl);
        image_processing_framework_rl = (RelativeLayout) findViewById(R.id.image_processing_framework_rl);
    }

    @Override
    public void initData() {
        mInstance = this;
        mContext = this;
    }

    @Override
    public void addListeners() {
        interface_frame_rl.setOnClickListener(ProgramFrameActivity.this);
        network_frame_rl.setOnClickListener(ProgramFrameActivity.this);
        image_processing_framework_rl.setOnClickListener(ProgramFrameActivity.this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.interface_frame_rl://1，界面框架
                startActivity(new Intent(mContext,InterfaceFrameActivity.class));
                break;
            case R.id.network_frame_rl://3，网络请求框架
                startActivity(new Intent(mContext,NetWorkFrameActivity.class));
                break;
            case R.id.image_processing_framework_rl://5，图片处理框架
                startActivity(new Intent(mContext,ImageProcessFrameworkActivity.class));
                break;
        }
    }
}
