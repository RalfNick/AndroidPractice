package com.ralf.www.retrofittest.RetrofitInterface;

import com.ralf.www.retrofittest.gson.Translation;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Author：Ralf on
 * Create on: 2018/3/14 20:34
 * description：
 * email: wang_lxin@163.com
 */

public interface PostService {

    @POST("translate?doctype=json&jsonversion=&type=&keyfrom=&model=&mid=&imei=&vendor=&screen=&ssid=&network=&abtest=")
    @FormUrlEncoded
    Call<Translation> getTranslate(@Field("i") String target);

}
