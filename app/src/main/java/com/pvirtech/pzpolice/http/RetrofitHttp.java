package com.pvirtech.pzpolice.http;


import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by pd on 2016/9/30.
 */

public class RetrofitHttp {
    public static final String SERVER_URL = "http://182.150.21.206:8080/gszf/api/";
    public static final String PICTRE_DOWN_URL = "http://182.150.21.206:8080/gszf/";
    public static long POSTDELAYED_TIME = 500;
    public final static int CONNECT_TIMEOUT = 10;
    public final static int READ_TIMEOUT = 10;
    public final static int WRITE_TIMEOUT = 10; //设置读取超时为100秒

    public static GithubApi provideClientApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(SERVER_URL)
//                .client(genericClient())
                .build();
        return retrofit.create(GithubApi.class);
    }
    public static OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)//设置读取超时时间
            .writeTimeout(WRITE_TIMEOUT,TimeUnit.SECONDS)//设置写的超时时间
            .connectTimeout(CONNECT_TIMEOUT,TimeUnit.SECONDS)//设置连接超时时间
            .build();

    public static OkHttpClient genericClient() {
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request()
                                .newBuilder()
//                                .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
//                                .addHeader("Accept-Encoding", "gzip, deflate")
//                                .addHeader("Connection", "keep-alive")
//                                .addHeader("Accept", "*/*")
//                                .addHeader("Cookie", "add cookies here")
                                .addHeader("Accept", "*/*")
                                .addHeader("Accept-Charset", "utf-8")
                                .addHeader("Accept-Encoding", "gzip")
                                .addHeader("Accept-Language", "zh-cn")
                                .addHeader("User-Agent", "Mozilla/5.0 (Linux; U; Android 2.3.3; zh-cn; GT-I9100 Build/GINGERBREAD) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1")
                                .build();
                        return chain.proceed(request);
                    }

                })
                .build();
        return httpClient;
    }
}
