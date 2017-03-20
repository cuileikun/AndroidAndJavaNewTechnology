package com.example.cuileikun.androidandjava.activity.thirdliarbry;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.view.View;
import android.widget.Button;

import com.example.cuileikun.androidandjava.R;
import com.example.cuileikun.androidandjava.activity.Constant.QkConstant;
import com.example.cuileikun.androidandjava.utils.Util;
import com.qk.applibrary.activity.QkActivity;

import java.util.List;

/**
 * 作者：popular cui
 * 时间：2017/3/13 14:50
 * 功能:
 */
public class LocateActivity extends QkActivity implements View.OnClickListener {
    private Context mContext;
    public static LocateActivity mInstance = null;
    private Button jump_to_baidu_offical_demo_app;
    private Button jump_to_share_demo_app;

    @Override
    public void initViews() {
        jump_to_baidu_offical_demo_app = (Button) findViewById(R.id.jump_to_baidu_offical_demo_app);
        jump_to_share_demo_app = (Button) findViewById(R.id.jump_to_share_demo_app);
    }

    @Override
    public void initData() {
        mContext = LocateActivity.this;
        mInstance = LocateActivity.this;
    }

    @Override
    public void addListeners() {
        jump_to_baidu_offical_demo_app.setOnClickListener(LocateActivity.this);
        jump_to_share_demo_app.setOnClickListener(LocateActivity.this);

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_loacte;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.jump_to_baidu_offical_demo_app:
                //调起百度地图官方demo app
                jumpToBaiDuOfficalDemoApp();
                break;
            case R.id.jump_to_share_demo_app:
                //调起三方分享app
                jumpToShareDemoApp();
                break;

        }


    }

    private void jumpToShareDemoApp() {
        if (Util.isAvilibleAPP(mContext, QkConstant.checkoutFgy.SHAREDEMO)) {

            doStartApplicationWithPackageName(QkConstant.checkoutFgy.SHAREDEMO);

        } else {
            // CommonUtil.sendToast(mContext, "请安装远程验收app");
            Uri uri = Uri.parse("http://blog.csdn.net/q12q1ty/article/details/50973779");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }


    }

    private void jumpToBaiDuOfficalDemoApp() {
        if (Util.isAvilibleAPP(mContext, QkConstant.checkoutFgy.BAIDUMAPSDKDEMO)) {

            doStartApplicationWithPackageName(QkConstant.checkoutFgy.BAIDUMAPSDKDEMO);

        } else {
            // CommonUtil.sendToast(mContext, "请安装远程验收app");
            Uri uri = Uri.parse("http://download.csdn.net/detail/lintax/9720235");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }

    }


    /**
     * 通过百度地图demo app的包名  调起百度地图demo app
     */
    private void doStartApplicationWithPackageName(String packagename) {
        // 通过包名获取此APP详细信息，包括Activities、services、versioncode、name等等
        PackageInfo packageinfo = null;
        try {
            packageinfo = getPackageManager().getPackageInfo(packagename, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (packageinfo == null) {
            return;
        }
        // 创建一个类别为CATEGORY_LAUNCHER的该包名的Intent
        Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);
        resolveIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        resolveIntent.setPackage(packageinfo.packageName);

        // 通过getPackageManager()的queryIntentActivities方法遍历
        List<ResolveInfo> resolveinfoList = getPackageManager()
                .queryIntentActivities(resolveIntent, 0);

        ResolveInfo resolveinfo = resolveinfoList.iterator().next();
        if (resolveinfo != null) {
            // packagename = 参数packname
            String packageName = resolveinfo.activityInfo.packageName;
            // 这个就是我们要找的该APP的LAUNCHER的Activity[组织形式：packagename.mainActivityname]
            String className = resolveinfo.activityInfo.name;
            // LAUNCHER Intent
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            // 设置ComponentName参数1:packagename参数2:MainActivity路径
            ComponentName cn = new ComponentName(packageName, className);
            intent.setComponent(cn);

//            String login_account = SharedPreferencesUtil.getSetting(QkConstant.SharedPreferencesKeyDef.FILE_NAME, mContext, QkConstant.SharedPreferencesKeyDef.USER_ACCOUNT);
//            String user_password = SharedPreferencesUtil.getSetting(QkConstant.SharedPreferencesKeyDef.FILE_NAME, mContext, QkConstant.SharedPreferencesKeyDef.USER_PASSWORD);
//            intent.putExtra("login_account",login_account );//管员帐号
//            intent.putExtra("user_password", user_password);//登录密码
//            intent.putExtra("qrmId", qrmId);//密码

            startActivity(intent);
        }

    }



}
