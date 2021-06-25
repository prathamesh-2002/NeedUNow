package com.example.sos_parp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sos_parp.ui.home.HomeViewModel;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class Contact extends AppCompatActivity {
    public static SharedPref intro;

    Button save, backButton, editContact;
    private final int REQUEST_CODE = 99;
    List<listviewbutton> contactList;
    ListView listView;
    DBhandler dBhandler;
    Cursor res;
    Boolean check;
    MyCustomListAdapter adapter;
    static Boolean exists = false;
    AlertDialog.Builder alt_bld;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        //Shared Pref
        intro = new SharedPref(this);

        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        dialog.dismiss();
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        intro.setIntro(true);
                        Intent intent = new Intent(getApplication(), MainActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                }
            }
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        save = (Button) findViewById(R.id.b_next);


        //End of Shared Pref

        dBhandler = new DBhandler(this);
        res = dBhandler.getContacts();

        alt_bld = new AlertDialog.Builder(this, R.style.Theme_AlertDialog_SOS);
        alt_bld.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        contactList = new ArrayList<>();

        while (res.moveToNext()) {
            contactList = dBhandler.getAllContacts();
        }

        listView = findViewById(R.id.setupListview);
        adapter = new MyCustomListAdapter(this, R.layout.set_edit_contact, contactList);
        listView.setAdapter(adapter);

        editContact = findViewById(R.id.b_addContact);
        editContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(intent,REQUEST_CODE);
            }
        });

        backButton=(Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (adapter.isEmpty()) {
                    builder.setTitle("Contacts not added");
                    builder.setMessage("Setup emergency contacts to send alert messages").setPositiveButton("Add Contacts", dialogClickListener)
                            .setNegativeButton("Do it later", dialogClickListener).show();
                }
                else {
                    intro.setIntro(true);
                    Intent intent = new Intent(getApplication(), MainActivity.class);
                    startActivity(intent);
                }
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        listView = findViewById(R.id.setupListview);
        adapter = new MyCustomListAdapter(this, R.layout.set_edit_contact, contactList);
        dBhandler = new DBhandler(this);
        res = dBhandler.getContacts();
        res.moveToFirst();

        switch (requestCode){

            case (REQUEST_CODE):
                if (resultCode == Activity.RESULT_OK){
                    Uri contactData = data.getData();
                    Cursor c = getContentResolver().query(contactData,null,null,null,null);

                    if(c.moveToFirst()){
                        String contactId = c.getString(c.getColumnIndex(ContactsContract.Contacts._ID));
                        String hasNumber = c.getString(c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
                        String name = "";
                        String num = "";

                        if (Integer.valueOf(hasNumber) == 1) {

                            Cursor numbers = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + contactId, null,null);
                            while (numbers.moveToNext()){
                                num = numbers.getString(numbers.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                                name = numbers.getString(numbers.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));

                                while (res.moveToNext()) {
                                    if (name.equals(res.getString(0)) && num.equals(res.getString(1))) {
                                        Toast.makeText(getApplicationContext(), "Already exists", Toast.LENGTH_SHORT).show();
                                        exists = true;
                                        break;
                                    }
                                    else {
                                        exists = false;
                                    }
                                }

                                if (!exists) {
                                    check = dBhandler.insertContacts(new listviewbutton(name, num));

                                    if (check == true) {
                                        alt_bld.setTitle("");
                                        alt_bld.setMessage("Contact was added successfully");
                                        contactList.add(new listviewbutton(name, num));
                                    }
                                    listView.setAdapter(adapter);
                                    adapter.notifyDataSetChanged();
                                    AlertDialog alert = alt_bld.create();
                                    alert.show();
                                }
                            }
                        }
                    }
                    break;
                }
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
