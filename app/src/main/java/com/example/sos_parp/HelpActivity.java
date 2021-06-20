package com.example.sos_parp;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class HelpActivity extends AppCompatActivity {

    Dialog permission;
    Button p1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        permission = new Dialog(this);
        p1 = (Button) findViewById(R.id.permissions);

        p1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pDialog(v);
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

    public void goToHomePage(View view) {
        finish();
    }
}
