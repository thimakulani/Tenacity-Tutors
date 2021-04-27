package com.que.mytutor.fragments;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.que.mytutor.R;
import com.que.mytutor.adapters.AppointmentAdapter;
import com.que.mytutor.dialogs.AddAppointmentFragment;
import com.que.mytutor.model.AppointModel;
import com.que.mytutor.model.Appointment;

import java.util.ArrayList;
import java.util.List;


public class AppointmentFragment extends Fragment {


    public AppointmentFragment() {
        // Required empty public constructor
    }



    List<AppointModel> Items = new ArrayList<>();
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
                app.show(getChildFragmentManager(), "Appointments");

            }
        });

        recycler.setLayoutManager(new LinearLayoutManager(view.getContext()));
        AppointmentAdapter adapter = new AppointmentAdapter(Items);
        recycler.setAdapter(adapter);
        try {


            FirebaseFirestore.getInstance().collection("Appointments")
                    .whereEqualTo("stud_id", FirebaseAuth.getInstance().getUid())
                    .orderBy("time_stamp", Query.Direction.ASCENDING)
                    .addSnapshotListener(new EventListener<QuerySnapshot>() {
                        @RequiresApi(api = Build.VERSION_CODES.N)
                        @Override
                        public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                            if (error != null) {
                                Toast.makeText(view.getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                            if (value != null) {
                                for (DocumentChange dc : value.getDocumentChanges()) {
                                    switch (dc.getType()) {
                                        case ADDED:
                                            AppointModel app = dc.getDocument().toObject(AppointModel.class);
                                            app.setId(dc.getDocument().getId());
                                            Items.add(app);
                                            adapter.notifyDataSetChanged();
                                            break;
                                        case MODIFIED:
                                            try {

                                                for (AppointModel a : Items)
                                                {
                                                    AppointModel doc = dc.getDocument().toObject(AppointModel.class);
                                                    doc.setId(dc.getDocument().getId());
                                                    if (a.getId().contains(doc.getId())) {
                                                        a = dc.getDocument().toObject(AppointModel.class);
                                                        adapter.notifyDataSetChanged();
                                                        break;
                                                    }
                                                }
                                            }
                                            catch (Exception ex){
                                                Toast.makeText(view.getContext(), "Try::::::::::::::" + ex.getMessage(), Toast.LENGTH_SHORT).show();
                                                Log.d("error:::", ex.getMessage());
                                            }

                                            break;
                                        case REMOVED:
                                            break;
                                    }
                                }
                            }
                        }
                    });
        }
        catch (Exception ex){
            Toast.makeText(view.getContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
        }



    }
    private void selectSubjectDialog(){

    }
}