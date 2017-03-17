package com.example.cuileikun.androidandjava.activity;

import com.example.cuileikun.androidandjava.R;
import com.qk.applibrary.activity.QkActivity;
import com.qk.applibrary.listener.TopbarImplListener;
import com.qk.applibrary.widget.TopbarView;

public class AndroidBaseActivity extends QkActivity {
    private TopbarView topbarView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_android_base;
    }

    @Override
    public void initViews() {
        topbarView = (TopbarView) findViewById(R.id.top_bar_view);
    }

    @Override
    public void initData() {
        topbarView.setTopbarTitle("3.0Android基础列表");


    }

    @Override
    public void addListeners() {
        topbarView.setTopBarClickListener(topListener);
    }

    private TopbarImplListener topListener = new TopbarImplListener() {
        @Override
        public void leftButtonClick() {
            finish();
        }
    };
}
