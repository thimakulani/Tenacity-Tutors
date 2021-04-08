package com.que.mytutor.dialogs;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.que.mytutor.R;

public class MentorProfileDialog extends DialogFragment {

    public MentorProfileDialog(String id) {
        this.id = id;
    }

    private String id;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_FRAME, R.style.FullScreenDialogStyle);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.dialog_mentor_profile_dialog, container, false);

        ConnectViews(view);
        return view;
    }

    private void ConnectViews(View view) {

    }
}