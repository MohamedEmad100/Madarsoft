package com.m.emad.madarsoft.view.addlocation.view;


import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.m.emad.madarsoft.R;
import com.m.emad.madarsoft.base.BaseFragment;
import com.m.emad.madarsoft.data.model.Coord;
import com.m.emad.madarsoft.view.addlocation.AddLocationContarct;
import com.m.emad.madarsoft.view.custom.CustomDialog;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

import static android.content.Context.LOCATION_SERVICE;

public class LocationFragment extends BaseFragment implements AddLocationContarct.View ,GoogleMap.OnMapClickListener,GoogleMap.OnMarkerClickListener, android.location.LocationListener {

    SupportMapFragment mapFragment;

    private CameraPosition cameraPosition;

    @BindView(R.id.tv_num_places)
    TextView tv_numPlaces;
    @BindView(R.id.tv_back_home)
    TextView tv_backHome;


    boolean mLocationPermissionGranted = false;
    LocationManager mLocationManager;
    private final static int MIN_TIME = 2000;
    private final static float MIN_DISTANCE = 100;
    private CustomDialog customDialog;



    @Inject
    AddLocationContarct.Presenter locationPresenter;


    public static LocationFragment newInstance(){
        Bundle args = new Bundle();
        LocationFragment locationFragment = new LocationFragment();
        locationFragment.setArguments(args);
        return locationFragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setBackView(tv_backHome);
        setPresenter(locationPresenter);

        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.fragmentMap);

        mLocationManager = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);

        getLocationPermission();
        getCurrentLocation();
        locationPresenter.getAllLocation(2);

    }


    @SuppressLint("MissingPermission")
    public void getCurrentLocation() {
        if(mLocationPermissionGranted){

            String provider = LocationManager.NETWORK_PROVIDER;
            mLocationManager.requestLocationUpdates(provider, MIN_TIME, MIN_DISTANCE, this);
        }
    }


    @Override
    public void onMapClick(final LatLng latLng) {
        customDialog = CustomDialog.getInstance("Add Location", "Location: " + latLng.latitude + " , " + latLng.longitude);
        customDialog.setPrimaryButton("Save", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addLocation(new Coord(latLng.latitude ,latLng.longitude));
            }
        });
        customDialog.setSecondaryButton("Cancel", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customDialog.dismiss();
            }
        });
        customDialog.show(getFragmentManager() , "Add Location");

    }

    @Override
    public boolean onMarkerClick(final Marker marker) {
        boolean value = false;
        Log.i(getClass().getName(), marker.getId());
        final Coord coord = locationPresenter.getLocation(marker.getId());
        if (coord != null) {
            value = true;
             customDialog = CustomDialog.getInstance("Location Info","Location: " + coord.getLat() + "," + coord.getLon());
             customDialog.setPrimaryButton("Delete", new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                    locationPresenter.deleteLocation(coord);
                    marker.remove();
                    customDialog.dismiss();
                 }
             });
            customDialog.setSecondaryButton("Cancel", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    customDialog.dismiss();
                }
            });
            customDialog.show(getFragmentManager() , "Location Info");
        }
        return value;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_location;
    }


    @Override
    public void addMyLocationMarker(LatLng latLng) {
//        MarkerOptions myMarker = new MarkerOptions();
//        myMarker.title("My Location");
//        myMarker.snippet("You are currently here.");
//        myMarker.position(latLng);
//        myMarker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
//        mapFragment.getMap().addMarker(myMarker);
        locationPresenter.getAllLocation(1);
    }

    @Override
    public void addSavedLocations(List<Coord> coords) {
        if(coords!= null) {
            for (Coord coord : coords) {
                if(coord !=null){
                    MarkerOptions placeMarker = new MarkerOptions();
                    placeMarker.title(""+coord.getLat()+" , "+coord.getLon());
                    LatLng latLng = new LatLng(coord.getLat(), coord.getLon());
                    placeMarker.position(latLng);
                    mapFragment.getMap().addMarker(placeMarker);
                }
            }
        }
    }


    @Override
    public void addLocation(Coord coord) {
        if (coord != null) {
            MarkerOptions placeMarker = new MarkerOptions();
            placeMarker.title(""+coord.getLat()+" , "+coord.getLon());
            LatLng latLng = new LatLng(coord.getLat(), coord.getLon());
            placeMarker.position(latLng);
            Marker marker = mapFragment.getMap().addMarker(placeMarker);
            coord.setID(marker.getId());
            locationPresenter.saveLocation(coord);
            customDialog.dismiss();
        }
    }


    @Override
    public void updateCounter(List<Coord>coords) {
        if(coords == null){
            tv_numPlaces.setText("Zero Places Selected");
        }else{
            tv_numPlaces.setText("["+coords.size()+"]"+" Places Selected");
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showContent() {

    }

    @Override
    public void getLocationPermission() {

        if (ContextCompat.checkSelfPermission(getContext().getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mLocationPermissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 99);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case 99: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mLocationPermissionGranted = true;
                }
            }
        }
    }


    @Override
    public void onLocationChanged(Location location) {
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        cameraPosition = new CameraPosition(latLng, 5, 0, 0);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
        mapFragment.getMap().moveCamera(cameraUpdate);
        mapFragment.getMap().setOnMapClickListener(this);
        addMyLocationMarker(latLng);
        mapFragment.getMap().setOnMarkerClickListener(this);

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
