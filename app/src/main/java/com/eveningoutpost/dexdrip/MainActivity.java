package com.eveningoutpost.dexdrip;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;

import com.eveningoutpost.dexdrip.utils.ActivityWithMenu;

public class MainActivity extends ActivityWithMenu implements ActivityCompat.OnRequestPermissionsResultCallback {

    @Override
    public String getMenuName() {
        return getString(R.string.home_screen);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openMyTrips(View view) {
        Intent intent = new Intent(this, WillTakeCare.class);
        startActivity(intent);
    }
}
