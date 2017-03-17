package com.example.cuileikun.androidandjava.view;

import android.content.Intent;
import android.view.View;

import com.example.cuileikun.androidandjava.R;
import com.qk.applibrary.activity.QkActivity;
import com.qk.applibrary.widget.DownloadImageView;

/**
 * Created by april on 16/1/4.
 */
public class DisplayImageActivity extends QkActivity {
    private DownloadImageView imageView;
    @Override
    public void initViews() {
        super.initViews();
        imageView = (DownloadImageView) findViewById(R.id.display_image_view);

    }

    @Override
    public void initData() {
        super.initData();
        Intent intent = getIntent();
        String path = intent.getStringExtra("path");
        imageView.loadNativePic(path);
        imageView.loadNetworkPic(path);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_display_image;
    }

    @Override
    public void addListeners() {
        super.addListeners();
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
