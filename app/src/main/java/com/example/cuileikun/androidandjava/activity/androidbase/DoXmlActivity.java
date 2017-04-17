package com.example.cuileikun.androidandjava.activity.androidbase;

import android.content.res.AssetManager;
import android.util.Xml;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cuileikun.androidandjava.R;
import com.example.cuileikun.androidandjava.bean.Sms;
import com.qk.applibrary.activity.QkActivity;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;

/**
 * 作者：popular cui
 * 时间：2017/4/17 16:58
 * 功能:
 */
public class DoXmlActivity extends QkActivity{
    private TextView tv_sms;

    @Override
    public void initViews() {
        tv_sms = (TextView) findViewById(R.id.tv_sms);
    }

    /**
     * 拼接字符串生成xml <sms> <address>5557</address> <body>nihao</body>
     * <time>140xxxxxxx</time> </sms>
     *
     * @param view
     * @throws IOException
     */
    public void click1(View view) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version='1.0' encoding='utf-8' standalone='yes' ?>");
        sb.append("<smses>");
        for (int i = 0; i < 50; i++) {
            sb.append("<sms>");
            sb.append("<address>");
            sb.append(5550 + i);
            sb.append("</address>");
            sb.append("<body>");
            sb.append("我是短信内容" + i);
            sb.append("</body>");
            sb.append("<time>");
            sb.append(new Date().getTime());
            sb.append("</time>");
            sb.append("</sms>");
        }
        sb.append("</smses>");
        //将数据保存到sdcard
        File file = new File("/mnt/sdcard/smses.xml");
        BufferedWriter bf = new BufferedWriter(new FileWriter(file));
        bf.write(sb.toString());
        bf.close();

    }
    /**
     * 使用Xml序列化器生成xml
     * @param view
     * @throws IOException
     */
    public void click2(View view) throws IOException{
        //获取序列化器
        XmlSerializer serializer = Xml.newSerializer();
        File file = new File("/mnt/sdcard/smses2.xml");
        FileWriter fw = new FileWriter(file);
        //设置输出流
        serializer.setOutput(fw);
        //设置xml的头信息
        serializer.startDocument("utf-8", true);
        //开始写 开始标签
        serializer.startTag(null, "smses");
        for(int i=0;i<50;i++){
            serializer.startTag(null, "sms");
            serializer.startTag(null, "address");
            serializer.text((5554+i)+"");
            serializer.endTag(null, "address");
            serializer.startTag(null, "body");
            serializer.text("我是<></?ddxx>内容"+i);
            serializer.endTag(null, "body");
            serializer.startTag(null, "time");
            serializer.text(""+new Date().getTime());
            serializer.endTag(null, "time");
            serializer.endTag(null, "sms");
        }
        serializer.endTag(null, "smses");
        //结束文档,释放资源
        serializer.endDocument();
        Toast.makeText(this, "写完了", Toast.LENGTH_SHORT).show();
    }
    /**
     * 使用PULL解析assets中的xml
     * @param view
     * @throws IOException
     * @throws XmlPullParserException
     */
//    public void click3(View view) throws IOException, XmlPullParserException {
//        //1. 获取资源管理器
//        AssetManager manager = getAssets();
//        //打开资源
//        InputStream inputStream = manager.open("smses2.xml");
//        //获取PULL解析器
//        XmlPullParser parser = Xml.newPullParser();
//        //设置数据源(InputStream)
//        parser.setInput(inputStream, "utf-8");
//        //获取第一个事件
//        int event = 0;
//        ArrayList<Sms> smses = null;
//        Sms sms = null;
//        //避免死循环
//        while((event=parser.next())!=XmlPullParser.END_DOCUMENT){
//            //获取触发当前事件的标签名字
//            String tagName = parser.getName();
//            if (event==XmlPullParser.START_TAG) {
//                //判断是哪个开始标签触发的该事件
//                if ("smses".equals(tagName)) {
//                    smses = new ArrayList<Sms>();
//                }else if ("sms".equals(tagName)) {
//                    sms = new Sms();
//                }else if ("body".equals(tagName)) {
//                    //获取文本
//                    String body = parser.nextText();
//                    sms.setBody(body);
//                }else if ("time".equals(tagName)) {
//                    String time = parser.nextText();
//                    sms.setTime(time);
//                }else if ("address".equals(tagName)) {
//                    String address = parser.nextText();
//                    sms.setAddress(address);
//                }
//
//            }else if (event== XmlPullParser.END_TAG) {
//                if ("sms".equals(tagName)) {
//                    smses.add(sms);
//                }
//            }
//        }
//        inputStream.close();
//        showSMS(smses);
//
//    }

    private void showSMS(ArrayList<Sms> smses) {
        StringBuilder sb = new StringBuilder();
        for(Sms sms : smses){
            sb.append(sms.toString()+"\n");
        }
        tv_sms.setText(sb.toString());
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_do_xml;
    }

}
