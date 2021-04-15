package com.que.mytutor.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.que.mytutor.R;
import com.que.mytutor.adapters.ChatListAdapter;
import com.que.mytutor.model.ChatList;

import java.util.ArrayList;
import java.util.List;


public class ChatFragment extends Fragment {


    public ChatFragment() {
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
        View view = inflater.inflate(R.layout.fragment_chat, container, false);
        ConnectViews(view);
        return view;
    }
    private List<ChatList> Items = new ArrayList<>();
    private void ConnectViews(View view) {
        RecyclerView recycler = (RecyclerView)view.findViewById(R.id.recycler_chats_list);

        FirebaseDatabase.getInstance()
                .getReference()
                .child("Chats")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


        recycler.setLayoutManager(new LinearLayoutManager(view.getContext()));
        ChatListAdapter adapter = new ChatListAdapter(Items, getChildFragmentManager());
        recycler.setAdapter(adapter);
    }
}