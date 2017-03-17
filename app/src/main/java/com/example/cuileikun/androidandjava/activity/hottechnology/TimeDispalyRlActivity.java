package com.example.cuileikun.androidandjava.activity.hottechnology;

import android.widget.TextView;

import com.example.cuileikun.androidandjava.R;
import com.example.cuileikun.androidandjava.utils.Util;
import com.qk.applibrary.activity.QkActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeDispalyRlActivity extends QkActivity {

    private TextView tv_nian2;

    @Override
    public int getLayoutId() {
        return R.layout.activity_time_dispaly_rl;
    }

    @Override
    public void initViews() {
        //   1，t1=现在时间秒值
        // 2，t2=退房日期  + 离过完这天还剩余的秒值
        //  t1 - t2 > 0  直接显示现在的时间
        //  t1 - t2  0  显示第二天上午10：:00
        setContentView(R.layout.activity_time_dispaly_rl);
        TextView tv_nian = (TextView) findViewById(R.id.tv_nian);
        TextView tv_yue = (TextView) findViewById(R.id.tv_yue);
        TextView tv_ri = (TextView) findViewById(R.id.tv_ri);

        TextView tv_shi = (TextView) findViewById(R.id.tv_shi);
        TextView tv_fen = (TextView) findViewById(R.id.tv_fen);
        TextView tv_miao = (TextView) findViewById(R.id.tv_miao);

        //退房日期
        String checkoutTime = "2016-12-01 09:03:09";

        //现在时间秒值
        TextView current_miao = (TextView) findViewById(R.id.current_miao);
        //退房日期的秒值
        TextView checkout_miao = (TextView) findViewById(R.id.checkout_miao);
        //离过完这一天剩余的毫秒值
        TextView onedayremain_miao = (TextView) findViewById(R.id.onedayremain_miao);
        //应该显示的毫秒值 should_show_time
        TextView should_show_time = (TextView) findViewById(R.id.should_show_time);


        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //获取当前时间
        Date curDate = new Date(System.currentTimeMillis());
        String str = formatter.format(curDate);
        // current_miao.setText("现在的时dsdsd间：" + str);

        //获取退房日期的年月日
        int nian = Integer.parseInt(checkoutTime.substring(0, 4));
        int yue = Integer.parseInt(checkoutTime.substring(5, 7));
        int ri = Integer.parseInt(checkoutTime.substring(8, 10));


        //获取退房日期的时分秒
        int shi = Integer.parseInt(checkoutTime.substring(11, 13));
        int fen = Integer.parseInt(checkoutTime.substring(14, 16));
        int miao = Integer.parseInt(checkoutTime.substring(17, 19));

        tv_nian.setText(nian + "");
        tv_yue.setText(yue + "");
        tv_ri.setText(ri + "");

        tv_shi.setText(shi + "");
        tv_fen.setText(fen + "");
        tv_miao.setText(miao + "");

        //现在时间的秒值
        long currentTimeMillis = System.currentTimeMillis();
        current_miao.setText("t1=现在时间毫秒值: " + currentTimeMillis);


        //退房日期的毫秒值
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 此处会抛异常
        Date date = null;
        try {
            date = sdf.parse(checkoutTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 获取毫秒数
        long longDate = date.getTime();
        checkout_miao.setText("退房日期的毫秒值:" + longDate);


        //每天的毫秒数
        long oneDay = 24 * 60 * 60 * 1000;
        //离过完这天还剩余的秒值
        long oneDayRemaining = oneDay - shi * 60 * 60 * 1000 - fen * 60 * 1000 - miao * 1000;
        onedayremain_miao.setText("离过完这一天剩余的毫秒值：" + oneDayRemaining);

        //退房时间第二天上午10:00 的毫秒值
        long nextTenTime = longDate + oneDayRemaining + 10 * 60 * 60 * 1000;

        //   1，t1=现在时间秒值
        // 2，t2=退房日期  + 离过完这天还剩余的秒值
        //  t1 - t2 > 0  直接显示现在的时间
        //  t1 - t2  < 0  显示第二天上午10：:00

        if (currentTimeMillis - longDate - oneDayRemaining > 0) {
            //直接显示当前时间
            SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //获取当前时间
            Date curDate1 = new Date(System.currentTimeMillis());
            String str1 = formatter.format(curDate1);

            should_show_time.setText( "测试当前时间啦啦啦"+str1);
        } else {
            //显示第二天上午十点的时间
            SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //获取当前时间
            Date curDate2 = new Date(nextTenTime);
            String str2 = formatter.format(curDate2);

            should_show_time.setText(str2);
        }

        //退房日期
        String checkoutTime1 = "2016-12-01 09:03:09";
        TextView tv_nian2 = (TextView) findViewById(R.id.tv_nian2);
        TextView tv_yue2 = (TextView) findViewById(R.id.tv_yue2);
        TextView tv_ri2 = (TextView) findViewById(R.id.tv_ri2);
        TextView tv_shi2 = (TextView) findViewById(R.id.tv_shi2);
        TextView tv_fen2 = (TextView) findViewById(R.id.tv_fen2);
        TextView tv_miao2 = (TextView) findViewById(R.id.tv_miao2);

        int s = Integer.parseInt(Util.formatNian(checkoutTime1));
        tv_nian2.setText(s+"");

        int s1 = Integer.parseInt(Util.formatYue(checkoutTime1));
        tv_yue2.setText(s1+"");

        int s2 = Integer.parseInt(Util.formatRi(checkoutTime1));
        tv_ri2.setText(s2+"");
        int s3 = Integer.parseInt(Util.formatShi(checkoutTime1));
        tv_shi2.setText(s3+"");
        int s4 = Integer.parseInt(Util.formatFen(checkoutTime1));
        tv_fen2.setText(s4+"");
        int s5= Integer.parseInt(Util.formatMiao(checkoutTime1));
        tv_miao2.setText(s5+"");










    }

}
