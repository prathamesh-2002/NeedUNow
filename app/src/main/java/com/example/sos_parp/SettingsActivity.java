package com.example.sos_parp;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class SettingsActivity extends AppCompatActivity {

    private Toolbar sToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        sToolbar = findViewById(R.id.set_toolbar);
        setSupportActionBar(sToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

    }
}
