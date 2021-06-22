package com.example.sos_parp;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import com.example.sos_parp.EditContacts;
import com.example.sos_parp.HelpCenter;
import com.example.sos_parp.MainActivity;
import com.example.sos_parp.PersonalInfo;
import com.example.sos_parp.R;

public class SettingsActivity extends AppCompatActivity {

    Dialog dialog, panic, dialog1;
    EditText editText;
    Button button;
    static Switch myswitch;
    CheckBox checkBox;
    public static SharedPref sharedpref,sharedPreferences;
    private Toolbar sToolbar;
    CardView card1, card2, card3, card4, card5, card6, card7;
    AlertDialog.Builder alt_bld;
    EditText message;
    Button set;
    DBhandler dBhandler;
    Cursor res;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        dialog=new Dialog(this);
        dialog1=new Dialog(this);
        sToolbar = findViewById(R.id.setting_toolbar);
        setSupportActionBar(sToolbar);
        dBhandler = new DBhandler(this);
        res = dBhandler.getDetails();
        res.moveToFirst();

        alt_bld = new AlertDialog.Builder(this, R.style.Theme_AlertDialog_SOS);

        alt_bld.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        panic = new Dialog(this);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.outline_arrow_back_ios_new_24);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Settings");

        card1 = (CardView) findViewById(R.id.scard1);
        card2 = (CardView) findViewById(R.id.scard2);
        card3 = (CardView) findViewById(R.id.scard3);
        card4 = (CardView) findViewById(R.id.scard4);
        card5 = (CardView) findViewById(R.id.theme);
        card6 = (CardView) findViewById(R.id.sfeedback);
        card7 = (CardView) findViewById(R.id.aboutus);

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

                startActivity(new Intent(getApplication(), HelpActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                panic(v);
            }
        });

        card5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mode(v);
            }
        });
        card6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                feed(v);
            }
        });

        card7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AboutUs.class));
            }
        });
    }

    private void feed(View v) {
        dialog1.setContentView(R.layout.feedback);

        editText=(EditText)dialog1.findViewById(R.id.feedback);
        button=(Button)dialog1.findViewById(R.id.send);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg=editText.getText().toString();
                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL,new String[]{"prathmeshmane334@gmail.com"});
                email.putExtra(Intent.EXTRA_SUBJECT, "Feedback on app");
                email.putExtra(Intent.EXTRA_TEXT, msg);
                email.setType("message/rfc822");
                email.setPackage("com.google.android.gm");
                startActivity(email);
            }
        });
        dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog1.show();
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

    private void panic(View v) {
        panic.setContentView(R.layout.panic);
        panic.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        message = (EditText) panic.findViewById(R.id.MessageEditText);
        set = (Button) panic.findViewById(R.id.MessageSet);
        res = dBhandler.getDetails();
        res.moveToFirst();
        message.setText(res.getString(4));
        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean check = dBhandler.updatePanicMessage("0", message.getText().toString());
                alt_bld.setTitle("");
                if (check == true) {
                    alt_bld.setMessage("Your panic message was updated");
                }
                else {
                    alt_bld.setMessage("Could not update message due to unexpected error.");
                }
                AlertDialog alert = alt_bld.create();
                alert.show();
            }
        });
        panic.show();
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

    public void dialogClose(View view) {
        panic.dismiss();
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

    public void fback(View view) {
        dialog1.dismiss();
    }

}