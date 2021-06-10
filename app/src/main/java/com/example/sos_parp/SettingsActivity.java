package com.example.sos_parp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

public class SettingsActivity extends AppCompatActivity {

    CardView c1;

    private Toolbar sToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        sToolbar = findViewById(R.id.set_toolbar);
        setSupportActionBar(sToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        c1 = (CardView)findViewById(R.id.c1);

        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplication(), MainActivity.class));
            }
        });
    }
}
