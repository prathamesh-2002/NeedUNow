package com.example.sos_parp;

import android.content.Context;
import android.content.SharedPreferences;


public class SharedPref {
    SharedPreferences mySharedPref,sharedPreferences ;
    public SharedPref(Context context) {
        mySharedPref = context.getSharedPreferences("filename",Context.MODE_PRIVATE);
        sharedPreferences=context.getSharedPreferences("filename",Context.MODE_PRIVATE);
    }

    public void setNightModeState(Boolean state) {
        SharedPreferences.Editor editor = mySharedPref.edit();
        editor.putBoolean("NightMode",state);
        editor.commit();
    }
    public void setDefaultmode(Boolean state) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("system",state);
        editor.commit();
    }

    public Boolean loadNightModeState (){
        Boolean state = mySharedPref.getBoolean("NightMode",false);
        return  state;
    }
    public Boolean loadDefaultmode (){
        Boolean state = sharedPreferences.getBoolean("system",true);
        return  state;
    }
}