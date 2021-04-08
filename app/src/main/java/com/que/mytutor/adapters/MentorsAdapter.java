package com.que.mytutor.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;
import com.que.mytutor.R;
import com.que.mytutor.dialogs.MentorProfileDialog;
import com.que.mytutor.interfaces.CircleTransform;
import com.que.mytutor.model.Mentors;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MentorsAdapter extends RecyclerView.Adapter<MentorViewHolder> {
    private List<Mentors> Items;
    private FragmentManager fm;

    public MentorsAdapter(List<Mentors> items, FragmentManager fm) {
        Items = items;
        this.fm = fm;
    }

    @NonNull
    @Override
    public MentorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflater = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_mentors, parent, false);
        return new MentorViewHolder(inflater);
    }

    @Override
    public void onBindViewHolder(@NonNull MentorViewHolder holder, int position) {

        holder.TxtNames.setText(String.format("%s %s", Items.get(position).getNames(), Items.get(position).getSurname()));
        holder.TxtDescription.setText(Items.get(position).getDescription());

        Picasso.get().load(Items.get(position).getImgUrl())
                .resize(200, 200)
                .transform(new CircleTransform())
                .placeholder(R.drawable.baseline_account_circle_black_24dp)
                .into(holder.Img);
        holder.BtnProfile.setOnClickListener(v -> {
            MentorProfileDialog dialog = new MentorProfileDialog(Items.get(position).getId());
            dialog.show(fm.beginTransaction(), "Profile");
        });

    }

    @Override
    public int getItemCount() {
        return Items.size();
    }
}
class MentorViewHolder extends RecyclerView.ViewHolder{

    public MaterialButton BtnProfile;
    public MaterialButton BtnSendMessage;
    public MaterialTextView TxtNames;
    public MaterialTextView TxtDescription;
    public AppCompatImageView Img;
    public MentorViewHolder(@NonNull View itemView) {
        super(itemView);
        BtnProfile = (MaterialButton)itemView.findViewById(R.id.row_m_btn_profile);
        BtnSendMessage = (MaterialButton)itemView.findViewById(R.id.row_m_btn_message);

        TxtDescription = (MaterialTextView) itemView.findViewById(R.id.row_m_description);
        TxtNames = (MaterialTextView) itemView.findViewById(R.id.row_m_names);
        Img = (AppCompatImageView) itemView.findViewById(R.id.row_m_profile);
    }
}
