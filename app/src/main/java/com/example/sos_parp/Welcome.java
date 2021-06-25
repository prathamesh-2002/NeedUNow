package com.example.sos_parp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.sos_parp.ui.home.HomeViewModel;



public class Welcome extends AppCompatActivity {
    public static SharedPref intro;

    DBhandler dBhandler;
    Cursor res;
    EditText name;
    Button b1;
    Boolean check;
    AlertDialog.Builder alt_bld;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        intro = new SharedPref(this);
        if(intro.getIntro()==true){
            Intent intent = new Intent(getApplication(), MainActivity.class);

            startActivity(intent);
        }
        else {
            setContentView(R.layout.activity_welcome);

            alt_bld = new AlertDialog.Builder(this, R.style.Theme_AlertDialog_SOS);

            name = (EditText) findViewById(R.id.setupName);
            b1=(Button) findViewById(R.id.b_welcome);
            dBhandler = new DBhandler(this);
            res = dBhandler.getDetails();
            res.moveToFirst();

            name.setText(res.getString(1));

            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (name.getText().toString().isEmpty()) {
                        alt_bld.setTitle("");
                        alt_bld.setMessage("Name cannot be empty");
                        alt_bld.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        alt_bld.show();

                    }
                    else {
                        dBhandler.updateName("0", name.getText().toString());

                        Intent intent = new Intent(getApplication(), MedicalInfo.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    }
                }
            });
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}