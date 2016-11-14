package com.ks.triporganizer.adapters;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckedTextView;
import android.widget.ExpandableListAdapter;
import android.widget.TextView;

import com.ks.triporganizer.R;
import com.ks.triporganizer.pojo.TripDetails;
import com.ks.triporganizer.pojo.User;

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
    TripDetails tripDetails;

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
        String task = tasks.get(groupPosition);
        return 4;
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
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.expandable_group_layout, null);
        }

        TextView item = (TextView) convertView.findViewById(R.id.txt1);
        item.setTypeface(null, Typeface.BOLD);
        item.setText(tasks.get(groupPosition));
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        String task = tasks.get(groupPosition);
        StringBuilder sb = new StringBuilder();
        switch(task) {
            case "Users":
                List<User> users = tripDetails.getUsers();
                if (users == null) sb.append("No Users yet!");
                else {
                    for (User u : users) {
                        sb.append(u.getFirstName());
                        sb.append(" ");
                    }
                }
                break;
        }

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.expandable_task_layout, null);
        }

        TextView textView = (TextView) convertView.findViewById(R.id.txtChld1);
        textView.setText(sb.toString());

        return convertView;
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
            System.out.println(details);
            tripDetails = details;
        }
    }
}
