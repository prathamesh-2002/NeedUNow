package com.example.sos_parp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.sos_parp.ui.notifications.NotificationsFragment;

public class AlertActivity extends AppCompatActivity {

    Button Safe, Call, Exit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert);
        Safe = (Button)findViewById(R.id.SafeButton);
        Call = (Button)findViewById(R.id.Emergencies);
        Exit = (Button)findViewById(R.id.Close);

        Safe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Alert sent!", Toast.LENGTH_LONG).show();
            }
        });

        Call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), MainActivity.class);
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
}
