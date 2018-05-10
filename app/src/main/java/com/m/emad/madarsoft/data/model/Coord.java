package com.m.emad.madarsoft.data.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.m.emad.madarsoft.base.BaseModel;

@DatabaseTable(tableName = "COORD")
public class Coord extends BaseModel {

    @DatabaseField(columnName = "LON")
    double lon;
    @DatabaseField(columnName = "LAT")
    double lat;

    public Coord() {
    }

    public Coord(double lat, double lon) {
        this.lon = lon;
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }
}
