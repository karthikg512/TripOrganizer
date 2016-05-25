package com.ks.triporganizer.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ks.triporganizer.R;
import com.ks.triporganizer.pojo.Trip;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class DisplayTripsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_trips);

        Intent intent = getIntent();
        String email = intent.getStringExtra(LoginActivity.EMAIL_ID);

        // get list of trips based on email
        new HttpRequestTask().execute();

    }

    private class HttpRequestTask extends AsyncTask<Void, Void, List<Trip>> {
        @Override
        protected List<Trip> doInBackground(Void... params) {
            try {
                final String url = "http://10.0.2.2:8080/trip-organizer/team/1";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                List<Trip> trips = restTemplate.getForObject(url, List.class);
                return trips;
            } catch (Exception e) {
                Log.e("DisplayTripsActivity", e.getMessage(), e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(List<Trip> trips) {
            TextView tripsView = (TextView) findViewById(R.id.trip_label);
            if (trips == null)
                tripsView.setText("null");
            else
                tripsView.setText(trips.toString());
        }
    }
}
