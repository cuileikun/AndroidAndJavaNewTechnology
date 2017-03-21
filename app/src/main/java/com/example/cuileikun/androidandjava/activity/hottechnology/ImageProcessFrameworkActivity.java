package com.example.cuileikun.androidandjava.activity.hottechnology;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.cuileikun.androidandjava.R;
import com.qk.applibrary.activity.QkActivity;

/**
 * 作者：popular cui
 * 时间：2017/3/21 10:12
 * 功能:
 */
public class ImageProcessFrameworkActivity extends QkActivity implements View.OnClickListener {

    public static ImageProcessFrameworkActivity mInstance=null;
    private Context mContext;
    private RelativeLayout glide_rl;//1，Gilde

    @Override
    public void initViews() {
        mInstance=ImageProcessFrameworkActivity.this;
        mContext=this;
        glide_rl = (RelativeLayout) findViewById(R.id.glide_rl);
    }

    @Override
    public void addListeners() {
        glide_rl.setOnClickListener(ImageProcessFrameworkActivity.this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_image_process_framework;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.glide_rl:
                startActivity(new Intent(mContext,GlideTestActivitry.class));
                break;

        }
    }



}
