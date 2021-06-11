package com.example.sos_parp.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.sos_parp.HospitalActivity;
import com.example.sos_parp.R;
import com.example.sos_parp.T_EarthquakeActivity;
import com.example.sos_parp.T_FireActivity;
import com.example.sos_parp.T_FirstAid;
import com.example.sos_parp.T_FloodActivity;
import com.example.sos_parp.T_HarmActivity;
import com.example.sos_parp.T_MedicalActivity;
import com.example.sos_parp.T_VehicleActivity;
import com.example.sos_parp.T_WomenActivity;

import org.jetbrains.annotations.NotNull;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    CardView card1,card2,card3,card4,card5,card6,card7,card8;

    @Override
    public void onViewCreated(@NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        card1 = (CardView)getView().findViewById(R.id.t_c1);
        card2 = (CardView)getView().findViewById(R.id.t_c2);
        card3 = (CardView)getView().findViewById(R.id.t_c3);
        card4 = (CardView)getView().findViewById(R.id.t_c4);
        card5 = (CardView)getView().findViewById(R.id.t_c5);
        card6 = (CardView)getView().findViewById(R.id.t_c6);
        card7 = (CardView)getView().findViewById(R.id.t_c7);
        card8 = (CardView)getView().findViewById(R.id.t_c8);

        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), T_VehicleActivity.class);
                getActivity().startActivity(intent);

            }

        });

        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), T_WomenActivity.class);
                getActivity().startActivity(intent);
            }

        });

        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), T_FireActivity.class);
                getActivity().startActivity(intent);
            }

        });

        card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), T_HarmActivity.class);
                getActivity().startActivity(intent);
            }

        });

        card5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), T_MedicalActivity.class);
                getActivity().startActivity(intent);
            }

        });

        card6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), T_EarthquakeActivity.class);
                getActivity().startActivity(intent);
            }

        });

        card7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), T_FloodActivity.class);
                getActivity().startActivity(intent);
            }

        });

        card8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), T_FirstAid.class);
                getActivity().startActivity(intent);
            }

        });


    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
            }
        });
        return root;
    }

}