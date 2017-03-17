package com.example.cuileikun.androidandjava.activity.hottechnology;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.example.cuileikun.androidandjava.R;
import com.example.cuileikun.androidandjava.activity.Constant.QkConstant;
import com.example.cuileikun.androidandjava.api.Connect;
import com.example.cuileikun.androidandjava.api.Protocol;
import com.example.cuileikun.androidandjava.bean.CustomerRomDetail;
import com.example.cuileikun.androidandjava.utils.FileUtil;
import com.example.cuileikun.androidandjava.utils.PhotoUtil;
import com.example.cuileikun.androidandjava.view.DisplayImageActivity;
import com.qk.applibrary.activity.QkActivity;
import com.qk.applibrary.api.ImageAsyncTask;
import com.qk.applibrary.bean.ResponseResult;
import com.qk.applibrary.listener.ResponseResultListener;
import com.qk.applibrary.listener.TopbarImplListener;
import com.qk.applibrary.util.CommonUtil;
import com.qk.applibrary.widget.DownloadImageView;
import com.qk.applibrary.widget.TopbarView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SecondSubmitActivity extends QkActivity implements BDLocationListener { private TopbarView topbarView;
    private Context mContext;
    private EditText et_declarant_phone, et_describe;
    private Button bt_submit;
    private  String mTempFileUri;
    private LayoutInflater inflater;
    private LinearLayout photoContainerLl;
    private List<String> photoFilePathList;//图片文件路径
    private Date date;
    private final int TAKE_PHOTO_REQUEST = 100;
    private final int REQUEST_CODE_PICK_IMAGE = 200;
    private final int OPEN_CAMERA = 300;
    private String filePath;
    private CustomerRomDetail detail, romDetail;
    private LocationClient mLocationClient = null;
    private LocationClientOption option = null;
    private String Address;//定位地址
    public  final String PHOTOLIST = "photoList";
    private List<String> list;//保存删除的图片在list中的下标


    @Override
    public void initViews() {
        topbarView = (TopbarView) findViewById(R.id.top_bar_view);
        et_declarant_phone = (EditText) findViewById(R.id.et_declarant_phone);
        bt_submit = (Button) findViewById(R.id.bt_submit);
        photoContainerLl = (LinearLayout) findViewById(R.id.photo_container_ll);
        et_describe = (EditText) findViewById(R.id.et_describe);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mLocationClient != null) {
            mLocationClient.stop();
            mLocationClient = null;
        }
    }

    @Override
    public void initData() {
        mContext = this;
        topbarView.setTopbarTitle("3.0报修");
        inflater = LayoutInflater.from(this);
        photoFilePathList = new ArrayList<String>();
        list = new ArrayList<String>();
        filePath = CommonUtil.getSDCardPath() + QkConstant.PHOTO_LOG_DIRECTORY;
        FileUtil.createTempFile(null, filePath);
        addPhoto(null);
        File photoDirectory = new File(CommonUtil.getSDCardPath() + filePath);
        if (!photoDirectory.exists()) {
            photoDirectory.mkdir();
        }
        mLocationClient = new LocationClient(this.getApplicationContext());
        option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        mLocationClient.registerLocationListener(this);
        if (CommonUtil.isGpsEnable(mContext)) {
            InitLocation();
        } else {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setMessage("房管员APP想获取你当前位置");
            dialog.setPositiveButton("确定",
                    new android.content.DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            // 转到手机设置界面，用户设置GPS
                            Intent intent = new Intent(
                                    Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                            startActivityForResult(intent, 0); // 设置完成后返回到原来的界面
                            InitLocation();
                        }
                    });
            dialog.setNeutralButton("取消", new android.content.DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    arg0.dismiss();
                }
            });
            dialog.show();
        }
    }

    /**
     * 隐藏键盘
     */
    public void keyBoardCancle() {
        View view = getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputmanger = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void addListeners() {
        topbarView.setTopBarClickListener(topListener);
        bt_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (photoFilePathList.size() == 0) {
                    CommonUtil.sendToast(SecondSubmitActivity.this, "至少上传一张图片");
                    return;
                }
                if (CommonUtil.isEmpty(et_describe.getText().toString())) {
                    CommonUtil.sendToast(SecondSubmitActivity.this, "问题描述不能为空");
                    return;
                }

                /**
                 * 判断有没有拍五张图片,如果没有就创建几张空文件
                 */
                if (photoFilePathList.size() < 5) {
                    for (int index = photoFilePathList.size(); index < 5; index++) {
                        photoFilePathList.add(Uri.fromFile(createEmptyPhotoFile()).getPath());
                    }
                }
                lodCreateRepair();

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("ReparisActivtiy","onResume");
        /**
         * 解决在红米和三星手机 拍照不回调onActivityResult 所以直接从sd卡读取拍照图片并显示
         */
        if ( mTempFileUri!= null) {
            Log.i("ReparisActivtiy","拍照");
            saveTakePhoto();
        }
    }

    /**
     * 报修提交接口
     */

    private void lodCreateRepair() {

                if (CommonUtil.checkNetwork(mContext)) {
                    showProgressDialog(null, getString(R.string.loading));
                    String testUrl = Connect.getInstance().getApiUrl() + Protocol.CREATEREPAIR;
                    ImageAsyncTask asyncTask = ImageAsyncTask.getInstance(mContext);
                    HashMap<String, Object> map = new HashMap<>();
                    map.put("sacCreateId", 1697);
                    map.put("type", 0);
                    map.put("romId", detail.getRomId());
                    map.put("cutName", detail.getCutName());
                    map.put("description", et_describe.getText().toString());
                    map.put("romAddress", detail.getRomAddress());
                    map.put("mobile", et_declarant_phone.getText().toString());
                    map.put("callTelephone", romDetail.getCallTelephone());
                    map.put("declarant", romDetail.getDeclarant());
                    map.put("memberId", romDetail.getMemberId());
                    int index = 0;
                    for (String imageFilePath : photoFilePathList) {
                        index = index + 1;
                        Pattern p = Pattern.compile("imageFile(.+?)");
                        Matcher m=p.matcher(imageFilePath); //进行匹配
                        String string = "";
                        while (m.find()) {
                            string = m.group();
                        }
                        map.put("picfile"+string.substring(string.length() - 1,string.length()), Uri.parse(imageFilePath));

                    }
                    String apiLogFileDirectory = CommonUtil.getSDCardPath() + "/qk/log";
                    String apiLogFileName = "qk_api_log.txt";
                    asyncTask.post(apiLogFileDirectory, apiLogFileName, testUrl, map, new ResponseResultListener() {
                        @Override
                        public void onResult(ResponseResult result) {
                            doCreateRepair(result);
                        }
                    });
                }

    }

    /**
     * 报修提交接口成功
     *
     * @param result
     */
    private void doCreateRepair(ResponseResult result) {
        if (result.code == ResponseResult.SUCESS_CODE) {
            mLocationClient.stop();
            finish();
        } else if (!CommonUtil.isEmpty(result.message)) {
            /**
             * 请求失败
             */
            CommonUtil.sendToast(this, result.message);
        }
        dissmissProgressDialog();
    }



    private TopbarImplListener topListener = new TopbarImplListener() {
        @Override
        public void leftButtonClick() {
            SecondSubmitActivity.this.finish();
        }
    };

    /**
     * 添加图片
     *
     * @param picFilePath 图片文件路径
     */
    private void addPhoto(final String picFilePath) {
        final View view = inflater.inflate(R.layout.take_photo_item,
                photoContainerLl, false);
        DownloadImageView photoIv = (DownloadImageView) view
                .findViewById(R.id.photo_iv);
        ImageView deleteImage = (ImageView) view.findViewById(R.id.photo_delete);
        if (!CommonUtil.isEmpty(picFilePath)) {
            photoIv.loadNativePic(picFilePath);
            photoIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //跳到大图页面
                    Intent intent = new Intent(SecondSubmitActivity.this, DisplayImageActivity.class);
                    intent.putExtra("path", picFilePath);
                    startActivity(intent);

                }
            });
            photoFilePathList.add(picFilePath);
            deleteImage.setVisibility(View.VISIBLE);
        } else {
            /**
             * 如果文件路径为空 就增加拍照图标
             */
            photoIv.setImageResource(R.drawable.take_photo);
            photoIv.setOnClickListener(takePhotoListener);
        }
        photoContainerLl.addView(view);
        deleteImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(SecondSubmitActivity.this);
                builder.setTitle("确定要删除吗?");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        photoFilePathList.remove(picFilePath);
                        photoContainerLl.removeView(view);
                        list.add(picFilePath);
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.show();

            }
        });
    }

    /**
     * 添加图片
     */
    private void addPhotoList(final List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            addPhoto(list.get(i));
        }
    }

    /**
     * 拍照事件
     */
    private View.OnClickListener takePhotoListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (photoFilePathList.size() >= 5) {
                CommonUtil.sendToast(SecondSubmitActivity.this, "最多上传五张图片");
                return;
            }
            final String[] strings = {"打开相机", "打开相册"};
            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
            builder.setTitle("请选择");

            //    设置一个下拉的列表选择项
            builder.setItems(strings, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (strings[which].equals("打开相机")) {
                        openCamera();
                    } else if (strings[which].equals("打开相册")) {
                        openPhoto();
                    }
                }
            });
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.show();
        }
    };

    /**
     * 打开照相机
     */
    private void openCamera() {
        if (CommonUtil.isGpsEnable(mContext)) {
            date = new Date(System.currentTimeMillis());
            Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            //照片的本地路径
            File file = createTempPhotoFile();
            mTempFileUri = file.getAbsolutePath();
            camera.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
            startActivityForResult(camera, TAKE_PHOTO_REQUEST);

        } else {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setMessage("房管员APP想获取你当前位置");
            dialog.setPositiveButton("确定",
                    new android.content.DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            // 转到手机设置界面，用户设置GPS
                            Intent intent = new Intent(
                                    Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                            startActivityForResult(intent, OPEN_CAMERA); // 设置完成后返回到原来的界面
                            InitLocation();
                        }
                    });
            dialog.setNeutralButton("取消", new android.content.DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    if (detail == null) {
                        CommonUtil.sendToast(mContext, "请填写会员手机号");
                        return;
                    }
                    date = new Date();
                    Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    File file = createTempPhotoFile();
                    //照片的本地路径
                    mTempFileUri = file.getAbsolutePath();
                    camera.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                    startActivityForResult(camera, TAKE_PHOTO_REQUEST);
                }
            });
            dialog.show();
        }
    }

    /**
     * 打开相册
     */
    private void openPhoto() {
        date = new Date(System.currentTimeMillis());
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");//相片类型
        startActivityForResult(intent, REQUEST_CODE_PICK_IMAGE);
    }


    private String getImageFromGallery(Intent data) {
        String filePath = null;
        try {
            Bundle extras = data.getExtras();
            Bitmap bitmap = (Bitmap) extras.get("data");
            File file = createTempPhotoFile();
            FileOutputStream out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.flush();
            out.close();
            filePath = file.getPath();
        } catch (Exception e) {
            Uri imageUrl = data.getData();
            BitmapFactory.Options opts = new BitmapFactory.Options();
            opts.inSampleSize = 4;
            try {
                Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUrl), null, opts);
                File file = createTempPhotoFile();
                FileOutputStream out = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
                out.flush();
                out.close();
                filePath = file.getPath();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return filePath;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("ReparisActivtiy", "onActivityResult");
        if (resultCode != RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_CODE_PICK_IMAGE) {
            Uri uri = data.getData();
            String img_path = getImageFromGallery(data);
            if (img_path == null || CommonUtil.isEmpty(img_path)) {
                return;
            }
            Bitmap bitmap = PhotoUtil.getSmallBitmap(img_path, 480, 800);//找到压缩前的照片
            if (bitmap == null) {
                return;
            }
            Uri bitmapFileUri = Uri.fromFile(createPhotoFile());
            FileOutputStream fileOutputStream = null;
            try {
                fileOutputStream = new FileOutputStream(bitmapFileUri.getPath());
                bitmap.compress(Bitmap.CompressFormat.JPEG, 85, fileOutputStream);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            addPhoto(bitmapFileUri.getPath());
            //把图片保存到数据库
            try {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                    fileOutputStream = null;
                }
                if (!bitmap.isRecycled()) {
                    bitmap.isRecycled(); //回收图片所占的内存
                    bitmap = null;
                }
                System.gc();  //提醒系统及时回收
            } catch (Exception e) {
                e.printStackTrace();
            }
            String path = uri.getPath();
            //删除当前的照片
            File file = new File(path);
            try {
                file.delete();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (requestCode == OPEN_CAMERA) {
            date = new Date();
            Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            File file = createTempPhotoFile();
            //照片的本地路径
            mTempFileUri = file.getAbsolutePath();
            camera.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
            startActivityForResult(camera, TAKE_PHOTO_REQUEST);
        }
    }

    /**
     * 创建临时图片文件
     *
     * @return
     */
    private File createTempPhotoFile() {
        return FileUtil.createTempFile(CommonUtil.dataToString(CommonUtil.FULLDATEFORMATNOCONN, date, null, null),
                filePath);
    }

    /**
     * 创建空图片文件
     *
     * @return
     */
    private File createEmptyPhotoFile() {
        return FileUtil.createTempFile("imageFile" + (photoFilePathList.size() + 1) + "_0",
                filePath);

    }

    private File createPhotoFile() {
        File file = null;
        if (list.size() > 0){
            String filePaths = list.get(0);
            list.remove(0);
            file = new File(filePaths);
        }else{
            file = FileUtil.createTempFile(CommonUtil.dataToString(CommonUtil.FULLDATEFORMATNOCONN, new Date(), photoFilePathList.size() + 1),
                    filePath);
        }
        return file;
    }

    /**
     * 保存拍照的照片
     */
    private void saveTakePhoto() {
        if (CommonUtil.isEmpty(mTempFileUri)) {
            return;
        }

        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        Bitmap bitmap = PhotoUtil.getSmallBitmap(mTempFileUri, 480, 800);//找到压缩前的照片
        if (bitmap == null) {
            return;
        }

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        String time = format.format(date);
        String title = "";
        if (CommonUtil.checkNetwork(mContext)) {
            if (CommonUtil.isGpsEnable(mContext)) {
                if (null == Address) {
                    title = "本图由<" + "华为" + ">于<" + time + ">申报在<" + "网络不好，定位失败，请重新拍照！" + ">拍摄";
                } else {
                    title = "本图由<" + "华为" + ">于<" + time + ">申报在<" + Address + ">拍摄";
                }
            } else {
                title = "本图由<" + "华为" + ">于<" + time + ">申报在<" + "房管员拒绝获取房间地址" + ">拍摄";
            }
        } else {
            title = "本图由<" +"华为"+ ">于<" + time + ">申报在<" + "网络不好，定位失败，请重新拍照！" + ">拍摄";
        }
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        float radio = 1;
        if (w > 480) {
            radio = w / 480;
            w = (int) (w / radio);
            h = (int) (h / radio);
        }
        Bitmap newb = Bitmap.createBitmap(w, h, Bitmap.Config.RGB_565);
        Canvas cv = new Canvas(newb);
        Paint paint = new Paint();
        paint.setDither(true); //获取跟清晰的图像采样
        paint.setFilterBitmap(true);//过滤一些
        cv.drawBitmap(bitmap, 0, 0, paint);
        Rect src = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());//创建一个指定的新矩形的坐标
        Rect dst = new Rect(0, 0, w, h);
        cv.drawBitmap(bitmap, src, dst, paint);
        if (title != null) {
            String familyName = "宋体";
            Typeface font = Typeface.create(familyName, Typeface.BOLD);
            TextPaint textPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG | Paint.DEV_KERN_TEXT_FLAG);//设置画笔
            textPaint.setColor(Color.RED);
            textPaint.setTypeface(font);
            textPaint.setTextSize(25);
            cv.save(Canvas.ALL_SAVE_FLAG);
            cv.restore();
            StaticLayout layout = new StaticLayout(title, textPaint, w, Layout.Alignment.ALIGN_NORMAL, 1.2F, 0.0F, true);
            cv.translate(0, h - 100);
            layout.draw(cv);
        }
        Uri bitmapFileUri = Uri.fromFile(createPhotoFile());
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(bitmapFileUri.getPath());
            newb.compress(Bitmap.CompressFormat.JPEG, 85, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //把图片保存到数据库
        try {
            if (fileOutputStream != null) {
                fileOutputStream.flush();
                fileOutputStream.close();
                fileOutputStream = null;
            }
            if (!bitmap.isRecycled()) {
                bitmap.isRecycled(); //回收图片所占的内存
                bitmap = null;
            }
            System.gc();  //提醒系统及时回收
        } catch (Exception e) {
            e.printStackTrace();
        }
        String path = mTempFileUri;
        //删除当前的照片
        File file = new File(path);
        try {
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        addPhoto(bitmapFileUri.getPath());
        mTempFileUri = null;

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_second_submit;
    }

    /**
     * 定位方式
     */
    private void InitLocation() {
        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
        int span = 5000;
        option.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setLocationNotify(true);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        option.setIsNeedLocationDescribe(false);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(false);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(true);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤gps仿真结果，默认需要
        mLocationClient.setLocOption(option);
        mLocationClient.start();
    }

    /**
     * 百度定位结果回调
     *
     * @param bdLocation
     */
    @Override
    public void onReceiveLocation(BDLocation bdLocation) {
        Address = bdLocation.getAddrStr();
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i("RepairsActivity", "onSaveInstanceState");
        if (Address != null){
            outState.putString("address",Address);
        }else{
            outState.putString("address","");
        }
        outState.putStringArrayList(PHOTOLIST, (ArrayList<String>) photoFilePathList);//保存图片路径
        if(mTempFileUri != null) {
            outState.putString("take_photo_file_path", mTempFileUri);//
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i("RepairsActivity", "onRestoreInstanceState");
        if (null == savedInstanceState){
            return;
        }
        String address = savedInstanceState.getString("address");
        if (CommonUtil.isEmpty(address)){
            Address = null;
        }else{
            Address = address;
        }
        mTempFileUri = savedInstanceState.getString("take_photo_file_path");
        List<String> list = savedInstanceState.getStringArrayList(PHOTOLIST);
        if (null != list && list.size() > 0) {
            addPhotoList(list);
        }
    }
}
