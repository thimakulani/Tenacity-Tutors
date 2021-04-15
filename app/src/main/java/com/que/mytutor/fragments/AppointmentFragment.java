package com.que.mytutor.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.que.mytutor.R;
import com.que.mytutor.adapters.AppointmentAdapter;
import com.que.mytutor.dialogs.AddAppointmentFragment;
import com.que.mytutor.model.Appointment;

import java.util.ArrayList;
import java.util.List;


public class AppointmentFragment extends Fragment {


    public AppointmentFragment() {
        // Required empty public constructor
    }



    List<Appointment> Items = new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_appointment, container, false);
        ConnectViews(view);

        return view;
    }

    private void ConnectViews(View view) {
        RecyclerView recycler = (RecyclerView)view.findViewById(R.id.RecyclerAppointment);

        FloatingActionButton fab_add_appointment = view.findViewById(R.id.fab_add_appointment);

        fab_add_appointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AddAppointmentFragment app = new AddAppointmentFragment();
                app.setCancelable(false);
                app.show(getChildFragmentManager(), "Appointment");

            }
        });

        recycler.setLayoutManager(new LinearLayoutManager(view.getContext()));
        AppointmentAdapter adapter = new AppointmentAdapter(Items);
        recycler.setAdapter(adapter);
    }
}