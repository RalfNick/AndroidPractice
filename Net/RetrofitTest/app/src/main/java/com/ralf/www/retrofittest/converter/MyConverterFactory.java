package com.ralf.www.retrofittest.converter;

import com.ralf.www.retrofittest.gson.City;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.text.TextUtils;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Author：Ralf on
 * Create on: 2018/3/27 14:39
 * description：
 * email: wang_lxin@163.com
 */

public class MyConverterFactory extends Converter.Factory {

    public static MyConverterFactory create() {

        return new MyConverterFactory();
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations,
                                                            Retrofit retrofit) {
        return new MyConverter();
    }

    class MyConverter implements Converter<ResponseBody, List<City>> {

        @Override
        public List<City> convert(ResponseBody value) throws IOException {

            List<City> resultList = new ArrayList<>();
            String cityInfo = value.string();

            if (cityInfo == null || TextUtils.isEmpty(cityInfo)) {
                return resultList;
            }

            try {
                JSONArray jsonArray = new JSONArray(cityInfo);
                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject object = jsonArray.getJSONObject(i);
                    String cityName = object.getString("name");
                    int cityId = object.getInt("id");
                    resultList.add(new City(cityId, cityName));
                }

            } catch (JSONException e) {

                e.printStackTrace();
            }

            return resultList;
        }
    }

}
