package com.m.emad.madarsoft.view.addlocation.presenter;


import com.m.emad.madarsoft.base.BasePresenter;
import com.m.emad.madarsoft.data.model.Coord;
import com.m.emad.madarsoft.data.repository.CoordRepository;
import com.m.emad.madarsoft.view.addlocation.AddLocationContarct;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

public class LocationPresenter extends BasePresenter<AddLocationContarct.View> implements AddLocationContarct.Presenter{

    private CoordRepository coordRepository;



    @Inject
    public LocationPresenter(CoordRepository coordRepository) {
        this.coordRepository = coordRepository;
    }

    @Override
    public void saveLocation(Coord coord) {
        try {
            coordRepository.saveCoord(coord);
            getAllLocation(2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Coord getLocation(String id) {
        try {
            return coordRepository.getCoord(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteLocation(Coord coord) {
        try {
            coordRepository.deleteCoord(coord);
            getAllLocation(2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getAllLocation(int mCase) {
        try {
            List<Coord> coords = coordRepository.getAllCoord();
            if(mCase == 1) {
                getView().addSavedLocations(coords);
            }else if (mCase == 2){
                getView().updateCounter(coords);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void cancelAllRequests() {

    }

}
