package com.ap.SociaLite.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ap.SociaLite.Activity.SpotLightActivity;
import com.ap.SociaLite.Pojo.data;
import com.ap.SociaLite.Pojo.story;
import com.ap.SociaLite.R;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SpotlightAdapter extends RecyclerView.Adapter<SpotlightAdapter.MyHolder> {

    int abc;
    Handler handler;
    Context mContext;
    SpotLightActivity spotLightActivity;
    List<data> datas;
    List <story> stories;
    data item;

    public SpotlightAdapter(Context mContext, SpotLightActivity spotLightActivity, List<data> datas) {
        this.mContext = mContext;
        this.spotLightActivity = spotLightActivity;
        this.datas = datas;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_friend_spotlight_rs, parent, false);
        SpotlightAdapter.MyHolder myHolder = new SpotlightAdapter.MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        item = datas.get(position);

        holder.spotlight_textview_rs.setText(item.username);

        if (item.profile_pic.equals("http://the-socialite.com/admin/")) {
            Drawable upload_img = mContext.getDrawable(R.drawable.ic_user_icon);
            holder.spotlight_user_profile_rs.setImageDrawable(upload_img);
        } else {
            Picasso.get().load(item.profile_pic).into(holder.spotlight_user_profile_rs);
        }

      holder.spotlight_linearLayout_user_story.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                abc = 0;
//                handler = new Handler();
//                for (int i = 0; i < item.story.get(position).image.length(); i++) {
//                    int finalI = i;
//
//                    handler.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//
////                            List<String> storyimage = new ArrayList<>();
////                            for (int i = 0; i < item.story.size(); i++) {
////                                storyimage.add(item.story.get(i).image);
////                            }
//
//                            Picasso.get().load(item.story.get(position).image).into(spotLightActivity.user_frndstory);
//                        }
//                    }, 2000 * i);
//                }

                //     Intent in = new Intent(view.getContext(), UserFriendSpotlightViewActivity.class);
                //    view.getContext().startActivity(in);
            }
        });

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }


    public class MyHolder extends RecyclerView.ViewHolder {

        CircularImageView spotlight_user_profile_rs;
        TextView spotlight_textview_rs;
    LinearLayout spotlight_linearLayout_user_story;
        public MyHolder(@NonNull View itemView) {
            super(itemView);

            spotlight_user_profile_rs = itemView.findViewById(R.id.spotlight_user_profile_rs);
            spotlight_textview_rs = itemView.findViewById(R.id.spotlight_textview_rs);
            spotlight_linearLayout_user_story= itemView.findViewById(R.id.spotlight_linearLayout_user_story);


        }

    }
}
