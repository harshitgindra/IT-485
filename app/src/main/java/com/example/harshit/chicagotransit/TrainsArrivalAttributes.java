package com.example.harshit.chicagotransit;

/**
 * Created by harshit on 3/26/2015.
 */
public class TrainsArrivalAttributes {

    private String stopName;
    private String runnumber;
    private String predictionTime;
    private String destinationSt;
    private String destStnName;


    public String getStopName() {
        return stopName;
    }

    public void setStopName(String stopName) {
        this.stopName = stopName;
    }

    public String getRunnumber() {
        return runnumber;
    }

    public void setRunnumber(String runnumber) {
        this.runnumber = runnumber;
    }

    public String getPredictionTime() {
        return predictionTime;
    }

    public void setPredictionTime(String predictionTime) {
        this.predictionTime = predictionTime;
    }

    public String getDestinationSt() {
        return destinationSt;
    }

    public void setDestinationSt(String destinationSt) {
        this.destinationSt = destinationSt;
    }

    public String getDestStnName() {
        return destStnName;
    }

    public void setDestStnName(String destStnName) {
        this.destStnName = destStnName;
    }
}
