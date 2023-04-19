package com.example.sos_parp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Vibrator;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.sos_parp.ui.home.HomeViewModel;
import com.example.sos_parp.ui.notifications.NotificationsFragment;

public class
AlertActivity extends AppCompatActivity {

    Button Safe, Call, Exit;
    TextView notify;
    SmsManager smsManager;
    private Vibrator myVib;
    DBhandler dBhandler;
    Cursor cRes;
    String[] contactList;

    private static final int MSG_SEND_ATTEMPTS = 0;
    private int getMsgSendAttempts = MSG_SEND_ATTEMPTS;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert);
        Safe = (Button)findViewById(R.id.SafeButton);
        Call = (Button)findViewById(R.id.Emergencies);
        Exit = (Button)findViewById(R.id.Close);
        notify = (TextView)findViewById(R.id.notify);
        smsManager = SmsManager.getDefault();
        myVib = (Vibrator) this.getSystemService(VIBRATOR_SERVICE);
        dBhandler = new DBhandler(this);
        cRes = dBhandler.getContacts();
        contactList = new String[cRes.getCount()];
        if(cRes.moveToFirst()) {
            int i = 0;
            do {
                contactList[i] = cRes.getString(1);
                i++;
            } while (cRes.moveToNext());
        }

        Safe.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                myVib.vibrate(100);
                sendMsg();
                return false;
            }
        });

        Call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), helplines.class);
                startActivity(intent);
            }
        });

        Exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void sendMsg(){

        if(getMsgSendAttempts < 3) {
            try {
                for (int i = 0; i<contactList.length; i++) {
                    smsManager.sendTextMessage(contactList[i], null, "I'm Safe", null, null);
                }
                Toast.makeText(getApplicationContext(), "Alert sent!", Toast.LENGTH_LONG).show();
                Safe.setText("Sent!");
                notify.setVisibility(View.INVISIBLE);
                Safe.setEnabled(false);
            }
            catch (Exception e) {
                getMsgSendAttempts++;
                sendMsg();
            }
        }
        else {
            Toast.makeText(this, "Alert could not be sent. Try again.", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onBackPressed() {

    }
}
