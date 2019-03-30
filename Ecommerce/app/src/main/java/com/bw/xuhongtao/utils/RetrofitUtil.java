package com.bw.xuhongtao.utils;

import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author xuhongtao
 * @fileName RetrofitUtil
 * @package com.bw.xuhongtao.utils
 * @date 2019/3/17/017 12:43
 */
public class RetrofitUtil {
    private static RetrofitUtil retrofitUtil;

    private RetrofitUtil() {
    }

    public static RetrofitUtil getRetrofitUtil() {
        if (retrofitUtil == null) {
            synchronized (RetrofitUtil.class) {
                if (retrofitUtil == null) {
                    retrofitUtil = new RetrofitUtil();
                }
            }
        }
        return retrofitUtil;
    }

    //封装okhttp
    private static OkHttpClient okHttpClient;

    public static synchronized OkHttpClient getokHttp(final String userId, final String sessionId) {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.i("lan", message);
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request().newBuilder()
                                .addHeader("userId", userId)
                                .addHeader("sessionId", sessionId)
                                .build();
//                        Log.i("loginssss",userId+"加入"+sessionId);
                        return chain.proceed(request);
                    }
                })
                .build();
        return okHttpClient;

    }

    //Retrofit封装带拦截器
    public static Retrofit getretrofit(String url, String userId, String sessionId) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(url)
                .client(getokHttp(userId, sessionId))
                .build();
        return retrofit;
    }

    //Retrofit封装不带拦截器
    public static Retrofit getRetrofit(String url) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit;
    }

    //获取动态代理对象    不带拦截器
    public <T> T getApiServer(String url, Class<T> service) {
        Retrofit retrofit = getRetrofit(url);
        T t = retrofit.create(service);
        return t;
    }
    //获取动态代理对象    带拦截器
    public  <T> T getApiServer(String url, String userId, String sessionId, Class<T> serice) {
        Retrofit retrofit = getretrofit(url, userId, sessionId);
        T t = retrofit.create(serice);
        return t;
    }


}
