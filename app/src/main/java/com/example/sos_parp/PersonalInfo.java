package com.example.sos_parp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PersonalInfo extends AppCompatActivity {

    EditText name, phone;
    TextView bloodGroup;
    Button save;
    int selectedItem = 9;
    int checkSelect;
    String bloodGroupList[]={"O+","O-","A+","A-","B+","B-","AB+","AB-","H/H","Unknown"};
    AlertDialog.Builder alt_bld, alt_bld2;
    Boolean check;
    DBhandler dBhandler;
    Cursor res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personalinfo);

        alt_bld = new AlertDialog.Builder(this, R.style.Theme_AlertDialog_SOS);
        alt_bld2 = new AlertDialog.Builder(this, R.style.Theme_AlertDialog_SOS);

        alt_bld2.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        checkSelect = 0;
        name = (EditText) findViewById(R.id.editTextTextPersonName);
        phone = (EditText) findViewById(R.id.editTextTextPersonName2);
        bloodGroup = (TextView) findViewById(R.id.textView24);
        save = (Button) findViewById(R.id.button11);
        dBhandler = new DBhandler(this);
        res = dBhandler.getDetails();
        res.moveToFirst();

        name.setText(res.getString(1));
        phone.setText(res.getString(2));
        bloodGroup.setText(res.getString(3));

        bloodGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog();

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.getText().toString().isEmpty()) {
                    alt_bld2.setTitle("");
                    alt_bld2.setMessage("Name field cannot be empty");
                }
                else {
                    check = dBhandler.updateDetails("0", name.getText().toString(), phone.getText().toString(), bloodGroup.getText().toString());
                    if (check == true) {
                        alt_bld2.setTitle("");
                        alt_bld2.setMessage("Your details were updated");
                    } else {
                        alt_bld2.setTitle("");
                        alt_bld2.setMessage("Failed");
                    }
                }
                AlertDialog alert = alt_bld2.create();
                alert.show();
            }
        });
    }

    void dialog(){

        //alt_bld.setIcon(R.drawable.icon);
        alt_bld.setTitle("Blood Type");
        if (checkSelect == 0) {
            for (int i = 0; i < bloodGroupList.length; i++) {
                if (bloodGroupList[i].equals(res.getString(3))) {
                    selectedItem = i;
                    break;
                }
            }
            checkSelect++;
        }
        alt_bld.setSingleChoiceItems(bloodGroupList, selectedItem, new DialogInterface
                .OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                selectedItem = item;
                Toast.makeText(getApplicationContext(),
                        "Group Name = "+bloodGroupList[item], Toast.LENGTH_SHORT).show();
                if (item == 9) {
                    bloodGroup.setText("");
                }
                else {
                    bloodGroup.setText(bloodGroupList[item]);
                }
                dialog.dismiss();


            }
        });
        AlertDialog alert = alt_bld.create();
        alert.show();
    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}