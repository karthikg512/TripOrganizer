package com.ks.triporganizer.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.ks.triporganizer.R;
import com.ks.triporganizer.pojo.Trip;
import com.ks.triporganizer.pojo.TripDetails;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class AddTripActivity extends AppCompatActivity {

    public final static String TRIP = "com.ks.triporganizer.activities.TRIP";
    Trip trip = new Trip();
    RestTemplate restTemplate = new RestTemplate();

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

        trip.setTripName(name);
        trip.setPurpose(purpose);
        trip.setDestination(destination);
        trip.setStartDate(startDate);
        trip.setEndDate(endDate);

        new HttpRequestTask().execute();

    }

    // TODO : Refactor to resuse
    private class HttpRequestTask extends AsyncTask<Void, Void, Trip> {
        @Override
        protected Trip doInBackground(Void... params) {
            try {
                final String url = "http://10.0.2.2:8080/trip-organizer/trip";
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                ResponseEntity<Trip> responseEntity = restTemplate.postForEntity(url, trip, Trip.class);
                return responseEntity.getBody();
            } catch (Exception e) {
                Log.e("DisplayTripsActivity", e.getMessage(), e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Trip trip) {
            Intent intent = new Intent(getApplicationContext(), TripDetailsActivity.class);
            intent.putExtra(TRIP, (Serializable) trip);
            startActivity(intent);
        }
    }

}
