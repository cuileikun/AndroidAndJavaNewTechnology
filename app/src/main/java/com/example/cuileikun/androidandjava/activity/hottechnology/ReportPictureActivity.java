package com.example.cuileikun.androidandjava.activity.hottechnology;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.cuileikun.androidandjava.R;
import com.example.cuileikun.androidandjava.activity.Constant.QkConstant;
import com.example.cuileikun.androidandjava.api.Connect;
import com.example.cuileikun.androidandjava.api.Protocol;
import com.example.cuileikun.androidandjava.utils.FileUtil;
import com.example.cuileikun.androidandjava.utils.PhotoUtil;
import com.example.cuileikun.androidandjava.view.BigImageViewActivity;
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
/**
 * 把图片以数组的方式提交给后台
 */
public class ReportPictureActivity extends QkActivity {
    private Context mContext;
    public static ReportPictureActivity mInstance = null;
    private TopbarView topbarView;
    private LinearLayout photoContainerLl;
    private Button submit;//提交按钮
    private ArrayList<String> photoFilePathList;//图片文件路径
    private String remark = "上传照片";
    private LayoutInflater inflater;
    private Date date;
    private String mTempFileUri;
    private String filePath;
    public final String PHOTOLIST = "photoList";
    private final int TAKE_PHOTO_REQUEST = 100;
    private final int REQUEST_CODE_PICK_IMAGE = 200;
    private final int OPEN_CAMERA = 300;
    private Button btn_summit_second;

    @Override
    public void initViews() {
        mContext = this;
        mInstance = this;
        topbarView = (TopbarView) findViewById(R.id.top_bar_view);
        photoContainerLl = (LinearLayout) findViewById(R.id.photo_container_ll);
        submit = (Button) findViewById(R.id.submit);
        btn_summit_second = (Button) findViewById(R.id.btn_summit_second);
        photoFilePathList = new ArrayList<String>();
        filePath = CommonUtil.getSDCardPath() + QkConstant.PHOTO_LOG_DIRECTORY;
        FileUtil.createTempFile(null, filePath);
        File photoDirectory = new File(CommonUtil.getSDCardPath() + filePath);
        if (!photoDirectory.exists()) {
            photoDirectory.mkdir();
        }
    }

    @Override
    public void initData() {
        topbarView.setTopbarTitle("上传照片");
        inflater = LayoutInflater.from(this);
        addPhoto(null);
    }

    @Override
    public void addListeners() {
        topbarView.setTopBarClickListener(topListener);
        submit.setOnClickListener(submitClistener);
        btn_summit_second.setOnClickListener(secondSummitListener);
    }
View.OnClickListener secondSummitListener=new View.OnClickListener(){

    @Override
    public void onClick(View v) {
        startActivity(new Intent(mContext,SecondSubmitActivity.class));
    }
};
    View.OnClickListener submitClistener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            //检查是否有图片
            if (photoFilePathList.size() == 0) {
                CommonUtil.sendToast(ReportPictureActivity.this, "至少上传一张图片");
                return;
            }
            /**
             * 判断有没有拍五张图片,如果没有就创建几张空文件
             * 因为后台必须要接收十张，如果没有拍够十张就要传空的，不能不传
             */
//           if (photoFilePathList.size() < 5) {
//                for (int index = photoFilePathList.size(); index < 5; index++) {
//                   photoFilePathList.add(Uri.fromFile(createEmptyPhotoFile()).getPath());
//                }
//           }
            //凭证上报接口
            lodSubmitForApproval();
        }
    };

    /**
     * 审批提交接口
     */
    private void lodSubmitForApproval() {

        if (CommonUtil.checkNetwork(mContext)) {
            showProgressDialog(null, getString(R.string.loading));
            String testUrl = Connect.getInstance().getApiHmUrl() + Protocol.VOUCHERREPORT;//凭证上报接口
            ImageAsyncTask asyncTask = ImageAsyncTask.getInstance(mContext);
            HashMap<String, Object> map = new HashMap<>();
            //URLEncoder.encode(content,"utf-8")  对特殊字符表情符号进行编码
            //   map.put("remark", etRemarks.getText().toString().trim());//1，备注说明
          /*  try {
                remark = URLEncoder.encode(remark);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }*/

            map.put("remark", "上传照片");//1，备注说明
            map.put("qrmId", 13);//2，退房信息id
            map.put("usrId", 1697);//3，用户id userId

            int index = 0;
            Uri imageString[] = new Uri[photoFilePathList.size()];
//                    for (String imageFilePath : photoFilePathList) {
            for (int i = 0; i < photoFilePathList.size(); i++) {
                String imageFilePath = photoFilePathList.get(i);
                imageString[i] = Uri.parse(imageFilePath);
//                        index = index + 1;
//                        Pattern p = Pattern.compile("imageFile(.+?)");
//                        Matcher m = p.matcher(imageFilePath); //进行匹配
//                        String string = "";
//                        while (m.find()) {
//                            string = m.group();
//                        }
//                        map.put("imageList" + string.substring(string.length() - 1, string.length()), Uri.parse(imageFilePath));

            }
            map.put("imageList", imageString);
            String apiLogFileDirectory = CommonUtil.getSDCardPath() + "/qk/log";
            String apiLogFileName = "qk_api_log.txt";
            asyncTask.post(apiLogFileDirectory, apiLogFileName, testUrl, map, new ResponseResultListener() {
                @Override
                public void onResult(ResponseResult result) {
                    doSubmitForApproval(result);
                }
            });
        }
    }

    /**
     * 审批提交结果成功
     *
     * @param result
     */
    private void doSubmitForApproval(ResponseResult result) {
        if (result.code == ResponseResult.SUCESS_CODE) {
            CommonUtil.sendToast(mContext, "上传成功");
            finish();
        } else if (!CommonUtil.isEmpty(result.message)) {
            /**
             * 请求失败
             */
            CommonUtil.sendToast(this, result.message);
        }
        dissmissProgressDialog();
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

    private TopbarImplListener topListener = new TopbarImplListener() {
        @Override
        public void leftButtonClick() {
            finish();
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
            photoFilePathList.add(picFilePath);
            deleteImage.setVisibility(View.VISIBLE);
            photoIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //跳到大图页面
                    Intent intent = new Intent(ReportPictureActivity.this, BigImageViewActivity.class);
                    intent.putStringArrayListExtra("urlList", photoFilePathList);
                    intent.putExtra(QkConstant.URLPOSITION, getCurrentPosition(picFilePath));
                    startActivity(intent);

                }
            });
            //上移修复拍照删除两张以上再查看错位的bug
          /*  photoFilePathList.add(picFilePath);
            deleteImage.setVisibility(View.VISIBLE);*/

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
                final AlertDialog.Builder builder = new AlertDialog.Builder(ReportPictureActivity.this);
                builder.setTitle("确定要删除吗?");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        photoFilePathList.remove(picFilePath);
                        photoContainerLl.removeView(view);
                        //  list.add(picFilePath);
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
            //检查图片数量是否大于5张
            if (photoFilePathList.size() > 4) {
                CommonUtil.sendToast(ReportPictureActivity.this, "最多上传5张图片");
                return;
            } else {
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

        }
    };

    /**
     * 打开照相机
     */
    private void openCamera() {
        date = new Date(System.currentTimeMillis());
        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //照片的本地路径
        File file = createTempPhotoFile();
        mTempFileUri = file.getAbsolutePath();
        camera.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        startActivityForResult(camera, TAKE_PHOTO_REQUEST);
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

    private File createPhotoFile() {
        File file = null;
//        if (list.size() > 0) {
//            String filePaths = list.get(0);
//            list.remove(0);
//            file = new File(filePaths);
//        } else {
        file = FileUtil.createTempFile(CommonUtil.dataToString(CommonUtil.FULLDATEFORMATNOCONN, new Date(), photoFilePathList.size() + 1),
                filePath);
        //  }
        return file;
    }

    /**
     * 保存拍照的照片
     */
    private void saveTakePhoto() {
        if (CommonUtil.isEmpty(mTempFileUri)) {
            return;
        }

        Bitmap bitmap = PhotoUtil.getSmallBitmap(mTempFileUri, 480, 800);//找到压缩前的照片
        if (bitmap == null) {
            return;
        }

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
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
    protected void onResume() {
        super.onResume();
        Log.i("ReparisActivtiy", "onResume");
        /**
         * 解决在红米和三星手机 拍照不回调onActivityResult 所以直接从sd卡读取拍照图片并显示
         */
        if (mTempFileUri != null) {
            Log.i("ReparisActivtiy", "拍照");
            saveTakePhoto();
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i("RepairsActivity", "onSaveInstanceState");
        outState.putStringArrayList(PHOTOLIST, (ArrayList<String>) photoFilePathList);//保存图片路径
        if (mTempFileUri != null) {
            outState.putString("take_photo_file_path", mTempFileUri);//
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i("RepairsActivity", "onRestoreInstanceState");
        if (null == savedInstanceState) {
            return;
        }
        mTempFileUri = savedInstanceState.getString("take_photo_file_path");
        List<String> list = savedInstanceState.getStringArrayList(PHOTOLIST);
        if (null != list && list.size() > 0) {
            addPhotoList(list);
        }
    }

    //加载布局
    @Override
    public int getLayoutId() {
        return R.layout.activity_report_picture;
    }

    public int getCurrentPosition(String path) {
        int currentLocation = 0;
        for (int i = 0; i < photoFilePathList.size(); i++) {
            String currentPath = photoFilePathList.get(i);
            if (path.equals(currentPath)) {
                currentLocation = i;
                break;
            }

        }
        return currentLocation;
    }
}
