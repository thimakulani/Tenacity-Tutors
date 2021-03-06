package com.que.mytutor.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.que.mytutor.R;
import com.que.mytutor.model.AppointModel;
import com.que.mytutor.model.TutorsModel;

import java.util.List;

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentViewHolder>{
    private final List<AppointModel> Items;

    public AppointmentAdapter(List<AppointModel> items) {
        Items = items;
    }

    @NonNull
    @Override
    public AppointmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_appointment, parent, false);

        return new AppointmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentViewHolder holder, int position) {
        try{

            holder.row_app_date_time.setText(String.format("%s %s", Items.get(position).getDate(), Items.get(position).getDate()));
            holder.row_app_status.setText(Items.get(position).getStatus());
            holder.row_app_grade.setText(Items.get(position).getGrade());
            if( Items.get(position).getTutor_id() == null){
                holder.row_app_name.setVisibility(View.GONE);
            }
            else{
                FirebaseFirestore.getInstance()
                        .collection("Tutors")
                        .document(Items.get(position).getTutor_id())
                        .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                            @Override
                            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                                if(value != null && value.exists()){
                                    TutorsModel m = value.toObject(TutorsModel.class);
                                    holder.row_app_name.setText(String.format("%s %s", m.getName(), m.getSurname()));
                                    holder.row_app_name.setVisibility(View.VISIBLE);
                                }
                            }
                        });
            }
            if(!Items.get(position).getStatus().equals("Request")){
                holder.row_app_btn_cancel.setVisibility(View.GONE);
            }
            else{
                holder.row_app_btn_cancel.setVisibility(View.VISIBLE);
            }
        }
        catch (Exception ex){
            Toast.makeText(holder.itemView.getContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
        holder.row_app_btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               try {


                   FirebaseFirestore.getInstance()
                           .collection("Appointments")
                           .document(Items.get(position).getId())
                           .update("status", "Cancelled");
               }
               catch (Exception ex){
                   Toast.makeText(holder.itemView.getContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
               }

            }
        });
    }

    @Override
    public int getItemCount() {
        return Items.size();
    }
}
class AppointmentViewHolder extends RecyclerView.ViewHolder{
    public MaterialTextView row_app_name;
    public MaterialTextView row_app_grade;
    public MaterialTextView row_app_date_time;
    public MaterialTextView row_app_status;
    public MaterialButton row_app_btn_cancel;
    public AppointmentViewHolder(@NonNull View itemView) {
        super(itemView);
        row_app_grade = (MaterialTextView)itemView.findViewById(R.id.row_app_grade);
        row_app_name = (MaterialTextView)itemView.findViewById(R.id.row_app_name);
        row_app_date_time = (MaterialTextView)itemView.findViewById(R.id.row_app_date_time);
        row_app_status = (MaterialTextView)itemView.findViewById(R.id.row_app_status);
        row_app_btn_cancel = (MaterialButton)itemView.findViewById(R.id.row_app_cancel);
    }
}
