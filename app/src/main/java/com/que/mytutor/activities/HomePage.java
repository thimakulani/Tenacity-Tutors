package com.que.mytutor.activities;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.que.mytutor.R;
import com.que.mytutor.fragments.AppointmentFragment;
import com.que.mytutor.fragments.HomeFragment;
import com.que.mytutor.fragments.MessagingFragment;
import com.que.mytutor.fragments.ProfileFragment;

public class HomePage extends AppCompatActivity {

    MaterialToolbar tool_bar;

    BottomNavigationView nav_menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.host_frag, new HomeFragment())
                    .commit();

        }

        ConnectViews();
    }
    int selected = 0;
    private void ConnectViews()
    {
        tool_bar = (MaterialToolbar)findViewById(R.id.tool_bar);
        nav_menu = (BottomNavigationView)findViewById(R.id.bottom_nav_menu);
        tool_bar.setTitle("Home");
        nav_menu.setOnNavigationItemSelectedListener(item -> {
            if(item.getItemId() == R.id.bottom_nav_home && selected != R.id.bottom_nav_home){

                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.host_frag, new HomeFragment())
                        .commit();
                selected = R.id.bottom_nav_home;
                tool_bar.setTitle("Home");
            }
            if(item.getItemId() == R.id.nav_profile && selected != R.id.nav_profile){
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.host_frag, new ProfileFragment())
                        .commit();
                tool_bar.setTitle("Profile");
                selected = R.id.nav_profile;
            }
            if(item.getItemId() == R.id.nav_appointments && selected != R.id.nav_appointments){
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.host_frag, new AppointmentFragment())
                        .commit();
                tool_bar.setTitle("Appointment");
                selected = R.id.nav_appointments;
            }
            if(item.getItemId() == R.id.nav_messages && selected != R.id.nav_messages){
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.host_frag, new MessagingFragment())
                        .commit();
                tool_bar.setTitle("Chat With Admin");
                selected = R.id.nav_messages;
            }
            if(item.getItemId() == R.id.nav_logout){
                FirebaseAuth.getInstance().signOut();
                finish();
            }

            return true;
        });

    }


}