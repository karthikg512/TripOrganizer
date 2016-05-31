package com.ks.triporganizer.activities;

import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ks.triporganizer.R;
import com.ks.triporganizer.pojo.Trip;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class DisplayTripsActivity extends ListActivity {

    RestTemplate restTemplate = new RestTemplate();
    ArrayList<String> listItems=new ArrayList<String>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_trips);

        Intent intent = getIntent();
        String email = intent.getStringExtra(LoginActivity.EMAIL_ID);

        adapter=new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                listItems);
        setListAdapter(adapter);

        // get list of trips based on email
        new HttpRequestTask().execute();

    }

    private class HttpRequestTask extends AsyncTask<Void, Void, Trip[]> {
        @Override
        protected Trip[] doInBackground(Void... params) {
            try {
                final String url = "http://10.0.2.2:8080/trip-organizer/team/1";
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                ResponseEntity<Trip[]> responseEntity = restTemplate.getForEntity(url, Trip[].class);
                Trip[] trips = responseEntity.getBody();
                return trips;
            } catch (Exception e) {
                Log.e("DisplayTripsActivity", e.getMessage(), e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Trip[] trips) {

            if (trips != null && trips.length > 0) {
                for (Trip trip : trips) {
                    listItems.add(trip.getTripName());
                }
                adapter.notifyDataSetChanged();
            }
            else
                listItems.add("No Trips Yet!!");
        }

    }
}
