package com.ap.SociaLite.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ap.SociaLite.Activity.SpotlightActivityForUser;
import com.ap.SociaLite.Activity.UserFriendSpotlightViewActivity;
import com.ap.SociaLite.Fragment.CategoryFragment;
import com.ap.SociaLite.Pojo.post_list;
import com.ap.SociaLite.Pojo.story_view;
import com.ap.SociaLite.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SpotlightViewerAdapter extends RecyclerView.Adapter<SpotlightViewerAdapter.MyHolder> {

    Context mContext;
    SpotlightActivityForUser spotlightActivityForUser;
    List<story_view> views;
    story_view item;

    public SpotlightViewerAdapter(Context context, List<story_view> list, SpotlightActivityForUser fragment) {
        this.mContext = context;
        this.views = list;
        this.spotlightActivityForUser = fragment;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewes_rs, parent, false);
        SpotlightViewerAdapter.MyHolder myHolder = new SpotlightViewerAdapter.MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        item = views.get(position);
        String id = views.get(position).user_id;

        if (item.profile_pic.equals("http://the-socialite.com/admin/")) {
            Drawable upload_img = mContext.getDrawable(R.drawable.ic_user_icon);
            holder.profile.setImageDrawable(upload_img);
        } else {
            Picasso.get().load(item.profile_pic).into(holder.profile);
        }
        holder.name.setText(item.username);
     }

    @Override
    public int getItemCount() {
        return views.size();
    }


    public class MyHolder extends RecyclerView.ViewHolder{

        ImageView profile;
        TextView name;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            profile = itemView.findViewById(R.id.viewer_profile);
            name = itemView.findViewById(R.id.viewer_name);

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent in = new Intent(view.getContext(), UserFriendSpotlightViewActivity.class);
//                    view.getContext().startActivity(in);
//                }
//            });

        }

    }

}
