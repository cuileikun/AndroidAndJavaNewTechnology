package com.example.cuileikun.androidandjava.activity.androidbase;

import android.graphics.Color;
import android.os.Environment;
import android.os.StatFs;
import android.text.format.Formatter;
import android.view.View;
import android.widget.TextView;

import com.example.cuileikun.androidandjava.R;
import com.qk.applibrary.activity.QkActivity;

import java.io.File;

/**
 * 作者：popular cui
 * 时间：2017/4/17 16:56
 * 功能:
 */
public class GetSdcardSizeActivity extends QkActivity{
    private TextView tv_info1;
    private TextView tv_info2;

    @Override
    public void initViews() {
        tv_info1 = (TextView) findViewById(R.id.tv_info1);
        tv_info2 = (TextView) findViewById(R.id.tv_info2);
    }
    /**
     * 第一种方式获取sdcard空间大小 通过File
     *
     * @param view
     */
    public void click1(View view) {
        // 获取sdcard的状态
        String state = Environment.getExternalStorageState();
        // 判断sdcard的状态是否已经挂载
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            // 1.总空间2.可用空间
            File directory = Environment.getExternalStorageDirectory();
            // 获取总空间 单位 byte
            long totalSpace = directory.getTotalSpace();
            // 获取可用空间 byte
            long usableSpace = directory.getUsableSpace();
            // 格式化数据
            String total = Formatter.formatFileSize(this, totalSpace);
            String usable = Formatter.formatFileSize(this, usableSpace);
            // 显示数据
            tv_info1.setText("总空间:" + total + "\n" + "可用空间:" + usable);

        } else {
            tv_info1.setTextColor(Color.RED);
            tv_info1.setText("sdcard不可用");
        }
    }

    /**
     * 通过StatFs获取sdcard大小
     *
     * @param view
     */
    public void click2(View view) {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            // 创建StatFs对象
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getAbsolutePath());
            // 获取总块个数
            int blockCount = statFs.getBlockCount();
            // 获取可用块个数
            int availableBlocks = statFs.getAvailableBlocks();
            // 获取块大小
            int blockSize = statFs.getBlockSize();
            // 计算总空间
            long totalSpace = blockCount * blockSize;
            // 计算可用空间
            long usableSpace = availableBlocks * blockSize;
            // 格式化数据
            String total = Formatter.formatFileSize(this, totalSpace);
            String usable = Formatter.formatFileSize(this, usableSpace);
            // 显示数据
            tv_info2.setText("总空间:" + total + "\n" + "可用空间:" + usable);

        } else {
            tv_info2.setTextColor(Color.RED);
            tv_info2.setText("sdcard不可用");
        }
    }
    @Override
    public int getLayoutId() {
        return R.layout.activity_get_sdcard_size;
    }
}
