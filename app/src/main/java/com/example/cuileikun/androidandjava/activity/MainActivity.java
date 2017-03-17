package com.example.cuileikun.androidandjava.activity;

import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.cuileikun.androidandjava.R;
import com.qk.applibrary.activity.QkActivity;

public class MainActivity extends QkActivity implements View.OnClickListener {
    public static MainActivity mInstace = null;
    private Context mContext;
    private RelativeLayout topbarView;
    private RelativeLayout javaRl;    //1java项目
    private RelativeLayout androidBaseRl;  //2Android基础
    private RelativeLayout androidProgramRl;   //3Android项目
    private RelativeLayout transsionProgramRl; //4传音控股项目
    private RelativeLayout qkProgramRl;     //5青客项目
    private RelativeLayout brotherProgramRl;   //6同学项目
    private RelativeLayout hotTechnologyRl;  //7热门技术
    private RelativeLayout thirdLiarbryRl;//8三方类库
    private RelativeLayout daShenDemo;//大神demo


    /**
     * 加载布局
     */
    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initViews() {
        mInstace = this;
        mContext = this;
        topbarView = (RelativeLayout) findViewById(R.id.rl_top_bar_view);
        javaRl = (RelativeLayout) findViewById(R.id.java_rl);
        androidBaseRl = (RelativeLayout) findViewById(R.id.android_base_rl);
        androidProgramRl = (RelativeLayout) findViewById(R.id.android_program_rl);
        transsionProgramRl = (RelativeLayout) findViewById(R.id.transsion_program_rl);
        qkProgramRl = (RelativeLayout) findViewById(R.id.qk_program_rl);
        brotherProgramRl = (RelativeLayout) findViewById(R.id.brother_program_tl);
        hotTechnologyRl = (RelativeLayout) findViewById(R.id.hot_technology_rl);
        thirdLiarbryRl = (RelativeLayout) findViewById(R.id.third_liarbry_rl);
        daShenDemo = (RelativeLayout) findViewById(R.id.dashen_demo_rl);
    }

    @Override
    public void initData() {

    }

    @Override
    public void addListeners() {
        javaRl.setOnClickListener(MainActivity.this);
        androidBaseRl.setOnClickListener(MainActivity.this);
        androidProgramRl.setOnClickListener(MainActivity.this);
        transsionProgramRl.setOnClickListener(MainActivity.this);
        qkProgramRl.setOnClickListener(MainActivity.this);
        brotherProgramRl.setOnClickListener(MainActivity.this);
        hotTechnologyRl.setOnClickListener(MainActivity.this);
        thirdLiarbryRl.setOnClickListener(MainActivity.this);
        daShenDemo.setOnClickListener(MainActivity.this);

    }

    private long mExitTime = 0;

    // 程序退出时的操作
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                // Object mHelperUtils;
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();
            } else {
                MainActivity.mInstace.finish();
                android.os.Process.killProcess(android.os.Process.myPid());

            }

        }
        return true;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.java_rl:
                startActivity(new Intent(mContext,JavaActivity.class));
                break;
            case R.id.android_base_rl:
                startActivity(new Intent(mContext,AndroidBaseActivity.class));
                break;
            case R.id.android_program_rl:
                startActivity(new Intent(mContext,AndroidProgramActivity.class));
                break;
            case R.id.transsion_program_rl:
                startActivity(new Intent(mContext,TranssionProgramActivity.class));
                break;
            case R.id.qk_program_rl:
                startActivity(new Intent(mContext,QKProgramActivity.class));
                break;
            case R.id.brother_program_tl:
                startActivity(new Intent(mContext,BrotherProgramActivity.class));
                break;
            case R.id.hot_technology_rl:
                startActivity(new Intent(mContext,HotTechnologyActivity.class));
                break;
            case R.id.third_liarbry_rl:
                startActivity(new Intent(mContext,ThirdLiarbryActivity.class));
                break;
            case R.id.dashen_demo_rl:
                startActivity(new Intent(mContext,DaShenDemoActivity.class));
                break;

        }

    }
}
