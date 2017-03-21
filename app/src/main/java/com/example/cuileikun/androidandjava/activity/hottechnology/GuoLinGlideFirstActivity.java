package com.example.cuileikun.androidandjava.activity.hottechnology;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.cuileikun.androidandjava.R;
import com.qk.applibrary.activity.QkActivity;

/**
 * 作者：popular cui
 * 时间：2017/3/21 10:43
 * 功能:郭霖glide系列第一篇文章
 */
public class GuoLinGlideFirstActivity extends QkActivity {
    public static GuoLinGlideFirstActivity mInstance = null;
    private Context mContext;
    private ImageView imageView;

    @Override
    public void initViews() {
        imageView = (ImageView) findViewById(R.id.image);
    }

    @Override
    public void initData() {
        mInstance = GuoLinGlideFirstActivity.this;
        mContext = this;

    }

    public void loadImage(View view) {
        /**
         * 应用1：加载图片
         */
    /*    // 加载本地图片File file = getImagePath();
        Glide.with(this).load(file).into(imageView);

       // 加载应用资源
        int resource = R.drawable.image;
        Glide.with(this).load(resource).into(imageView);

      // 加载二进制流
        byte[] image = getImageBytes();
        Glide.with(this).load(image).into(imageView);

       // 加载Uri对象
        Uri imageUri = getImageUri();
        Glide.with(this).load(imageUri).into(imageView);*/

//jpg 格式图片
        String url = "http://cn.bing.com/az/hprichbg/rb/Dongdaemun_ZH-CN10736487148_1920x1080.jpg";


//        Glide.with(this).load(url).into(imageView);

        /**
         * 应用2：占位图  异常占位图
         */
//        Glide.with(this)
//                .load(url)
//                .placeholder(R.drawable.loading)//加载到图片之前显示的图片  占位图
//                .into(imageView);


    /*    Glide.with(this)
                .load(url)
                .placeholder(R.drawable.loading)
                .diskCacheStrategy(DiskCacheStrategy.NONE)//配置缓存的
                .into(imageView);*/

    /*    Glide.with(this)
                .load(url)
                .placeholder(R.drawable.loading)//加载到图片之前显示的图片  占位图
                .error(R.drawable.error)//异常占位图
                .diskCacheStrategy(DiskCacheStrategy.NONE)//配置缓存的
                .into(imageView);*/

        /**
         * 应用3：指定图片格式   Glide内部会自动判断图片格式
         */
        //gif图
//        String url = "http://p1.pstatp.com/large/166200019850062839d3";

//        Glide.with(this)
//                .load(url)
//                .placeholder(R.drawable.loading)//加载到图片之前显示的图片  占位图
//                .error(R.drawable.error)//异常占位图
//                .diskCacheStrategy(DiskCacheStrategy.NONE)//配置缓存的
//                .into(imageView);

        /**
         * 应用3.1：指定只加载静态图   如果指定了只能加载动态图片，而传入的图片却是一张静图的话，那么结果自然就只有加载失败
         */
        Glide.with(this)
                .load(url)
                .asBitmap()
                .placeholder(R.drawable.loading)
                .error(R.drawable.error)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(imageView);
        /**
         * 应用3.2：指定只加载gif图
         */
     /*   Glide.with(this)
                .load(url)
                .asGif()
                .placeholder(R.drawable.loading)
                .error(R.drawable.error)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(imageView);*/

        /**
         * 应用4：指定图片大小  也支持指定图片大小
         *
         * 使用Glide，我们就完全不用担心图片内存浪费，甚至是内存溢出的问题。
         * 因为Glide从来都不会直接将图片的完整尺寸全部加载到内存中，而是用多少加载多少。
         * Glide会自动判断ImageView的大小，
         * 然后只将这么大的图片像素加载到内存当中，帮助我们节省内存开支
         */
//        Glide.with(this)
//                .load(url)
//                .placeholder(R.drawable.loading)
//                .error(R.drawable.error)
//                .diskCacheStrategy(DiskCacheStrategy.NONE)
//                .override(100, 100)
//                .into(imageView);


    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_guolin_glise_first;
    }
}
