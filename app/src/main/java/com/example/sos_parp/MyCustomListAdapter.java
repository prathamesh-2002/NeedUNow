package com.example.sos_parp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class MyCustomListAdapter extends ArrayAdapter<listviewbutton> {

    Context mCtx;
    int resource;
    List<listviewbutton> contactList;
    DBhandler dBhandler;
    Boolean check;

    public MyCustomListAdapter(Context mCtx, int resource, List<listviewbutton> contactList){

        super(mCtx,resource,contactList);

        this.mCtx = mCtx;
        this.resource = resource;
        this.contactList = contactList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);

        View view = inflater.inflate(resource, null);

        TextView listextView1  = view.findViewById(R.id.listextView1);
        TextView listextView2  = view.findViewById(R.id.listextView2);

        listviewbutton listviewbutton = contactList.get(position);

        listextView1.setText(listviewbutton.getName());
        listextView2.setText((listviewbutton.getNumber()));

        view.findViewById(R.id.edit_image).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                removeItem (position);
            }
        });
        return view;
    }

    public void removeItem(int position){

        AlertDialog.Builder builder = new AlertDialog.Builder(mCtx);
        AlertDialog.Builder alt = new AlertDialog.Builder(mCtx);
        builder.setTitle("");
        builder.setMessage("Are you sure you want to delete this Contact?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dBhandler = new DBhandler(getContext());
                check = dBhandler.deleteContacts(contactList.get(position));
                if (check == true) {
                    alt.setTitle("");
                    alt.setMessage("Contact deleted");
                    contactList.remove(position);
                }
                else {
                    alt.setTitle("");
                    alt.setMessage("Could not delete contact");
                }
                alt.show();
                notifyDataSetChanged();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}