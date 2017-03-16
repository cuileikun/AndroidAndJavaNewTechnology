package com.example.cuileikun.androidandjava.activity.hottechnology;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioButton;

import com.example.cuileikun.androidandjava.R;
import com.example.cuileikun.androidandjava.adapter.CustomViewPagerAdapter;
import com.example.cuileikun.androidandjava.fragment.BorrowFragment;
import com.example.cuileikun.androidandjava.fragment.HelpFragment;
import com.example.cuileikun.androidandjava.fragment.SearchFragment;
import com.example.cuileikun.androidandjava.fragment.StoreFragment;
import com.example.cuileikun.androidandjava.fragment.base.QKFragmentActivity;
import com.qk.applibrary.listener.TopbarImplListener;
import com.qk.applibrary.widget.TopbarView;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：popular cui
 * 时间：2017/1/16 17:36
 * 功能:
 */
public class InterfaceFrameSecondActivity extends QKFragmentActivity implements View.OnClickListener {
    private TopbarView topbarView;
    private RadioButton rb_search;//约球按钮
    private RadioButton rb_store;//球队按钮
    private RadioButton rb_borrowed;//发现按钮rb_borrowed
    private RadioButton rb_help;//我的按钮

    private ViewPager mViewPager;

    private List<Fragment> fragments = new ArrayList<>();
    private SearchFragment mSearchFragment;//约球fragment
    private StoreFragment mStoreFragment;//球队fragment
    private BorrowFragment mBorrowedFragment;//发现fragment
    private HelpFragment mHelpFragment;//我的fragment

    @Override
    public void initViews() {
        topbarView = (TopbarView) findViewById(R.id.top_bar_view);

        rb_search = (RadioButton) findViewById(R.id.rb_search);
        rb_store = (RadioButton) findViewById(R.id.rb_store);
        rb_borrowed = (RadioButton) findViewById(R.id.rb_borrowed);
        rb_help = (RadioButton) findViewById(R.id.rb_help);
        mViewPager = (ViewPager) findViewById(R.id.test_viewpager);

        mSearchFragment = new SearchFragment();
        mStoreFragment = new StoreFragment();
        mBorrowedFragment = new BorrowFragment();
        mHelpFragment = new HelpFragment();
        fragments.add(mSearchFragment);
        fragments.add(mStoreFragment);
        fragments.add(mBorrowedFragment);
        fragments.add(mHelpFragment);
        mViewPager.setAdapter(new CustomViewPagerAdapter(getSupportFragmentManager(), fragments));

    }

    @Override
    public void initData() {
        topbarView.setTopbarTitle("第二个界面框架demo");
        mViewPager.setOffscreenPageLimit(1);
        mViewPager.setCurrentItem(0);
        rb_search.setChecked(Boolean.TRUE);
    }

    @Override
    public void addListeners() {
        topbarView.setTopBarClickListener(topListener);
        mViewPager.addOnPageChangeListener(new viewPagerPageChangeListener());
        rb_search.setOnClickListener(InterfaceFrameSecondActivity.this);
        rb_store.setOnClickListener(InterfaceFrameSecondActivity.this);
        rb_borrowed.setOnClickListener(InterfaceFrameSecondActivity.this);
        rb_help.setOnClickListener(InterfaceFrameSecondActivity.this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rb_search:
                mViewPager.setCurrentItem(0);
                break;
            case R.id.rb_store:
                mViewPager.setCurrentItem(1);
                break;
            case R.id.rb_borrowed:
                mViewPager.setCurrentItem(2);
                break;
            case R.id.rb_help:
                mViewPager.setCurrentItem(3);
                break;
        }
    }

    class viewPagerPageChangeListener extends ViewPager.SimpleOnPageChangeListener {

        @Override
        public void onPageSelected(int position) {
            switch (position) {
                case 0:
                    rb_search.setChecked(Boolean.TRUE);
                    break;
                case 1:
                    rb_store.setChecked(Boolean.TRUE);
                    break;
                case 2:
                    rb_borrowed.setChecked(Boolean.TRUE);
                    break;
                case 3:
                    rb_help.setChecked(Boolean.TRUE);
                    break;
            }
        }

    }


    private TopbarImplListener topListener = new TopbarImplListener() {
        @Override
        public void leftButtonClick() {
            finish();
        }
    };

    @Override
    public int getLayoutId() {
        return R.layout.activity_interface_frame_second;
    }
}
