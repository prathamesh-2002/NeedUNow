package com.example.sos_parp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Looper;
import android.os.Vibrator;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.Delayed;

public class TimerActivity extends AppCompatActivity {

    RadioButton r1, r2, r3;
    Button StopTimer;
    SmsManager smsManager;
    String alertMsg;
    DBhandler dBhandler;
    Cursor res, cRes;
    RadioGroup radioGroup;
    String eType;
    String[] contactList;
    private ProgressBar progressBar;
    private Vibrator myVib;

    private static final int REQUEST_CODE_LOCATION_PERMISSION = 1;
    private static final int MSG_SEND_ATTEMPTS = 0;
    private int getMsgSendAttempts = MSG_SEND_ATTEMPTS;

    private static final long START_TIME_IN_MILLIS = 16000;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;
    private TextView mTextViewName, mTextViewCountDown, mTextViewLocation;
    private static double latitude;
    private static double longitude;

    private CountDownTimer mCountDownTimer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        dBhandler = new DBhandler(this);
        res = dBhandler.getDetails();
        res.moveToFirst();
        cRes = dBhandler.getContacts();
        contactList = new String[cRes.getCount()];
        cRes.moveToFirst();
        int i = 0;
        while (cRes.moveToNext()) {
           contactList[i] = cRes.getString(1);
           i++;
        }
        mTextViewName = (TextView) findViewById(R.id.NameTextView);
        mTextViewCountDown = (TextView) findViewById(R.id.TimerTextView);
        mTextViewLocation = (TextView) findViewById(R.id.LocationTextView);
        progressBar = (ProgressBar) findViewById(R.id.progressLocation);
        myVib = (Vibrator) this.getSystemService(VIBRATOR_SERVICE);
        radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        r1 = (RadioButton) findViewById(R.id.radioButton);
        r2 = (RadioButton) findViewById(R.id.radioButton2);
        r3 = (RadioButton) findViewById(R.id.radioButton3);
        StopTimer = (Button) findViewById(R.id.CancelTimer);
        alertMsg = getString(R.string.alert_msg);
        smsManager = SmsManager.getDefault();

        mTextViewName.setText(res.getString(1) + ",");
        startTimer();
        getCurrentLocation();

        StopTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCountDownTimer.cancel();
                finish();
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioButton:
                        eType = "Vehicle Accident";
                        break;
                    case R.id.radioButton2:
                        eType = "Women intentional harm";
                        break;
                    case R.id.radioButton3:
                        eType = "Fire Outage";
                        break;
                }
            }
        });

        r1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myVib.vibrate(50);
            }
        });

        r2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myVib.vibrate(50);
            }
        });

        r3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myVib.vibrate(50);
            }
        });
    }

    private void startTimer(){
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                mTextViewCountDown.setText("Sending...");
                mTextViewCountDown.setTextSize(40);
                sendMsg();
            }
        }.start();
    }

    private void updateCountDownText(){
        int seconds = (int) (mTimeLeftInMillis/1000);

        String timeLeftFormatted = String.format(Locale.getDefault(),"%02d", seconds);

        mTextViewCountDown.setText(timeLeftFormatted);
    }

    private void getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    TimerActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_CODE_LOCATION_PERMISSION
            );
        } else {
            progressBar.setVisibility(View.VISIBLE);

            LocationRequest locationRequest = new LocationRequest();
            locationRequest.setInterval(10000);
            locationRequest.setFastestInterval(3000);
            locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

            LocationServices.getFusedLocationProviderClient(TimerActivity.this)
                    .requestLocationUpdates(locationRequest, new LocationCallback() {

                        @Override
                        public void onLocationResult(LocationResult locationResult) {
                            super.onLocationResult(locationResult);
                            LocationServices.getFusedLocationProviderClient(TimerActivity.this)
                                    .removeLocationUpdates(this);
                            if (locationResult != null && locationResult.getLocations().size() > 0) {
                                int latestLocationIndex = locationResult.getLocations().size() - 1;
                                 latitude = locationResult.getLocations().get(latestLocationIndex).getLatitude();
                                 longitude = locationResult.getLocations().get(latestLocationIndex).getLongitude();
                                mTextViewLocation.setText(String.format("Latitude: %s\nLongitude: %s", latitude, longitude));
                            }
                            progressBar.setVisibility(View.GONE);
                        }
                    }, Looper.getMainLooper());
        }
    }
    private void sendMsg(){

        if(getMsgSendAttempts < 3) {
            try {
                for (int i=0 ; i<contactList.length-1; i++) {
                    smsManager.sendTextMessage(contactList[i], null, res.getString(4) + alertMsg + "https://maps.google.com/?q=" + latitude + "," + longitude + "\n\nEmergency Type: " + eType, null, null);
                }
                alertSent();
            }
            catch (Exception e) {
                Toast.makeText(this, "Alert sending failed, trying again...", Toast.LENGTH_LONG).show();
                getMsgSendAttempts++;
                sendMsg();
            }
        }
        else {
            Toast.makeText(this, "Alert could not be sent.", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    private void alertSent(){
        Intent intent = new Intent(getApplication(), AlertActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        finish();
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCountDownTimer.cancel();
    }
}
