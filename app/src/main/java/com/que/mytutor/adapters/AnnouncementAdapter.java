package com.que.mytutor.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;
import com.que.mytutor.R;
import com.que.mytutor.model.Announcement;

import java.util.ArrayList;
import java.util.List;

public class AnnouncementAdapter extends RecyclerView.Adapter<AnnouncementHolder> {
    public AnnouncementAdapter(List<Announcement> items) {
        Items = items;
    }

    private List<Announcement> Items;
    @NonNull
    @Override
    public AnnouncementHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_announcement, parent, false);

        return new AnnouncementHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnnouncementHolder holder, int position) {
        holder.txt_announcement.setText(Items.get(position).getAnnouncement());
        holder.txt_date_time.setText(Items.get(position).getDate_time());
    }

    @Override
    public int getItemCount() {
        return Items.size();
    }
}
class AnnouncementHolder extends RecyclerView.ViewHolder {

    public MaterialTextView txt_announcement;
    public MaterialTextView txt_date_time;
    public AnnouncementHolder(@NonNull View itemView) {
        super(itemView);
        txt_announcement = (MaterialTextView)itemView.findViewById(R.id.txt_announcement);
        txt_date_time = (MaterialTextView)itemView.findViewById(R.id.txt_date_time);
    }
}
