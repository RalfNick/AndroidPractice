package com.ralf.www.retrofittest;

import com.ralf.www.retrofittest.RetrofitInterface.GetService;
import com.ralf.www.retrofittest.RetrofitInterface.PostService;
import com.ralf.www.retrofittest.RetrofitInterface.QueryService;
import com.ralf.www.retrofittest.RetrofitInterface.UpLoadService;
import com.ralf.www.retrofittest.gson.City;
import com.ralf.www.retrofittest.gson.Country;
import com.ralf.www.retrofittest.gson.Message;
import com.ralf.www.retrofittest.gson.Province;
import com.ralf.www.retrofittest.gson.Translation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textView;
    private Button button1;

    private Button button2;
    private TextView textView2;

    private Button button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = (Button) findViewById(R.id.button);
        textView = (TextView) findViewById(R.id.textView);

        button2 = (Button) findViewById(R.id.button2);
        textView2 = (TextView) findViewById(R.id.textView2);

        button3 = (Button) findViewById(R.id.button3);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
    }

    /**
     * 使用Retrofit框架请求测试方法
     */
    private void getProvinces() {

        //step1
        GetService getService = (GetService) getService("http://guolin.tech/", GetService.class);

        //step3
        //Call<List<Province>> call = getService.getProvinces(); // 不使用Block方法
        Call<List<Province>> call = getService.getProvinces("china"); // 使用Block方法

        //step4
        call.enqueue(new Callback<List<Province>>() {
            @Override
            public void onResponse(Call<List<Province>> call, final Response<List<Province>> response) {

                List<Province> list = response.body();
                for (Province province : list) {
                    province.show();
                }

            }

            @Override
            public void onFailure(Call<List<Province>> call, Throwable t) {
                Log.e("ralf", "the retrofit is failed! ");
            }
        });

    }

    /**
     * 通过retrofit 获取所有省份
     */
    private void getCities() {

        GetService getService = (GetService) getService("http://guolin.tech/", GetService.class);
        Call<List<City>> call = getService.getCities("china", 12);
        call.enqueue(new Callback<List<City>>() {
            @Override
            public void onResponse(Call<List<City>> call, Response<List<City>> response) {
                List<City> list = response.body();
                for (City city : list) {
                    city.show();
                }
            }

            @Override
            public void onFailure(Call<List<City>> call, Throwable t) {
                Log.e("ralf", "the request is failed!");
            }
        });
    }

    /**
     * 提取出来，用来创建Retrofit请求接口
     */
    private <T> T getService(String urlStr, Class<? extends T> claz) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(urlStr)
                .addConverterFactory(GsonConverterFactory.create())
                //.addConverterFactory(MyConverterFactory.create())//自定义转换器
                .build();

        return retrofit.create(claz);
    }


    /**
     * 获取所有的县或市区
     */
    private void getCountries() {

        GetService getService = (GetService) getService("http://guolin.tech/", GetService.class);

        Call<List<Country>> call = getService.getCountries("china", 10, 32);

        call.enqueue(new Callback<List<Country>>() {

            @Override
            public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {
                List<Country> list = response.body();
                for (Country country : list) {
                    country.show();
                }
            }

            @Override
            public void onFailure(Call<List<Country>> call, Throwable t) {

            }
        });
    }

    /**
     * 通过map获取信息
     */
    private void getMessage() {

        QueryService getService = (QueryService) getService("http://fy.iciba.com/", GetService.class);

        Map<String, String> paramsMap = new HashMap<>();

        /**
         * url
         * http://fy.iciba.com/ajax.php?a=fy&f=auto&t=auto&w=hello%20world
         */

        //方式一：
        //Call<Message> call = getService.getMessage("ajax.php","fy","auto","auto","hello%20world");

        //方式二:
        paramsMap.put("a", "fy");
        paramsMap.put("f", "auto");
        paramsMap.put("t", "auto");
        paramsMap.put("w", "hello%20world");

        Call<Message> call = getService.getMessage("ajax.php", paramsMap);

        call.enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {

                Message message = response.body();
                message.show();
            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {

            }
        });

    }

    private void getTranslateResult() {

        PostService postService = getService("http://fanyi.youdao.com/", PostService.class);
        Call<Translation> call = postService.getTranslate("How are you");

        call.enqueue(new Callback<Translation>() {
            @Override
            public void onResponse(Call<Translation> call, Response<Translation> response) {
                String result = response.body().getTranslateResult().get(0).get(0).getTgt();
                System.out.println("翻译结果：" + result);
            }

            @Override
            public void onFailure(Call<Translation> call, Throwable t) {

            }
        });

    }

    private void showInfo(final String info) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (!TextUtils.isEmpty(info))
                    textView.setText(info);
            }
        });
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();
        switch (id) {

            case R.id.button:

                //getProvinces();
                getCities();
                //getCountries();

                break;

            case R.id.button2:

                getMessage();
                break;

            case R.id.button3:
                getTranslateResult();
                break;

            default:
                break;
        }
    }


    /**
     * init
     */


    //test upload method, there is no server,just method
    //method 1
    /*private void upload() {

        //upload a image
        UpLoadService upLoadService = getService("http://webset", UpLoadService.class);
        File file1 = new File("d:/1/test1.jpg");

        RequestBody requestFile1 = RequestBody.create(MediaType.parse("multipart/form-data"), file1);
        Call<ResponseBody> uploadImageCall = upLoadService.upload(requestFile1);

        //exct the call request
    }*/

    /**
     * method 2
     */
    /*private void upload() {

        UpLoadService upLoadService = getService("http://webset", UpLoadService.class);
        File file1 = new File("d:/1/test1.jpg");
        File file2 = new File("d:/1/test2.jpg");
        File file3 = new File("d:/1/test3.jpg");

        //upload serveral images, the number is certain
        RequestBody requestFile1 = RequestBody.create(MediaType.parse("multipart/form-data"), file1);
        RequestBody requestFile2 = RequestBody.create(MediaType.parse("multipart/form-data"), file2);
        RequestBody requestFile3 = RequestBody.create(MediaType.parse("multipart/form-data"), file3);

        Call<ResponseBody> uploadImagesCall = upLoadService.upload(requestFile1, requestFile2, requestFile3);

        //exct the call request
    }*/

    /**
     * method 3
     */

    /*private void upload() {

        UpLoadService upLoadService = getService("http://webset", UpLoadService.class);

        String describString = "this are a image and text";

        File file1 = new File("d:/1/test1.jpg");
        RequestBody requestFile1 = RequestBody.create(MediaType.parse("multipart/form-data"), file1);
        MultipartBody.Part filePart1 = MultipartBody.Part.createFormData("image1", file1.getName(), requestFile1);

        Call<ResponseBody> uploadImageAndText = upLoadService.upload(describString,filePart1);

        //exct the call request
    }*/

    /**
     * method 4
     */
/*    private void upload() {

        UpLoadService upLoadService = getService("http://webset", UpLoadService.class);

        File file1 = new File("d:/1/test1.jpg");
        RequestBody requestFile1 = RequestBody.create(MediaType.parse("multipart/form-data"), file1);
        MultipartBody.Part filePart1 = MultipartBody.Part.createFormData("image1", file1.getName(), requestFile1);

        String describString = "this are a image and text";
        RequestBody description = RequestBody.create(MediaType.parse("multipart/form-data"), describString);

        //one part is a image and text
        Call<ResponseBody> uploadImagesAndText = upLoadService.upload(description, filePart1);

        //exct the call request
    }*/

    /**
     * method 5
     */
    private void upload() {

        UpLoadService upLoadService = getService("http://webset", UpLoadService.class);

        File file1 = new File("d:/1/test1.jpg");
        File file2 = new File("d:/1/test2.jpg");
        File file3 = new File("d:/1/test3.jpg");

        //upload serveral images, the number is certain
        RequestBody requestFile1 = RequestBody.create(MediaType.parse("multipart/form-data"), file1);
        RequestBody requestFile2 = RequestBody.create(MediaType.parse("multipart/form-data"), file2);
        RequestBody requestFile3 = RequestBody.create(MediaType.parse("multipart/form-data"), file3);

        Map<String, RequestBody> maps = new HashMap<>();

        //the key is the field in the database
        maps.put("image1", requestFile1);
        maps.put("image2", requestFile2);
        maps.put("image3", requestFile3);

        String describString = "this are images and text";
        RequestBody description = RequestBody.create(MediaType.parse("multipart/form-data"), describString);

        Call<ResponseBody> uploadImagesAndTextCall = upLoadService.upload(description, maps);

        //execute the call
        uploadImagesAndTextCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.e("tag", "upload the images and text success");
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("tag", "upload the image and text failed");
            }
        });
    }


}
