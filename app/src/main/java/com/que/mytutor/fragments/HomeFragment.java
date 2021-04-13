package com.que.mytutor.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.que.mytutor.R;
import com.que.mytutor.adapters.AnnouncementAdapter;
import com.que.mytutor.adapters.MentorsAdapter;
import com.que.mytutor.model.Announcement;
import com.que.mytutor.model.Mentors;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {



    public HomeFragment() {
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
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ConnectViews(view);
        return view;
    }
    private void ConnectViews(View view) {
        RecyclerView recycler_announcement = (RecyclerView) view.findViewById(R.id.RecyclerAnnouncement);

        List<Announcement> Items = new ArrayList<>();

        FirebaseFirestore.getInstance()
                .collection("Announcements")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if(value != null && !value.isEmpty())
                        {
                            for (DocumentChange item : value.getDocumentChanges()){
                                if(item.getType() == DocumentChange.Type.ADDED){
                                    Items.add(item.getDocument().toObject(Announcement.class));

                                }

                            }
                        }
                    }
                });
        AnnouncementAdapter announce_adapter = new AnnouncementAdapter(Items);
        recycler_announcement.setAdapter(announce_adapter);
        announce_adapter.notifyDataSetChanged();


    }


}