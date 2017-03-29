package com.example.cuileikun.androidandjava.activity.hottechnology;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.widget.TextView;

import com.example.cuileikun.androidandjava.R;
import com.example.cuileikun.androidandjava.view.MyEditText;

/**
 * 作者：popular cui
 * 时间：2017/3/29 14:41
 * 功能:github MyEditText
 */
public class MyEditTextActivity extends Activity{

    private MyEditText mEditText;
    private TextView mShowContent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_edit_text);

        mEditText = (MyEditText) findViewById(R.id.et);
        mShowContent = (TextView) findViewById(R.id.et_content);

        mEditText.setTextWatcher(new MyEditText.MyTextWatcher() {

            @Override
            public void textChange(Editable s) {
                mShowContent.setText("MyEditText的内容是：" + s);
            }
        });

    }

}
