package com.example.bshelper;

import java.io.Serializable;

public class BSInfo implements Serializable {

    private double longitude;
    private double latitude;
    private String BSName;
    private String BSAddres;
    public BSInfo(){

    }
    public BSInfo(double longitude, double latitude, String BSName, String BSAddres) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.BSName = BSName;
        this.BSAddres = BSAddres;
    }

    public void setLongitude(double longitude){
        this.longitude = longitude;
    }
    public double getLongitude(){
        return longitude;
    }

    public void setLatitude(double latitude){
        this.latitude = latitude;
    }
    public double getLatitude(){
        return latitude;
    }

    public void setBSName(String BSName){
        this.BSName = BSName;
    }
    public String getBSName(){
        return BSName;
    }
    public void setBSAdrres(String BSAddres){
        this.BSAddres = BSAddres;
    }
    public String getBSAdrres(){
        return BSAddres;
    }


}
