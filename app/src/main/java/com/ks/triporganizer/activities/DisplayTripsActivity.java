package com.ks.triporganizer.activities;

import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.ks.triporganizer.R;
import com.ks.triporganizer.pojo.Trip;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class DisplayTripsActivity extends ListActivity {

    RestTemplate restTemplate = new RestTemplate();
    List<String> listItems= new ArrayList<String>();
    ArrayAdapter<String> adapter;
    ListView listView;
    public final static String TRIP_ID = "com.ks.triporganizer.activities.TRIP_ID";
    Map<String, Long> tripNameToId = new HashMap<String, Long>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_trips);

        Intent intent = getIntent();
        String email = intent.getStringExtra(LoginActivity.EMAIL_ID);

        adapter=new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                listItems);

        listView = getListView(); // OR (ListView) findViewById(android.R.id.list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(mMessageClickedHandler);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabAdd);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addTrip();
            }
        });

        // get list of trips based on email
        new HttpRequestTask().execute();

    }

    private void addTrip() {
        Intent intent = new Intent(getApplicationContext(), AddTripActivity.class);
        startActivity(intent);
    }

    // TODO : Refactor to resuse
    private class HttpRequestTask extends AsyncTask<Void, Void, Trip[]> {
        @Override
        protected Trip[] doInBackground(Void... params) {
            try {
                final String url = "http://10.0.2.2:8080/trip-organizer/team/1";
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                ResponseEntity<Trip[]> responseEntity = restTemplate.getForEntity(url, Trip[].class);
                return responseEntity.getBody();
            } catch (Exception e) {
                Log.e("DisplayTripsActivity", e.getMessage(), e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Trip[] trips) {

            if (trips != null && trips.length > 0) {
                for (Trip trip : trips) {
                    tripNameToId.put(trip.getTripName(), trip.getId());
                    listItems.add(trip.getTripName());
                }
                adapter.notifyDataSetChanged();
            }
            else
                listItems.add("No Trips Yet!!");
        }

    }

    // Create a message handling object as an anonymous class.
    private AdapterView.OnItemClickListener mMessageClickedHandler = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView parent, View v, int position, long id) {
            Intent intent = new Intent(getApplicationContext(), TripDetailsActivity.class);
            String tripName = (String)((TextView)v).getText();
            intent.putExtra(TRIP_ID, tripNameToId.get(tripName));
            startActivity(intent);
        }
    };

}
