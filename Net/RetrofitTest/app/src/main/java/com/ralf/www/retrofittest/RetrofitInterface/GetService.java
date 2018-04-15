package com.ralf.www.retrofittest.RetrofitInterface;

import com.ralf.www.retrofittest.gson.City;
import com.ralf.www.retrofittest.gson.Country;
import com.ralf.www.retrofittest.gson.Province;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Author：Ralf on
 * Create on: 2018/3/9 20:58
 * description：
 * email: wang_lxin@163.com
 */

public interface GetService {

    @GET("api/china")
    Call<List<Province>> getProvinces();

    @GET("api/{Country}")
    Call<List<Province>> getProvinces(@Path("Country") String country);

    @GET("api/{Country}/{province}")
    Call<List<City>> getCities(@Path("Country") String country,@Path("province") int provinceId);

    @GET("api/{Country}/{province}/{city}")
    Call<List<Country>> getCountries(@Path("Country") String country, @Path("province") int provinceId,
            @Path("city") int cityId);
}
