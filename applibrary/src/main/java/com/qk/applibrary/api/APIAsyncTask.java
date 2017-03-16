package com.qk.applibrary.api;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.qk.applibrary.bean.ResponseResult;
import com.qk.applibrary.http.AsyncHttpClient;
import com.qk.applibrary.http.AsyncHttpResponseHandler;
import com.qk.applibrary.http.RequestParams;
import com.qk.applibrary.listener.ResponseResultListener;
import com.qk.applibrary.util.LogUtil;

/**
 * 客户端调用后台接口
 *
 * @author Administrator
 */
public class APIAsyncTask {
    private Context context;
    private ResponseResultListener mListener;
    private String mUrl;
    private int requestType;// 1为get  2为post 3为delete
    private AsyncHttpClient http;
    private String apiLogFileDirectory;//api 日志文件目录
    private String apiLogFileName;//api 日志文件名字

    public APIAsyncTask(Context context) {
        // TODO Auto-generated constructor stub
        super();
        this.context = context;
    }

    /**
     * get请求
     *
     * @param apiLogFileDirectory api日志文件目录
     * @param apiLogFileName      api日志文件名
     * @param url                 接口地址
     * @param paramMap            参数map
     * @param listener            回调接口
     */
    public void get(String apiLogFileDirectory, String apiLogFileName, String url, HashMap<String, Object> paramMap, ResponseResultListener listener) {
        mListener = listener;
        this.apiLogFileName = apiLogFileName;
        this.apiLogFileDirectory = apiLogFileDirectory;
        this.requestType = 1;
        mUrl = url;
        convert(paramMap);
    }

    /**
     * post请求
     *
     * @param apiLogFileDirectory api日志文件目录
     * @param apiLogFileName      api日志文件名
     * @param url                 接口地址
     * @param paramMap            参数map
     * @param listener            回调接口
     */
    public void post(String apiLogFileDirectory, String apiLogFileName, String url, HashMap<String, Object> paramMap, ResponseResultListener listener) {
        mListener = listener;
        this.apiLogFileName = apiLogFileName;
        this.apiLogFileDirectory = apiLogFileDirectory;
        mUrl = url;
        this.requestType = 2;
        convert(paramMap);
    }

    public void execute() {

    }

    private AsyncHttpClient getAsyncHttp() {
        if (http == null) {
            http = new AsyncHttpClient();
            http.setTimeout(1000 * 10);
        }
        return http;
    }

    private void convert(HashMap<String, Object> paramMap) {
        StringBuffer params = new StringBuffer();
        try {
            AsyncHttpClient client = this.getAsyncHttp();
            if (requestType == 1) {
                params.append(copy(paramMap)).toString();
                String url = mUrl + "?" + params.toString();
                LogUtil.log("[---------send-----" + "get" + "----]:" + url, apiLogFileDirectory, apiLogFileName);
                client.get(url, new AsyncHttpResponseHandler() {
                    public void onSuccess(String response) {
                        LogUtil.log("[---------result----GET-----]:" + response, apiLogFileDirectory, apiLogFileName);
                        requestFinished(response);
                    }

                    public void onFinish() {

                    }

                    public void onFailure(Throwable error, String content) {
                        error.printStackTrace();
                        requestFailed(error, content);
                    }

                });

            } else if (requestType == 2) {
                Gson gson = new Gson();
                JsonObject jsonBody = new JsonObject();
                Iterator iterator = paramMap.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry entry = (Map.Entry) iterator.next();
                    jsonBody.add((String) entry.getKey(), gson.toJsonTree(entry.getValue()));
                }
                params.append(gson.toJson(jsonBody));
                byte[] jsonBytes = jsonBody.toString().getBytes();
                HttpEntity entity = new ByteArrayEntity(jsonBytes);
                String contentType = "application/json;charset=UTF-8";
                LogUtil.log("[---------send-----" + "post" + "----]:" + mUrl + "?" + params.toString(), apiLogFileDirectory, apiLogFileName);
                client.post(null, mUrl, entity, contentType,
                        new AsyncHttpResponseHandler() {
                            public void onSuccess(String response) {
                                LogUtil.log("[---------result----POST-----]:"
                                        + response, apiLogFileDirectory, apiLogFileName);
                                requestFinished(response);
                            }

                            public void onFinish() {

                            }

                            public void onFailure(Throwable error,
                                                  String content) {
                                error.printStackTrace();
                                requestFailed(error, content);
                            }

                        });

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 处理服务器返回数据
     */
    private void requestFinished(String result) {

        if (StringUtils.isNotBlank(result)) {

            try {
                JSONObject jsons = new JSONObject(result);
                int code = jsons.getInt("result");
                String message = "";
//				if(jsons.has("message")) {
//					message = jsons.getString("message");
//				}
                switch (code) {
                    case 0:
                        ResponseResult data = new ResponseResult();
                        data.code = code;
                        data.data = result;
                        data.message = "";
                        mListener.onResult(data);
                        break;
                    //增加
                    case 2:
                        ResponseResult datas = new ResponseResult();
                        datas.code = code;
                        datas.data = result;
                        datas.message = "";
                        mListener.onResult(datas);
                        break;
                    default:
                        String msg = "";
                        if (jsons.has("error")) {
                            msg = jsons.getString("error");
                        }
                        data = new ResponseResult();
                        data.code = code;
                        data.data = result;
                        data.message = msg;
                        mListener.onResult(data);
                        break;
                }
            } catch (JSONException e) {
                // TODO: handle exception
                e.printStackTrace();
                //推送平台的数据
                try {
                    JSONObject jsons = new JSONObject(result);
                    if (jsons.has("access_token")) {
                        ResponseResult data = new ResponseResult();
                        data.pushToken = jsons.getString("access_token");
                        mListener.onResult(data);
                    }

                } catch (JSONException e1) {
                    e1.printStackTrace();
                }

            }

        }
    }

    /**
     * 连接失败
     */
    private void requestFailed(Throwable error, String content) {
        LogUtil.log("error:" + error.getMessage() + ":" + content, apiLogFileDirectory, apiLogFileName);
        ResponseResult result = new ResponseResult();
        result.code = -1;
        result.message = "连接失败";
        mListener.onResult(result);
    }

    /**
     * 封装请求参数
     *
     * @param obj
     * @return
     * @throws IllegalArgumentException
     * @throws SecurityException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     */
    private String copy(Object obj) throws IllegalArgumentException,
            SecurityException, InstantiationException, IllegalAccessException,
            InvocationTargetException, NoSuchMethodException {

        Class<?> classType = obj.getClass();
        if (classType.equals(RequestParams.class)) {
            return obj.toString();
        } else if(classType.equals(HashMap.class)) {
            StringBuilder result = new StringBuilder();
            HashMap<String,Object> map = (HashMap<String,Object>)obj;
            Iterator<Map.Entry<String,Object>> entrie = map.
                    entrySet().iterator();
            while (entrie.hasNext()) {
                Map.Entry<String,Object> entry = entrie.next();
                if(result.length() > 0)
                    result.append("&");
                result.append(entry.getKey());
                result.append("=");
                result.append(entry.getValue());
            }
            return result.toString();
        } else {
            Object objectCopy = classType.getConstructor(new Class[]{})
                    .newInstance(new Object[]{});
            Field[] fields = classType.getDeclaredFields();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                String fieldName = field.getName();
                if (fieldName.equals("serialVersionUID")) {
                    continue;
                }
                String stringLetter = fieldName.substring(0, 1).toUpperCase();
                String getName = "get" + stringLetter + fieldName.substring(1);
                String setName = "set" + stringLetter + fieldName.substring(1);
                Method getMethod = classType.getMethod(getName, new Class[]{});
                Method setMethod = classType.getMethod(setName,
                        new Class[]{field.getType()});
                Object value = getMethod.invoke(obj, new Object[]{});
                if (value == null)
                    value = "";
                sb.append("&" + fieldName + "=" + value);
                setMethod.invoke(objectCopy, new Object[]{value});
            }
            return sb.toString();

        }
    }
}
