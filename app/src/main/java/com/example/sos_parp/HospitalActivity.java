package com.example.sos_parp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.sos_parp.ui.notifications.NotificationsFragment;

import static android.app.PendingIntent.getActivity;

public class HospitalActivity extends AppCompatActivity {
    ImageView i;
    Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hospital);
        b=(Button)findViewById(R.id.call102);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = "102";
                Uri call = Uri.parse("tel:" + number);
                Intent intent = new Intent(Intent.ACTION_DIAL, call);
                startActivity(intent);
            }
        });
        i=(ImageView)findViewById(R.id.back);
        i.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                onBackPressed();
            }


        });
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }


}