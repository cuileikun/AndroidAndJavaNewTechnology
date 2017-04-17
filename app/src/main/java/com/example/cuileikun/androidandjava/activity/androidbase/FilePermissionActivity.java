package com.example.cuileikun.androidandjava.activity.androidbase;

import android.util.Log;
import android.widget.Toast;

import com.example.cuileikun.androidandjava.R;
import com.qk.applibrary.activity.QkActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * 作者：popular cui
 * 时间：2017/4/17 16:54
 * 功能:
 */
public class FilePermissionActivity extends QkActivity{

    @Override
    public void initViews() {
        try {
            File file = new File("/data/data/com.itheima.login.data/files/info.txt");
            BufferedReader bf = new BufferedReader(new FileReader(file ));
            String readLine = bf.readLine();
            bf.close();
            Toast.makeText(this, readLine, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
            Log.d("tag", "异常",e);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_file_permission;
    }
}
