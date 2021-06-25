package com.example.sos_parp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MedicalInfo extends AppCompatActivity {

    DBhandler dBhandler;
    Cursor res;
    EditText  allergies, medications, mNotes;
    TextView bloodGroup, organDonor;
    CheckBox includeMed;
    Boolean includeFields;
    Button save, backButton;
    int selectedItem = 9, selectedItem1 = 2;
    int checkSelect, checkSelect1;
    String[] bloodGroupList ={"O+","O-","A+","A-","B+","B-","AB+","AB-","H/H","Unknown"};
    String[] organDonorList={"Yes","No","Unknown"};

    AlertDialog.Builder alt_bld, alt_bld1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicalinfo);

        dBhandler = new DBhandler(this);
        res = dBhandler.getDetails();
        res.moveToFirst();

        alt_bld = new AlertDialog.Builder(this, R.style.Theme_AlertDialog_SOS);
        alt_bld1 = new AlertDialog.Builder(this, R.style.Theme_AlertDialog_SOS);

        allergies = (EditText) findViewById(R.id.setupAllergies);
        medications = (EditText) findViewById(R.id.setupMedication);
        mNotes = (EditText) findViewById(R.id.setupMNotes);
        bloodGroup = (TextView) findViewById(R.id.setupBlood);
        organDonor = (TextView) findViewById(R.id.setupOrgan);
        includeMed = (CheckBox) findViewById(R.id.setupIncludeCheck);
        backButton = (Button) findViewById(R.id.backButton);
        save = (Button) findViewById(R.id.b_medical);
        checkSelect = 0;
        checkSelect1 = 0;

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
                includeFields = includeMed.isChecked();

                dBhandler.updateMedInfo("0",
                        bloodGroup.getText().toString(),
                        allergies.getText().toString(),
                        medications.getText().toString(),
                        organDonor.getText().toString(),
                        mNotes.getText().toString(),
                        includeFields.toString());
                Intent intent = new Intent(getApplication(), Contact.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
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
        if (checkSelect1 == 0) {
            for (int i = 0; i < organDonorList.length; i++) {
                if (organDonorList[i].equals(res.getString(5))) {
                    selectedItem1 = i;
                    break;
                }
            }
            checkSelect1++;
        }
        alt_bld1.setSingleChoiceItems(organDonorList, selectedItem1, new DialogInterface
                .OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                selectedItem1 = item;

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
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

}