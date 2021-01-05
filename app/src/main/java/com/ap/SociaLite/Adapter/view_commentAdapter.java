package com.ap.SociaLite.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ap.SociaLite.Activity.CommentActivity;
import com.ap.SociaLite.Activity.Faq;
import com.ap.SociaLite.Model.comments;
import com.ap.SociaLite.Pojo.faq_list;
import com.ap.SociaLite.R;
import com.mikhaellopez.circularimageview.CircularImageView;

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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_rs, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull view_commentAdapter.Holder holder, int position) {
        item = comments.get(position);

//        holder.viewer_name.setText(item.comment);
//        Log.d("comment",item.comment);

        Log.d("service",item.comment);
        holder.viewer_name.setText(item.user_name);
        holder.viewer_comment.setText(item.comment);
      //  holder.viewer_name.setText(comments.get(0).comment);



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
        CircularImageView viewer_profile;

        @BindView(R.id.viewer_comment)
        TextView viewer_comment;



        public Holder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
