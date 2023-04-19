package com.example.sos_parp;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class HelpActivity extends AppCompatActivity {

    Dialog permission;
    Button p1,g1;

    private Toolbar sToolbar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        sToolbar = findViewById(R.id.helpcenter_toolbar);
        setSupportActionBar(sToolbar);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.outline_arrow_back_ios_new_24);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Help Center");

        permission = new Dialog(this);
        p1 = (Button) findViewById(R.id.permissions);
        g1 = (Button) findViewById(R.id.okay);

        p1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pDialog(v);
            }
        });

        g1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    public void pDialog(View view) {
        permission.setContentView(R.layout.permissions);
        permission.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        permission.show();
    }

    public void dialogClose(View view) {
        permission.dismiss();
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