package com.example.cuileikun.androidandjava.activity.androidbase;

import android.content.SharedPreferences;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import android.content.SharedPreferences.Editor;
import com.example.cuileikun.androidandjava.R;
import com.qk.applibrary.activity.QkActivity;

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
public class UserLoginSpActivity extends QkActivity{
    private EditText et_name;
    private EditText et_pwd;
    private CheckBox cb;
    private SharedPreferences sp;
    @Override
    public void initViews() {
        // 1.初始化控件
        et_name = (EditText) findViewById(R.id.et_name);
        et_pwd = (EditText) findViewById(R.id.et_pwd);
        cb = (CheckBox) findViewById(R.id.cb);
        sp = getSharedPreferences("config", MODE_PRIVATE);
        //数据回显
        String name = sp.getString("name", "");
        String pwd = sp.getString("pwd", "");
        et_name.setText(name);
        et_pwd.setText(pwd);
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
        // name==zhangsan
        // pwd == 123
        if (cb.isChecked()) {
            // 获取编辑器
			/*Editor editor = sp.edit();
			// 设置数据
			editor.putString("name", name);
			editor.putString("pwd", pwd);
			// 保存数据
			editor.commit();*/
            sp.edit().putString("name", name).putString("pwd", pwd).commit();
            Toast.makeText(this, "数据保存成功", Toast.LENGTH_SHORT).show();
        } else {
            Editor editor = sp.edit();
            //删除单个数据
//			editor.remove("name");
//			editor.remove("pwd");
            //清空所有数据
            editor.clear();
            //必须提交,否则白搭
            editor.commit();
            Toast.makeText(this, "数据删除成功", Toast.LENGTH_SHORT).show();
        }

    }



    @Override
    public int getLayoutId() {
        return R.layout.activity_user_login_sp;
    }
}
