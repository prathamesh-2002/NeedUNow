package com.example.sos_parp.ui.home;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
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
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.sos_parp.DBhandler;
import com.example.sos_parp.EditContacts;
import com.example.sos_parp.HospitalActivity;
import com.example.sos_parp.R;
import com.example.sos_parp.SettingsActivity;
import com.example.sos_parp.TimerActivity;

import static android.content.Context.VIBRATOR_SERVICE;

public class HomeFragment extends Fragment{

    private HomeViewModel homeViewModel;
    TimerActivity timerActivity = new TimerActivity();
    private Vibrator myVib;
    DBhandler dBhandler;
    Cursor c;
    Button sButton;
    AlertDialog.Builder alt_bld;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        myVib = (Vibrator) this.getContext().getSystemService(VIBRATOR_SERVICE);
        sButton=(Button)getView().findViewById(R.id.button);
        alt_bld = new AlertDialog.Builder(getActivity(), R.style.Theme_AlertDialog_SOS);

        alt_bld.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alt_bld.setNegativeButton("ADD CONTACTS", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(getActivity(), EditContacts.class);
                startActivity(intent);
            }
        });
        dBhandler = new DBhandler(getActivity());
        c = dBhandler.getContacts();
        c.moveToFirst();

        sButton.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                c = dBhandler.getContacts();
                if (c.getCount() > 0) {
                    sos();
                    myVib.vibrate(100);
                    return true;
                }
                else {
                    myVib.vibrate(100);
                    alt_bld.setTitle("Contacts not added");
                    alt_bld.setMessage("Setup emergency contacts to send alert messages");
                    alt_bld.show();
                    return false;
                }
            }
            private void sos(){
                Intent intent = new Intent(getActivity(), TimerActivity.class);
                getActivity().startActivity(intent);
            }
        });
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