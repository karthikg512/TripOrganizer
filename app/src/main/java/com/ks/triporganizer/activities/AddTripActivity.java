package com.ks.triporganizer.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.ks.triporganizer.R;
import com.ks.triporganizer.pojo.Trip;
import com.ks.triporganizer.pojo.TripDetails;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class AddTripActivity extends AppCompatActivity {

    public final static String TRIP_DETAILS = "com.ks.triporganizer.activities.TRIP_DETAILS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_trip);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void tripInfo(View view) {
        EditText nameView = (EditText)findViewById(R.id.tripName);
        String name = nameView.getText().toString();

        EditText purposeView = (EditText)findViewById(R.id.purpose);
        String purpose = purposeView.getText().toString();

        EditText destinationView = (EditText)findViewById(R.id.destination);
        String destination = destinationView.getText().toString();

        EditText sdView = (EditText)findViewById(R.id.startDate);
        Calendar start = Calendar.getInstance();
        String[] sdDate = sdView.getText().toString().split("/");
        start.set(Integer.parseInt(sdDate[2]), Integer.parseInt(sdDate[0]), Integer.parseInt(sdDate[1]));
        Date startDate = start.getTime();

        EditText edView = (EditText)findViewById(R.id.endDate);
        Calendar end = Calendar.getInstance();
        String[] edDate = edView.getText().toString().split("/");
        start.set(Integer.parseInt(edDate[2]), Integer.parseInt(edDate[0]), Integer.parseInt(edDate[1]));
        Date endDate = start.getTime();

        Trip trip = new Trip();
        trip.setTripName(name);
        trip.setPurpose(purpose);
        trip.setDestination(destination);
        trip.setStartDate(startDate);
        trip.setEndDate(endDate);

        TripDetails tripDetails = new TripDetails();
        tripDetails.setTrip(trip);

        Intent intent = new Intent(this, AddUsersToTripActivity.class);
        intent.putExtra(TRIP_DETAILS, (Serializable) tripDetails);
        startActivity(intent);

    }

}
