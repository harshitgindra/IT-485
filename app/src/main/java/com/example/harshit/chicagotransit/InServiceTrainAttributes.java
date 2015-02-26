package com.example.harshit.chicagotransit;

/**
 * Created by harshit on 2/21/2015.
 */
public class InServiceTrainAttributes {
    private String routeName;
    private String lastStop;
    private String nextStop;
    private String time;
    String temp = "";

    public String geResults() {
        return getRouteName() + " " + getLastStop() + " " + getNextStop() + " " + getTime();
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public String getLastStop() {
        return lastStop;
    }

    public void setLastStop(String lastStop) {
        this.lastStop = lastStop;
    }

    public String getNextStop() {
        return nextStop;
    }

    public void setNextStop(String nextStop) {
        this.nextStop = nextStop;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


}


