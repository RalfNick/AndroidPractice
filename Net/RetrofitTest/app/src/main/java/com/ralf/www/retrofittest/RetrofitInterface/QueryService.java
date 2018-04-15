package com.ralf.www.retrofittest.RetrofitInterface;

import com.ralf.www.retrofittest.gson.Message;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Author：Ralf on
 * Create on: 2018/3/14 20:28
 * description：
 * email: wang_lxin@163.com
 */

public interface QueryService {

    @GET("{message}")
    Call<Message> getMessage(@Path("message") String message, @Query("a") String param1,
                             @Query("f") String param2, @Query("t") String param3,
                             @Query("w") String param4);

    @GET("{message}")
    Call<Message> getMessage(@Path("message") String message, @QueryMap Map<String, String> params);
}
