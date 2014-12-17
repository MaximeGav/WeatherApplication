package com.rootsoft.weatherapplication.managers;

/**
 * Created by RootSoft on 17/12/14.
 */
public interface OnCityListener {
    public void onCityDownloading();
    public void onCityReceived(boolean success, String result);
}
