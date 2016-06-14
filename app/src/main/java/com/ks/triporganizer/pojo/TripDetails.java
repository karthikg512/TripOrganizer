package com.ks.triporganizer.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by kgundimeda on 6/2/16.
 */
public class TripDetails implements Serializable {

    private List<User> users;
    private CarRentalDetails carRental;
    private Activities activities;
    private DeptFlightDetails deptFlightDetails;
    private ArrFlightDetails arrFlightDetails;
    private AccommodationDetails accommDetails;
    private Trip trip;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public CarRentalDetails getCarRental() {
        return carRental;
    }

    public void setCarRental(CarRentalDetails carRental) {
        this.carRental = carRental;
    }

    public Activities getActivities() {
        return activities;
    }

    public void setActivities(Activities activities) {
        this.activities = activities;
    }

    public DeptFlightDetails getDeptFlightDetails() {
        return deptFlightDetails;
    }

    public void setDeptFlightDetails(DeptFlightDetails deptFlightDetails) {
        this.deptFlightDetails = deptFlightDetails;
    }

    public ArrFlightDetails getArrFlightDetails() {
        return arrFlightDetails;
    }

    public void setArrFlightDetails(ArrFlightDetails arrFlightDetails) {
        this.arrFlightDetails = arrFlightDetails;
    }

    public AccommodationDetails getAccommDetails() {
        return accommDetails;
    }

    public void setAccommDetails(AccommodationDetails accommDetails) {
        this.accommDetails = accommDetails;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    @Override
    public String toString() {
        return "TripDetails{" +
                "users=" + users +
                ", carRental=" + carRental +
                ", activities=" + activities +
                ", deptFlightDetails=" + deptFlightDetails +
                ", arrFlightDetails=" + arrFlightDetails +
                ", accommDetails=" + accommDetails +
                ", trip=" + trip +
                '}';
    }
}
