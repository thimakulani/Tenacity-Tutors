package com.que.mytutor.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.que.mytutor.R;


public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen_layout);
        TextView appName = findViewById(R.id.txtAppName);
        TextView developed = findViewById(R.id.txtDevelopedBy);
        ImageView ImageAppLogo = findViewById(R.id.ImageAppLogo);
        developed.setVisibility(View.GONE);
        ImageAppLogo.setVisibility(View.GONE);


        developed.setText(R.string.txt_developed_by);


        Animation slideUp = AnimationUtils.loadAnimation(this, R.anim.float_up);
        Animation slideUp2 = AnimationUtils.loadAnimation(this, R.anim.float_up);
        Animation slideUp3 = AnimationUtils.loadAnimation(this, R.anim.float_up);
        appName.startAnimation(slideUp);
        slideUp.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                developed.setVisibility(View.VISIBLE);
                ImageAppLogo.setVisibility(View.VISIBLE);
                developed.startAnimation(slideUp3);
                ImageAppLogo.startAnimation(slideUp2);
                slideUp3.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
//                        LoadingDialogFragment loadingDialogFragment = new LoadingDialogFragment("Authenticating...");
//                        FragmentTransaction ft = getSupportFragmentManager()
//                                .beginTransaction();
//                        loadingDialogFragment.setCancelable(false);
//                        loadingDialogFragment.show(ft, "Loading");
                        new Handler().postDelayed(() -> {
                            Intent intent;
                            if(FirebaseAuth.getInstance().getCurrentUser() == null){
                                intent = new Intent(getApplicationContext(), LoginSignupActivity.class);
                            }
                            else{
                                intent = new Intent(getApplicationContext(), HomePage.class);
                            }
                            startActivity(intent);
                            overridePendingTransition(R.anim.slide_up, R.anim.slide_right);
                            finish();


                        }, 3000);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();


    }
}