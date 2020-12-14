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
import com.ap.SociaLite.Activity.SavedPostDetailActivity;
import com.ap.SociaLite.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SavedPostAdapter extends RecyclerView.Adapter<SavedPostAdapter.MyHolder> {

    ArrayList images;
    Context context;

    public SavedPostAdapter(ArrayList images, Context context) {
        this.images = images;
        this.context = context;
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

        holder.img_category.setImageResource((Integer) images.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent saved = new Intent(view.getContext(), SavedPostDetailActivity.class);
                view.getContext().startActivity(saved);
            }
        });

    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public static class MyHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.img_category)
        ImageView img_category;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
