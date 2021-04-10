package com.que.mytutor.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.auth.FirebaseAuth;
import com.que.mytutor.R;
import com.que.mytutor.activities.HomePage;
import com.que.mytutor.dialogs.LoadingDialogFragment;
import com.que.mytutor.interfaces.FragmentClickInterface;


public class LoginFragment extends Fragment {

    private TextInputEditText InputEmail;
    private TextInputEditText InputPassword;
    private Context context;
    public String TAG = "LOGIN FRAGMENT";
    public LoginFragment(FragmentClickInterface clickInterface) {
        // Required empty public constructor
        this.clickInterface = clickInterface;
    }


    private final FragmentClickInterface clickInterface;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_login, container, false);
        ConnectViews(view);
        context = view.getContext();
        return view;
    }


    private void ConnectViews(View view)
    {
        try {
            MaterialTextView btnSignup = view.findViewById(R.id.login_btn_sign_up);
            MaterialButton BtnLogin = view.findViewById(R.id.login_btn_login);
            MaterialButton BtnForgotPassword = view.findViewById(R.id.btn_forgot_password);
            InputEmail = view.findViewById(R.id.login_input_email);
            InputPassword = view.findViewById(R.id.login_input_password);


            BtnLogin.setOnClickListener(v -> {
                if(InputEmail.getText().toString().trim().isEmpty()){
                    InputEmail.setError("Email cannot be empty");
                    return;
                }
                if(InputPassword.getText().toString().trim().isEmpty()){
                    InputPassword.setError("Password cannot be empty");
                    return;
                }
                LoadingDialogFragment loading = new LoadingDialogFragment("Logging in");
                loading.show(getChildFragmentManager().beginTransaction(), "Loading");
                FirebaseAuth.getInstance()
                        .signInWithEmailAndPassword(InputEmail.getText().toString().trim(), InputPassword.getText().toString().trim())
                        .addOnSuccessListener(authResult -> {
                            Intent intent = new Intent(context, HomePage.class);
                            startActivity(intent);
                        }).addOnFailureListener(e -> {

                        }).addOnCompleteListener(task -> loading.dismiss());


            });
            btnSignup.setOnClickListener(v -> clickInterface.BtnSignUpClick());

            BtnForgotPassword.setOnClickListener(v -> {

            });

        }
        catch (Exception ex){
            Toast.makeText(context, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }




    }

}