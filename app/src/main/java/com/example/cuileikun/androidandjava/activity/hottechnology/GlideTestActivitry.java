package com.example.cuileikun.androidandjava.activity.hottechnology;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.cuileikun.androidandjava.R;
import com.qk.applibrary.activity.QkActivity;
import com.qk.applibrary.widget.TopbarView;

/**
 * 作者：popular cui
 * 时间：2017/3/21 10:23
 * 功能:Glide
 */
public class GlideTestActivitry extends QkActivity implements View.OnClickListener {
    public static GlideTestActivitry mInstance = null;
    private Context mContext;
    private RelativeLayout guolin_glide_test_first;
    private TopbarView top_bar_view;

    @Override
    public void initViews() {
        mInstance = GlideTestActivitry.this;
        mContext = this;
        guolin_glide_test_first = (RelativeLayout) findViewById(R.id.guolin_glide_test_first);
        top_bar_view = (TopbarView) findViewById(R.id.top_bar_view);
    }

    @Override
    public void initData() {
        top_bar_view.setTopbarTitle("Glide使用系列");
    }

    @Override
    public void addListeners() {
        guolin_glide_test_first.setOnClickListener(GlideTestActivitry.this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_glide_test;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.guolin_glide_test_first:
                startActivity(new Intent(mContext, GuoLinGlideFirstActivity.class));
                break;
        }

    }


}
