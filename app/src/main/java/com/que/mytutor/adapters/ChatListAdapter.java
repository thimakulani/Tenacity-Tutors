package com.que.mytutor.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;
import com.que.mytutor.R;
import com.que.mytutor.model.ChatList;

import java.util.ArrayList;
import java.util.List;

public class ChatListAdapter extends RecyclerView.Adapter<ChatListViewHolder> {
    private FragmentManager fm;
    public ChatListAdapter(List<ChatList> items, FragmentManager fm) {
        Items = items;
        this.fm = fm;
    }
    private List<ChatList> Items;
    @NonNull
    @Override
    public ChatListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_chats_list, parent, false);
        return new ChatListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatListViewHolder holder, int position) {
        holder.row_chat_user_name.setText(Items.get(position).getNames());


    }

    @Override
    public int getItemCount() {
        return Items.size();
    }
}
class ChatListViewHolder extends RecyclerView.ViewHolder{
    public ShapeableImageView row_chat_user_img;
    public MaterialTextView row_chat_user_name;
    public ChatListViewHolder(@NonNull View itemView) {
        super(itemView);
        row_chat_user_img = (ShapeableImageView)itemView.findViewById(R.id.row_chat_user_img);
        row_chat_user_name = (MaterialTextView)itemView.findViewById(R.id.row_chat_user_name);
    }
}
