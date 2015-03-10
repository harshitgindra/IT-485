package com.example.harshit.chicagotransit;

/**
 * Created by harshit on 3/8/2015.
 */
public class BusStopListAttributes {

    private String stopID;
    private String stopName;
    private double lat;
    private double lon;

    public double getLat() {
        return lat;
    }

    public String getStopID() {
        return stopID;
    }

    public void setStopID(String stopID) {
        this.stopID = stopID;
    }

    public String getStopName() {
        return stopName;
    }

    public void setStopName(String stopName) {
        this.stopName = stopName;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }
}
