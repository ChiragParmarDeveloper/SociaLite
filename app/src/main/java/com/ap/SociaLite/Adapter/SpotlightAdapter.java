package com.ap.SociaLite.Adapter;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ap.SociaLite.Activity.UserFriendSpotlightViewActivity;
import com.ap.SociaLite.R;

import java.util.ArrayList;

public class SpotlightAdapter extends RecyclerView.Adapter<SpotlightAdapter.MyHolder> {

    ArrayList Name;
    Context context;

    public SpotlightAdapter(ArrayList name, Context context) {
        Name = name;
        this.context = context;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_friend_spotlight_rs, parent, false);
        SpotlightAdapter.MyHolder myHolder = new SpotlightAdapter.MyHolder(view);
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

        public MyHolder(@NonNull View itemView) {
            super(itemView);


            profile = itemView.findViewById(R.id.spotlight_user_profile_rs);
            name = itemView.findViewById(R.id.spotlight_textview_rs);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent in = new Intent(view.getContext(), UserFriendSpotlightViewActivity.class);
                    view.getContext().startActivity(in);
                }
            });

        }

    }
}
