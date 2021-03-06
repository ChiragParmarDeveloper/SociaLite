package com.ap.SociaLite.Adapter;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ap.SociaLite.Activity.SpotlightActivityForUser;
import com.ap.SociaLite.Pojo.story_data;
import com.ap.SociaLite.Presenter.SpotlightActivityForUserPresenter;
import com.ap.SociaLite.R;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MyAllStoryAdapter extends RecyclerView.Adapter<MyAllStoryAdapter.Holder> {

    Context mContext;
    SpotlightActivityForUser spotlightActivityForUser;
    List<story_data> story = new ArrayList<>();
    story_data item;
    int abc;
    public MyAllStoryAdapter(Context context, List<story_data> list, SpotlightActivityForUser fragment) {
        this.mContext = context;
        this.story = list;
        this.spotlightActivityForUser = fragment;
    }

    @NonNull
    @Override
    public MyAllStoryAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_friend_spotlight_rs, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyAllStoryAdapter.Holder holder, int position) {
        item = story.get(position);
        String id = story.get(position).story_id;

        holder.spotlight_textview_rs.setText(item.username);
        Picasso.get().load(item.story_image).into(holder.spotlight_user_profile_rs);

        holder.spotlight_linearLayout_user_story.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Picasso.get().load(story.get(position).story_image).into(spotlightActivityForUser.img_status);
                new SpotlightActivityForUserPresenter(mContext, spotlightActivityForUser).view_story_viewer(spotlightActivityForUser.user_id, story.get(position).story_id);
            }
        });
    }

    @Override
    public int getItemCount() {
        return story.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        CircularImageView spotlight_user_profile_rs;
        TextView spotlight_textview_rs;
        LinearLayout spotlight_linearLayout_user_story;

        public Holder(@NonNull View itemView) {
            super(itemView);
            spotlight_user_profile_rs = itemView.findViewById(R.id.spotlight_user_profile_rs);
            spotlight_textview_rs = itemView.findViewById(R.id.spotlight_textview_rs);
            spotlight_linearLayout_user_story = itemView.findViewById(R.id.spotlight_linearLayout_user_story);
        }
    }
}
