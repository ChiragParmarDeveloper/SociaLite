package com.ap.SociaLite.Adapter.ConnectionAdapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.ap.SociaLite.Fragment.Connection.ConnectionFragment;
import com.ap.SociaLite.Pojo.user_connection;
import com.ap.SociaLite.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ConnectionAdapter extends RecyclerView.Adapter<ConnectionAdapter.MyHolder> {
    private int selectedItem;
    Context mContext;
    public List<user_connection> user_connections;
    ConnectionFragment connectionFragment;
    List<user_connection> alldata;
    user_connection item;

    public ConnectionAdapter(Context mContext, List<user_connection> user_connections, ConnectionFragment connectionFragment) {
        this.mContext = mContext;
        this.user_connections = user_connections;
        this.connectionFragment = connectionFragment;
        selectedItem = 0;

        this.alldata = new ArrayList<>();
        this.alldata.addAll(user_connections);

    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_profile_rs, parent, false);
        ConnectionAdapter.MyHolder myHolder = new ConnectionAdapter.MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        item = user_connections.get(position);
        holder.name.setText(item.username);

//        if (item.profile_pic.equals("http://the-socialite.com/admin/")) {
//            Drawable upload_img = mContext.getDrawable(R.drawable.ic_user_icon);
//            holder.profile.setImageDrawable(upload_img);
//        } else {
//            Picasso.get().load(item.profile_pic).into(holder.profile);
//        }

        if (user_connections.get(position).profile_pic.equals("http://the-socialite.com/admin/")) {
            holder.viewer_profile.setVisibility(View.VISIBLE);
            String avatarTitle = String.valueOf(user_connections.get(position).username.charAt(0)).toUpperCase();
            ColorGenerator generator = ColorGenerator.MATERIAL;
            int randomcolor = generator.getRandomColor();

            TextDrawable.IBuilder builder = TextDrawable.builder().beginConfig().endConfig().round();

            TextDrawable drawable = builder.build(avatarTitle, randomcolor);
            holder.viewer_profile.setImageDrawable(drawable);
        } else {
            holder.viewer_profile.setVisibility(View.GONE);
            Picasso.get().load(user_connections.get(position).profile_pic).into(holder.profile);
        }


        if (selectedItem == position) {
            connectionFragment.RequestId = user_connections.get(position).Request_Id;
            connectionFragment.search_profile_user_name.setText(user_connections.get(position).username);
//            if (item.profile_pic.equals("http://the-socialite.com/admin/")) {
//                Drawable upload_img = mContext.getDrawable(R.drawable.ic_user_icon);
//                connectionFragment.search_profile_image.setImageDrawable(upload_img);
//            } else {
//                Picasso.get().load(item.profile_pic).into(connectionFragment.search_profile_image);
//            }

            if (user_connections.get(position).profile_pic.equals("http://the-socialite.com/admin/")) {
                connectionFragment.img_pic.setVisibility(View.VISIBLE);
                String avatarTitle = String.valueOf(user_connections.get(position).username.charAt(0)).toUpperCase();
                ColorGenerator generator = ColorGenerator.MATERIAL;
                int randomcolor = generator.getRandomColor();

                TextDrawable.IBuilder builder = TextDrawable.builder().beginConfig().endConfig().round();

                TextDrawable drawable = builder.build(avatarTitle, randomcolor);
                connectionFragment.img_pic.setImageDrawable(drawable);

            } else {
                connectionFragment.img_pic.setVisibility(View.GONE);
                Picasso.get().load(user_connections.get(position).profile_pic).into(connectionFragment.search_profile_image);
           }
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                connectionFragment.RequestId = user_connections.get(position).Request_Id;
                connectionFragment.search_profile_user_name.setText(user_connections.get(position).username);

//                if (user_connections.get(position).profile_pic.equals("http://the-socialite.com/admin/")) {
//                    Drawable upload_img = mContext.getDrawable(R.drawable.ic_user_icon);
//                    connectionFragment.search_profile_image.setImageDrawable(upload_img);
//                } else {
//                    Picasso.get().load(user_connections.get(position).profile_pic).into(connectionFragment.search_profile_image);
//                }

                if (user_connections.get(position).profile_pic.equals("http://the-socialite.com/admin/")) {
                    connectionFragment.img_pic.setVisibility(View.VISIBLE);

                    String avatarTitle = String.valueOf(user_connections.get(position).username.charAt(0)).toUpperCase();
                    ColorGenerator generator = ColorGenerator.MATERIAL;
                    int randomcolor = generator.getRandomColor();

                    TextDrawable.IBuilder builder = TextDrawable.builder().beginConfig().endConfig().round();

                    TextDrawable drawable = builder.build(avatarTitle, randomcolor);
                    connectionFragment.img_pic.setImageDrawable(drawable);
                } else {
                    connectionFragment.img_pic.setVisibility(View.GONE);
                    Picasso.get().load(user_connections.get(position).profile_pic).into(connectionFragment.search_profile_image);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return user_connections.size();
    }

    public static class MyHolder extends RecyclerView.ViewHolder {

        ImageView profile;
        TextView name;

        @BindView(R.id.viewer_profile)
        ImageView viewer_profile;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            profile = itemView.findViewById(R.id.user_profile);
            name = itemView.findViewById(R.id.user_name);

        }
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        user_connections.clear();
        if (charText.length() == 0) {
            user_connections.addAll(alldata);
        } else {
            for (user_connection wp : alldata) {
                if (wp.username.toLowerCase(Locale.getDefault()).contains(charText)) {
                    user_connections.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }


}
