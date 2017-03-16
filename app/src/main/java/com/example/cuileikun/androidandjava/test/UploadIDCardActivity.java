package com.example.cuileikun.androidandjava.test;

import com.qk.applibrary.activity.QkActivity;

/**
 * Created by TangHao on 2016/5/26.
 */
public class UploadIDCardActivity extends QkActivity {
/*

    private static final int gallery = 1, camera = 2;

    private TopbarView tbvTop;
    private Button btnNext;
    private Context mContext;
    private ImageView ivIdentityPicture, ivIdentityPicture2;
    private ImageView ivIdentityPictureTip, ivIdentityPicture2Tip;
    private TextView tvTip, tvTip2;

    private HashMap<String, File> fileMap;
    private File takePhotoFile;//临时存储File

    private boolean isFirstPicture = true;


    @Override
    public void initViews() {
        tbvTop = (TopbarView) findViewById(R.id.tbv_top);
        btnNext = (Button) findViewById(R.id.btn_next);
        ivIdentityPicture = (ImageView) findViewById(R.id.iv_identity_picture);
        ivIdentityPictureTip = (ImageView) findViewById(R.id.iv_identity_picture_tip);
        ivIdentityPicture2 = (ImageView) findViewById(R.id.iv_identity_picture2);
        ivIdentityPicture2Tip = (ImageView) findViewById(R.id.iv_identity_picture2_tip);
        tvTip = (TextView) findViewById(R.id.tv_identity_tip);
        tvTip2 = (TextView) findViewById(R.id.tv_identity_tip2);
    }


    @Override
    public void initData() {
//        etPhone.setText("18651405214");

        mContext = this;

        fileMap = new HashMap<>();
        tbvTop.setTopbarTitle("绑定账户操作人");
        tbvTop.getTopbarBackIv().setVisibility(View.GONE);

    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_upload_idcard;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putSerializable("files", fileMap);
        outState.putSerializable("takePhotoFile", takePhotoFile);
        outState.putBoolean("isFirstPicture", isFirstPicture);

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        isFirstPicture = savedInstanceState.getBoolean("isFirstPicture", false);
        fileMap = (HashMap<String, File>) savedInstanceState.getSerializable("files");
        if (fileMap == null)
            return;

        if (fileMap.containsKey("ID_UP")) {
            File file = fileMap.get("ID_UP");
            ivIdentityPicture.setImageURI(Uri.fromFile(file));
            ivIdentityPicture.setVisibility(View.VISIBLE);
            ivIdentityPictureTip.setVisibility(View.GONE);
            tvTip.setVisibility(View.VISIBLE);
        }
        if (fileMap.containsKey("ID_BACK")) {
            File file = fileMap.get("ID_BACK");
            ivIdentityPicture2.setImageURI(Uri.fromFile(file));
            ivIdentityPicture2.setVisibility(View.VISIBLE);
            ivIdentityPicture2Tip.setVisibility(View.GONE);
            tvTip2.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void addListeners() {
        ivIdentityPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isFirstPicture = true;
                showSelectDialog();
            }
        });
        ivIdentityPicture2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isFirstPicture = false;
                showSelectDialog();
            }
        });
        ivIdentityPictureTip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isFirstPicture = true;
                showSelectDialog();
            }
        });
        ivIdentityPicture2Tip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isFirstPicture = false;
                showSelectDialog();
            }
        });


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<File> files = new ArrayList<>();
                File file = null;
                if (fileMap.containsKey("ID_UP")) {
                    file = fileMap.get("ID_UP");
                    files.add(file);
                }
                if (fileMap.containsKey("ID_BACK")) {
                    file = fileMap.get("ID_BACK");
                    files.add(file);
                }


                if (files != null && files.size() == 2)
                    uploadIdentityPictures(files);
                else
                    CommonUtil.sendToast(mContext, "请选择身份证照片");

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK)
            return;

        if (requestCode == gallery) {
            if (data == null)
                return;

            String filePath = FileUtil.getFilePathFromUri(mContext, data.getData());
            takePhotoFile = new File(filePath);
        } else if (requestCode == camera) {
        }

        //处理takePhotoFile原图
        //压缩图片
        if (takePhotoFile != null)
            compressPictureAndShow();
    }


    */
/**
     * 压缩图片
     *//*

    private void compressPictureAndShow() {

        if (FileUtil.getFileSize(takePhotoFile) / 1024 / 1024 >= FileUtil.maxSize) {
            CommonUtil.sendToast(mContext, FileUtil.outMaxSizeTip);
            return;
        }

        if (FileUtil.getFileSize(takePhotoFile) / 1024 > FileUtil.compressMinSize) {//大于300kb压缩
            Bitmap bitmap = FileUtil.compressPicture(takePhotoFile.getPath());

            File compressFileDir = new File(Constants.PictureFileDef.PICTURE_FILE_COMPRESS_DIRECTORY);
            if (!compressFileDir.exists())
                compressFileDir.mkdirs();

            takePhotoFile = FileUtil.saveCompressFile(bitmap, compressFileDir + "/" + takePhotoFile.getName());
        }

        //显示压缩后的图片
        if (isFirstPicture) {
            ivIdentityPicture.setImageURI(Uri.fromFile(takePhotoFile));
            ivIdentityPicture.setVisibility(View.VISIBLE);
            ivIdentityPictureTip.setVisibility(View.GONE);

            fileMap.put("ID_UP", takePhotoFile);
            tvTip.setVisibility(View.VISIBLE);

        } else {
            ivIdentityPicture2.setImageURI(Uri.fromFile(takePhotoFile));
            ivIdentityPicture2.setVisibility(View.VISIBLE);
            ivIdentityPicture2Tip.setVisibility(View.GONE);

            fileMap.put("ID_BACK", takePhotoFile);
            tvTip2.setVisibility(View.VISIBLE);

        }
    }
    private void showSelectDialog() {
        new AlertDialog.Builder(mContext)
                .setItems(new String[]{"从手机相册选取", "拍一张图片"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        if (which == 0) {
                            startGallery();
                        } else if (which == 1) {
                            startCamera();
                        }
                    }
                })
                .show()
                .getWindow().setGravity(Gravity.BOTTOM);
    }

    */
/**
     * 启动图片画廊
     *//*

    public void startGallery() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image*/
/*");
        startActivityForResult(intent, gallery);

    }

    */
/**
     * 启动相机
     *//*

    public void startCamera() {

        if (CommonUtil.isSDCardExist()) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            */
/* 创建存放图片文件夹 *//*

            File dir = new File(Constants.PictureFileDef.PICTURE_FILE_DIRECTORY);
            if (!dir.exists())
                dir.mkdirs();
            */
/* 设置图片参数 得到原尺寸的图片 *//*

            takePhotoFile = new File(dir, System.currentTimeMillis() + ".jpg");
            intent.putExtra(MediaStore.EXTRA_SCREEN_ORIENTATION,
                    Configuration.ORIENTATION_UNDEFINED);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(takePhotoFile));

            startActivityForResult(intent, camera);
        }
    }


    */
/**
     * 上传图片
     *//*

    private void uploadIdentityPictures(List<File> files) {
        if (CommonUtil.checkNetwork(this)) {
            showProgressDialog(null, "服务正在玩命加载中");
            APIAsyncTask task = new APIAsyncTask(this);
            String checkMobileUrl = Connect.getInstance().getApiUrl() + "/Api/Company/UploadIdentityCard";
            HashMap<String, Object> headParams = new HashMap<>();
            String token = SharedPreferencesUtil.getSetting(Constants.UserDef.USER_FILE_NAME, this, Constants.UserDef.TOKEN);
            headParams.put("Host", Constants.HOST);
            headParams.put("Authorization", token);
            HashMap<String, Object> hashMap = new HashMap<>();
//            hashMap.put("pic", Uri.fromFile(filePicture));
            hashMap.put("files", files);
            task.post(Constants.LogDef.logFileDirectory, Constants.LogDef.logFileName, checkMobileUrl, hashMap, headParams, new ResponseResultListener() {
                @Override
                public void onResult(ResponseResult result) {
                    dissmissProgressDialog();
                    if (result.code == result.SUCESS_CODE) {
                        Intent intent = new Intent(mContext, CreateCompanyPayPasswordActivity.class);
                        startActivity(intent);

                    } else {
                        CommonUtil.sendToast(mContext, result.message);
                    }
                }
            });
        }
    }
*/

}
