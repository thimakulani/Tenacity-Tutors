package com.que.mytutor.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.que.mytutor.R;
import com.que.mytutor.adapters.AppointmentAdapter;
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
        Items.add(new Appointment("XCVBNM","XCVBNM","2020/02/13","Approved"));
        Items.add(new Appointment("XCVBNM","XCVBNM","2020/02/13","Approved"));
        Items.add(new Appointment("XCVBNM","XCVBNM","2020/02/13","Approved"));

        recycler.setLayoutManager(new LinearLayoutManager(view.getContext()));
        AppointmentAdapter adapter = new AppointmentAdapter(Items);
        recycler.setAdapter(adapter);
    }
}