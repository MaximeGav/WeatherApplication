package com.rootsoft.weatherapplication.models;

/**
 * Created by RootSoft on 17/12/14.
 */
public class City {

    //Attributes
    private String cityName;
    private double maxTemp, minTemp;

    //Constructor

    public City(String cityName, double maxTemp, double minTemp) {
        this.cityName = cityName;
        this.maxTemp = maxTemp;
        this.minTemp = minTemp;
    }

    //Properties

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public double getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(int maxTemp) {
        this.maxTemp = maxTemp;
    }

    public double getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(int minTemp) {
        this.minTemp = minTemp;
    }
}
