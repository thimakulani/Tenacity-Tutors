package com.que.mytutor.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;
import com.que.mytutor.R;
import com.que.mytutor.model.Appointment;

import java.util.ArrayList;
import java.util.List;

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentViewHolder>{
    private List<Appointment> Items;

    public AppointmentAdapter(List<Appointment> items) {
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
        holder.row_app_date_time.setText(Items.get(position).getTimeDate());
        holder.row_app_status.setText(Items.get(position).getStatus());
        holder.row_app_name.setText("Name");
        holder.row_app_btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
    public MaterialTextView row_app_date_time;
    public MaterialTextView row_app_status;
    public MaterialButton row_app_btn_cancel;
    public AppointmentViewHolder(@NonNull View itemView) {
        super(itemView);
        row_app_name = (MaterialTextView)itemView.findViewById(R.id.row_app_name);
        row_app_date_time = (MaterialTextView)itemView.findViewById(R.id.row_app_date_time);
        row_app_status = (MaterialTextView)itemView.findViewById(R.id.row_app_status);
        row_app_btn_cancel = (MaterialButton)itemView.findViewById(R.id.row_app_cancel);
    }
}
