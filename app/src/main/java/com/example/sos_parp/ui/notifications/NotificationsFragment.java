package com.example.sos_parp.ui.notifications;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.sos_parp.CarrrrActivity;
import com.example.sos_parp.ChildActivity;
import com.example.sos_parp.FireeActivity;
import com.example.sos_parp.HelpActivity;
import com.example.sos_parp.HospitalActivity;
import com.example.sos_parp.PoliceActivity;
import com.example.sos_parp.R;
import com.example.sos_parp.SettingsActivity;
import com.example.sos_parp.WomennnActivity;
import com.example.sos_parp.helplines;


import org.jetbrains.annotations.NotNull;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;
    CardView card1,card2,card3,card4,card5,card6;
    Button button1,button2;
    Dialog dialog;
    @Override
    public void onViewCreated(@NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        button1=(Button)getView().findViewById(R.id.call112) ;
        button2=(Button)getView().findViewById(R.id.button2) ;
        card1=(CardView)getView().findViewById(R.id.c1);
        card2=(CardView)getView().findViewById(R.id.c2);
        card3=(CardView)getView().findViewById(R.id.c3);
        card4=(CardView)getView().findViewById(R.id.c4);
        card5=(CardView)getView().findViewById(R.id.c5);
        card6=(CardView)getView().findViewById(R.id.c6);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = "112";
                Uri call = Uri.parse("tel:" + number);
                Intent intent = new Intent(Intent.ACTION_DIAL, call);
                startActivity(intent);
            }
        });
        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hosdial();
            }

            private void hosdial() {

                Intent intent = new Intent(getActivity(), HospitalActivity.class);
                getActivity().startActivity(intent);

            }
        });
        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                podial();
            }

            private void podial() {

                Intent intent = new Intent(getActivity(), PoliceActivity.class);
                getActivity().startActivity(intent);

            }
        });
        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chodial();
            }

            private void chodial() {

                Intent intent = new Intent(getActivity(), ChildActivity.class);
                getActivity().startActivity(intent);

            }
        });
        card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hosdial();
            }

            private void hosdial() {

                Intent intent = new Intent(getActivity(), FireeActivity.class);
                getActivity().startActivity(intent);

            }
        });
        card5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hosdial();
            }

            private void hosdial() {

                Intent intent = new Intent(getActivity(), CarrrrActivity.class);
                getActivity().startActivity(intent);

            }
        });
        card6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hosdial();
            }

            private void hosdial() {

                Intent intent = new Intent(getActivity(), WomennnActivity.class);
                getActivity().startActivity(intent);

            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {helplines();}

            private void helplines() {
                Intent intent = new Intent(getActivity(), helplines.class);
                getActivity().startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        switch (id) {

            case R.id.navigation_settings:
                Intent intent = new Intent(getActivity(), SettingsActivity.class);
                getActivity().startActivity(intent);

            case android.R.id.home:
                startActivity(new Intent(getActivity(), HelpActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
                 
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        setHasOptionsMenu(true);
        setMenuVisibility(true);
        notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        //final TextView textView = root.findViewById(R.id.text_notification);

        notificationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
            }
        });
        return root;
    }
}