package com.ap.SociaLite.Adapter.SchedulePostAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ap.SociaLite.Adapter.HiddenPostAdapter;
import com.ap.SociaLite.R;

import java.util.ArrayList;

public class SchedulePostSettingAdapter extends RecyclerView.Adapter<SchedulePostSettingAdapter.MyHolder> {

    ArrayList images;
    Context context;

    public SchedulePostSettingAdapter(ArrayList images, Context context) {
        this.images = images;
        this.context = context;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.schedule_post_rs, parent, false);
        SchedulePostSettingAdapter.MyHolder holder = new SchedulePostSettingAdapter.MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

        holder.schedule_post_image.setImageResource((Integer) images.get(position));

    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public static class MyHolder extends RecyclerView.ViewHolder{

        ImageView schedule_post_image;
        TextView schedule_post_contain,schedule_post_time;
        Button schedule_post_delete;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            schedule_post_image = itemView.findViewById(R.id.schedule_post_image);
            schedule_post_contain = itemView.findViewById(R.id.schedule_post_contain);
            schedule_post_time = itemView.findViewById(R.id.schedule_post_time);
            schedule_post_delete = itemView.findViewById(R.id.schedule_post_delete);


        }
    }

}
