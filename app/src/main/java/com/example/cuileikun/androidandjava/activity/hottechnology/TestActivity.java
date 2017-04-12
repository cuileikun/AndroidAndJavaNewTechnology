package com.example.cuileikun.androidandjava.activity.hottechnology;

import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.cuileikun.androidandjava.R;
import com.example.cuileikun.androidandjava.activity.CustomView.ApprovalProgressActivity;
import com.example.cuileikun.androidandjava.activity.CustomView.LogisticsProgressActivity;
import com.qk.applibrary.activity.QkActivity;
import com.qk.applibrary.listener.TopbarImplListener;
import com.qk.applibrary.widget.TopbarView;

public class TestActivity extends QkActivity implements View.OnClickListener {

    private TopbarView topbarView;
    private RelativeLayout first_custom_view_rl;//1，物流进度
    private RelativeLayout second_custom_view__rl;//2审批进度


    @Override
    public void initViews() {
        topbarView = (TopbarView) findViewById(R.id.top_bar_view);
        first_custom_view_rl = (RelativeLayout) findViewById(R.id.first_custom_view_rl);
        second_custom_view__rl = (RelativeLayout) findViewById(R.id.second_custom_view__rl);
    }

    @Override
    public void initData() {
        topbarView.setTopbarTitle("7.3自定义控件");

    }

    @Override
    public void addListeners() {
        topbarView.setTopBarClickListener(topListener);
        first_custom_view_rl.setOnClickListener(TestActivity.this);
        second_custom_view__rl.setOnClickListener(TestActivity.this);
    }

    private TopbarImplListener topListener = new TopbarImplListener() {
        @Override
        public void leftButtonClick() {
            finish();
        }
    };


    @Override
    public int getLayoutId() {
        return R.layout.activity_test;
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.first_custom_view_rl:
                startActivity(new Intent(TestActivity.this, LogisticsProgressActivity.class));
                break;

            case R.id.second_custom_view__rl:
                startActivity(new Intent(TestActivity.this, ApprovalProgressActivity.class));
                break;
        }

    }


}
