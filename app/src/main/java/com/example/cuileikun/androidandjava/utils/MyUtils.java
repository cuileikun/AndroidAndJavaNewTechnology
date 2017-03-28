package com.example.cuileikun.androidandjava.utils;

import android.content.Context;

import com.example.cuileikun.androidandjava.base.LocalUserDataModel;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 作者：popular cui
 * 时间：2017/3/28 16:39
 * 功能:自己平时总计的方法
 */
public class MyUtils {

    /**
     * 1，判断手机号是否合法
     *
     * @param mobiles
     * @return
     */
    public static boolean checkPhoneNumber(String mobiles) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        p = Pattern.compile("^[1][3,4,5,8][0-9]{9}$"); // 验证手机号
        m = p.matcher(mobiles);
        b = m.matches();
        return b;
    }

    /**
     *2， 把用户数据保存起来
     * 保存一个对象到本地
     * @param context
     * @param data
     */
    public static void saveLocalUser(Context context, LocalUserDataModel data) {
        Writer writer = null;
        try {
            OutputStream out = context.openFileOutput("userInfo.json", Context.MODE_PRIVATE);
            writer = new OutputStreamWriter(out);
            writer.write(convertJson(data).toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }



    /**
     *,3，把对象转换为json
     *
     * @param model
     * @return
     */
    public static JSONObject convertJson(LocalUserDataModel model) {
        Gson gson = new Gson();
        String result = gson.toJson(model);
        JSONObject json = null;
        try {
            json = new JSONObject(result);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }






}
