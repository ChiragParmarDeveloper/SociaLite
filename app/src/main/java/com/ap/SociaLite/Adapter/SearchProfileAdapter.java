package com.ap.SociaLite.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ap.SociaLite.Activity.Search;
import com.ap.SociaLite.Pojo.user_list;
import com.ap.SociaLite.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SearchProfileAdapter extends RecyclerView.Adapter<SearchProfileAdapter.MyHolder> {

    Context mContext;
    Search search;
    List<user_list> user_lists;
    List<user_list> allUser_List;
    user_list item;
    public static String User_id;
    String id;
    private int selectedItem;

    public SearchProfileAdapter(Context context, List<user_list> list, Search fragment) {
        this.mContext = context;
        this.user_lists = list;
        this.allUser_List= new ArrayList<>();
        this.allUser_List.addAll(user_lists);
        this.search = fragment;
        selectedItem = 0;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_profile_rs, parent, false);
        SearchProfileAdapter.MyHolder myHolder = new SearchProfileAdapter.MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        item = user_lists.get(position);
        id = user_lists.get(position).id;

        if (selectedItem == position) {
            User_id = user_lists.get(position).id;
            search.search_profile_user_name.setText(user_lists.get(position).username);
            if (item.profile_pic.length() > 0) {
                Picasso.get().load(user_lists.get(position).profile_pic).placeholder(R.mipmap.ic_launcher).into(search.search_profile_image);
            }
        }

        if (item.profile_pic.length() > 0) {
            Picasso.get().load(item.profile_pic).placeholder(R.mipmap.ic_launcher).into(holder.profile);
        }

        holder.name.setText(item.username);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               User_id = user_lists.get(position).id;

                if (item.profile_pic.length() > 0) {
                    Picasso.get().load(user_lists.get(position).profile_pic).placeholder(R.mipmap.ic_launcher).into(search.search_profile_image);
                }

                search.search_profile_user_name.setText(user_lists.get(position).username);
            }
        });
    }

    @Override
    public int getItemCount() {
        return user_lists.size();
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
        user_lists.clear();
        if (charText.length() == 0) {
            user_lists.addAll(allUser_List);
        } else {
            for (user_list  wp : allUser_List) {
                if (wp.username.toLowerCase(Locale.getDefault()).contains(charText)) {
                    user_lists.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

}
