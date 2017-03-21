package com.example.cuileikun.androidandjava.activity.hottechnology;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.cuileikun.androidandjava.R;
import com.qk.applibrary.activity.QkActivity;

/**
 * 作者：popular cui
 * 时间：2017/3/21 14:06
 * 功能:
 */
public class CircleImageViewTestActivity extends QkActivity{

    private ImageView imageView;

    @Override
    public void initViews() {
        imageView = (ImageView) findViewById(R.id.image);
    }
    @Override
    public int getLayoutId() {
        return R.layout.activity_circle_image_view_test;
    }


    public void loadImage(View view) {
        //jpg 格式图片地址
        String url = "http://dynamic-image.yesky.com/1080x-/uploadImages/2013/326/BJMEPKDC0KP4.jpg";

        Glide.with(this)
                .load(url)
                .into(imageView);


    }
}
