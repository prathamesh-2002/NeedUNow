
package com.example.sos_parp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Intro extends AppCompatActivity {
    private ViewPager viewPager;
    private SliderAdapter sliderAdapter;
    private TextView[] dots;
    private Button b2;
    private int current;
    SharedPreferences s1;
    SharedPreferences.Editor editor;
    private LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        viewPager=(ViewPager)findViewById(R.id.v1);
        linearLayout=(LinearLayout)findViewById(R.id.l1);
        s1=getApplicationContext().getSharedPreferences("Intro", Context.MODE_PRIVATE);
        editor= s1.edit();
        if(s1!=null){
            boolean checkshared=s1.getBoolean("check",false);
            if(checkshared==true){
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
        }

        b2=(Button)findViewById(R.id.next);
        sliderAdapter=new SliderAdapter(this);
        viewPager.setAdapter(sliderAdapter);
        addDots(0);
        viewPager.addOnPageChangeListener(viewL);

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                editor.putBoolean("check",true).commit();
            }
        });
    }
    public void addDots(int position){
        dots=new TextView[4];
        linearLayout.removeAllViews();
        for(int i=0;i<dots.length;i++){
            dots[i]=new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.darkGrey));
            linearLayout.addView(dots[i]);
        }
        if(dots.length>0){
            dots[position].setTextColor(getResources().getColor(R.color.white));
        }
    }
    ViewPager.OnPageChangeListener viewL=new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDots(position);

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

}