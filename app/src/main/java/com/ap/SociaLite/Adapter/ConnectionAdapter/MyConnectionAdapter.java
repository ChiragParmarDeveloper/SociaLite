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

import com.ap.SociaLite.Fragment.Connection.MyConnectionFragment;
import com.ap.SociaLite.Pojo.user_connection;
import com.ap.SociaLite.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MyConnectionAdapter extends RecyclerView.Adapter<MyConnectionAdapter.MyHolder> {
    private int selectedItem;
    Context mContext;
    public List<user_connection> user_connections;
    MyConnectionFragment myConnectionFragment;
    List<user_connection> alldata;
    user_connection item;

    public MyConnectionAdapter(Context mContext, List<user_connection> user_connections, MyConnectionFragment myConnectionFragment) {
        this.mContext = mContext;
        this.user_connections = user_connections;
        this.myConnectionFragment = myConnectionFragment;
        selectedItem = 0;

        this.alldata = new ArrayList<>();
        this.alldata.addAll(user_connections);

    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_profile_rs, parent, false);
        MyConnectionAdapter.MyHolder myHolder = new MyConnectionAdapter.MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        item = user_connections.get(position);
        holder.name.setText(item.username);

        if (item.profile_pic.equals("http://the-socialite.com/admin/")) {
            Drawable upload_img = mContext.getDrawable(R.drawable.ic_user_icon);
            holder.profile.setImageDrawable(upload_img);
        } else {
            Picasso.get().load(item.profile_pic).into(holder.profile);
        }

        if (selectedItem == position) {

            myConnectionFragment.RequestId = user_connections.get(position).Request_Id;
            myConnectionFragment.search_profile_user_name.setText(user_connections.get(position).username);
            if (item.profile_pic.equals("http://the-socialite.com/admin/")) {
                Drawable upload_img = mContext.getDrawable(R.drawable.ic_user_icon);
                myConnectionFragment.search_profile_image.setImageDrawable(upload_img);
            } else {
                Picasso.get().load(item.profile_pic).into(myConnectionFragment.search_profile_image);
            }
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myConnectionFragment.RequestId = user_connections.get(position).Request_Id;
                myConnectionFragment.search_profile_user_name.setText(user_connections.get(position).username);

                if (user_connections.get(position).profile_pic.equals("http://the-socialite.com/admin/")) {
                    Drawable upload_img = mContext.getDrawable(R.drawable.ic_user_icon);
                    myConnectionFragment.search_profile_image.setImageDrawable(upload_img);
                } else {
                    Picasso.get().load(user_connections.get(position).profile_pic).into(myConnectionFragment.search_profile_image);
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

        public MyHolder(@NonNull View itemView) {
            super(itemView);

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
