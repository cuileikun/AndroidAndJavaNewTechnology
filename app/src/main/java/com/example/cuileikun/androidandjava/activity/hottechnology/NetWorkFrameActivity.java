package com.example.cuileikun.androidandjava.activity.hottechnology;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.cuileikun.androidandjava.R;
import com.qk.applibrary.activity.QkActivity;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 作者：popular cui
 * 时间：2017/2/17 16:23
 * 功能:
 */
public class NetWorkFrameActivity extends QkActivity implements View.OnClickListener {

    private Button ok_http_btn;//

    @Override
    public void initViews() {
        ok_http_btn = (Button) findViewById(R.id.ok_http_btn);
    }

    @Override
    public void addListeners() {
        ok_http_btn.setOnClickListener(NetWorkFrameActivity.this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_net_work_frame;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ok_http_btn:
                doOkHttpGet();
                break;
        }
    }

    /**
     * 使用okHttp进行一次get请求
     */
    private void doOkHttpGet() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://wenwen.sogou.com/z/q703987735.htm")
                .build();
        Call call  = okHttpClient.newCall(request);
        try{
            Response response = call.execute();
            if(response.isSuccessful()){
                //The call was successful.print it to the log
                Log.v("OKHttp",response.body().string());
            }
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
