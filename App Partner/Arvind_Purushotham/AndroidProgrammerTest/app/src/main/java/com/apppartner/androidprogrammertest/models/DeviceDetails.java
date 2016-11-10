package com.apppartner.androidprogrammertest.models;

/**
 * Created by ARVIND on 10/1/2016.
 * This class is a base class that helps in holding device information.
 */

public class DeviceDetails {
    private int screenWidth, screenHeight, apiLevel;
    private String deiceModel;

    public void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }

    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
    }

    public void setApiLevel(int apiLevel) {
        this.apiLevel = apiLevel;
    }

    public void setDeiceModel(String deiceModel) {
        this.deiceModel = deiceModel;
    }

    /*
    public String getDeiceModel() {
        return deiceModel;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public int getApiLevel() {
        return apiLevel;
    }

    public int getScreenWidth() {
        return screenWidth;
    }
    */


    @Override
    public String toString() {
        return "API '" + this.apiLevel + "', MODEL '" + this.deiceModel + "', SCREEN HEIGHT '" +
                this.screenHeight + "', SCREEN WIDTH '" + this.screenWidth + "'";
    }

    // equals(), hashCode(), not required because we are not comparing the instance of this class.

}
