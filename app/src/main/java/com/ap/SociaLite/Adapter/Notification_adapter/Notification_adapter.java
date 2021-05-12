package com.ap.SociaLite.Adapter.Notification_adapter;

import android.content.Context;
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
    // String Type;

    public Notification_adapter(Context mContext, Notification notification, List<data> datas) {
        this.mContext = mContext;
        this.notification = notification;
        this.datas = datas;
    }

    @Override
    public int getItemViewType(int position) {
        // return a value between 0 and (getViewTypeCount - 1)
        switch (datas.get(position).notification_type) {
            case "Requested":
                return 0;

            case "Public":
                return 1;

            case "Rating":
                return 2;

            case "Interest":
                return 3;

            default:
                return -1;

        }
    }

    @NonNull
    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        int Type = getItemViewType(viewType);

        switch (Type) {
            case 0:
                //Requested
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_accept_decline_rs, parent, false);
                Notification_adapter.Frnd_request myholder = new Notification_adapter.Frnd_request(view);
                return myholder;

            case 1:
                //Public
                View view1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_connect_rs, parent, false);
                Notification_adapter.Public holder1 = new Notification_adapter.Public(view1);
                return holder1;

            case 2:
                //Rating
                View view2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_rating_rs, parent, false);
                Notification_adapter.Rating holder2 = new Notification_adapter.Rating(view2);
                return holder2;

            case 3:

                //Interest
                View view3 = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_interested, parent, false);
                Notification_adapter.Interest holder3 = new Notification_adapter.Interest(view3);
                return holder3;
        }

        return null;
    }


    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {
        String type = datas.get(position).notification_type;

        if (type != null) {
            switch (type) {
                case "Requested":
                    item = datas.get(position);

                    ((Frnd_request) holder).txt_user_name.setText(item.username);
                    ((Frnd_request) holder).txt_request_username.setText(item.request_username);

                    if (item.user_profile_pic.equals("http://the-socialite.com/admin/")) {
                        ((Frnd_request) holder).img_pic.setVisibility(View.VISIBLE);
                        String avatarTitle = String.valueOf(item.username.charAt(0)).toUpperCase();
                        ColorGenerator generator = ColorGenerator.MATERIAL;
                        int randomcolor = generator.getRandomColor();

                        TextDrawable.IBuilder builder = TextDrawable.builder().beginConfig().endConfig().round();

                        TextDrawable drawable = builder.build(avatarTitle, randomcolor);
                        ((Frnd_request) holder).img_pic.setImageDrawable(drawable);

                    } else {
                        ((Frnd_request) holder).img_pic.setVisibility(View.GONE);
                        Picasso.get().load(item.user_profile_pic).into(((Frnd_request) holder).user_image);
                    }

                    if (datas.get(position).request_user_profile_pic.equals("http://the-socialite.com/admin/")) {
                        ((Frnd_request) holder).request_img_pic.setVisibility(View.VISIBLE);
                        String avatarTitle = String.valueOf(datas.get(position).request_username.charAt(0)).toUpperCase();
                        ColorGenerator generator = ColorGenerator.MATERIAL;
                        int randomcolor = generator.getRandomColor();

                        TextDrawable.IBuilder builder = TextDrawable.builder().beginConfig().endConfig().round();

                        TextDrawable drawable = builder.build(avatarTitle, randomcolor);
                        ((Frnd_request) holder).request_img_pic.setImageDrawable(drawable);

                    } else {
                        ((Frnd_request) holder).request_img_pic.setVisibility(View.GONE);
                        Picasso.get().load(item.request_user_profile_pic).into(((Frnd_request) holder).request_user_image);
                    }

//                    ((Frnd_request)holder).request_accept.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            new NotificationPresenter(notification, mContext).request_accept(item.request_id, notification.UserId);
//                        }
//                    });
//
//                    ((Frnd_request)holder).request_decline.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            new NotificationPresenter(notification, mContext).request_denied(item.request_id, notification.UserId);
//                        }
//                    });
                    break;

                case "Public":
                    break;

                case "Rating":
                    item = datas.get(position);

                    ((Rating) holder).rate_text.setText(item.username + " " + "rates" + " " + item.rate + " " + "star to your photo");

                    if (item.user_profile_pic.equals("http://the-socialite.com/admin/")) {
                        ((Rating) holder).rate_img_pic.setVisibility(View.VISIBLE);
                        String avatarTitle = String.valueOf(item.username.charAt(0)).toUpperCase();
                        ColorGenerator generator = ColorGenerator.MATERIAL;
                        int randomcolor = generator.getRandomColor();

                        TextDrawable.IBuilder builder = TextDrawable.builder().beginConfig().endConfig().round();

                        TextDrawable drawable = builder.build(avatarTitle, randomcolor);
                        ((Rating) holder).rate_img_pic.setImageDrawable(drawable);

                    } else {
                        ((Rating) holder).rate_img_pic.setVisibility(View.GONE);
                        Picasso.get().load(item.user_profile_pic).into(((Rating) holder).rate_user_pic);
                    }
                    break;

                case "Interest":
                    break;

            }
        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class Frnd_request extends RecyclerView.ViewHolder {

        @BindView(R.id.user_image)
        CircularImageView user_image;

        @BindView(R.id.request_user_image)
        CircularImageView request_user_image;

        @BindView(R.id.txt_user_name)
        TextView txt_user_name;

        @BindView(R.id.txt_request_username)
        TextView txt_request_username;

        @BindView(R.id.request_accept)
        Button request_accept;

        @BindView(R.id.request_decline)
        Button request_decline;

        @BindView(R.id.img_pic)
        ImageView img_pic;

        @BindView(R.id.request_img_pic)
        ImageView request_img_pic;

        public Frnd_request(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class Rating extends RecyclerView.ViewHolder {

        @BindView(R.id.rate_text)
        TextView rate_text;

        @BindView(R.id.rate_user_pic)
        CircularImageView rate_user_pic;

        @BindView(R.id.rate_img_pic)
        ImageView rate_img_pic;


        public Rating(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class Interest extends RecyclerView.ViewHolder {

        public Interest(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class Public extends RecyclerView.ViewHolder {

        public Public(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

//    @NonNull
//    @Override
//    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

//
//
//
//    @Override
//    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

//    }