package com.example.cuileikun.androidandjava.activity.androidbase;

import android.util.Log;
import android.view.View;

import com.example.cuileikun.androidandjava.R;
import com.qk.applibrary.activity.QkActivity;

/**
 * 作者：popular cui
 * 时间：2017/4/17 16:51
 * 功能:
 */
public class LogActivity extends QkActivity {
    private static final String TAG = "LogActivity";

    @Override
    public int getLayoutId() {
        return R.layout.activity_log;
    }

    /**
     * verbose
     */
    public void click1(View view) {
        Log.v(TAG, "我是verbose");
    }

    /**
     * debug
     */
    public void click2(View view) {
        Log.d(TAG, "我是debug");
    }

    /**
     * info
     */
    public void click3(View view) {
        Log.i(TAG, "我是info");
    }

    /**
     * warn
     */
    public void click4(View view) {
        Log.w(TAG, "我是warn");
    }

    /**
     * error
     */
    public void click5(View view) {
        Log.e(TAG, "我是error");
    }

    /**
     * System.out
     */
    public void click6(View view) {
        System.out.println("我是System.out");
    }

    /**
     * System.err
     */
    public void click7(View view) {
        System.err.println("我是System.err");
    }

    /**
     * e.printStackTrace();
     */
    public void click8(View view) {
        try {
            int a = 1 / 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}
