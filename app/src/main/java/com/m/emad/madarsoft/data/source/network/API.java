package com.m.emad.madarsoft.data.source.network;

import com.m.emad.madarsoft.data.model.CurrentWearher;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API {


    @GET("weather")
    Observable<CurrentWearher> getCurrentWeather(@Query("lat") double lat ,@Query("lon") double lon ,@Query("cnt") double cnt,
                                                 @Query("appid") String id , @Query("units") String unit);

}
