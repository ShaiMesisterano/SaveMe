package com.eveningoutpost.dexdrip;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.webkit.WebView;

public class MyTripsActivity extends BaseAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mytrips);
    }

    public void openTrip(WebView view) {
        Intent intent = new Intent(this, TripActivity.class);
        startActivity(intent);
    }
}
