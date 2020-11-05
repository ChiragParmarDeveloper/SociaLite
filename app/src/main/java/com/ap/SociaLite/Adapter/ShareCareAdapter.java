package com.ap.SociaLite.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ap.SociaLite.Activity.MessageChatActivity;
import com.ap.SociaLite.Activity.UserFriendSpotlightViewActivity;
import com.ap.SociaLite.R;

import java.util.ArrayList;

public class ShareCareAdapter extends RecyclerView.Adapter<ShareCareAdapter.MyHolder> {

    ArrayList Name;
    Context context;

    public ShareCareAdapter(ArrayList name, Context context) {
        Name = name;
        this.context = context;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_share_rs, parent, false);
        ShareCareAdapter.MyHolder myHolder = new ShareCareAdapter.MyHolder(view);
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

        ImageView profile,bussiness_icon;
        TextView name,message;
        ImageView user_offline,user_online;
        EditText message_text;
        ImageView img_send;
        View view_;

        public MyHolder(@NonNull View itemView) {
            super(itemView);


            profile = itemView.findViewById(R.id.viewer_profile);
            name = itemView.findViewById(R.id.viewer_name);
            bussiness_icon = itemView.findViewById(R.id.bussiness_icon);
            message = itemView.findViewById(R.id.message);
            user_online = itemView.findViewById(R.id.user_online);
            user_offline = itemView.findViewById(R.id.user_offline);
            message_text = itemView.findViewById(R.id.message_text);
            img_send = itemView.findViewById(R.id.img_send);
            view_ = itemView.findViewById(R.id.view);

            profile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent chat = new Intent(view.getContext(), MessageChatActivity.class);
                    view.getContext().startActivity(chat);
                }
            });

            message.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    message.setVisibility(View.GONE);
                    message_text.setVisibility(View.VISIBLE);
                    view_.setVisibility(View.VISIBLE);
                    img_send.setVisibility(View.VISIBLE);
                }
            });

            img_send.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    message.setVisibility(View.VISIBLE);
                    message_text.setVisibility(View.GONE);
                    view_.setVisibility(View.GONE);
                    img_send.setVisibility(View.GONE);
                }
            });

        }

    }
}
