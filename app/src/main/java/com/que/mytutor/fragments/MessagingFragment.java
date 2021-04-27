package com.que.mytutor.fragments;

import android.content.ClipData;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.ServerTimestamp;
import com.google.firebase.firestore.SetOptions;
import com.que.mytutor.R;
import com.que.mytutor.adapters.MessagesAdapter;
import com.que.mytutor.model.MessagesModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Objects;


public class MessagingFragment extends Fragment {



    public MessagingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    private MaterialToolbar chats_toolbar;
    private TextInputEditText InputMessage;
    List<MessagesModel> Items = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_messageing, container, false);
        ConnectViews(view);
        return view;
    }

    private void ConnectViews(View view) {
        RecyclerView recycler = view.findViewById(R.id.RecyclerMessages);
        FloatingActionButton fabSend = view.findViewById(R.id.FabSendMessage);
        InputMessage  = view.findViewById(R.id.InputMessage);
        recycler.setLayoutManager(new LinearLayoutManager(view.getContext()));
        MessagesAdapter adapter = new MessagesAdapter(Items);
        recycler.setAdapter(adapter);

        fabSend.setOnClickListener(v -> {
            if(!InputMessage.getText().toString().isEmpty())
            {
                HashMap<String, Object> msg = new HashMap<>();
                msg.put("text", InputMessage.getText().toString().trim());
                msg.put("uid", FirebaseAuth.getInstance().getUid());
                msg.put("time_stamp", FieldValue.serverTimestamp());

                InputMessage.getText().clear();
                FirebaseFirestore.getInstance()
                        .collection("Chats")
                        .document(Objects.requireNonNull(FirebaseAuth.getInstance().getUid()))
                        .collection("Message")
                        .add(msg)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                InputMessage.getText().clear();

                                HashMap<String, Object> dates = new HashMap<>();
                                dates.put("Dates", FieldValue.serverTimestamp());
                                FirebaseFirestore.getInstance()
                                        .collection("Chats")
                                        .document(FirebaseAuth.getInstance().getUid())
                                        .set(dates);
                            }
                        });
            }
        });
        FirebaseFirestore.getInstance()
                .collection("Chats")
                .document(Objects.requireNonNull(FirebaseAuth.getInstance().getUid()))
                .collection("Message")
                .orderBy("time_stamp", Query.Direction.ASCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if(value != null){
                            for (DocumentChange dc: value.getDocumentChanges()){
                                switch (dc.getType()) {
                                    case ADDED:
                                        Items.add(dc.getDocument().toObject(MessagesModel.class));
                                        adapter.notifyDataSetChanged();
                                        break;
                                    case MODIFIED:
                                        break;
                                    case REMOVED:
                                        break;
                                }
                            }
                        }
                    }
                });
    }
}