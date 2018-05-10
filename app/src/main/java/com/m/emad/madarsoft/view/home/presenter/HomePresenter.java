package com.m.emad.madarsoft.view.home.presenter;

import com.m.emad.madarsoft.base.BasePresenter;
import com.m.emad.madarsoft.constants.NetworkConstants;
import com.m.emad.madarsoft.data.model.Coord;
import com.m.emad.madarsoft.data.model.CurrentWearher;
import com.m.emad.madarsoft.data.repository.CoordRepository;
import com.m.emad.madarsoft.error.ErrorModel;
import com.m.emad.madarsoft.usecase.CurrentWeatherUseCase;
import com.m.emad.madarsoft.usecase.UseCaseObserver;
import com.m.emad.madarsoft.view.home.HomeContarct;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

public class HomePresenter extends BasePresenter<HomeContarct.View> implements HomeContarct.Presenter {

    private CurrentWeatherUseCase currentWeatherUseCase;
    CoordRepository coordRepository;
    private List<CurrentWearher> mList = new ArrayList<CurrentWearher>();

    @Inject
    public HomePresenter(CurrentWeatherUseCase currentWeatherUseCase,CoordRepository coordRepository) {
        this.currentWeatherUseCase = currentWeatherUseCase;
        this.coordRepository = coordRepository;
    }

    @Override
    public void loadList(final List<Coord> coords) {
        getView().showLoading();
        for (Coord coord : coords) {
            if (coord != null) {
                final Map<String, Object> map = new HashMap<>();
                map.put(NetworkConstants.Coord, coord);
                currentWeatherUseCase.execute(new UseCaseObserver<CurrentWearher>() {
                    @Override
                    public void onSuccess(CurrentWearher currentWearher) {
                        mList.add(currentWearher);
                        if(mList.size() == coords.size()){
                            getView().updateList(mList);
                            getView().showContent();
                        }

                    }

                    @Override
                    public void OnFailed(ErrorModel errorModel) {
                        getView().showError();
                        getView().showContent();
                    }
                }, map);
            }
        }
    }


    @Override
    public void getSaveCoord() {
        mList.clear();
        List<Coord> coords = new ArrayList<Coord>();
        try {
            coords = coordRepository.getAllCoord();
            if(coords!=null){loadList(coords);}else{getView().noLoactionsExist();}
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void cancelAllRequests() {
        currentWeatherUseCase.clear();
    }
}
