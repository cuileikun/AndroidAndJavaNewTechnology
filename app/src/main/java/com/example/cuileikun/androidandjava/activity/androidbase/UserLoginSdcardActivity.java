package com.example.cuileikun.androidandjava.activity.androidbase;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cuileikun.androidandjava.R;
import com.qk.applibrary.activity.QkActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 作者：popular cui
 * 时间：2017/4/17 16:53
 * 功能:
 * <p/>
 * 1. 初始化控件
 * 2. 声明onClick方法login
 * 3. 在login方法中
 * 3.1 获取数据
 * 3.2 校验数据
 * 3.3创建文件夹/data/data/包名/files/info.txt
 * 3.4 创建文件info.txt(FileWriter会自动创建的)
 * 3.5查看CheckBox的选中状态
 * 3.6 如果选中了: 写数据name##pwd到文件中
 * 3.7 如果没选:不处理,同时删除info.txt
 * <p/>
 * 4. 如果第二次进来,要进行数据的回显 4.1 先判断info.txt是否存在 4.2 如果存在读取,并显示 4.3 如果不存在,啥都不干
 */
public class UserLoginSdcardActivity extends QkActivity {
    public static UserLoginSdcardActivity mInstance = null;
    private Context mContext;
    private EditText et_name;
    private EditText et_pwd;
    private CheckBox cb;
    private static final String TAG = "UserLoginSdcardActivity";

    @Override
    public void initViews() {
        mInstance = this;
        mContext = this;

        // 1.初始化控件
        et_name = (EditText) findViewById(R.id.et_name);
        et_pwd = (EditText) findViewById(R.id.et_pwd);
        cb = (CheckBox) findViewById(R.id.cb);

    }


    @Override
    public void initData() {
        // 数据回显
        File sdcardDir = Environment.getExternalStorageDirectory();
        // 5创建文件夹
        File file = new File(sdcardDir, "info.txt");

        if (file.exists()) {//存在则回显

            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String readLine = br.readLine();
                br.close();
                String[] split = readLine.split("##");
                et_name.setText(split[0]);
                et_pwd.setText(split[1]);
                cb.setChecked(true);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {

        }

    }

    /*
     * 2. 声明方法
     */
    public void login(View view) throws IOException {

        // 3 获取数据
        String name = et_name.getText().toString().trim();
        String pwd = et_pwd.getText().toString().trim();
        // 4校验数据
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(pwd)) {
            Toast.makeText(this, "用户名或密码不能为空!", Toast.LENGTH_LONG).show();
            return;
        }
        File sdcardDir = Environment.getExternalStorageDirectory();
        Log.v(TAG, sdcardDir+"");

        File file = new File(sdcardDir, "info.txt");
        if (cb.isChecked()) {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(name + "##" + pwd);
            fileWriter.close();
            Toast.makeText(this, "文件已保存!" + name + "==" + pwd, Toast.LENGTH_SHORT).show();
        } else {
            if (file.exists()) {
                file.delete();
            }
            Toast.makeText(this, "已经删除!", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_user_login_sdcard;
    }
}
