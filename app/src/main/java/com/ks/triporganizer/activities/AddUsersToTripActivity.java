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
import com.ks.triporganizer.pojo.TripDetails;

public class AddUsersToTripActivity extends AppCompatActivity {

    private TripDetails tripDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_users_to_trip);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        tripDetails = (TripDetails)intent.getSerializableExtra(AddTripActivity.TRIP_DETAILS);

        EditText auView = (EditText)findViewById(R.id.addUsers);
        auView.setText(tripDetails.toString());
    }

}
