package com.que.mytutor.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.que.mytutor.R;
import com.que.mytutor.fragments.LoginFragment;
import com.que.mytutor.fragments.SignupFragment;
import com.que.mytutor.interfaces.FragmentClickInterface;

public class LoginSignupActivity extends AppCompatActivity implements FragmentClickInterface {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_signup);
        try {
            LoginFragment frag = new LoginFragment(this);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.FragHost, frag)
                    .commit();
        }
        catch (Exception ex)
        {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void BtnSignUpClick() {
        SignupFragment frag = new SignupFragment(this);
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.right_in, R.anim.right_out)
                .replace(R.id.FragHost, frag, frag.TAG)
                .commit();
    }

    @Override
    public void LoginClick() {
        LoginFragment frag = new LoginFragment(this);
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.left_in, R.anim.left_out)
                .replace(R.id.FragHost, frag, frag.TAG)
                .commit();
    }



}