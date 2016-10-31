package com.ks.triporganizer.adapters;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.TextView;

import com.ks.triporganizer.R;
import com.ks.triporganizer.pojo.TripDetails;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kgundimeda on 10/30/16.
 */
public class TripDetailsAdapter extends BaseExpandableListAdapter {

    RestTemplate restTemplate = new RestTemplate();
    long tripId;
    Context context;
    LayoutInflater layoutInflater;
    private List<String> tasks;

    public TripDetailsAdapter(Context context, LayoutInflater layoutInflater, long tripId, List<String> tasks) {
        this.context = context;
        this.layoutInflater = layoutInflater;
        this.tripId = tripId;
        this.tasks = tasks;

        new HttpRequestTask().execute();
    }

    @Override
    public int getGroupCount() {
        return tasks.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 0;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return tasks.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    // TODO : Refactor to resuse
    private class HttpRequestTask extends AsyncTask<Void, Void, TripDetails> {
        @Override
        protected TripDetails doInBackground(Void... params) {
            try {
                final String url = "http://10.0.2.2:8080/trip-organizer/trip/details/"+tripId;
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                ResponseEntity<TripDetails> responseEntity = restTemplate.getForEntity(url, TripDetails.class);
                return responseEntity.getBody();
            } catch (Exception e) {
                Log.e("TripDetailsActivity", e.getMessage(), e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(TripDetails details) {

        }
    }
}
