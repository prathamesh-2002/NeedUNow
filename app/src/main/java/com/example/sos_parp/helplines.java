package com.example.sos_parp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class helplines extends AppCompatActivity {
    Button b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,b12,b13,b14;
    private Toolbar sToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helplines);
        b1=(Button)findViewById(R.id.h108);
        b2=(Button)findViewById(R.id.h1091);
        b3=(Button)findViewById(R.id.h181);
        b4=(Button)findViewById(R.id.hair);
        b5=(Button)findViewById(R.id.h1097);
        b6=(Button)findViewById(R.id.hndrf1);
        b7=(Button)findViewById(R.id.hndrf2);
        b8=(Button)findViewById(R.id.h1094);
        b9=(Button)findViewById(R.id.h139);
        b10=(Button)findViewById(R.id.h1291);
        b11=(Button)findViewById(R.id.h1072);
        b12=(Button)findViewById(R.id.h1073);
        b13=(Button)findViewById(R.id.h1363);
        b14=(Button)findViewById(R.id.h1906);

        sToolbar = findViewById(R.id.helpline_toolbar);
        setSupportActionBar(sToolbar);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.outline_arrow_back_ios_new_24);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Helplines");

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = "108-";
                Uri call = Uri.parse("tel:" + number);
                Intent intent = new Intent(Intent.ACTION_DIAL, call);
                startActivity(intent);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = "1091";
                Uri call = Uri.parse("tel:" + number);
                Intent intent = new Intent(Intent.ACTION_DIAL, call);
                startActivity(intent);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = "181";
                Uri call = Uri.parse("tel:" + number);
                Intent intent = new Intent(Intent.ACTION_DIAL, call);
                startActivity(intent);
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = "9540161344";
                Uri call = Uri.parse("tel:" + number);
                Intent intent = new Intent(Intent.ACTION_DIAL, call);
                startActivity(intent);
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = "1097";
                Uri call = Uri.parse("tel:" + number);
                Intent intent = new Intent(Intent.ACTION_DIAL, call);
                startActivity(intent);
            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = "011-24363260";
                Uri call = Uri.parse("tel:" + number);
                Intent intent = new Intent(Intent.ACTION_DIAL, call);
                startActivity(intent);
            }
        });
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = "9711077372";
                Uri call = Uri.parse("tel:" + number);
                Intent intent = new Intent(Intent.ACTION_DIAL, call);
                startActivity(intent);
            }
        });
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = "1094";
                Uri call = Uri.parse("tel:" + number);
                Intent intent = new Intent(Intent.ACTION_DIAL, call);
                startActivity(intent);
            }
        });
        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = "139";
                Uri call = Uri.parse("tel:" + number);
                Intent intent = new Intent(Intent.ACTION_DIAL, call);
                startActivity(intent);
            }
        });
        b10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = "1291";
                Uri call = Uri.parse("tel:" + number);
                Intent intent = new Intent(Intent.ACTION_DIAL, call);
                startActivity(intent);
            }
        });
        b11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = "1073";
                Uri call = Uri.parse("tel:" + number);
                Intent intent = new Intent(Intent.ACTION_DIAL, call);
                startActivity(intent);
            }
        });
        b12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = "1073";
                Uri call = Uri.parse("tel:" + number);
                Intent intent = new Intent(Intent.ACTION_DIAL, call);
                startActivity(intent);
            }
        });
        b13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = "1363";
                Uri call = Uri.parse("tel:" + number);
                Intent intent = new Intent(Intent.ACTION_DIAL, call);
                startActivity(intent);
            }
        });
        b14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = "1906";
                Uri call = Uri.parse("tel:" + number);
                Intent intent = new Intent(Intent.ACTION_DIAL, call);
                startActivity(intent);
            }
        });
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
}