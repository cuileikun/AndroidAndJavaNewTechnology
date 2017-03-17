package com.example.cuileikun.androidandjava.activity.hottechnology;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.cuileikun.androidandjava.R;
import com.qk.applibrary.activity.QkActivity;
import com.qk.applibrary.listener.TopbarImplListener;
import com.qk.applibrary.widget.TopbarView;

/**
 * 作者：popular cui
 * 时间：2017/1/16 15:05
 * 功能:界面框架activity
 */
public class InterfaceFrameActivity extends QkActivity implements View.OnClickListener {

    public static InterfaceFrameActivity mInstance = null;
    private Context mContext;
    private RelativeLayout fragment_activity_viewpager_rl;
    private TopbarView topbarView;
    private RelativeLayout radio_group_view_pager_rl;

    @Override
    public int getLayoutId() {
        return R.layout.activity_interface_frame;
    }

    @Override
    public void initViews() {
        topbarView = (TopbarView) findViewById(R.id.top_bar_view);
        fragment_activity_viewpager_rl = (RelativeLayout) findViewById(R.id.fragment_activity_viewpager_rl);
        radio_group_view_pager_rl = (RelativeLayout) findViewById(R.id.radio_group_view_pager_rl);
    }

    @Override
    public void initData() {
        mInstance = this;
        mContext = this;
        topbarView.setTopbarTitle("界面框架");
    }

    @Override
    public void addListeners() {
        fragment_activity_viewpager_rl.setOnClickListener(InterfaceFrameActivity.this);
        radio_group_view_pager_rl.setOnClickListener(InterfaceFrameActivity.this);
        topbarView.setTopBarClickListener(topListener);
    }
    private TopbarImplListener topListener = new TopbarImplListener() {
        @Override
        public void leftButtonClick() {
            finish();
        }
    };
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_activity_viewpager_rl:
                startActivity(new Intent(mContext, InterfaceFrameFirstActivity.class));
                break;
            case R.id.radio_group_view_pager_rl:
                startActivity(new Intent(mContext, InterfaceFrameSecondActivity.class));
                break;
        }
    }
}
