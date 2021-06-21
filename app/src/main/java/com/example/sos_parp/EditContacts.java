package com.example.sos_parp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class EditContacts extends AppCompatActivity {

    private final int REQUEST_CODE = 99;
    public ExtendedFloatingActionButton editContact;
    Button back;
    List<listviewbutton> contactList;
    ListView listView;
    DBhandler dBhandler;
    Cursor res;
    Boolean check;
    static Boolean exists = false;
    String nameAdded = "", numAdded = "";
    AlertDialog.Builder alt_bld;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contacts);

        back = (Button) findViewById(R.id.backButton);
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


        listView = findViewById(R.id.list_view);
        MyCustomListAdapter adapter = new MyCustomListAdapter(this, R.layout.set_edit_contact, contactList);
        listView.setAdapter(adapter);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        editContact = findViewById(R.id.floating_button);
        editContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(intent,REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        listView = findViewById(R.id.list_view);
        MyCustomListAdapter adapter = new MyCustomListAdapter(this, R.layout.set_edit_contact, contactList);
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
                                        listView.setAdapter(adapter);
                                    } else {
                                        listView.setAdapter(adapter);
                                    }
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