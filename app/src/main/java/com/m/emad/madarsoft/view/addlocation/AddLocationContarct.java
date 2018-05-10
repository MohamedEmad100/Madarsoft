package com.m.emad.madarsoft.view.addlocation;


import com.google.android.gms.maps.model.LatLng;
import com.m.emad.madarsoft.base.BaseContract;
import com.m.emad.madarsoft.data.model.Coord;

import java.util.List;

public interface AddLocationContarct {

    interface View extends BaseContract.View{
        void addMyLocationMarker(LatLng latLng);
        void addSavedLocations(List<Coord> coords);
        void addLocation(Coord coord);
        void getLocationPermission();
        void updateCounter(List<Coord> coords);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void saveLocation(Coord coord);
        Coord getLocation(String id);
        void getAllLocation(int mCase);
        void deleteLocation(Coord coord);

    }
}
