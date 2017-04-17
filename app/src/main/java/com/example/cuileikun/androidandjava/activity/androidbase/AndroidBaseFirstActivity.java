package com.example.cuileikun.androidandjava.activity.androidbase;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.telephony.SmsManager;

import com.example.cuileikun.androidandjava.R;
import com.qk.applibrary.activity.QkActivity;
import com.qk.applibrary.util.CommonUtil;

import java.util.ArrayList;

/**
 * 作者：popular cui
 * 时间：2017/4/17 15:49
 * 功能:AndroidBase 01
 */
public class AndroidBaseFirstActivity extends QkActivity implements View.OnClickListener {
    private Context mContext;
    private Button btn_call_phone;
    private Button btn_send_message;
    private Button btn_click;
    private EditText et_num;
    private EditText et_num_second;
    private EditText et_sms;

    @Override
    public void initViews() {
        mContext = this;
        et_num = (EditText) findViewById(R.id.et_num);
        btn_call_phone = (Button) findViewById(R.id.btn_call_phone);

        et_num_second = (EditText) findViewById(R.id.et_num_second);
        et_sms = (EditText) findViewById(R.id.et_sms);
        btn_send_message = (Button) findViewById(R.id.btn_send_message);

        btn_click = (Button) findViewById(R.id.btn_click);
    }


    @Override
    public void addListeners() {
        btn_call_phone.setOnClickListener(AndroidBaseFirstActivity.this);
        btn_send_message.setOnClickListener(AndroidBaseFirstActivity.this);
        btn_click.setOnClickListener(AndroidBaseFirstActivity.this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_android_base_first;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_call_phone:

                String num = et_num.getText().toString().trim();
                //创建一个Intent
                Intent intent = new Intent();
                //设置我们的动作(Action)
                intent.setAction(Intent.ACTION_CALL);
                //设置数据URL :http://www.baidu.com
                Uri data = Uri.parse("tel:" + num);
                intent.setData(data);//uri
                //启动Activity
                startActivity(intent);

                break;
            case R.id.btn_send_message:

                String phonenum = et_num_second.getText().toString().trim();
                String sms = et_sms.getText().toString();
                if (TextUtils.isEmpty(phonenum) || TextUtils.isEmpty(sms)) {
                    //提示用户
                    Toast.makeText(AndroidBaseFirstActivity.this, "电话或者短信不能为空!", Toast.LENGTH_SHORT).show();
                    return;
                }
                //获取SmsManager对象
                SmsManager smsManager = SmsManager.getDefault();
                //拆分短信
                ArrayList<String> divideMessage = smsManager.divideMessage(sms);
                //发送
                /*
                 * 参数1:对方的号码
				 * 参数2:短信中心号码
				 * 参数3:短信内容
				 * 参数4:是否发送成功
				 * 参数5:是否接收到
				 */
                smsManager.sendMultipartTextMessage(phonenum, null, divideMessage, null, null);
                CommonUtil.sendToast(mContext, "短信已发送");
                break;

//            case R.id.btn_click:
//
//                break;

        }


    }


    /**
     * 1. 访问权限修饰符必须是public
     * 2. 形参必须是View
     * 3. 方法名必须跟  android:onClick="xxxx"中的xxxx保持一致
     *
     * @param view
     */
    public void btnClick(View view) {
        Toast.makeText(this, "第四种方式我被点击了=" + view, Toast.LENGTH_LONG).show();
    }


}
