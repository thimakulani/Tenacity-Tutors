package com.que.mytutor.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.que.mytutor.R;


public class MessagingFragment extends Fragment {



    public MessagingFragment() {
        // Required empty public constructor
    }
    String id;
    public MessagingFragment(String id) {
        this.id = id;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    private MaterialToolbar chats_toolbar;
    private RecyclerView RecyclerMessages;
    private FloatingActionButton FabSend;
    private TextInputEditText InputMessage;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_messageing, container, false);
        ConnectViews(view);
        return view;
    }

    private void ConnectViews(View view) {
        RecyclerMessages  = (RecyclerView)view.findViewById(R.id.RecyclerMessages);
        FabSend  = (FloatingActionButton)view.findViewById(R.id.FabSendMessage);
        InputMessage  = (TextInputEditText)view.findViewById(R.id.InputMessage);

        FabSend.setOnClickListener(v -> {
            if(!InputMessage.getText().toString().isEmpty())
            {

            }
            else{
                Toast.makeText(view.getContext(), "Cannot send an empty message", Toast.LENGTH_SHORT).show();
            }
        });

    }
}