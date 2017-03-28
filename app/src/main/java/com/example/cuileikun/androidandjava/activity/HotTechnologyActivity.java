package com.example.cuileikun.androidandjava.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.cuileikun.androidandjava.R;
import com.example.cuileikun.androidandjava.activity.hottechnology.CircleImageViewTestActivity;
import com.example.cuileikun.androidandjava.activity.hottechnology.CityPickerActivity;
import com.example.cuileikun.androidandjava.activity.hottechnology.CustomListViewActivity;
import com.example.cuileikun.androidandjava.activity.hottechnology.PhtotViewTestActivity;
import com.example.cuileikun.androidandjava.activity.hottechnology.ProgramFrameActivity;
import com.example.cuileikun.androidandjava.activity.hottechnology.ReportPictureActivity;
import com.example.cuileikun.androidandjava.activity.hottechnology.TestActivity;
import com.example.cuileikun.androidandjava.activity.hottechnology.TimeDispalyRlActivity;
import com.qk.applibrary.activity.QkActivity;
import com.qk.applibrary.listener.TopbarImplListener;
import com.qk.applibrary.widget.TopbarView;

public class HotTechnologyActivity extends QkActivity implements View.OnClickListener {
    public static HotTechnologyActivity mInstance;
    private TopbarView topbarView;
    private Context mContext;
    private RelativeLayout custom_listview_rl;//自定义listView
    private RelativeLayout time_display_rl;//时间展示demo
    private RelativeLayout testThree;
    private RelativeLayout report_picture;//上传图片demo
    private RelativeLayout programFrame;//框架模块
    private RelativeLayout github_photoview_rl;//github开源项目photoview  https://github.com/chrisbanes/PhotoView
    private RelativeLayout github_circleimageview_rl;//github开源项目CircleImageView  https://github.com/hdodenhof/CircleImageView
    private RelativeLayout github_shengshixian_rl;//github开源项目省市县三级联动  https://github.com/cuileikun/CityPickerWebView

    @Override
    public void initViews() {
        topbarView = (TopbarView) findViewById(R.id.top_bar_view);
        custom_listview_rl = (RelativeLayout) findViewById(R.id.custom_listview_rl);
        time_display_rl = (RelativeLayout) findViewById(R.id.time_display_rl);
        testThree = (RelativeLayout) findViewById(R.id.day_three_rl);
        report_picture = (RelativeLayout) findViewById(R.id.report_picture);
        programFrame = (RelativeLayout) findViewById(R.id.day_five_rl);
        github_photoview_rl = (RelativeLayout) findViewById(R.id.github_photoview_rl);
        github_circleimageview_rl = (RelativeLayout) findViewById(R.id.github_circleimageview_rl);
        github_shengshixian_rl = (RelativeLayout) findViewById(R.id.github_shengshixian_rl);

    }

    @Override
    public void initData() {
        mInstance = this;
        mContext = this;
        topbarView.setTopbarTitle("7.0常见技术点");
    }

    @Override
    public void addListeners() {
        topbarView.setTopBarClickListener(topListener);
        custom_listview_rl.setOnClickListener(HotTechnologyActivity.this);
        time_display_rl.setOnClickListener(HotTechnologyActivity.this);
        testThree.setOnClickListener(HotTechnologyActivity.this);
        report_picture.setOnClickListener(HotTechnologyActivity.this);
        programFrame.setOnClickListener(HotTechnologyActivity.this);
        github_photoview_rl.setOnClickListener(HotTechnologyActivity.this);
        github_circleimageview_rl.setOnClickListener(HotTechnologyActivity.this);
        github_shengshixian_rl.setOnClickListener(HotTechnologyActivity.this);
    }


    private TopbarImplListener topListener = new TopbarImplListener() {
        @Override
        public void leftButtonClick() {
            finish();
        }
    };

    @Override
    public int getLayoutId() {
        return R.layout.activity_hot_technology;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.custom_listview_rl://1，自定义listView
                startActivity(new Intent(mContext, CustomListViewActivity.class));
                break;
            case R.id.time_display_rl://2，根据条件展示时间
                startActivity(new Intent(mContext, TimeDispalyRlActivity.class));
                break;
            case R.id.day_three_rl://3，
                startActivity(new Intent(mContext, TestActivity.class));
                break;
            case R.id.report_picture://4，上传图片demo
                startActivity(new Intent(mContext, ReportPictureActivity.class));
                break;
            case R.id.day_five_rl://5，常用框架
                startActivity(new Intent(mContext, ProgramFrameActivity.class));
                break;
            case R.id.github_photoview_rl://6，github开源项目photoview
                startActivity(new Intent(mContext, PhtotViewTestActivity.class));
                break;

            case R.id.github_circleimageview_rl://7，github开源项目CircleImageView
                startActivity(new Intent(mContext, CircleImageViewTestActivity.class));
                break;

            case R.id.github_shengshixian_rl://8，github开源项目省市县三级联动
                startActivity(new Intent(mContext, CityPickerActivity.class));
                break;
        }
    }
}
