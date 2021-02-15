package com.ap.SociaLite.Adapter.SchedulePostAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ap.SociaLite.Activity.SchedulePostSettingActivity;
import com.ap.SociaLite.Pojo.post_list;
import com.ap.SociaLite.Presenter.SchedulePostSettingPresenter;
import com.ap.SociaLite.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SchedulePostSettingAdapter extends RecyclerView.Adapter<SchedulePostSettingAdapter.MyHolder> {

    Context mContext;
    SchedulePostSettingActivity SchedulePostSettingActivity;
    List<post_list> post_lists = new ArrayList<>();
    post_list item;

    public SchedulePostSettingAdapter(Context context, List<post_list> list, SchedulePostSettingActivity fragment) {
        this.mContext = context;
        this.post_lists = list;
        this.SchedulePostSettingActivity = fragment;
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
        item = post_lists.get(position);
        String id = post_lists.get(position).post_id;

        Picasso.get().load(item.image).placeholder(R.mipmap.ic_launcher).into(holder.schedule_post_image);
        holder.schedule_post_contain.setText(item.description);

        String date = item.schedule_date + " on " + item.schedule_time;
        holder.schedule_post_time.setText(date);

        holder.schedule_post_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new SchedulePostSettingPresenter(mContext,SchedulePostSettingActivity).delete_post(id);
                new SchedulePostSettingPresenter(mContext, SchedulePostSettingActivity).schedule_posts(SchedulePostSettingActivity.user_id);
            }
        });
    }

    @Override
    public int getItemCount() {
        return post_lists.size();
    }

    public static class MyHolder extends RecyclerView.ViewHolder {

        ImageView schedule_post_image;
        TextView schedule_post_contain, schedule_post_time;
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
