package com.example.sos_parp.ui.home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.sos_parp.HospitalActivity;
import com.example.sos_parp.R;
import com.example.sos_parp.SettingsActivity;
import com.example.sos_parp.TimerActivity;

import static android.content.Context.VIBRATOR_SERVICE;

public class HomeFragment extends Fragment{

    private HomeViewModel homeViewModel;
    TimerActivity timerActivity = new TimerActivity();
    private Vibrator myVib;
    Button sButton;
    long then = 0;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        myVib = (Vibrator) this.getContext().getSystemService(VIBRATOR_SERVICE);
        sButton=(Button)getView().findViewById(R.id.button);

        sButton.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                sos();
                myVib.vibrate(100);
                return false;
            }
            private void sos(){
                Intent intent = new Intent(getActivity(), TimerActivity.class);
                getActivity().startActivity(intent);
            }
        });
/*
        sButton.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    then = (Long) System.currentTimeMillis();
                }
                else if (((Long) System.currentTimeMillis() - then) > 3000){
                    sos();
                    return false;
                }

                return false;
            }
            private void sos(){
                Intent intent = new Intent(getActivity(), TimerActivity.class);
                getActivity().startActivity(intent);
            }
        });

    /*    sButton.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {

                sos();
                return false;
            }

            private void sos(){
                Intent intent = new Intent(getActivity(), TimerActivity.class);
                getActivity().startActivity(intent);
            }
        });

     */

  /*      sButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sos();
            }

        });*/
    }

    public View onCreateView (@NonNull LayoutInflater inflater,
                              ViewGroup container, Bundle savedInstanceState){
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
            }
        });
        return root;

    }

}