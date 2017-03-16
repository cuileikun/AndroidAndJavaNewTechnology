package com.example.cuileikun.androidandjava.activity;

import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.cuileikun.androidandjava.R;
import com.example.cuileikun.androidandjava.activity.thirdliarbry.LocateActivity;
import com.example.cuileikun.androidandjava.activity.thirdliarbry.SmsVerificationsActivity;
import com.qk.applibrary.activity.QkActivity;
import com.qk.applibrary.listener.TopbarImplListener;
import com.qk.applibrary.widget.TopbarView;

public class ThirdLiarbryActivity extends QkActivity implements View.OnClickListener {
    public static ThirdLiarbryActivity mInstance = null;
    private TopbarView topbarView;
    private RelativeLayout sms_verification_rl;//1，短信验证
    private RelativeLayout rl_baidu_locate;

    @Override
    public void initViews() {
        mInstance = ThirdLiarbryActivity.this;
        topbarView = (TopbarView) findViewById(R.id.top_bar_view);
        sms_verification_rl = (RelativeLayout) findViewById(R.id.sms_verification_rl);
        rl_baidu_locate = (RelativeLayout) findViewById(R.id.rl_baidu_locate);
    }

    @Override
    public void initData() {
        topbarView.setTopbarTitle("8.0常见三方类库使用");
    }

    @Override
    public void addListeners() {
        topbarView.setTopBarClickListener(topListener);
        sms_verification_rl.setOnClickListener(ThirdLiarbryActivity.this);
        rl_baidu_locate.setOnClickListener(ThirdLiarbryActivity.this);
    }

    private TopbarImplListener topListener = new TopbarImplListener() {
        @Override
        public void leftButtonClick() {
            finish();
        }
    };

    @Override
    public int getLayoutId() {
        return R.layout.activity_third_liarbry;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.sms_verification_rl:
                startActivity(new Intent(ThirdLiarbryActivity.this,SmsVerificationsActivity.class));
                break;
            case R.id.rl_baidu_locate:
                startActivity(new Intent(ThirdLiarbryActivity.this,LocateActivity.class));
                break;
        }

    }
}
