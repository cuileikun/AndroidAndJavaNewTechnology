package com.example.cuileikun.androidandjava.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.cuileikun.androidandjava.R;
import com.example.cuileikun.androidandjava.activity.brotherprogram.FootBallActivity;
import com.qk.applibrary.activity.QkActivity;

public class BrotherProgramActivity extends QkActivity implements View.OnClickListener {
    private Context mContext;
    public BrotherProgramActivity mInstance;
    private RelativeLayout zhipengprogram;//1，志鹏足球项目

    @Override
    public int getLayoutId() {
        return R.layout.activity_brother_program;
    }

    @Override
    public void initViews() {
        mContext=this;
        mInstance=this;
        zhipengprogram = (RelativeLayout) findViewById(R.id.zhipengprogram);
    }

    @Override
    public void addListeners() {
        zhipengprogram.setOnClickListener(BrotherProgramActivity.this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.zhipengprogram:
                startActivity(new Intent(mContext,FootBallActivity.class));
                break;
        }
    }
}
