package com.example.cuileikun.androidandjava.activity.CustomView;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.cuileikun.androidandjava.R;
import com.example.cuileikun.androidandjava.bean.StepModel;
import com.example.cuileikun.androidandjava.view.MyVerticalStepView;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：popular cui
 * 时间：2017/4/10 17:13
 * 功能:
 */
public class LogisticsProgressActivity extends AppCompatActivity {
    private MyVerticalStepView mStepView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ogistics_progress);
        mStepView= (MyVerticalStepView) findViewById(R.id.stepview);
        init();
    }
    private void init() {
        List<StepModel> datas=new ArrayList<>();
        StepModel step1=new StepModel("您已提交订单，等待系统确认",StepModel.STATE_COMPLETED);
        StepModel step2=new StepModel("订单已确认并打包，预计12月16日送达",StepModel.STATE_COMPLETED);
        StepModel step3=new StepModel("包裹正在路上",StepModel.STATE_COMPLETED);
        StepModel step4=new StepModel("包裹正在派送",StepModel.STATE_PROCESSING);
        StepModel step5=new StepModel("感谢光临涂涂女装（店铺号85833577），淘宝店铺，关注店铺更多动态尽在微淘动态！",StepModel.STATE_DEFAULT);
        datas.add(step1);
        datas.add(step2);
        datas.add(step3);
        datas.add(step4);
        datas.add(step5);
        mStepView.setmDatas(datas);

    }
}
