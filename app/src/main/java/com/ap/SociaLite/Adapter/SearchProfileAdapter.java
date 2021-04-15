package com.ap.SociaLite.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ap.SociaLite.Activity.Search;
import com.ap.SociaLite.Pojo.data;
import com.ap.SociaLite.R;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SearchProfileAdapter extends RecyclerView.Adapter<SearchProfileAdapter.MyHolder> {

    Context mContext;
    public List<data> datas;
    Search search;
    List<data> alldata;
    data item;

    String id;
    private int selectedItem;

    public SearchProfileAdapter(Context mContext, List<data> datas, Search search) {
        this.mContext = mContext;
        this.datas = datas;
        this.search = search;
        selectedItem = 0;

        this.alldata = new ArrayList<>();
        this.alldata.addAll(datas);
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
        item = datas.get(position);
        id = datas.get(position).request_id;
        holder.name.setText(item.username);

        if (selectedItem == position) {
            search.RequestId = datas.get(position).request_id;
            search.search_profile_user_name.setText(datas.get(position).username);
            if (item.profile_pic.equals("http://the-socialite.com/admin/")) {
                Drawable upload_img = mContext.getDrawable(R.drawable.ic_user_icon);
                search.search_profile_image.setImageDrawable(upload_img);
            } else {
                Picasso.get().load(item.profile_pic).into(search.search_profile_image);
            }
        }

        if (item.profile_pic.equals("http://the-socialite.com/admin/")) {
            Drawable upload_img = mContext.getDrawable(R.drawable.ic_user_icon);
            holder.profile.setImageDrawable(upload_img);
        } else {
            Picasso.get().load(item.profile_pic).into(holder.profile);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search.RequestId = datas.get(position).request_id;
                if (datas.get(position).profile_pic.equals("http://the-socialite.com/admin/")) {
                    Drawable upload_img = mContext.getDrawable(R.drawable.ic_user_icon);
                    search.search_profile_image.setImageDrawable(upload_img);
                } else {
                    Picasso.get().load(datas.get(position).profile_pic).into(search.search_profile_image);
                }
                search.search_profile_user_name.setText(datas.get(position).username);
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public static class MyHolder extends RecyclerView.ViewHolder {

        CircularImageView profile;
        TextView name;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            profile = itemView.findViewById(R.id.user_profile);
            name = itemView.findViewById(R.id.user_name);

        }
    }


    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        datas.clear();
        if (charText.length() == 0) {
            datas.addAll(alldata);
        } else {
            for (data wp : alldata) {
                if (wp.username.toLowerCase(Locale.getDefault()).contains(charText)) {
                    datas.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

}
