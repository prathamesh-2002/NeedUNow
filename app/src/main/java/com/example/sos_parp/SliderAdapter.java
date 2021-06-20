package com.example.sos_parp;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import org.jetbrains.annotations.NotNull;

public class SliderAdapter extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;
    public SliderAdapter(Context context){

        this.context=context;
    }
    public int[] slide_image={
            R.drawable.logo1,
            R.drawable.groupon,
            R.drawable.disconnect,
            R.drawable.tipsonbg
    };
    public String[] slide_heading={
            "NeedUNow",
            "Alert your Companions",
            "Works Offline",
            "Life Saving Tips"

    };
    public String[] slide_des={
            "NeedUNow is your one tap life saver. Feel secure about your friends/family with NeedUNow",
            "Tackle any sticky situation just on the press of one button and send an alert message to your friends/family.",
            "NeedUNow uses the traditional messaging method and so works even offline. The Panic Message along with you current location will be sent to your Emergency Contacts.",
            "Find a guide for all Emergency Situation to better understand 'What to do When?'. Also, have important helpline numbers just at your fingertips."
    };
    @Override
    public int getCount() {
        return slide_heading.length;
    }

    @Override
    public boolean isViewFromObject( View view, Object object) {
        return view == (RelativeLayout) object;
    }


    @Override
    public Object instantiateItem( ViewGroup container, int position) {
        layoutInflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.slides,container,false);
        ImageView slideimg=(ImageView)view.findViewById(R.id.slideimg);
        TextView slidedis=(TextView)view.findViewById(R.id.slide);
        TextView slidehead=(TextView)view.findViewById(R.id.slidehead);
        slideimg.setImageResource(slide_image[position]);
        slidehead.setText(slide_heading[position]);
        slidedis.setText(slide_des[position]);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem( ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout)object);
    }

}