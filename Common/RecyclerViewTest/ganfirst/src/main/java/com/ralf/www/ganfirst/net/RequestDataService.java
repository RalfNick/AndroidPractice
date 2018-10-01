package com.ralf.www.ganfirst.net;

import com.ralf.www.ganfirst.entity.GanHuo;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author Ralf
 * DESCRIPTION
 * @name RequestDataService
 * @date 2018/09/19 下午11:43
 **/
public interface RequestDataService {

    @GET("data/{type}/{number}/{index}")
    Observable<BaseEntity<GanHuo>> getGanHuoData(@Path("type") String type,
                                                 @Path("number") int number,
                                                 @Path("index") int index);
}
