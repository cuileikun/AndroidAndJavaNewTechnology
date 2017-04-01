package com.example.cuileikun.androidandjava.activity.thirdliarbry;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dtr.zxing.activity.CaptureActivity;
import com.example.cuileikun.androidandjava.R;
import com.example.cuileikun.androidandjava.activity.Constant.QkConstant;
import com.example.cuileikun.androidandjava.api.Connect;
import com.example.cuileikun.androidandjava.api.Protocol;
import com.example.cuileikun.androidandjava.widget.BaseAlertDialogManager;
import com.google.zxing.Result;
import com.qk.applibrary.api.APIAsyncTask;
import com.qk.applibrary.bean.ResponseResult;
import com.qk.applibrary.listener.CheckRepeatLoginCallbackInterface;
import com.qk.applibrary.listener.ResponseResultListener;
import com.qk.applibrary.listener.TopbarImplListener;
import com.qk.applibrary.util.CommonUtil;
import com.qk.applibrary.util.SharedPreferencesUtil;
import com.qk.applibrary.widget.TopbarView;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * 作者：popular cui
 * 时间：2017/3/30 11:03
 * 功能:二维码扫描
 * 主要方法：handleDecode() 在这个里面处理扫描的逻辑
 */
public class ZXingTestActivity extends CaptureActivity {
    Context mContext;
    TopbarView top_bar_view;
    private String checkRepeatLoginUrl;
    BaseAlertDialogManager baseAlertDialogManager;
    String userID;
    private TextView client_code_isstandard;//判断是否提示"无效二维码"
    private String scanClientPhone="8888888";//扫码获取的会员手机号
    private String scanLookNumbers="6666666";//扫码获取的被带看次数

    @Override
    protected int getLayoutId() {
        return R.layout.activity_zxing_test;
    }

    @Override
    protected void initView() {
        mContext = this;

        checkRepeatLoginUrl = Connect.getInstance().getApiUrl() + Protocol.REPEAT_LOGIN_CHECK;

        scanPreview = (SurfaceView) findViewById(R.id.capture_preview);
        scanContainer = (RelativeLayout) findViewById(R.id.capture_container);
        scanCropView = (RelativeLayout) findViewById(R.id.capture_crop_view);
        scanLine = (ImageView) findViewById(R.id.capture_scan_line);

        top_bar_view = (TopbarView) findViewById(R.id.top_bar_view);
        top_bar_view.setTopbarTitle("测试二维码扫描");
        top_bar_view.setTopBarClickListener(
                new TopbarImplListener() {
                    @Override
                    public void leftButtonClick() {
                        finish();
                    }
                }
        );


    }

    @Override
    protected void initData() {
        userID = SharedPreferencesUtil.getSetting(QkConstant.SharedPreferencesKeyDef.FILE_NAME, mContext,
                QkConstant.SharedPreferencesKeyDef.USER_ID);

    }

    @Override
    public Handler getHandler() {
        return super.getHandler();
    }

    @Override
    public void handleDecode(Result rawResult, Bundle bundle) {
        super.handleDecode(rawResult, bundle);

//        String reg = "^[0-9]*$";
        if (rawResult == null) {
            client_code_isstandard.setVisibility(View.VISIBLE);
            restartScan();
            return;
//        } else if (!outerCardNumber.matches(reg)) {
//            Toast.makeText(mContext, "此二维码为非标准二维码！", Toast.LENGTH_SHORT).show();
//            restartScan();
        } else {
            client_code_isstandard.setVisibility(View.INVISIBLE);
            //ceshi传值 on
//            RegistrationWithLookActivity.mInstance.setScanClientCodeData(scanClientPhone,scanLookNumbers);
            finish();

            //ceshi传值 on

//            //获取二维码信息
//            final String outerCardNumber = rawResult.getText();
//
//            StringBuilder stringBuilder = new StringBuilder();
//            stringBuilder.append("UserId=");
//            stringBuilder.append(userID);
//            stringBuilder.append("&");
//            stringBuilder.append("OuterCardNumber=");
//            stringBuilder.append(outerCardNumber);
//            String des = new Algorithm().getHousekeeperCardRsaPublicKey();
//            final String signature = RSA.encrypt(stringBuilder.toString(), des);
//
//            checkRepeatLogin(checkRepeatLoginUrl, true, "加载中", new CheckRepeatLoginCallbackInterface() {
//                @Override
//                public void noRepeatLogin(ResponseResult result) {
//
//                    String testUrl = Connect.getInstance().getApiUrl() + Protocol.GET_CLIENT_CODE_MESSAGE;
//                    APIAsyncTask asyncTask = new APIAsyncTask(mContext);
//                    HashMap<String, Object> params = new HashMap<String, Object>();
//                    //参数1 未知
//                    params.put("username", "");
//                    //参数2 未知
//                    params.put("deviceId", "");
//                    String apiLogFileDirectory = CommonUtil.getSDCardPath() + "/qk/log";
//                    String apiLogFileName = "qk_api_log.txt";
//                    asyncTask.post(apiLogFileDirectory, apiLogFileName, testUrl, params, new ResponseResultListener() {
//                        @Override
//                        public void onResult(ResponseResult result) {
//                            doGetClientCodeMessageSuccess(result);
//                        }
//                    });
//
//
//                }
//            });

        }

    }

    /**
     * 获取二维码信息成功
     * 客户注册手机号及被带看次数
     *
     * @param result
     */
    private void doGetClientCodeMessageSuccess(ResponseResult result) {
        if (result.code == ResponseResult.SUCESS_CODE) {
            try {
                /**
                 * 请求成功
                 */
                JSONObject json = new JSONObject((result.data));

                //获取客户注册手机号
                String scanClientPhone = json.getString("clientPhone");
                //获取被带看次数
                String scanLookTimes = json.getString("lookTimes");

                //传值
//                RegistrationWithLookActivity.mInstance.setScanClientCodeData(scanClientPhone,scanLookTimes);
                finish();

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (!CommonUtil.isEmpty(result.message)) {
            /**
             * 请求失败
             */
            CommonUtil.sendToast(mContext, result.message);
            dissmissProgressDialog();
        }
    }


    private ProgressDialog loadingProgressDialog;

    /**
     * 打开加载框,用于调用接口弹出的对话框
     *
     * @param title
     * @param message
     * @return
     */
    public ProgressDialog showProgressDialog(String title, String message) {
        try {
            if (isFinishing() == false) {
                if (loadingProgressDialog == null) {
                    loadingProgressDialog = ProgressDialog.show(this, title,
                            message);
                    loadingProgressDialog.setCancelable(true);
                    loadingProgressDialog.setCanceledOnTouchOutside(true);
                } else {
                    loadingProgressDialog.setTitle(title);
                    loadingProgressDialog.setMessage(message);
                    if (loadingProgressDialog.isShowing() == false) {
                        loadingProgressDialog.show();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return loadingProgressDialog;
    }

    //add by clk off

    public ProgressDialog getLoadingProgressDialog() {
        if (loadingProgressDialog != null) {
            return loadingProgressDialog;
        } else {
            return null;
        }
    }

    /**
     * 关闭对话框
     */
    public void dissmissProgressDialog() {
        try {
            if (isFinishing() == false && loadingProgressDialog != null) {
                loadingProgressDialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 检查账号重复
     *
     * @param url          接口地址
     * @param isShowDialog 是否弹出加载框
     * @param info         对话框文字信息
     * @CheckRepeatLoginCallbackInterface 回调接口 回调到所调用的界面
     */
    public void checkRepeatLogin(String url, boolean isShowDialog, String info, final CheckRepeatLoginCallbackInterface checkRepeatLoginCallbackInterface) {
        if (CommonUtil.checkNetwork(this)) {
            /**
             * 检查是否经要弹出加元框
             */
            String userName = SharedPreferencesUtil.getSetting("housekeeper", this, "login_account");
            String deviceId = ((TelephonyManager) this.getSystemService(this.TELEPHONY_SERVICE)).getDeviceId();
            if (isShowDialog)
                showProgressDialog(null, info);
            APIAsyncTask asyncTask = new APIAsyncTask(this);
            JSONObject json = new JSONObject();
            HashMap<String, Object> params = new HashMap<String, Object>();
            params.put("userAccount", userName);
            params.put("deviceId", deviceId);
            String apiLogFileDirectory = CommonUtil.getSDCardPath() + "/qk/log";
            String apiLogFileName = "qk_api_log.txt";
            asyncTask.post(apiLogFileDirectory, apiLogFileName, url, params, new ResponseResultListener() {
                @Override
                public void onResult(ResponseResult result) {
                    doSucessCheckRepeatLogin(result, checkRepeatLoginCallbackInterface);
                }
            });
        }
    }

    /**
     * 请求检查账号重复成功
     *
     * @param result
     */
    private void doSucessCheckRepeatLogin(ResponseResult result, CheckRepeatLoginCallbackInterface checkRepeatLoginCallbackInterface) {
        if (result.code == ResponseResult.SUCESS_CODE) {
            /**
             * 账号没有重复登录,返回到所调用的界面
             */
            if (checkRepeatLoginCallbackInterface != null) {
                checkRepeatLoginCallbackInterface.noRepeatLogin(result);
            } else {
                dissmissProgressDialog();
            }
        } else if (result.code == 1) {
            /**
             * 账号重复登录了 要返回到登录界面,再弹出对话框3秒消失
             */
            dissmissProgressDialog();
            /**
             * 发送挤下线广播
             */
            Intent mIntent = new Intent("repeat_login_action");
            mIntent.putExtra("repeat_login_error", result.message);
            sendBroadcast(mIntent);
            finish();
        }
    }

}
