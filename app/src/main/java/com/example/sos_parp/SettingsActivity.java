package com.example.sos_parp;

import android.content.Intent;
import android.os.Bundle;
<<<<<<< HEAD
=======
import android.view.MenuItem;
>>>>>>> 1.5
import android.view.View;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
<<<<<<< HEAD
=======
import android.widget.CheckBox;
>>>>>>> 1.5
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

<<<<<<< HEAD
public class SettingsActivity extends AppCompatActivity {
CardView c1;
Dialog dialog;

    private Toolbar sToolbar;
    RadioButton r1,r2,r3;
=======
import com.example.sos_parp.EditContacts;
import com.example.sos_parp.HelpCenter;
import com.example.sos_parp.MainActivity;
import com.example.sos_parp.PersonalInfo;
import com.example.sos_parp.R;

public class SettingsActivity extends AppCompatActivity {
    Dialog dialog;
    static Switch myswitch;
    CheckBox checkBox;
    public static SharedPref sharedpref,sharedPreferences;
    private Toolbar sToolbar;
>>>>>>> 1.5

    CardView c1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
<<<<<<< HEAD
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
=======
        dialog=new Dialog(this);
        sToolbar = findViewById(R.id.setting_toolbar);
        setSupportActionBar(sToolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.outline_arrow_back_ios_new_24);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Settings");
        c1=(CardView)findViewById(R.id.theme);
        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mode(v);
            }
        });





























    }

    private void mode(View view) {
        dialog.setContentView(R.layout.mode);
        checkBox=(CheckBox)dialog.findViewById(R.id.system);
        sharedPreferences = new SharedPref(this);
        myswitch=(Switch)dialog.findViewById(R.id.myswitch);
        sharedpref = new SharedPref(this);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(checkBox.isChecked()){
                    sharedPreferences.setDefaultmode(true);
                }
                else{
                    sharedPreferences.setDefaultmode(false);
                }
                if(sharedPreferences.loadDefaultmode()==true)
                {
                    myswitch.setEnabled(false);
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);

                }else {
                    myswitch.setEnabled(true);
                    if (sharedpref.loadNightModeState() == true) {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    } else {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    }
                }
            }
        });



            if(sharedPreferences.loadDefaultmode()==true){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                myswitch.setEnabled(false);
            }
            else {

                myswitch.setEnabled(true);
                if (sharedpref.loadNightModeState() == true) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    myswitch.setText("Night");
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
            }

            if (sharedPreferences.loadDefaultmode() == true) {
                checkBox.setChecked(true);
            }

            if (sharedpref.loadNightModeState() == true) {
                myswitch.setChecked(true);
            }

            myswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        sharedpref.setNightModeState(true);
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    } else {
                        sharedpref.setNightModeState(false);
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    }
                    if(sharedPreferences.loadDefaultmode()==true)
                    {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);

                    }else {
                        if (sharedpref.loadNightModeState() == true) {
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                        } else {
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                        }
                    }
                }
            });

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    public void back(View view) {
        if(sharedPreferences.loadDefaultmode()==true)
        {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);

        }else {
            if (sharedpref.loadNightModeState() == true) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
        }
       dialog.cancel();
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
>>>>>>> 1.5

        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplication(), MainActivity.class));
            }
        });

    }

<<<<<<< HEAD
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
=======
>>>>>>> 1.5
}
