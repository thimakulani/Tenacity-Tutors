package com.que.mytutor.dialogs;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.PopupMenu;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.que.mytutor.R;
import com.que.mytutor.model.SubjectModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AddAppointmentFragment extends DialogFragment {



    public AddAppointmentFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
Context ctx;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_appointment, container, false);
        ConnectView(view);

        return view;
    }
    List<SubjectModel> Items = new ArrayList<>();
    private void ConnectView(View view)
    {
        MaterialButton btn_date_picker = view.findViewById(R.id.btn_date_picker);
        MaterialButton btn_time_picker = view.findViewById(R.id.btn_time_picker);
        MaterialButton btn_subject_picker = view.findViewById(R.id.btn_subject_picker);

        btn_subject_picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu menu = new PopupMenu(view.getContext(), btn_subject_picker);


                FirebaseFirestore.getInstance().collection("Subjects")
                        .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if(!queryDocumentSnapshots.isEmpty()){
                            Items = queryDocumentSnapshots.toObjects(SubjectModel.class);
                        }
                    }
                });
                int i = 0;
                for (SubjectModel s : Items){
                    menu.getMenu().add(Menu.FIRST, 1, i, s.getSubject());
                }
                menu.show();
                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        btn_subject_picker.setText(item.getTitle());
                        return true;
                    }
                });


            }
        });

        btn_time_picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btn_date_picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    @Override
    public void onStart() {
        super.onStart();
        Objects.requireNonNull(getDialog()).getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth)
    {


    }
}