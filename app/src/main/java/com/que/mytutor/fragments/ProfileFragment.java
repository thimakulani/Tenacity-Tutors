package com.que.mytutor.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.auth.User;
import com.que.mytutor.R;
import com.que.mytutor.model.StudModel;

import java.util.HashMap;


public class ProfileFragment extends Fragment {



    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ConnectView(view);
        return view;
    }

    private void ConnectView(View view) {
        TextInputEditText profile_input_email = view.findViewById(R.id.profile_input_email);
        TextInputEditText profile_input_name = view.findViewById(R.id.profile_input_name);
        TextInputEditText profile_input_surname = view.findViewById(R.id.profile_input_surname);
        TextInputEditText profile_input_phone = view.findViewById(R.id.profile_input_phone);
        MaterialButton BtnUpdate = view.findViewById(R.id.btn_update_profile);


        FirebaseFirestore.getInstance()
                .collection("Students")
                .document(FirebaseAuth.getInstance().getUid())
                .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                        if(value.exists()){
                            StudModel user = value.toObject(StudModel.class);
                            profile_input_name.setText(user.getName());
                            profile_input_surname.setText(user.getSurname());
                            profile_input_phone.setText(user.getPhone());
                            profile_input_email.setText(user.getEmail());
                        }
                    }
                });


        BtnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StudModel studModel = new StudModel(profile_input_name.getText().toString(), profile_input_surname.getText().toString(), profile_input_email.getText().toString(), profile_input_phone.getText().toString());
                HashMap<String, Object> data = new HashMap<>();
                data.put("name", profile_input_name.getText().toString().trim());
                data.put("surname", profile_input_surname.getText().toString().trim());
                data.put("phone", profile_input_phone.getText().toString().trim());
                data.put("email", profile_input_email.getText().toString().trim());
                FirebaseFirestore.getInstance().collection("Students")
                        .document(FirebaseAuth.getInstance().getUid())
                        .update(data);

            }
        });

    }
    private void ImgUploadPicker(){
        Intent intent = new Intent();

    }
}