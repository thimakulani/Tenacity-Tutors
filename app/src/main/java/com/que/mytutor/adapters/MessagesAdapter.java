package com.que.mytutor.adapters;

import android.content.ClipData;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.auth.FirebaseAuth;
import com.que.mytutor.R;
import com.que.mytutor.model.MessagesModel;

import java.util.ArrayList;
import java.util.List;

public class MessagesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{

    private List<MessagesModel> Items = new ArrayList<>();
    public MessagesAdapter(List<MessagesModel> items) {
        Items = items;
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
        if(Items.get(position).getUid().equals(FirebaseAuth.getInstance().getUid())){
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
                v_type.row_sent_message.setText(Items.get(position).getText());
            }
            else{
                ReceivedViewHolder v_type = (ReceivedViewHolder) holder;
                v_type.row_received_message.setText(Items.get(position).getText());
            }

    }

    @Override
    public int getItemCount() {
        return Items.size();
    }

}
class SentViewHolder extends RecyclerView.ViewHolder{
    public MaterialTextView row_sent_message;
    public SentViewHolder(@NonNull View itemView) {
        super(itemView);
        row_sent_message = (MaterialTextView)itemView.findViewById(R.id.row_sent_message);
    }
}
class ReceivedViewHolder extends RecyclerView.ViewHolder{
    public MaterialTextView row_received_message;
    public ReceivedViewHolder(@NonNull View itemView) {
        super(itemView);
        row_received_message = (MaterialTextView)itemView.findViewById(R.id.row_received_message);
    }
}

