package com.ap.SociaLite.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ap.SociaLite.Activity.UserFriendSpotlightViewActivity;
import com.ap.SociaLite.R;

import java.util.ArrayList;

public class BlockedContactAdapter extends RecyclerView.Adapter<BlockedContactAdapter.MyHolder> {

    ArrayList Name;
    Context context;

    public BlockedContactAdapter(ArrayList name, Context context) {
        Name = name;
        this.context = context;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.block_contact_rs, parent, false);
        BlockedContactAdapter.MyHolder myHolder = new BlockedContactAdapter.MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

        holder.name.setText((CharSequence)Name.get(position));

    }

    @Override
    public int getItemCount() {
        return Name.size();
    }


    public class MyHolder extends RecyclerView.ViewHolder {

        ImageView profile;
        TextView name;
        Button unblock_btn;

        public MyHolder(@NonNull View itemView) {
            super(itemView);


            profile = itemView.findViewById(R.id.blocked_user_profile);
            name = itemView.findViewById(R.id.blocked_username);
            unblock_btn = itemView.findViewById(R.id.unblock_btn);


        }

    }

}
