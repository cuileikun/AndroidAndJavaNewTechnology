package com.example.cuileikun.androidandjava.activity.hottechnology;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.cuileikun.androidandjava.R;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        TextView text = (TextView) findViewById(R.id.text_test);
        //1,我是真的累我是真的累  100字 编码之后长度
        String ss = "%E6%88%91%E6%98%AF%E7%9C%9F%E7%9A%84%E7%B4%AF%E6%88%91%E6%98%AF%E7%9C%9F%E7%9A%84%E7%B4%AF%E6%88%91%E6%98%AF%E7%9C%9F%E7%9A%84%E7%B4%AF%E6%88%91%E6%98%AF%E7%9C%9F%E7%9A%84%E7%B4%AF%E6%88%91%E6%98%AF%E7%9C%9F%E7%9A%84%E7%B4%AF%E6%88%91%E6%98%AF%E7%9C%9F%E7%9A%84%E7%B4%AF%E6%88%91%E6%98%AF%E7%9C%9F%E7%9A%84%E7%B4%AF%E6%88%91%E6%98%AF%E7%9C%9F%E7%9A%84%E7%B4%AF%E6%88%91%E6%98%AF%E7%9C%9F%E7%9A%84%E7%B4%AF%E6%88%91%E6%98%AF%E7%9C%9F%E7%9A%84%E7%B4%AF%E6%88%91%E6%98%AF%E7%9C%9F%E7%9A%84%E7%B4%AF%E6%88%91%E6%98%AF%E7%9C%9F%E7%9A%84%E7%B4%AF%E6%88%91%E6%98%AF%E7%9C%9F%E7%9A%84%E7%B4%AF%E6%88%91%E6%98%AF%E7%9C%9F%E7%9A%84%E7%B4%AF%E6%88%91%E6%98%AF%E7%9C%9F%E7%9A%84%E7%B4%AF%E6%88%91%E6%98%AF%E7%9C%9F%E7%9A%84%E7%B4%AF%E6%88%91%E6%98%AF%E7%9C%9F%E7%9A%84%E7%B4%AF%E6%88%91%E6%98%AF%E7%9C%9F%E7%9A%84%E7%B4%AF%E6%88%91%E6%98%AF%E7%9C%9F%E7%9A%84%E7%B4%AF%E6%88%91%E6%98%AF%E7%9C%9F%E7%9A%84%E7%B4%AF";
        String ss1="%E6%98%A5%E5%A4%A9%E7%9A%84%E8%8A%B1%E5%BC%80%E7%A7%8B%E5%A4%A9%E7%9A%84%E9%A3%8E%E4%BB%A5%E5%8F%8A%E5%86%AC%E5%A4%A9%E7%9A%84%E8%90%BD%E9%98%B3%E5%BF%A7%E9%83%81%E7%9A%84%E9%9D%92%E6%98%A5%E5%B9%B4%E5%B0%91%E7%9A%84%E6%88%91%E6%9B%BE%E7%BB%8F%E6%97%A0%E7%9F%A5%E7%9A%84%E8%BF%99%E4%B9%88%E6%83%B3%E9%A3%8E%E8%BD%A6%E5%9C%A8%E5%9B%9B%E5%AD%A3%E8%BD%AE%E5%9B%9E%E7%9A%84%E6%AD%8C%E9%87%8C%E5%AE%83%E5%A4%A9%E5%A4%A9%E5%9C%B0%E6%B5%81%E8%BD%AC%E9%A3%8E%E8%8A%B1%E9%9B%AA%E6%9C%88%E7%9A%84%E8%AF%97%E5%8F%A5%E9%87%8C%E6%88%91%E5%9C%A8%E5%B9%B4%E5%B9%B4%E7%9A%84%E6%88%90%E9%95%BF%E7%94%9F%E5%91%BD%E4%B8%8E%E5%91%8A%E5%88%AB%E5%85%89%E9%98%B4%E7%9A%84%E6%95%85%E4%BA%8B%E6%94%B9%E5%8F%98%E4%BA%86%E4%B8%80%E4%B8%AA%E4%BA%BA%E5%B0%B1%E5%9C%A8%E9%82%A3%E5%A4%9A%E6%84%81%E5%96%84%E6%84%9F%E8%80%8C%E5%88%9D%E6%AC%A1%E7%AD%89%E5%BE%85%E7%9A%84%E9%9D%92%E6%98%A5%E5%8F%91%E9%BB%84%E7%9A%84%E7%9B%B8";
String ss2="%E9%81%A5%E8%BF%9C%E7%9A%84%E8%B7%AF%E7%A8%8B%E6%98%A8%E6%97%A5%E7%9A%84%E6%A2%A6%E4%BB%A5%E5%8F%8A%E8%BF%9C%E5%8E%BB%E7%9A%84%E7%AC%91%E5%A3%B0%E5%86%8D%E6%AC%A1%E7%9A%84%E8%A7%81%E9%9D%A2%E6%88%91%E4%BB%AC%E5%8F%88%E7%BB%8F%E5%8E%86%E4%BA%86%E5%A4%9A%E5%B0%91%E8%B7%AF%E7%A8%8B%E4%B8%8D%E5%86%8D%E6%98%AF%E6%97%A7%E6%97%A5%E7%86%9F%E6%82%89%E7%9A%84%E6%88%91%E6%9C%89%E7%9D%80%E6%97%A7%E6%97%A5%E7%8B%82%E7%83%AD%E7%9A%84%E6%A2%A6%E4%B9%9F%E4%B8%8D%E6%98%AF%E6%97%A7%E6%97%A5%E7%86%9F%E6%82%89%E7%9A%84%E4%BD%A0%E6%9C%89%E7%9D%80%E4%BE%9D%E7%84%B6%E7%9A%84%E7%AC%91%E5%AE%B9%E7%94%9F%E5%91%BD%E4%B8%8E%E5%91%8A%E5%88%AB%E5%85%89%E9%98%B4%E7%9A%84%E6%95%85%E4%BA%8B%E6%94%B9%E5%8F%98%E4%BA%86%E6%88%91%E4%BB%AC%E5%B0%B1%E5%9C%A8%E9%82%A3%E5%A4%9A%E6%84%81%E5%96%84%E6%84%9F%E8%80%8C%E5%88%9D%E6%AC%A1%E5%9B%9E%E9%A6%96%E7%9A%84%E9%9D%92%E6%98%A5%E5%88%BB%E5%88%92%E7%9D%80%E5%A4%9A%E5%B0%91%E7%BE%8E";

        String length = String.valueOf(ss.length());
        String length1 = String.valueOf(ss1.length());
        String length2 = String.valueOf(ss2.length());
        text.setText(length1);

    }
}
