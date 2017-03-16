package com.example.cuileikun.androidandjava.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.telephony.TelephonyManager;

import com.qk.applibrary.util.CommonUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

/**
 * Created by april on 16/6/13.
 */
public class Util {

    /**
     * 获取手机设备号
     *
     * @param context
     * @return
     */
    public static String getDeviceId(Context context) {
        TelephonyManager TelephonyMgr = (TelephonyManager) context.getSystemService(context.TELEPHONY_SERVICE);
        String deviceId = TelephonyMgr.getDeviceId();
        return deviceId;
    }

    /**
     * 获取当前版本号
     *
     * @param context
     * @return
     */
    public static String getVersionCode(Context context) {
        String versionName = "";
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
            if (versionName == null) {
                return "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return versionName;
    }

    /**
     * 判断是否是数字
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     *
     * @param context
     * @param pxValue
     * @return
     * @author SHANHY
     * @date 2015年10月28日
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
    /**
     * 编号转为对应的状态
     * @param result
     * @return
     */
    public static String getStatus(int result) {
        String companyResult = "";
        switch (result) {
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 18:
            case 20:
                companyResult = "通过";
                break;
            case 1:
            case 2:
            case 4:
            case 5:
            case 7:
            case 17:
                companyResult = "待审批";
                break;
            case 3:
            case 6:
            case 8:
            case 19:
                companyResult = "驳回";
                break;
        }
        return companyResult;
    }
    /**
     * 判断相对应的APP是否存在
     *
     * @param context
     * @param packageName(包名)(若想判断QQ，则改为com.tencent.mobileqq，若想判断微信，则改为com.tencent.mm)
     * @return
     */
    public static boolean isAvilibleAPP(Context context, String packageName) {
        PackageManager packageManager = context.getPackageManager();

        //获取手机系统的所有APP包名，然后进行一一比较
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
        for (int i = 0; i < pinfo.size(); i++) {
            if (((PackageInfo) pinfo.get(i)).packageName
                    .equalsIgnoreCase(packageName))
                return true;
        }
        return false;
    }

    /**
     * 将2018-10-16 10:19:10转换为2018-10-16
     * @param date1
     * @return
     */
    public static String parseDate(String date1){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date date = null;
        String checkoutDate = "";
        try {
            if (!CommonUtil.isEmpty(date1)){
                date = sdf.parse(date1);
                checkoutDate = sdf.format(date);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return checkoutDate;
    }

    /**
     * 将2018-10-16 10:19:10转换为10:19:10
     * @param date
     * @return
     */
    public static String format(String date) {
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
        try {
            return df.format(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *  获取给定日期的年值
     * @param date
     * @return
     */
    public static String formatNian(String date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy");
        try {
            return df.format(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *  获取给定日期的月值
     * @param date
     * @return
     */
    public static String formatYue(String date) {
        SimpleDateFormat df = new SimpleDateFormat("MM");
        try {
            return df.format(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     *  获取给定日期的日
     * @param date
     * @return
     */
    public static String formatRi(String date) {
        SimpleDateFormat df = new SimpleDateFormat("dd");
        try {
            return df.format(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *  获取给定日期的小时值
     * @param date
     * @return
     */
    public static String formatShi(String date) {
        SimpleDateFormat df = new SimpleDateFormat("HH");
        try {
            return df.format(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     *  获取给定日期的分值
     * @param date
     * @return
     */
    public static String formatFen(String date) {
        SimpleDateFormat df = new SimpleDateFormat("mm");
        try {
            return df.format(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 获取给定日期的秒值
     * @param date
     * @return
     */
    public static String formatMiao(String date) {
        SimpleDateFormat df = new SimpleDateFormat("ss");
        try {
            return df.format(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }




}
