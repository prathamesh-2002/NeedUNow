package com.example.sos_parp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PersonalInfo extends AppCompatActivity {

    private Toolbar sToolbar;
    EditText name, allergies, medications, mNotes;
    TextView bloodGroup, organDonor;
    CheckBox includeMed;
    Boolean includeFields, check;
    Button save;
    int selectedItem = 9;
    int checkSelect;
    String[] bloodGroupList={"O+","O-","A+","A-","B+","B-","AB+","AB-","H/H","Unknown"};
    String[] organDonorList={"Yes","No","Unknown"};
    AlertDialog.Builder alt_bld, alt_bld1, alt_bld2;
    DBhandler dBhandler;
    Cursor res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personalinfo);

        sToolbar = findViewById(R.id.personalinfo_toolbar);
        setSupportActionBar(sToolbar);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.outline_arrow_back_ios_new_24);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Personal Info");

        alt_bld = new AlertDialog.Builder(this, R.style.Theme_AlertDialog_SOS);
        alt_bld1 = new AlertDialog.Builder(this, R.style.Theme_AlertDialog_SOS);
        alt_bld2 = new AlertDialog.Builder(this, R.style.Theme_AlertDialog_SOS);

        alt_bld2.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        checkSelect = 0;
        name = (EditText) findViewById(R.id.editTextTextPersonName);
        allergies = (EditText) findViewById(R.id.algEditText);
        medications = (EditText) findViewById(R.id.medEditText);
        mNotes = (EditText) findViewById(R.id.notesEditText);
        bloodGroup = (TextView) findViewById(R.id.bloodGroup);
        organDonor = (TextView) findViewById(R.id.organChoice);
        includeMed = (CheckBox) findViewById(R.id.setupIncludeCheck);
        save = (Button) findViewById(R.id.updateInfo);
        dBhandler = new DBhandler(this);
        res = dBhandler.getDetails();
        res.moveToFirst();

        name.setText(res.getString(1));
        bloodGroup.setText(res.getString(2));
        allergies.setText(res.getString(3));
        medications.setText(res.getString(4));
        organDonor.setText(res.getString(5));
        mNotes.setText(res.getString(6));
        includeMed.setChecked(Boolean.parseBoolean(res.getString(8)));

        bloodGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog();

            }
        });

        organDonor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog1();
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
                    includeFields = includeMed.isChecked();

                    check = dBhandler.updateDetails("0",
                            name.getText().toString(),
                            bloodGroup.getText().toString(),
                            allergies.getText().toString(),
                            medications.getText().toString(),
                            organDonor.getText().toString(),
                            mNotes.getText().toString(),
                            includeFields.toString());
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

        alt_bld.setTitle("Blood Type");
        if (checkSelect == 0) {
            for (int i = 0; i < bloodGroupList.length; i++) {
                if (bloodGroupList[i].equals(res.getString(2))) {
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

    void dialog1(){

        alt_bld1.setTitle("Organ donor");
        if (checkSelect == 0) {
            for (int i = 0; i < organDonorList.length; i++) {
                if (organDonorList[i].equals(res.getString(5))) {
                    selectedItem = i;
                    break;
                }
            }
            checkSelect++;
        }
        alt_bld1.setSingleChoiceItems(organDonorList, selectedItem, new DialogInterface
                .OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                selectedItem = item;

                if (item == 2) {
                    organDonor.setText("");
                }
                else {
                    organDonor.setText(organDonorList[item]);
                }
                dialog.dismiss();

            }
        });
        AlertDialog alert1 = alt_bld1.create();
        alert1.show();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}