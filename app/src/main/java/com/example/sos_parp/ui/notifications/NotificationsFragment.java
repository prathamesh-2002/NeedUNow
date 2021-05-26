package com.example.sos_parp.ui.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.sos_parp.CarrrrActivity;
import com.example.sos_parp.ChildActivity;
import com.example.sos_parp.FireeActivity;
import com.example.sos_parp.HospitalActivity;
import com.example.sos_parp.PoliceActivity;
import com.example.sos_parp.R;
import com.example.sos_parp.WomennnActivity;

import org.jetbrains.annotations.NotNull;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;
    CardView card1,card2,card3,card4,card5,card6;
    @Override
    public void onViewCreated(@NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        card1=(CardView)getView().findViewById(R.id.c1);
        card2=(CardView)getView().findViewById(R.id.c2);
        card3=(CardView)getView().findViewById(R.id.c3);
        card4=(CardView)getView().findViewById(R.id.c4);
        card5=(CardView)getView().findViewById(R.id.c5);
        card6=(CardView)getView().findViewById(R.id.c6);
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