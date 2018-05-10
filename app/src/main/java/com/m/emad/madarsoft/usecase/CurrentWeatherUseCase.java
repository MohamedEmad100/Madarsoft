package com.m.emad.madarsoft.usecase;


import com.m.emad.madarsoft.constants.NetworkConstants;
import com.m.emad.madarsoft.data.model.Coord;
import com.m.emad.madarsoft.data.model.CurrentWearher;
import com.m.emad.madarsoft.data.repository.WeatherDataRepository;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

public class CurrentWeatherUseCase extends UseCase<CurrentWearher>{


    private final WeatherDataRepository weatherDataRepository;
    @Inject
    public CurrentWeatherUseCase(@Named("executor_thread") Scheduler executorThread,
                       @Named("ui_thread") Scheduler uiThread, WeatherDataRepository weatherDataRepository) {
        super(executorThread, uiThread);
        this.weatherDataRepository=weatherDataRepository;
    }

    @Override
    protected Observable<CurrentWearher> createObservableUseCase(Map<String, Object> map) {
        final double lat = ((Coord) map.get(NetworkConstants.Coord)).getLat();
        final double lon = ((Coord) map.get(NetworkConstants.Coord)).getLon();
        return weatherDataRepository.getCurrentWeather(lat , lon);
    }
}
