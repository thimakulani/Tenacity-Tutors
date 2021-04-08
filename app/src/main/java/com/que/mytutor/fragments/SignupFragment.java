package com.que.mytutor.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.que.mytutor.R;
import com.que.mytutor.interfaces.FragmentClickInterface;
import com.que.mytutor.model.StudModel;

import java.util.Objects;


public class SignupFragment extends Fragment {

    public String TAG = ":SIGN UP FRAGMENT";
    private final FragmentClickInterface clickInterface;

    public SignupFragment(FragmentClickInterface clickInterface){
        this.clickInterface = clickInterface;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_signup, container, false);
        ConnectViews(view);
        return view;
    }
    private void ConnectViews(View view) {
        MaterialButton btnBackToLogin = view.findViewById(R.id.sign_up_btn_login);
        MaterialButton btnRegister =  view.findViewById(R.id.sign_up_btn_register);

        TextInputEditText InputName = view.findViewById(R.id.sign_up_input_names);
        TextInputEditText InputLastname = view.findViewById(R.id.sign_up_input_lastname);
        TextInputEditText InputEmail =  view.findViewById(R.id.sign_up_input_email);
        TextInputEditText InputPhone =  view.findViewById(R.id.sign_up_input_phone_number);
        TextInputEditText InputPassword =  view.findViewById(R.id.sign_up_input_password);

        btnRegister.setOnClickListener(v -> {
            boolean check = false;
            if (InputName.getText().toString().trim().isEmpty()) {
                InputName.setError("Cannot be empty");
                check = true;
            }
            if (InputLastname.getText().toString().trim().isEmpty()) {
                InputLastname.setError("Cannot be empty");
            }
            if (Objects.requireNonNull(InputEmail.getText()).toString().trim().isEmpty()) {
                InputEmail.setError("Cannot be empty");
                check = true;
            }
            if (InputPassword.getText().toString().trim().isEmpty()) {
                InputPassword.setError("Cannot be empty");
                check = true;
            }

            if (!check){
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(InputEmail.getText().toString().trim(), InputPassword.getText().toString().trim())
                        .addOnSuccessListener(authResult -> {

                            StudModel studModel = new StudModel(InputName.getText().toString(), InputLastname.getText().toString(),
                                    InputEmail.getText().toString(), InputPhone.getText().toString());
                            FirebaseFirestore.getInstance()
                                    .collection("Students")
                                    .add(studModel);
                        });
            }
        });

        btnBackToLogin.setOnClickListener(v -> clickInterface.LoginClick());
    }

}