package com.example.phoneresetapp;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private BroadcastReceiver broadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Register receiver to listen for dial codes
        registerReceiver();
    }

    private void registerReceiver() {
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                String dialedNumber = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);

                if (action != null && action.equals(Intent.ACTION_NEW_OUTGOING_CALL)) {
                    if (dialedNumber.equals("*555#")) {
                        triggerPhoneReset();
                    }
                }
            }
        };

        IntentFilter filter = new IntentFilter(Intent.ACTION_NEW_OUTGOING_CALL);
        registerReceiver(broadcastReceiver, filter);
    }

    private void triggerPhoneReset() {
        // Code to trigger phone reset goes here. This is a placeholder toast for demonstration.
        Toast.makeText(this, "Triggering phone reset...", Toast.LENGTH_SHORT).show();
        // Actual reset logic requires appropriate permissions and code implementation.
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastReceiver);
    }
}