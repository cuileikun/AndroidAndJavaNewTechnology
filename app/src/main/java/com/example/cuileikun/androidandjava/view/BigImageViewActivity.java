package com.example.cuileikun.androidandjava.view;

import android.content.Intent;
import android.support.v4.view.ViewPager;

import com.example.cuileikun.androidandjava.R;
import com.example.cuileikun.androidandjava.activity.Constant.QkConstant;
import com.example.cuileikun.androidandjava.adapter.BigImageViewPageAdapter;
import com.example.cuileikun.androidandjava.base.QKFragmentActivity;

import java.util.List;

/**
 * Created by april on 16/11/10.
 */

public class BigImageViewActivity extends QKFragmentActivity {
    private ViewPager viewPager;
    private List<String> urlList;
    private BigImageViewPageAdapter adapter;
    @Override
    public void initViews() {
        super.initViews();
        viewPager = (ViewPager) findViewById(R.id.viewpager);
    }

    @Override
    public void initData() {
        super.initData();
        Intent intent = getIntent();
        urlList = intent.getStringArrayListExtra("urlList");
        int position = intent.getIntExtra(QkConstant.URLPOSITION,0);
        adapter = new BigImageViewPageAdapter(getSupportFragmentManager(), urlList);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(position);
    }

    @Override
    public void addListeners() {
        super.addListeners();
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_galley;
    }
}
