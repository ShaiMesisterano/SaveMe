package com.eveningoutpost.dexdrip;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.eveningoutpost.dexdrip.Models.BgReading;
import com.eveningoutpost.dexdrip.Models.UserError;
import com.eveningoutpost.dexdrip.UtilityModels.Inevitable;

import java.util.List;

public class WillTakeCare extends BaseAppCompatActivity {

    private BroadcastReceiver _broadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_will_take_care);
        _broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context ctx, Intent intent) {
                if (intent.getAction() != null && intent.getAction().equals(Intent.ACTION_TIME_TICK)) {
                    Inevitable.task("process-time-tick", 5, () -> runOnUiThread(() -> {
                        String bgValue = getBgValue();
                        TextView myTripsText = (TextView)findViewById(R.id.my_trips_text);
                        myTripsText.setText(bgValue);
                    }));
                }
            }
        };
        registerReceiver(_broadcastReceiver, new IntentFilter(Intent.ACTION_TIME_TICK));
    }

    @Override
    public void onPause() {
        super.onPause();
        if (_broadcastReceiver != null) {
            try {
                unregisterReceiver(_broadcastReceiver);
            } catch (IllegalArgumentException e) {
                UserError.Log.e("Error", "_broadcast_receiver not registered", e);
            }
        }
    }

    private String getBgValue() {
        final double a = BgReading.activePrediction();
        String result1 = String.valueOf(a);

        List<BgReading> bgReadingList = BgReading.latest(1, true);
        String result2 = String.valueOf(bgReadingList.get(0));

        final BgReading lastBgReading = BgReading.lastNoSenssor();
        String result3 = String.valueOf(lastBgReading.filtered_calculated_value);

        Log.d("*** result1", result1);
        Log.d("*** result2", result2);
        Log.d("*** result3", result3);

        return result1;
    }

    public void openTrip(View view) {
//        final BgReading lastBgReading = BgReading.lastNoSenssor();
//        String result = String.valueOf(lastBgReading.filtered_calculated_value);
//        Log.d("*** lastBgReading", result);
//        Intent intent = new Intent(this, TripActivity.class);
//        startActivity(intent);
    }
}
