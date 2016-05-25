package com.ks.triporganizer.pojo;

import java.util.Date;

/**
 * Created by kgundimeda on 5/24/16.
 */
public class Trip {

    private Long id;
    private String tripName;
    private String purpose;
    private String destination;

    public String getTripName() {
        return tripName;
    }

    public void setTripName(String tripName) {
        this.tripName = tripName;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
}
