package com.example.sos_parp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.CompoundButton;
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

    private Toolbar sToolbar;
    RadioButton r1,r2,r3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        c1=(CardView)findViewById(R.id.theme);
        r1=findViewById(R.id.sys);
        r2=findViewById(R.id.day);
        r3=findViewById(R.id.night);
        
        r1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean sysChecked) {
                save("sys",sysChecked);
            }
        });
        r2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean dayChecked) {
                save("day",dayChecked);
            }
        });
        r3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean nightChecked) {
                save("night",nightChecked);
            }
        });
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
        c1 = (CardView)findViewById(R.id.c1);

        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplication(), MainActivity.class));
            }
        });

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
    private void save(String key,Boolean value){
        SharedPreferences sp=getSharedPreferences("sos",MODE_PRIVATE);
        SharedPreferences.Editor editor= sp.edit();
        editor.putBoolean(key,value);
        editor.apply();
    }
    private void retrive(String key){
        SharedPreferences sp=getSharedPreferences("sos",MODE_PRIVATE);
        r1.setChecked(sp.getBoolean("sys", false));
        r2.setChecked(sp.getBoolean("day", false));
        r3.setChecked(sp.getBoolean("night", false));

    }
}
