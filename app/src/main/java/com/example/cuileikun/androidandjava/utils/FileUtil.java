package com.example.cuileikun.androidandjava.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Typeface;
import android.media.ExifInterface;
import android.net.Uri;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;

import com.qk.applibrary.util.CommonUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by TangHao on 2016/8/15.
 * 用于处理图片文件的相关操作
 */
public class FileUtil {

    public static int compressMinSize = 300;//300kb
    public static int maxSize = 5;//5M
    public static String outMaxSizeTip="上传图片不能超过5M";

    /**
     * 保存压缩图片至指定位置
     *
     * @param bm
     * @param filePath
     * @return
     */
    public static File saveCompressFile(Bitmap bm, String filePath) {

        File compressFile = null;
        try {
            if (bm != null) {
                compressFile = new File(filePath);
//            compressFile.createNewFile();
                FileOutputStream fos = new FileOutputStream(compressFile);
                bm.compress(Bitmap.CompressFormat.JPEG, 30, fos);//这里试验中最低设置成30相对不失真
                fos.flush();
                fos.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return compressFile;
    }


    /**
     * 将文件的图片压缩
     * 到指定压缩到的宽、高
     * 并且调整角度
     *
     * @param filePath
     * @param //reqWidth  预留参数
     * @param //reqHeight
     * @return
     */
    public static Bitmap compressPicture(String filePath) {
        Bitmap bm = getSmallBitmap(filePath, 480, 800);//得到指定大小的压缩图片

        int degree = readPictureDegree(filePath);//读取原图照片的角度

        if (degree != 0) {//旋转照片角度
            bm = rotateBitmap(bm, degree);
        }

        return bm;
    }

    /**
     * 获取文件大小
     * <p/>
     * FileInputStream的available()是int类型，
     * 最大值是1.99G有缺陷
     *
     * @param f
     * @return
     */
    public static long getFileSize(File f) {
        long s = 0;
        if (f.exists()) {
            s = f.length();
        }
        return s;
    }


    /**
     * 给图片文件加水印 也可以加文字
     *
     * @param src
     * @param title
     * @return
     */
    public static Bitmap watermarkBitmap(Bitmap src,
                                         String title) {
        if (src == null) {
            return null;
        }
        int w = src.getWidth();
        int h = src.getHeight();
        //需要处理图片太大造成的内存超过的问题,这里我的图片很小所以不写相应代码了
        Bitmap newb = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);// 创建一个新的和SRC长度宽度一样的位图
        Canvas cv = new Canvas(newb);
        cv.drawBitmap(src, 0, 0, null);// 在 0，0坐标开始画入src
//        Paint paint = new Paint();
//        //加入图片
//        if (watermark != null) {
//            int ww = watermark.getWidth();
//            int wh = watermark.getHeight();
//            paint.setAlpha(50);
//            cv.drawBitmap(watermark, w - ww + 5, h - wh + 5, paint);// 在src的右下角画入水印
//        }
        //加入文字
        if (title != null) {
            String familyName = "宋体";
            Typeface font = Typeface.create(familyName, Typeface.BOLD);
            TextPaint textPaint = new TextPaint();
            textPaint.setColor(Color.RED);
            textPaint.setTypeface(font);
            textPaint.setTextSize(35);
            //这里是自动换行的
            StaticLayout layout = new StaticLayout(title, textPaint, w, Layout.Alignment.ALIGN_NORMAL, 1.0F, 0.0F, true);
            cv.translate(0, h - 130);
            layout.draw(cv);
            //文字就加左上角算了
//            cv.drawText(title, 0, w - 40, textPaint);
        }
        cv.save(Canvas.ALL_SAVE_FLAG);// 保存
        cv.restore();// 存储
        return newb;
    }


    /**
     * 计算图片的缩放值
     *
     * @param options
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }


    /**
     * 根据路径获得图片并压缩，返回bitmap用于显示
     * 压缩指定宽度和高度
     *
     * @param filePath
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    private static Bitmap getSmallBitmap(String filePath, int reqWidth, int reqHeight) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);//这里返回的bitmap是null

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;

        return BitmapFactory.decodeFile(filePath, options);
    }


    /**
     * 旋转照片
     *
     * @param bitmap
     * @param degress
     * @return
     */
    public static Bitmap rotateBitmap(Bitmap bitmap, int degress) {
        if (bitmap != null) {
            Matrix m = new Matrix();
            m.postRotate(degress);
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
                    bitmap.getHeight(), m, true);
            return bitmap;
        }
        return bitmap;
    }

    /**
     * 读取照片角度
     *
     * @param path
     * @return
     */
    public static int readPictureDegree(String path) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

    /**
     * URI---》》》》filePath
     *
     * @param context
     * @param uri
     * @return
     */
    public static String getFilePathFromUri(Context context, Uri uri) {
        return null;
    }
    /**
     * 创建临时文件
     * @param fileName
     * @param path
     * @return
     */
    public static File createTempFile(String fileName, String path) {
        File file = null;
        try {
            File folder = new File(path);
            if(folder.exists() == false || folder.isDirectory() == false){
                folder.mkdirs();
            }
            if(!CommonUtil.isEmpty(fileName)) {
                file = new File(folder, fileName);
                if (file.exists()) {
                    file.delete();
                }
                file.createNewFile();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }


}
