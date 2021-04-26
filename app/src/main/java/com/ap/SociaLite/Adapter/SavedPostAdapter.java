package com.ap.SociaLite.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ap.SociaLite.Activity.SavedPostDetailActivity;
import com.ap.SociaLite.Pojo.hide_post;
import com.ap.SociaLite.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SavedPostAdapter extends RecyclerView.Adapter<SavedPostAdapter.MyHolder> {

    Context mContext;
    List<hide_post> hide_posts;
    hide_post item;

    public SavedPostAdapter(Context mContext, List<hide_post> hide_posts) {
        this.mContext = mContext;
        this.hide_posts = hide_posts;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hidenpost_rs, parent, false);
        SavedPostAdapter.MyHolder holder = new SavedPostAdapter.MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        item = hide_posts.get(position);
        Picasso.get().load(item.image).into(holder.img_category);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent saved = new Intent(view.getContext(), SavedPostDetailActivity.class);
                saved.putExtra("click_position",position);
                view.getContext().startActivity(saved);
            }
        });
    }

    @Override
    public int getItemCount() {
        return hide_posts.size();
    }

    public static class MyHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_category)
        ImageView img_category;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
