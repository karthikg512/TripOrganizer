package com.ks.triporganizer.pojo;

import java.util.Date;

/**
 * Created by kgundimeda on 6/13/16.
 */
public class AccommodationDetails {

    private Long id;
    private Long userId;
    private Long tripId;
    private String name;
    private String address;
    private Date checkin;
    private Date checkout;
    private double cost;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCheckin() {
        return checkin;
    }

    public void setCheckin(Date checkin) {
        this.checkin = checkin;
    }

    public Date getCheckout() {
        return checkout;
    }

    public void setCheckout(Date checkout) {
        this.checkout = checkout;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "AccommodationDetails{" +
                "id=" + id +
                ", userId=" + userId +
                ", tripId=" + tripId +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", checkin=" + checkin +
                ", checkout=" + checkout +
                ", cost=" + cost +
                '}';
    }
}
