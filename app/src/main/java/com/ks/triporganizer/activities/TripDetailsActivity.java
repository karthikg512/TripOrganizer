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

import com.ks.triporganizer.R;
import com.ks.triporganizer.pojo.Trip;
import com.ks.triporganizer.pojo.TripDetails;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class TripDetailsActivity extends AppCompatActivity {

    RestTemplate restTemplate = new RestTemplate();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Intent intent = getIntent();
        String tripName = intent.getStringExtra(DisplayTripsActivity.TRIP_NAME);

        new HttpRequestTask().execute();

    }

    // TODO : Refactor to resuse
    private class HttpRequestTask extends AsyncTask<Void, Void, TripDetails> {
        @Override
        protected TripDetails doInBackground(Void... params) {
            try {
                final String url = "http://10.0.2.2:8080/trip-organizer/trip/details/1";
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                ResponseEntity<TripDetails> responseEntity = restTemplate.getForEntity(url, TripDetails.class);
                return responseEntity.getBody();
            } catch (Exception e) {
                Log.e("DisplayTripsActivity", e.getMessage(), e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(TripDetails details) {
            System.out.println(details.toString());

        }
    }
}
