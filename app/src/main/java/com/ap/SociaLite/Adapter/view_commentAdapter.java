package com.ap.SociaLite.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.ap.SociaLite.Activity.CommentActivity;
import com.ap.SociaLite.Model.comments;
import com.ap.SociaLite.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class view_commentAdapter extends RecyclerView.Adapter<view_commentAdapter.Holder> {

    Context mContext;
    CommentActivity commentActivity;
    List<comments> comments;
    comments item;
    //  List<String> mList;

    public view_commentAdapter(Context context, List<comments> list, CommentActivity fragment) {
        this.mContext = context;
        this.comments = list;
        this.commentActivity = fragment;
    }

    @NonNull
    @Override
    public view_commentAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_rs_two, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull view_commentAdapter.Holder holder, int position) {
        item = comments.get(position);
        holder.viewer_name.setText(item.user_name);
        holder.viewer_comment.setText(item.comment);

        String avatarTitle = String.valueOf(item.user_name.charAt(0)).toUpperCase();
        ColorGenerator generator = ColorGenerator.MATERIAL;
        int randomcolor = generator.getRandomColor();

        TextDrawable.IBuilder builder = TextDrawable.builder().beginConfig().endConfig().round();

        TextDrawable drawable = builder.build(avatarTitle, randomcolor);
        holder.viewer_profile.setImageDrawable(drawable);


//        String avatarTitle = String.valueOf(item.user_name.charAt(0)).toUpperCase();
//        holder.text_user_avatar_title.setText(avatarTitle);

//        if (item.profile_pic.equals("http://the-socialite.com/admin/")) {
//            Drawable upload_img = mContext.getDrawable(R.drawable.ic_user_icon);
//            holder.viewer_profile.setImageDrawable(upload_img);
//        } else {
//            Picasso.get().load(item.profile_pic).into(holder.viewer_profile);
//        }

    }

    @Override
    public int getItemCount() {
        if (comments == null) {
            return 0;
        } else {
            return comments.size();
        }
    }

    public class Holder extends RecyclerView.ViewHolder {

        @BindView(R.id.viewer_name)
        TextView viewer_name;

        @BindView(R.id.viewer_profile)
        ImageView viewer_profile;

        @BindView(R.id.viewer_comment)
        TextView viewer_comment;

        @BindView(R.id.text_user_avatar_title)
        TextView text_user_avatar_title;

        public Holder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
