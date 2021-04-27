package com.que.mytutor.dialogs;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.cazaea.sweetalert.SweetAlertDialog;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.que.mytutor.R;
import com.que.mytutor.model.SubjectModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class AddAppointmentFragment extends DialogFragment {



    public AddAppointmentFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    Context ctx;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_appointment, container, false);
        ctx = view.getContext();
        ConnectView(view);

        return view;
    }
    private final List<SubjectModel> Items = new ArrayList<>();
    private MaterialButton btn_subject_picker;
    private boolean subject = false, time  = false, date = false;
    private String subject_id = null;
    private void ConnectView(View view)
    {
        MaterialButton btn_date_picker = view.findViewById(R.id.btn_date_picker);
        MaterialButton btn_time_picker = view.findViewById(R.id.btn_time_picker);
         btn_subject_picker = view.findViewById(R.id.btn_subject_picker);
        MaterialButton btn_submit_appointment = view.findViewById(R.id.btn_submit_appointment);
        FloatingActionButton close_fab_app = view.findViewById(R.id.close_fab_app);




       btn_submit_appointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(!date)
                {
                    btn_date_picker.setError("Select dates");
                    return;
                }
                if(!time){
                    btn_time_picker.setError("Select time");
                    return;
                }
                if(!subject){
                    btn_subject_picker.setError("Select subject");
                    return;
                }


                HashMap<String, Object> map = new HashMap<>();
                map.put("mentor_id", null);
                map.put("id", null);
                map.put("time", btn_time_picker.getText().toString());
                map.put("date", btn_date_picker.getText().toString());
                map.put("stud_id", FirebaseAuth.getInstance().getUid());
                map.put("status", "Request");
                map.put("subject", btn_subject_picker.getText().toString());
                map.put("time_stamp", FieldValue.serverTimestamp());

                FirebaseFirestore.getInstance()
                        .collection("Appointments")
                        .add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference)
                    {
                        try {
                            HashMap<String, Object> data = new HashMap<>();

                            documentReference.update("id", documentReference.getId());


                            new SweetAlertDialog(view.getContext(), SweetAlertDialog.SUCCESS_TYPE)
                                    .setTitleText("Success!!")
                                    .setContentText("Successfully updated")
                                    .setConfirmText("Ok")
                                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog sweetAlertDialog) {

                                            sweetAlertDialog.dismissWithAnimation();
                                            dismiss();
                                        }

                                    })
                                    .show();
                        }
                        catch (Exception ex){
                            Toast.makeText(ctx, "", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        new SweetAlertDialog(view.getContext(), SweetAlertDialog.ERROR_TYPE)
                                .setTitleText("Success!!")
                                .setContentText("Successfully updated")
                                .setConfirmText("Ok")
                                .show();
                    }
                }).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        if(!task.isSuccessful())
                            dismiss();
                    }
                });



            }
        });



        close_fab_app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        btn_subject_picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                FirebaseFirestore.getInstance().collection("Subjects")
                        .get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                                //Toast.makeText(ctx, String.valueOf(queryDocumentSnapshots.size()), Toast.LENGTH_SHORT).show();
                                if(!queryDocumentSnapshots.isEmpty()) {
                                    try{
                                        Items.clear();
                                        Items.addAll(queryDocumentSnapshots.toObjects(SubjectModel.class));
                                        String[] arr = new String[Items.size()];
                                        int i = 0;
                                        for (SubjectModel s : Items) {
                                            arr[i] = s.getSubject();
                                            i++;
                                        }
                                        SetDiaogSubjects(arr, Items);
                                    }
                                    catch(Exception ex){
                                        Toast.makeText(ctx, ex.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        });
            }
        });

        btn_time_picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

        try {
            final Calendar c = Calendar.getInstance();
            int mHour = c.get(Calendar.HOUR_OF_DAY);
            int mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(view.getContext(),
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {
                            btn_time_picker.setText(String.format("%d:%d", hourOfDay, minute));
                            time =true;
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }catch(Exception ex){
            Toast.makeText(ctx, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }


            }
        });
        btn_date_picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(view.getContext(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                btn_date_picker.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                                date = true;
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();


            }
        });
    }
    private void SetDiaogSubjects(String[] arr, List<SubjectModel> items)
    {

        //Toast.makeText(ctx, arr[0], Toast.LENGTH_SHORT).show();
        try {
            final int[] checkedItem = {-1};

            AlertDialog.Builder alertDialog = new AlertDialog.Builder(ctx);
            alertDialog.setCancelable(false);
            alertDialog.setTitle("SELECT A SUBJECT");
            alertDialog.setIcon(R.drawable.tenacity);
            alertDialog.setSingleChoiceItems(arr, -1, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    btn_subject_picker.setText(arr[which]);
                    subject_id = items.get(which).getId();
                }
            });
            alertDialog.setPositiveButton("CONTINUE", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    subject = true;
                    dialog.dismiss();
                }
            });
            alertDialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    btn_subject_picker.setText("SELECT  SUBJECT");
                }
            });
            alertDialog.show();
        }
        catch (Exception ex)
        {
            Toast.makeText(ctx, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }
    @Override
    public void onStart() {
        super.onStart();
        Objects.requireNonNull(getDialog()).getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }


}