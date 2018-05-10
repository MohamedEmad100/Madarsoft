package com.m.emad.madarsoft.data.model;


import com.m.emad.madarsoft.base.BaseModel;

import java.util.List;

public class CurrentWearher extends BaseModel {

    private Coord coord;
    private List<WeatherDes> weather;
    private String base;
    private Main main;
    private double visibility;
    private Wind wind;
    private Clouds clouds;
    private int dt;
    private Sys sys;
    private String name;
    private double cod;


    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public List<WeatherDes> getWeather() {
        return weather;
    }

    public void setWeather(List<WeatherDes> weather) {
        this.weather = weather;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public double getVisibility() {
        return visibility;
    }

    public void setVisibility(double visibility) {
        this.visibility = visibility;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public int getDt() {
        return dt;
    }

    public void setDt(int dt) {
        this.dt = dt;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCod() {
        return cod;
    }

    public void setCod(double cod) {
        this.cod = cod;
    }
}
