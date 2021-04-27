package com.que.mytutor.fragments;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.que.mytutor.R;
import com.que.mytutor.adapters.AnnouncementAdapter;
import com.que.mytutor.model.Announcement;

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
        recycler_announcement.setLayoutManager(new LinearLayoutManager(view.getContext()));
        List<Announcement> Items = new ArrayList<>();


        AnnouncementAdapter adapter = new AnnouncementAdapter(Items);
        recycler_announcement.setAdapter(adapter);

        FirebaseFirestore.getInstance()
                .collection("Announcements")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if(value != null && !value.isEmpty())
                        {
                            for (DocumentChange dc : value.getDocumentChanges())
                            {
                                switch (dc.getType()) {
                                    case ADDED:
                                        Announcement item = dc.getDocument().toObject(Announcement.class);
                                        item.setId(dc.getDocument().getId());
                                        Items.add(item);

                                        adapter.notifyDataSetChanged();
                                        break;
                                    case MODIFIED:
                                        break;
                                    case REMOVED:
                                        if(Items.removeIf(t -> t.getId().contains(dc.getDocument().getId())))
                                        {
                                            adapter.notifyDataSetChanged();
                                        }
                                        break;
                                    default:
                                }
                            }

                        }
                    }
                });




    }


}