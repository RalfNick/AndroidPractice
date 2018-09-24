package com.ralf.www.recyclerviewtest.net;

import com.ralf.www.recyclerviewtest.utils.LogUtils;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by karasjoker on 2018/2/7.
 */

public class RetrofitClient {

    private static RetrofitClient sRetrofitClient = null;
    private RetrofitClient() {
    }

    public static RetrofitClient getRetrofitClientInstance(){

        if (sRetrofitClient == null){
            synchronized (RetrofitClient.class){
                if (sRetrofitClient == null){
                    sRetrofitClient = new RetrofitClient();
                }
            }
        }
        return sRetrofitClient;
    }

    /**
     * 获取无Headers的NetService
     *
     * @param baseUrl
     * @param cs
     * @param <T>
     * @return
     */
    public <T> T requestNetForData(String baseUrl, Class<T> cs) {
        T t = getRetrofitInstance(baseUrl)
                .create(cs);
        return t;
    }

    /**
     * 获取有Header的NetService
     *
     * @param baseUrl
     * @param headers
     * @param cs
     * @param <T>
     * @return
     */
    public <T> T requestNetForData(String baseUrl, HashMap<String, String> headers, Class<T> cs) {
        T t = getRetrofitInstance(baseUrl, headers)
                .create(cs);
        return t;
    }

    /**
     * 得到Retrofit对象(无Header)
     *
     * @param baseUrl
     * @return
     */
    private Retrofit getRetrofitInstance(String baseUrl) {
        Retrofit.Builder retrofitBuilder = getRetrofitBuilder(baseUrl);
        retrofitBuilder.client(getOkHttpClientInstance(null));
        return retrofitBuilder.build();
    }

    /**
     * 得到Retrofit对象(有Header)
     *
     * @param baseUrl
     * @return
     */
    private Retrofit getRetrofitInstance(String baseUrl, HashMap<String, String> headersMap) {
        Retrofit.Builder retrofitBuilder = getRetrofitBuilder(baseUrl);
        retrofitBuilder.client(getOkHttpClientInstance(headersMap));
        return retrofitBuilder.build();
    }

    /**
     * 初始化Retrofit.Builder
     *
     * @param baseUrl
     * @return
     */
    private Retrofit.Builder getRetrofitBuilder(String baseUrl) {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create());

        return builder;
    }

    /**
     * 初始化OKHttpClient
     *
     * @param headersMap
     * @return
     */
    private OkHttpClient getOkHttpClientInstance(final HashMap<String, String> headersMap) {

        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.addInterceptor(initHttpLoggingInterceptor());
        builder.addInterceptor(initHeaderInterceptor(headersMap));

        OkHttpClient okHttpClient = builder.build();
        return okHttpClient;
    }

    /**
     * 初始化OKHttp的Log拦截器
     *
     * @return
     */
    private HttpLoggingInterceptor initHttpLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(
                new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log(String message) {
                        LogUtils.json("HttpInfo", message);
                    }
                }
        );
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }

    /**
     * 初始化Header拦截器，添加头部信息
     *
     * @param headersMap
     * @return
     */
    private Interceptor initHeaderInterceptor(final HashMap<String, String> headersMap) {
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request.Builder requestBuilder = chain.request().newBuilder();
                if (headersMap != null && !headersMap.isEmpty()) {
                    Headers headers = Headers.of(headersMap);
                    requestBuilder.headers(headers);
                }
                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        };
        return interceptor;
    }
}
