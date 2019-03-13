package com.bw.xuhongtao.utils;

import android.util.Log;

import com.bw.xuhongtao.R;

import java.util.Map;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * @author xuhongtao   单例模式    饿汉式
 * @fileName OkhttpUtils
 * @package com.bw.xuhongtao.utils
 * @date 2019/2/16/016 17:23
 */
public class OkhttpUtils {
    //私有的静态
    private static OkhttpUtils okhttpUtils;

    private OkhttpUtils() {
    }

    public static OkhttpUtils getInstance() {
        if (okhttpUtils == null) {
            synchronized (OkhttpUtils.class) {
                if (okhttpUtils == null) {
                    okhttpUtils = new OkhttpUtils();
                }
            }
        }
        return okhttpUtils;

    }
//封装OKHTTP
    private static OkHttpClient okHttpClient=null;
    private  static synchronized OkHttpClient getOkHttpClient(){
        if(okHttpClient==null){
            //创建拦截器
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {
                    Log.i("reg", message);
                }
            });
            //设置拦截爱情
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .build();
        }
        return okHttpClient;
    }
    //网络请求
    public  void doGet(String url,String userId, String sessionId, Callback callback) {

        OkHttpClient okHttpClient = getOkHttpClient();

        //请求方式
        Request request = new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(callback);
    }

    public  void doPost(String url, Map<String, String> map, Callback callback) {
        OkHttpClient okHttpClient = getOkHttpClient();
        FormBody.Builder formBody = new FormBody.Builder();
        for (String key : map.keySet() ) {
            formBody.add(key,map.get(key));
        }
        //请求方式
        Request request = new Request.Builder().url(url).post(formBody.build()).build();
      okHttpClient.newCall(request).enqueue(callback);
    }

}
