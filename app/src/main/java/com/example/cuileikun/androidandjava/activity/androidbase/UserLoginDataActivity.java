package com.example.cuileikun.androidandjava.activity.androidbase;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cuileikun.androidandjava.R;
import com.qk.applibrary.activity.QkActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

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

public class UserLoginDataActivity extends QkActivity {
    public static UserLoginDataActivity mInstance = null;
    private Context mContext;
    private EditText et_name;
    private EditText et_pwd;
    private CheckBox cb;

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
        String dirPath = "/data/data/com.itheima.login.data/files";
        // 5创建文件夹
        File file = new File(dirPath, "info.txt");
        if (file.exists()){//存在则回显

            // 读取数据
            try {
				/*
				 * BufferedReader br = new BufferedReader(new FileReader(file));
				 * String readLine = br.readLine();//只读第一行,因为只有一行数据 br.close();
				 * //拆分数据 String[] split = readLine.split("##"); //给控件设置数据
				 * et_name.setText(split[0]); et_pwd.setText(split[1]);
				 * //给CheckBox设置勾选状态 cb.setChecked(true);
				 */
                FileInputStream fis = openFileInput("info.txt");
                BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                String readLine = br.readLine();// 只读第一行,因为只有一行数据
                br.close();
                // 拆分数据
                String[] split = readLine.split("##");
                // 给控件设置数据
                et_name.setText(split[0]);
                et_pwd.setText(split[1]);
                // 给CheckBox设置勾选状态
                cb.setChecked(true);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }else {

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
            Toast.makeText(this, "用户名或密码不能为空!", Toast.LENGTH_SHORT).show();
            return;
        }
		/*
		 * String dirPath = "/data/data/com.itheima.login.data/files"; //5创建文件夹
		 * File file = new File(dirPath); if (!file.exists()) {//如果不存在则创建
		 * file.mkdirs(); } File filePath = new File(file, "info.txt");
		 */
        // 6判断CheckBox状态
        if (cb.isChecked()) {// 如果选中
			/*
			 * FileWriter writer = new FileWriter(filePath);
			 * writer.write(name+"##"+pwd); writer.close();
			 */
			/*
			 * 1.创建文件的地址/data/data/baoming/files/info.txt 2. 文件的权限
			 */
            FileOutputStream fos = openFileOutput("info.txt", MODE_PRIVATE);
            fos.write((name + "##" + pwd).getBytes());
            fos.close();
            Toast.makeText(this, "文件已保存!" + name + "==" + pwd, Toast.LENGTH_SHORT).show();
        } else {// 没选中
			/*
			 * if (filePath.exists()) { filePath.delete(); Toast.makeText(this,
			 * "文件已经删除!", 0).show(); }else { Toast.makeText(this, "文件不存在,无法删除!",
			 * 0).show(); }
			 */
            boolean deleteFile = deleteFile("info.txt");
            if (deleteFile) {
                Toast.makeText(this, "文件已经删除!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "文件不存在,无法删除!", Toast.LENGTH_SHORT).show();
            }
        }

    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_user_login_data;

    }
}
