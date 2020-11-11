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
import com.ap.SociaLite.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HiddenPostAdapter extends RecyclerView.Adapter<HiddenPostAdapter.Holder> {


    ArrayList images;
    Context context;


    public HiddenPostAdapter(Context context, ArrayList images) {
        this.context = context;
        this.images = images;
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
        holder.img_category.setImageResource((Integer) images.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent hidden = new Intent(view.getContext(),HiddedPostDetailActivity.class);
                view.getContext().startActivity(hidden);
            }
        });

    }

    @Override
    public int getItemCount() {
        return images.size();
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
