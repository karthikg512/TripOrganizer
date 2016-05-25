package com.ks.triporganizer.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.ks.triporganizer.R;

public class LoginActivity extends AppCompatActivity {

    public final static String EMAIL_ID = "com.ks.triporganizer.activities.EMAIL_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void login(View view) {
        Intent intent = new Intent(this, DisplayTripsActivity.class);
        EditText emailText = (EditText) findViewById(R.id.email);
        String emailId = emailText.getText().toString();
        intent.putExtra(EMAIL_ID, emailId);
        startActivity(intent);
    }
}
