package com.que.mytutor.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
    private final List<Mentors> Items = new ArrayList<>();
    private void ConnectViews(View view) {
        RecyclerView recycler_tutors = (RecyclerView) view.findViewById(R.id.RecyclerMentors);
        RecyclerView recycler_announcement = (RecyclerView) view.findViewById(R.id.RecyclerAnnouncement);
        SearchView SearchMentor = (SearchView) view.findViewById(R.id.SearchMentors);
        ArrayList<Mentors> Items = new ArrayList<>();



        for (int i = 0; i < 20; i++){
            Items.add(new Mentors("Thima" + i, "Sigauque" +1, "1","Male", "https://picsum.photos/id/237/200/300","Desc"));
        }

        MentorsAdapter adapter = new MentorsAdapter(Items, getChildFragmentManager());
        recycler_tutors.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recycler_tutors.setAdapter(adapter);

        List<Announcement> announce = new ArrayList<>();
        announce.add(new Announcement("2020/02/15", "Welcome to " + getString(R.string.app_name)));
        announce.add(new Announcement("2020/02/15", "Welcome to " + getString(R.string.app_name)));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recycler_announcement.setLayoutManager(linearLayoutManager);
        AnnouncementAdapter announce_adapter = new AnnouncementAdapter(announce);
        recycler_announcement.setAdapter(announce_adapter);


    }


}