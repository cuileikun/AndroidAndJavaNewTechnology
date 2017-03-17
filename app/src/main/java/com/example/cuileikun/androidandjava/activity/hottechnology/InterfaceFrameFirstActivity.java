package com.example.cuileikun.androidandjava.activity.hottechnology;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.cuileikun.androidandjava.R;
import com.example.cuileikun.androidandjava.activity.Constant.QkConstant;
import com.example.cuileikun.androidandjava.activity.MainActivity;
import com.example.cuileikun.androidandjava.adapter.CustomViewPagerAdapter;
import com.example.cuileikun.androidandjava.fragment.BeiJingFragment;
import com.example.cuileikun.androidandjava.fragment.HaiNanFragment;
import com.example.cuileikun.androidandjava.fragment.HeBeiFragment;
import com.example.cuileikun.androidandjava.fragment.HeNanFragment;
import com.example.cuileikun.androidandjava.fragment.ShangHaiFragment;
import com.example.cuileikun.androidandjava.fragment.TianJinFragment;
import com.example.cuileikun.androidandjava.fragment.base.AddressFragment;
import com.example.cuileikun.androidandjava.fragment.base.QKFragmentActivity;
import com.qk.applibrary.listener.TopbarImplListener;
import com.qk.applibrary.util.CommonUtil;
import com.qk.applibrary.util.SharedPreferencesUtil;
import com.qk.applibrary.widget.TopbarView;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：popular cui
 * 时间：2017/1/16 15:16
 * 功能:界面框架第一个例子
 */
public class InterfaceFrameFirstActivity extends QKFragmentActivity implements View.OnClickListener {

    public static InterfaceFrameFirstActivity mInstance = null;
    private Context mContext;
    private TopbarView topbarView;
    private ViewPager mViewPager;
    private LinearLayout first_item_rl;
    private LinearLayout second_item_rl;
    private LinearLayout third_item_rl;
    private LinearLayout fourth_item_rl;
    private LinearLayout sixth_item_rl;
    private LinearLayout fifth_item_rl;

    private TextView first_item_title_tv;
    private TextView second_item_title_tv;
    private TextView third_item_title_tv;
    private TextView fourth_item_title_tv;
    private TextView fifth_item_title_tv;
    private TextView sixth_item_title_tv;

    private TextView tv_divider_1;
    private TextView tv_divider_2;
    private TextView tv_divider_3;
    private TextView tv_divider_4;
    private TextView tv_divider_5;
    private TextView tv_divider_6;
    private RelativeLayout back_main;

    private List<AddressFragment> fragments = new ArrayList<>();//三个页面集合
    private BeiJingFragment mBeiJingFragment;//1，北京fragment
    private ShangHaiFragment mShangHaiFragment;//2，上海fragment
    private HeBeiFragment mHeBeiFragment;//3，河北fragment
    private HeNanFragment mHeNanFragment;//4，河南fragment
    private TianJinFragment mTianJinFragment;//5，天津fragment
    private HaiNanFragment mHaiNanFragment;//6，海南fragment
    private Handler handler;


    @Override
    public void initViews() {

        mContext = this;
        mInstance = this;
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 66:
                        String setting = SharedPreferencesUtil.getSetting(QkConstant.SharedPreferencesKeyDef.FILE_NAME, mContext,
                                QkConstant.SharedPreferencesKeyDef.CHANGLIANGZHI);
                        String userId=setting;
                        CommonUtil.sendToast(mContext,userId);
                }
            }
        };
        topbarView = (TopbarView) findViewById(R.id.top_bar_view);
        first_item_rl = (LinearLayout) findViewById(R.id.first_item_rl);
        second_item_rl = (LinearLayout) findViewById(R.id.second_item_rl);
        third_item_rl = (LinearLayout) findViewById(R.id.third_item_rl);
        fourth_item_rl = (LinearLayout) findViewById(R.id.fourth_item_rl);
        fifth_item_rl = (LinearLayout) findViewById(R.id.fifth_item_rl);
        sixth_item_rl = (LinearLayout) findViewById(R.id.sixth_item_rl);

        first_item_title_tv = (TextView) findViewById(R.id.first_item_title_tv);
        second_item_title_tv = (TextView) findViewById(R.id.second_item_title_tv);
        third_item_title_tv = (TextView) findViewById(R.id.third_item_title_tv);
        fourth_item_title_tv = (TextView) findViewById(R.id.fourth_item_title_tv);
        fifth_item_title_tv = (TextView) findViewById(R.id.fifth_item_title_tv);
        sixth_item_title_tv = (TextView) findViewById(R.id.sixth_item_title_tv);

        tv_divider_1 = (TextView) findViewById(R.id.tv_divider_1);
        tv_divider_2 = (TextView) findViewById(R.id.tv_divider_2);
        tv_divider_3 = (TextView) findViewById(R.id.tv_divider_3);
        tv_divider_4 = (TextView) findViewById(R.id.tv_divider_4);
        tv_divider_5 = (TextView) findViewById(R.id.tv_divider_5);
        tv_divider_6 = (TextView) findViewById(R.id.tv_divider_6);

        mViewPager = (ViewPager) findViewById(R.id.test_viewpager);
        back_main = (RelativeLayout) findViewById(R.id.back_main);

        mBeiJingFragment = new BeiJingFragment();
        mShangHaiFragment = new ShangHaiFragment();
        mHeBeiFragment = new HeBeiFragment();
        mHeNanFragment = new HeNanFragment();
        mTianJinFragment = new TianJinFragment();
        mHaiNanFragment = new HaiNanFragment(handler,mContext);
        fragments.add(mBeiJingFragment);
        fragments.add(mShangHaiFragment);
        fragments.add(mHeBeiFragment);
        fragments.add(mHeNanFragment);
        fragments.add(mTianJinFragment);
        fragments.add(mHaiNanFragment);

        mViewPager.setAdapter(new CustomViewPagerAdapter(getSupportFragmentManager(), fragments));


    }

    @Override
    public void initData() {
        topbarView.setTopbarTitle("第一个界面框架demo");
    }

    @Override
    public void addListeners() {
        topbarView.setTopBarClickListener(topListener);

        mViewPager.addOnPageChangeListener(new viewPagerPageChangeListener());
        first_item_rl.setOnClickListener(InterfaceFrameFirstActivity.this);
        second_item_rl.setOnClickListener(InterfaceFrameFirstActivity.this);
        third_item_rl.setOnClickListener(InterfaceFrameFirstActivity.this);
        fourth_item_rl.setOnClickListener(InterfaceFrameFirstActivity.this);
        fifth_item_rl.setOnClickListener(InterfaceFrameFirstActivity.this);
        sixth_item_rl.setOnClickListener(InterfaceFrameFirstActivity.this);

        back_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//那么把首页之前的所有Activity都关掉
                startActivity(intent);
                finish();

            }
        });
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
            case R.id.first_item_rl:
                mViewPager.setCurrentItem(0);
                break;
            case R.id.second_item_rl:
                mViewPager.setCurrentItem(1);
                break;
            case R.id.third_item_rl:
                mViewPager.setCurrentItem(2);
                break;
            case R.id.fourth_item_rl:
                mViewPager.setCurrentItem(3);
                break;
            case R.id.fifth_item_rl:
                mViewPager.setCurrentItem(4);
                break;
            case R.id.sixth_item_rl:
                mViewPager.setCurrentItem(5);
                break;
        }
    }


    class viewPagerPageChangeListener extends ViewPager.SimpleOnPageChangeListener {

        @Override
        public void onPageSelected(int position) {
            switch (position) {
                case 0:
                    first_item_title_tv.setTextColor(Color.parseColor("#02ab82"));
                    second_item_title_tv.setTextColor(Color.parseColor("#333333"));
                    third_item_title_tv.setTextColor(Color.parseColor("#333333"));
                    fourth_item_title_tv.setTextColor(Color.parseColor("#333333"));
                    fifth_item_title_tv.setTextColor(Color.parseColor("#333333"));
                    sixth_item_title_tv.setTextColor(Color.parseColor("#333333"));
                    tv_divider_1.setVisibility(View.VISIBLE);
                    tv_divider_2.setVisibility(View.INVISIBLE);
                    tv_divider_3.setVisibility(View.INVISIBLE);
                    tv_divider_4.setVisibility(View.INVISIBLE);
                    tv_divider_5.setVisibility(View.INVISIBLE);
                    tv_divider_6.setVisibility(View.INVISIBLE);
                    break;
                case 1:
                    first_item_title_tv.setTextColor(Color.parseColor("#333333"));
                    second_item_title_tv.setTextColor(Color.parseColor("#02ab82"));
                    third_item_title_tv.setTextColor(Color.parseColor("#333333"));
                    fourth_item_title_tv.setTextColor(Color.parseColor("#333333"));
                    fifth_item_title_tv.setTextColor(Color.parseColor("#333333"));
                    sixth_item_title_tv.setTextColor(Color.parseColor("#333333"));
                    tv_divider_1.setVisibility(View.INVISIBLE);
                    tv_divider_2.setVisibility(View.VISIBLE);
                    tv_divider_3.setVisibility(View.INVISIBLE);
                    tv_divider_4.setVisibility(View.INVISIBLE);
                    tv_divider_5.setVisibility(View.INVISIBLE);
                    tv_divider_6.setVisibility(View.INVISIBLE);
                    break;
                case 2:
                    first_item_title_tv.setTextColor(Color.parseColor("#333333"));
                    second_item_title_tv.setTextColor(Color.parseColor("#333333"));
                    third_item_title_tv.setTextColor(Color.parseColor("#02ab82"));
                    fourth_item_title_tv.setTextColor(Color.parseColor("#333333"));
                    fifth_item_title_tv.setTextColor(Color.parseColor("#333333"));
                    sixth_item_title_tv.setTextColor(Color.parseColor("#333333"));
                    tv_divider_1.setVisibility(View.INVISIBLE);
                    tv_divider_2.setVisibility(View.INVISIBLE);
                    tv_divider_3.setVisibility(View.VISIBLE);
                    tv_divider_4.setVisibility(View.INVISIBLE);
                    tv_divider_5.setVisibility(View.INVISIBLE);
                    tv_divider_6.setVisibility(View.INVISIBLE);
                    break;
                case 3:
                    first_item_title_tv.setTextColor(Color.parseColor("#333333"));
                    second_item_title_tv.setTextColor(Color.parseColor("#333333"));
                    third_item_title_tv.setTextColor(Color.parseColor("#333333"));
                    fourth_item_title_tv.setTextColor(Color.parseColor("#02ab82"));
                    fifth_item_title_tv.setTextColor(Color.parseColor("#333333"));
                    sixth_item_title_tv.setTextColor(Color.parseColor("#333333"));
                    tv_divider_1.setVisibility(View.INVISIBLE);
                    tv_divider_2.setVisibility(View.INVISIBLE);
                    tv_divider_3.setVisibility(View.INVISIBLE);
                    tv_divider_4.setVisibility(View.VISIBLE);
                    tv_divider_5.setVisibility(View.INVISIBLE);
                    tv_divider_6.setVisibility(View.INVISIBLE);
                    break;
                case 4:
                    first_item_title_tv.setTextColor(Color.parseColor("#333333"));
                    second_item_title_tv.setTextColor(Color.parseColor("#333333"));
                    third_item_title_tv.setTextColor(Color.parseColor("#333333"));
                    fourth_item_title_tv.setTextColor(Color.parseColor("#333333"));
                    fifth_item_title_tv.setTextColor(Color.parseColor("#02ab82"));
                    sixth_item_title_tv.setTextColor(Color.parseColor("#333333"));
                    tv_divider_1.setVisibility(View.INVISIBLE);
                    tv_divider_2.setVisibility(View.INVISIBLE);
                    tv_divider_3.setVisibility(View.INVISIBLE);
                    tv_divider_4.setVisibility(View.INVISIBLE);
                    tv_divider_5.setVisibility(View.VISIBLE);
                    tv_divider_6.setVisibility(View.INVISIBLE);
                    break;
                case 5:
                    first_item_title_tv.setTextColor(Color.parseColor("#333333"));
                    second_item_title_tv.setTextColor(Color.parseColor("#333333"));
                    third_item_title_tv.setTextColor(Color.parseColor("#333333"));
                    fourth_item_title_tv.setTextColor(Color.parseColor("#333333"));
                    fifth_item_title_tv.setTextColor(Color.parseColor("#333333"));
                    sixth_item_title_tv.setTextColor(Color.parseColor("#02ab82"));
                    tv_divider_1.setVisibility(View.INVISIBLE);
                    tv_divider_2.setVisibility(View.INVISIBLE);
                    tv_divider_3.setVisibility(View.INVISIBLE);
                    tv_divider_4.setVisibility(View.INVISIBLE);
                    tv_divider_5.setVisibility(View.INVISIBLE);
                    tv_divider_6.setVisibility(View.VISIBLE);
                    break;

            }
        }

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_interface_frame_first;
    }
}
