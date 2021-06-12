package com.example.sos_parp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

public class SettingsActivity extends AppCompatActivity {

    private Toolbar sToolbar;

    CardView card1;
    CardView card2;
    CardView card3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        sToolbar = findViewById(R.id.setting_toolbar);
        setSupportActionBar(sToolbar);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.outline_arrow_back_ios_new_24);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Settings");


        card1 = (CardView) findViewById(R.id.scard1);
        card2 = (CardView) findViewById(R.id.scard2);
        card3 = (CardView) findViewById(R.id.scard3);

        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplication(), PersonalInfo.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }

        });

        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplication(), EditContacts.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }

        });

        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplication(), HelpCenter.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }

        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.setting_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
