package com.example.cuileikun.androidandjava.activity.hottechnology;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.cuileikun.androidandjava.R;
import com.qk.applibrary.activity.QkActivity;

/**
 * 作者：popular cui
 * 时间：2017/3/21 13:56
 * 功能:PhtotView 随手指移动而缩放图片大小的类库
 */
public class PhtotViewTestActivity extends QkActivity{

    private ImageView imageView;

    @Override
    public void initViews() {
        imageView = (ImageView) findViewById(R.id.image);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_photoview_test;
    }

    public void loadImage(View view) {
    //jpg 格式图片地址
        String url = "http://dynamic-image.yesky.com/1080x-/uploadImages/2013/326/BJMEPKDC0KP4.jpg";

        Glide.with(this)
                .load(url)
                .placeholder(R.drawable.loading)//加载到图片之前显示的图片  占位图
                .error(R.drawable.error)//异常占位图
                .into(imageView);
    }
}
