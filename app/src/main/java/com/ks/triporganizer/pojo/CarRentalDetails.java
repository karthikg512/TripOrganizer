package com.ks.triporganizer.pojo;

import java.util.Date;

/**
 * Created by kgundimeda on 6/13/16.
 */
public class CarRentalDetails {

    private Long id;
    private Long tripId;
    private Long userId;
    private Date pickupDate;
    private String pickupLocation;
    private String dropOffLocation;
    private Date dropoffDate;
    private String company;
    private double cost;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTripId() {
        return tripId;
    }

    public void setTripId(Long tripId) {
        this.tripId = tripId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(Date pickupDate) {
        this.pickupDate = pickupDate;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public String getDropOffLocation() {
        return dropOffLocation;
    }

    public void setDropOffLocation(String dropOffLocation) {
        this.dropOffLocation = dropOffLocation;
    }

    public Date getDropoffDate() {
        return dropoffDate;
    }

    public void setDropoffDate(Date dropoffDate) {
        this.dropoffDate = dropoffDate;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "CarRentalDetails{" +
                "id=" + id +
                ", tripId=" + tripId +
                ", userId=" + userId +
                ", pickupDate=" + pickupDate +
                ", pickupLocation='" + pickupLocation + '\'' +
                ", dropOffLocation='" + dropOffLocation + '\'' +
                ", dropoffDate=" + dropoffDate +
                ", company='" + company + '\'' +
                ", cost=" + cost +
                '}';
    }
}
