package com.que.mytutor.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;
import com.que.mytutor.R;
import com.que.mytutor.model.MessagesModel;

import java.util.ArrayList;

public class MessagesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{

    private ArrayList<MessagesModel> Items;
    public MessagesAdapter(ArrayList<MessagesModel> items) {
        this.Items = items;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if(viewType == R.layout.row_recieved){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_recieved, parent, false);
            return new ReceivedViewHolder(view);
        }
        else{
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_sent, parent, false);
            return new SentViewHolder(view);
        }

    }

    @Override
    public int getItemViewType(int position) {
        if(position%2 == 0){
            return R.layout.row_recieved;
        }
        else{
            return R.layout.row_sent;
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

            if(holder.getItemViewType() == R.layout.row_sent){
                SentViewHolder v_type = (SentViewHolder) holder;
                v_type.row_sent_message.setText(Items.get(position).getMessage());
            }
            else{
                ReceivedViewHolder v_type = (ReceivedViewHolder) holder;
                v_type.row_received_message.setText(Items.get(position).getMessage());
            }

    }

    @Override
    public int getItemCount() {
        return Items.size();
    }

}
class SentViewHolder extends RecyclerView.ViewHolder{
    public MaterialTextView row_sent_date;
    public MaterialTextView row_sent_message;
    public SentViewHolder(@NonNull View itemView) {
        super(itemView);
        row_sent_message = (MaterialTextView)itemView.findViewById(R.id.row_sent_message);
    }
}
class ReceivedViewHolder extends RecyclerView.ViewHolder{
    public MaterialTextView row_received_date;
    public MaterialTextView row_received_message;
    public ReceivedViewHolder(@NonNull View itemView) {
        super(itemView);
        row_received_message = (MaterialTextView)itemView.findViewById(R.id.row_received_message);
    }
}

