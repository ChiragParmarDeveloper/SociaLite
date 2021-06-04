package com.ap.SociaLite.Adapter.Notification_adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.ap.SociaLite.Activity.Notification;
import com.ap.SociaLite.Pojo.data;
import com.ap.SociaLite.Presenter.NotificationPresenter;
import com.ap.SociaLite.R;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Notification_adapter extends RecyclerView.Adapter {

    Context mContext;
    Notification notification;
    List<data> datas;
    data item;
    int pos = 0;

    public Notification_adapter(Context mContext, Notification notification, List<data> datas) {
        this.mContext = mContext;
        this.notification = notification;
        this.datas = datas;
    }

//    @Override
//    public int getItemViewType(int position) {
//        switch (datas.get(position).notification_type) {
//            case "Requested":
//                return 0;
//
//            case "Interest":
//                return 1;
//
//            case "Rating":
//                return 2;
//
//            case "Public":
//                return 3;
//
//            default:
//                return -1;
//        }
//    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                return 0;
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
                return 3;
        }
        throw new RuntimeException(String.format("unexpected position - %d", position));
    }

    @NonNull
    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int i) {

        int Type = getItemViewType(pos);

        if (datas.size() > pos) {
       //     Log.d("log", String.valueOf(datas.size()));
            pos++;
        }

        switch (i) {
            case 0:
                //Requested
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_accept_decline_rs, parent, false);
           //     Notification_adapter.Frnd_request myholder = new Notification_adapter.Frnd_request(view);
            //    return myholder;

            case 1:
                //Interest
                View view3 = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_interested, parent, false);
        //        Notification_adapter.Interest holder3 = new Notification_adapter.Interest(view3);
//                return holder3;

            case 2:
                //Rating
                View view2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_rating_rs, parent, false);
            //    Notification_adapter.Rating holder2 = new Notification_adapter.Rating(view2);
              //  return holder2;

            case 3:
                //Public
                View view1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_connect_rs, parent, false);
                //Notification_adapter.Public_user holder1 = new Notification_adapter.Public_user(view1);
        //        return holder1;
        }

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {
        String type = datas.get(position).notification_type;

        switch (type) {
            case "Requested":

//                item = datas.get(position);
//
//                ((Frnd_request) holder).txt_user_name.setText(item.username);
//                ((Frnd_request) holder).txt_request_username.setText(item.request_username);
//
//                if (item.user_profile_pic.equals("http://the-socialite.com/admin/")) {
//                    ((Frnd_request) holder).img_pic.setVisibility(View.VISIBLE);
//                    String avatarTitle = String.valueOf(item.username.charAt(0)).toUpperCase();
//                    ColorGenerator generator = ColorGenerator.MATERIAL;
//                    int randomcolor = generator.getRandomColor();
//
//                    TextDrawable.IBuilder builder = TextDrawable.builder().beginConfig().endConfig().round();
//
//                    TextDrawable drawable = builder.build(avatarTitle, randomcolor);
//                    ((Frnd_request) holder).img_pic.setImageDrawable(drawable);
//
//                } else {
//                    ((Frnd_request) holder).img_pic.setVisibility(View.GONE);
//                    Picasso.get().load(item.user_profile_pic).into(((Frnd_request) holder).user_image);
//                }
//
//                if (datas.get(position).request_user_profile_pic.equals("http://the-socialite.com/admin/")) {
//                    ((Frnd_request) holder).request_img_pic.setVisibility(View.VISIBLE);
//                    String avatarTitle = String.valueOf(datas.get(position).request_username.charAt(0)).toUpperCase();
//                    ColorGenerator generator = ColorGenerator.MATERIAL;
//                    int randomcolor = generator.getRandomColor();
//
//                    TextDrawable.IBuilder builder = TextDrawable.builder().beginConfig().endConfig().round();
//
//                    TextDrawable drawable = builder.build(avatarTitle, randomcolor);
//                    ((Frnd_request) holder).request_img_pic.setImageDrawable(drawable);
//
//                } else {
//                    ((Frnd_request) holder).request_img_pic.setVisibility(View.GONE);
//                    Picasso.get().load(item.request_user_profile_pic).into(((Frnd_request) holder).request_user_image);
//                }
//
//                ((Frnd_request) holder).request_accept.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        new NotificationPresenter(notification, mContext).request_accept(item.request_id, notification.UserId);
//                        //   removeAt(position);
//                    }
//                });
//
//                ((Frnd_request) holder).request_decline.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        new NotificationPresenter(notification, mContext).request_denied(item.request_id, notification.UserId);
//                        //  removeAt(position);
//                    }
//                });
                break;

            case "Interest":
//
//                item = datas.get(position);
//
//                ((Interest) holder).txt_interest_username.setText(item.username + " " + "Interested in your post");
//                ((Interest) holder).interest_email.setText(item.email);
//                Picasso.get().load(item.post_image).into(((Interest) holder).interest_post);
//
//                if (item.user_profile_pic.equals("http://the-socialite.com/admin/")) {
//                    ((Interest) holder).interest_img_pic.setVisibility(View.VISIBLE);
//                    String avatarTitle = String.valueOf(item.username.charAt(0)).toUpperCase();
//                    ColorGenerator generator = ColorGenerator.MATERIAL;
//                    int randomcolor = generator.getRandomColor();
//
//                    TextDrawable.IBuilder builder = TextDrawable.builder().beginConfig().endConfig().round();
//
//                    TextDrawable drawable = builder.build(avatarTitle, randomcolor);
//                    ((Interest) holder).interest_img_pic.setImageDrawable(drawable);
//
//                } else {
//                    ((Interest) holder).interest_img_pic.setVisibility(View.GONE);
//                    Picasso.get().load(item.user_profile_pic).into(((Interest) holder).interest_user_pic);
//                }

                break;

            case "Rating":
//                item = datas.get(position);
//
//                ((Rating) holder).rate_text.setText(item.username + " " + "rates" + " " + item.rate + " " + "star to your post");
//                Picasso.get().load(item.post_image).into(((Rating) holder).rating_post);
//
//                if (item.user_profile_pic.equals("http://the-socialite.com/admin/")) {
//                    ((Rating) holder).rate_img_pic.setVisibility(View.VISIBLE);
//                    String avatarTitle = String.valueOf(item.username.charAt(0)).toUpperCase();
//                    ColorGenerator generator = ColorGenerator.MATERIAL;
//                    int randomcolor = generator.getRandomColor();
//
//                    TextDrawable.IBuilder builder = TextDrawable.builder().beginConfig().endConfig().round();
//
//                    TextDrawable drawable = builder.build(avatarTitle, randomcolor);
//                    ((Rating) holder).rate_img_pic.setImageDrawable(drawable);
//
//                } else {
//                    ((Rating) holder).rate_img_pic.setVisibility(View.GONE);
//                    Picasso.get().load(item.user_profile_pic).into(((Rating) holder).rate_user_pic);
//                }
                break;


            case "Public":
//                item = datas.get(position);
//                //    ((Notification_Public) holder).notificationconnect_text.setText(datas.get(position).request_username + " " + "has made connection with you");
//
//                if (item.request_user_profile_pic.equals("http://the-socialite.com/admin/")) {
//                    ((Public_user) holder).notification_connect_img_pic.setVisibility(View.VISIBLE);
//                    String avatarTitle = String.valueOf(item.request_username.charAt(0)).toUpperCase();
//                    ColorGenerator generator = ColorGenerator.MATERIAL;
//                    int randomcolor = generator.getRandomColor();
//
//                    TextDrawable.IBuilder builder = TextDrawable.builder().beginConfig().endConfig().round();
//
//                    TextDrawable drawable = builder.build(avatarTitle, randomcolor);
//                    ((Public_user) holder).notification_connect_img_pic.setImageDrawable(drawable);
//
//                } else {
//                    ((Public_user) holder).notification_connect_img_pic.setVisibility(View.GONE);
//                    Picasso.get().load(item.request_user_profile_pic).into(((Public_user) holder).notification_connect_user_pic);
//                }
//
//                ((Public_user) holder).notification_connect.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        new NotificationPresenter(notification, mContext).request_accept(item.request_id, notification.UserId);
//                        removeAt(position);
//                    }
//                });
                break;
         }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }
//
//    public class Frnd_request extends RecyclerView.ViewHolder {
//
//        @BindView(R.id.user_image)
//        CircularImageView user_image;
//
//        @BindView(R.id.request_user_image)
//        CircularImageView request_user_image;
//
//        @BindView(R.id.txt_user_name)
//        TextView txt_user_name;
//
//        @BindView(R.id.txt_request_username)
//        TextView txt_request_username;
//
//        @BindView(R.id.request_accept)
//        Button request_accept;
//
//        @BindView(R.id.request_decline)
//        Button request_decline;
//
//        @BindView(R.id.img_pic)
//        ImageView img_pic;
//
//        @BindView(R.id.request_img_pic)
//        ImageView request_img_pic;
//
//        public Frnd_request(@NonNull View itemView) {
//            super(itemView);
//            ButterKnife.bind(this, itemView);
//        }
//    }
//
//    public class Rating extends RecyclerView.ViewHolder {
//
//        @BindView(R.id.rate_text)
//        TextView rate_text;
//
//        @BindView(R.id.rate_user_pic)
//        CircularImageView rate_user_pic;
//
//        @BindView(R.id.rate_img_pic)
//        ImageView rate_img_pic;
//
//        @BindView(R.id.rating_post)
//        CircularImageView rating_post;
//
//        public Rating(@NonNull View itemView) {
//            super(itemView);
//            ButterKnife.bind(this, itemView);
//        }
//    }
//
//    public class Interest extends RecyclerView.ViewHolder {
//
//        @BindView(R.id.txt_interest_username)
//        TextView txt_interest_username;
//
//        @BindView(R.id.interest_email)
//        TextView interest_email;
//
//        @BindView(R.id.interest_user_pic)
//        CircularImageView interest_user_pic;
//
//        @BindView(R.id.interest_img_pic)
//        ImageView interest_img_pic;
//
//        @BindView(R.id.interest_post)
//        CircularImageView interest_post;
//
//
//        public Interest(@NonNull View itemView) {
//            super(itemView);
//            ButterKnife.bind(this, itemView);
//        }
//    }
//
//    public class Public_user extends RecyclerView.ViewHolder {
//
//        @BindView(R.id.notificationconnect_text)
//        TextView notificationconnect_text;
//
//        @BindView(R.id.notification_connect_user_pic)
//        CircularImageView notification_connect_user_pic;
//
//        @BindView(R.id.notification_connect_img_pic)
//        ImageView notification_connect_img_pic;
//
//        @BindView(R.id.notification_connect)
//        Button notification_connect;
//
//        public Public_user(@NonNull View itemView) {
//            super(itemView);
//            ButterKnife.bind(this, itemView);
//        }
//    }

//    public class holder extends RecyclerView.ViewHolder {
//
//        public holder(@NonNull View itemView) {
//            super(itemView);
//            ButterKnife.bind(this, itemView);
//        }
//    }

    public void removeAt(int pos) {
        datas.remove(pos);
        notifyDataSetChanged();
    }
}
