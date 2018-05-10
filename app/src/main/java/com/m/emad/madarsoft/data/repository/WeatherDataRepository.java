package com.m.emad.madarsoft.data.repository;

import com.m.emad.madarsoft.base.Repository;
import com.m.emad.madarsoft.constants.NetworkConstants;
import com.m.emad.madarsoft.data.model.CurrentWearher;
import com.m.emad.madarsoft.data.source.network.API;
import com.m.emad.madarsoft.manager.RequestManager;

import javax.inject.Inject;

import io.reactivex.Observable;


public class WeatherDataRepository extends Repository {


    @Inject
    public WeatherDataRepository(RequestManager requestManager) {
        this.mRequestManager = requestManager;
    }

    public Observable<CurrentWearher> getCurrentWeather(double lat , double lon ) {
        return mRequestManager.startRequest(API.class).getCurrentWeather(lat,lon,1, NetworkConstants.APP_ID,"metric");
    }

}
