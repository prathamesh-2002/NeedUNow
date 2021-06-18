package com.example.sos_parp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

public class SettingsActivity extends AppCompatActivity {

    Dialog dialog, panic;
    RadioButton radioButton,radioButton1;
    SharedPreferences sharedPreferences=null;

    private Toolbar sToolbar;
    RadioGroup r1;
    CardView c1;
    CardView card1, card2, card3, card4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        
        sToolbar = findViewById(R.id.setting_toolbar);
        setSupportActionBar(sToolbar);
        panic = new Dialog(this);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.outline_arrow_back_ios_new_24);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Settings");
       
        c1=(CardView)findViewById(R.id.theme);

        dialog=new Dialog(this);
        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mode();
            }
        });

        card1 = (CardView) findViewById(R.id.scard1);
        card2 = (CardView) findViewById(R.id.scard2);
        card3 = (CardView) findViewById(R.id.scard3);
        card4 = (CardView) findViewById(R.id.scard4);

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

        card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                panic.setContentView(R.layout.panic);
                panic.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                panic.show();
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
    public void onRadioButtonClicked(View v) {
        // Is the button now checked?

        boolean checked = ((RadioButton) v).isChecked();

        // Check which radio button was clicked
        switch(v.getId()) {
            case R.id.day:
                if (checked)
                    Toast.makeText(this,"Day Mode",Toast.LENGTH_SHORT).show();
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

                break;
            case R.id.night:
                if (checked)
                    Toast.makeText(this," Night Mode", Toast.LENGTH_SHORT).show();
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

                break;
            case R.id.sys:
                if (checked)
                    Toast.makeText(this," System Default", Toast.LENGTH_SHORT).show();
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);

                break;
        }
    }

    private void mode() {
        dialog.setContentView(R.layout.mode);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    public void back(View view) {
        dialog.dismiss();
    }
}
