package com.example.sos_parp;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;
import java.util.concurrent.Delayed;

public class TimerActivity extends AppCompatActivity {

    RadioButton r1, r2, r3;
    Button StopTimer;
    SmsManager smsManager;
    String alertMsg;
    private Vibrator myVib;

    private static final int MSG_SEND_ATTEMPTS = 0;
    private int getMsgSendAttempts = MSG_SEND_ATTEMPTS;

    private static final long START_TIME_IN_MILLIS = 16000;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;
    private TextView mTextViewCountDown;

    private CountDownTimer mCountDownTimer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        mTextViewCountDown = (TextView)findViewById(R.id.TimerTextView);
        myVib = (Vibrator) this.getSystemService(VIBRATOR_SERVICE);
        r1 = (RadioButton)findViewById(R.id.radioButton);
        r2 = (RadioButton)findViewById(R.id.radioButton2);
        r3 = (RadioButton)findViewById(R.id.radioButton3);
        StopTimer = (Button)findViewById(R.id.CancelTimer);
        alertMsg = getString(R.string.alert_msg);
        smsManager = SmsManager.getDefault();

        startTimer();

        StopTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCountDownTimer.cancel();
                finish();
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
                mTextViewCountDown.setTextSize(45);
                sendMsg();
            }
        }.start();
    }

    private void updateCountDownText(){
        int seconds = (int) (mTimeLeftInMillis/1000);

        String timeLeftFormatted = String.format(Locale.getDefault(),"%02d", seconds);

        mTextViewCountDown.setText(timeLeftFormatted);
    }

    private void sendMsg(){

        if(getMsgSendAttempts < 3) {
            try {
                smsManager.sendTextMessage("9867147799", null, alertMsg, null, null);
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
