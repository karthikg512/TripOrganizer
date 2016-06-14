package com.ks.triporganizer.pojo;

import java.util.Date;

/**
 * Created by kgundimeda on 6/13/16.
 */
public class ArrFlightDetails {

    private Long id;
    private Long userId;
    private Long tripId;
    private Date time;
    private String airport;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTripId() {
        return tripId;
    }

    public void setTripId(Long tripId) {
        this.tripId = tripId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getAirport() {
        return airport;
    }

    public void setAirport(String airport) {
        this.airport = airport;
    }

    @Override
    public String toString() {
        return "ArrFlightDetails{" +
                "id=" + id +
                ", userId=" + userId +
                ", tripId=" + tripId +
                ", time=" + time +
                ", airport='" + airport + '\'' +
                '}';
    }
}
