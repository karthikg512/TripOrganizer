package com.ks.triporganizer.activities;

import android.app.ExpandableListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.ks.triporganizer.R;
import com.ks.triporganizer.adapters.TripDetailsAdapter;

import java.util.ArrayList;
import java.util.List;

public class TripDetailsActivity extends ExpandableListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_details);

        Intent intent = getIntent();
        long tripId = intent.getLongExtra(DisplayTripsActivity.TRIP_ID, Long.MAX_VALUE);

        List<String> tasks = new ArrayList<String>();
        tasks.add("Users");
        tasks.add("Car");
        tasks.add("Activities");
        tasks.add("Departures");
        tasks.add("Arrivals");
        tasks.add("Stay");

        TripDetailsAdapter adapter = new TripDetailsAdapter(this, getLayoutInflater(), tripId, tasks);
        setListAdapter(adapter);

    }
}
