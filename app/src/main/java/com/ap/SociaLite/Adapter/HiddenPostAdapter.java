package com.ap.SociaLite.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ap.SociaLite.Activity.HiddedPostDetailActivity;
import com.ap.SociaLite.Activity.HidedPost;
import com.ap.SociaLite.Pojo.hide_post;
import com.ap.SociaLite.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HiddenPostAdapter extends RecyclerView.Adapter<HiddenPostAdapter.Holder> {

    Context mContext;
    HidedPost hidedPost;
    List<hide_post> hide_posts;
    hide_post item;

    public HiddenPostAdapter(Context mContext, HidedPost hidedPost, List<hide_post> hide_posts) {
        this.mContext = mContext;
        this.hidedPost = hidedPost;
        this.hide_posts = hide_posts;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hidenpost_rs, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        item = hide_posts.get(position);
        Picasso.get().load(item.image).into(holder.img_category);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent hidden = new Intent(view.getContext(), HiddedPostDetailActivity.class);
                view.getContext().startActivity(hidden);
            }
        });

    }

    @Override
    public int getItemCount() {

        return hide_posts.size();

    }

    public class Holder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_category)
        ImageView img_category;

        public Holder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
