package com.que.mytutor.dialogs;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ListAdapter;
import android.widget.PopupMenu;
import android.widget.TimePicker;
import android.widget.Toast;

import com.cazaea.sweetalert.SweetAlertDialog;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.que.mytutor.R;
import com.que.mytutor.model.Appointment;
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
                if(btn_date_picker.getText().toString().contains("DATE")){
                    btn_date_picker.setError("Select dates");
                    return;
                }
                if(btn_time_picker.getText().toString().contains("DATE")){
                    btn_time_picker.setError("Select time");
                    return;
                }
                Appointment data = new Appointment("-", null, btn_time_picker.getText().toString(),
                        btn_date_picker.getText().toString(), FirebaseAuth.getInstance().getUid(), "Request",
                        btn_subject_picker.getText().toString());

                FirebaseFirestore.getInstance()
                        .collection("Appointments")
                        .add(data).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
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

                                if(!queryDocumentSnapshots.isEmpty()) {
                                    try{
                                        Items.addAll(queryDocumentSnapshots.toObjects(SubjectModel.class));
                                        List<String> arr = new ArrayList<>();

                                        for (SubjectModel s : Items) {
                                            arr.add(s.getSubject());

                                        }
                                        SetDiaogSubjects(arr);
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

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();


            }
        });
    }
    private void SetDiaogSubjects(List<String> items)
    {

        try {
            final int[] checkedItem = {-1};
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(ctx);
            alertDialog.setIcon(R.drawable.tenacity);
            // title of the alert dialog
            alertDialog.setTitle("Choose an Item");
            alertDialog.setSingleChoiceItems((ListAdapter) items, checkedItem[0], new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    checkedItem[0] = which;
                    btn_subject_picker.setText(items.get(which));
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

    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth)
    {


    }
}