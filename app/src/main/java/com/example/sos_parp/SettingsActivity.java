package com.example.sos_parp;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

public class SettingsActivity extends AppCompatActivity {
CardView c1;
Dialog dialog;
RadioButton radioButton,radioButton1;
SharedPreferences sharedPreferences=null;

    private Toolbar sToolbar;
    RadioGroup r1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        c1=(CardView)findViewById(R.id.theme);



        dialog=new Dialog(this);
        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mode();
            }
        });

        sToolbar = findViewById(R.id.set_toolbar);
        setSupportActionBar(sToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


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
