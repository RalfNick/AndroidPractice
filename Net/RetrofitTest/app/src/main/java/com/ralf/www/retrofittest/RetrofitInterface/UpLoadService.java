package com.ralf.www.retrofittest.RetrofitInterface;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

/**
 * Author：Ralf on
 * Create on: 2018/3/22 11:24
 * description：
 * email: wang_lxin@163.com
 */

public interface UpLoadService {

    //1 upload a image
    @Multipart
    @POST("user")
    Call<ResponseBody> upload(@Part("file\";filename=\"image.jpg") RequestBody file);

    //2 upload some images,the number is certain
    @Multipart
    @POST("user")
    Call<ResponseBody> upload(@Part("file\";filename=\"image1.jpg") RequestBody file1,
                             @Part("file\";filename=\"image2.jpg") RequestBody file2,
                             @Part("file\";filename=\"image3.jpg") RequestBody file3);

    //3 upload one image and text
    @Multipart
    @POST("user")
    Call<ResponseBody> upload(@Part("description") String description,
                              @Part MultipartBody.Part file);

    //4 upload one image and text
    @Multipart
    @POST("user")
    Call<ResponseBody> upload(@Part("description") RequestBody description,
                              @Part MultipartBody.Part file);

    //5 upload images and text
    @Multipart
    @POST("user")
    Call<ResponseBody> upload(@Part("description") RequestBody description,
                              @PartMap() Map<String,RequestBody> maps);

}
