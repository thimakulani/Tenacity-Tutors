package com.que.mytutor.activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;
import com.que.mytutor.R;
import com.que.mytutor.dialogs.AboutDialogFragment;
import com.que.mytutor.fragments.AppointmentFragment;
import com.que.mytutor.fragments.HomeFragment;
import com.que.mytutor.fragments.MessagingFragment;
import com.que.mytutor.fragments.ProfileFragment;

public class HomePage extends AppCompatActivity {

    MaterialToolbar tool_bar;

    ChipNavigationBar nav_menu;
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
        tool_bar = findViewById(R.id.tool_bar);
        nav_menu = findViewById(R.id.bottom_nav_menu);
        tool_bar.setTitle("Home");
        tool_bar.setNavigationOnClickListener(v -> {
            AboutDialogFragment dlg = new AboutDialogFragment();
            dlg .show(getSupportFragmentManager().beginTransaction(), "About");
        });
        nav_menu.setOnItemSelectedListener(i -> {
            Toast.makeText(HomePage.this, String.valueOf(i), Toast.LENGTH_SHORT).show();
            if(i== R.id.bottom_nav_home){

                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.host_frag, new HomeFragment())
                        .commit();
                selected = R.id.bottom_nav_home;
                tool_bar.setTitle("Home");
            }
            if(i == R.id.nav_profile){
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.host_frag, new ProfileFragment())
                        .commit();
                tool_bar.setTitle("Profile");
                selected = R.id.nav_profile;
            }
            if(i == R.id.nav_appointments){
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.host_frag, new AppointmentFragment())
                        .commit();
                tool_bar.setTitle("Appointment");
                selected = R.id.nav_appointments;
            }
            if(i == R.id.nav_messages){
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.host_frag, new MessagingFragment())
                        .commit();
                tool_bar.setTitle("Chat With Admin");
                selected = R.id.nav_messages;
            }
            if(i==R.id.nav_logout){
                FirebaseAuth.getInstance().signOut();
                finish();
            }
        });


    }


}