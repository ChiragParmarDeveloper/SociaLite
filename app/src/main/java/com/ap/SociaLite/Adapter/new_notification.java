package com.ap.SociaLite.Adapter;

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
import com.ap.SociaLite.Application.RService;
import com.ap.SociaLite.Application.json;
import com.ap.SociaLite.Pojo.data;
import com.ap.SociaLite.Presenter.NotificationPresenter;
import com.ap.SociaLite.R;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class new_notification extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int TYPE_Request = -1;
    private final int TYPE_Public = 0;
    private final int TYPE_Rating = 1;
    private final int TYPE_Interest = 2;

    Context mContext;
    Notification notification;
    List<data> datas;
    data item;

    List<data> new_data = new ArrayList<>();

    public new_notification(Context mContext, Notification notification, List<data> datas) {
        this.mContext = mContext;
        this.notification = notification;
        this.datas = datas;
    }


    @NonNull
    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        Log.d("log", String.valueOf(viewType));
        switch (viewType) {

            case TYPE_Request:
                View headerView = LayoutInflater.from(mContext)
                        .inflate(R.layout.notification_accept_decline_rs, parent, false);
                return new Frnd_request(headerView);

            case TYPE_Public:
                View childView = LayoutInflater.from(mContext)
                        .inflate(R.layout.notification_connect_rs, parent, false);
                return new Public_user(childView);

            case TYPE_Rating:
                View childView1 = LayoutInflater.from(mContext)
                        .inflate(R.layout.notification_rating_rs, parent, false);
                return new Rating(childView1);

            case TYPE_Interest:
                View childView2 = LayoutInflater.from(mContext)
                        .inflate(R.layout.notification_interested, parent, false);
                return new Interest(childView2);

        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {

            case TYPE_Request:

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

                ((Frnd_request) holder).request_accept.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new NotificationPresenter(notification, mContext).request_accept(item.request_id, notification.UserId);
                        user_notification_list(notification.UserId, position);
                    }
                });

                ((Frnd_request) holder).request_decline.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new NotificationPresenter(notification, mContext).request_denied(item.request_id, notification.UserId);
                        user_notification_list(notification.UserId, position);
                    }
                });
                break;

            case TYPE_Public:
                item = datas.get(position);
                ((Public_user) holder).notificationconnect_text.setText(datas.get(position).request_username + " " + "has made connection with you");

                if (item.request_user_profile_pic.equals("http://the-socialite.com/admin/")) {
                    ((Public_user) holder).notification_connect_img_pic.setVisibility(View.VISIBLE);
                    String avatarTitle = String.valueOf(item.request_username.charAt(0)).toUpperCase();
                    ColorGenerator generator = ColorGenerator.MATERIAL;
                    int randomcolor = generator.getRandomColor();

                    TextDrawable.IBuilder builder = TextDrawable.builder().beginConfig().endConfig().round();

                    TextDrawable drawable = builder.build(avatarTitle, randomcolor);
                    ((Public_user) holder).notification_connect_img_pic.setImageDrawable(drawable);

                } else {
                    ((Public_user) holder).notification_connect_img_pic.setVisibility(View.GONE);
                    Picasso.get().load(item.request_user_profile_pic).into(((Public_user) holder).notification_connect_user_pic);
                }

                ((Public_user) holder).notification_connect.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new NotificationPresenter(notification, mContext).request_accept(item.request_id, notification.UserId);
                        user_notification_list(notification.UserId, position);
                    }
                });
                break;

            case TYPE_Rating:
                item = datas.get(position);

                ((Rating) holder).rate_text.setText(item.username + " " + "rates" + " " + item.rate + " " + "star to your post");
                Picasso.get().load(item.post_image).into(((Rating) holder).rating_post);

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

            case TYPE_Interest:

                item = datas.get(position);

                ((Interest) holder).txt_interest_username.setText(item.username + " " + "Interested in your post");
                ((Interest) holder).interest_email.setText(item.email);
                Picasso.get().load(item.post_image).into(((Interest) holder).interest_post);

                if (item.user_profile_pic.equals("http://the-socialite.com/admin/")) {
                    ((Interest) holder).interest_img_pic.setVisibility(View.VISIBLE);
                    String avatarTitle = String.valueOf(item.username.charAt(0)).toUpperCase();
                    ColorGenerator generator = ColorGenerator.MATERIAL;
                    int randomcolor = generator.getRandomColor();

                    TextDrawable.IBuilder builder = TextDrawable.builder().beginConfig().endConfig().round();

                    TextDrawable drawable = builder.build(avatarTitle, randomcolor);
                    ((Interest) holder).interest_img_pic.setImageDrawable(drawable);

                } else {
                    ((Interest) holder).interest_img_pic.setVisibility(View.GONE);
                    Picasso.get().load(item.user_profile_pic).into(((Interest) holder).interest_user_pic);
                }
                break;
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


    public class Public_user extends RecyclerView.ViewHolder {

        @BindView(R.id.notificationconnect_text)
        TextView notificationconnect_text;

        @BindView(R.id.notification_connect_user_pic)
        CircularImageView notification_connect_user_pic;

        @BindView(R.id.notification_connect_img_pic)
        ImageView notification_connect_img_pic;

        @BindView(R.id.notification_connect)
        Button notification_connect;

        public Public_user(@NonNull View itemView) {
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

        @BindView(R.id.rating_post)
        CircularImageView rating_post;

        public Rating(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    public class Interest extends RecyclerView.ViewHolder {

        @BindView(R.id.txt_interest_username)
        TextView txt_interest_username;

        @BindView(R.id.interest_email)
        TextView interest_email;

        @BindView(R.id.interest_user_pic)
        CircularImageView interest_user_pic;

        @BindView(R.id.interest_img_pic)
        ImageView interest_img_pic;

        @BindView(R.id.interest_post)
        CircularImageView interest_post;


        public Interest(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public int getItemViewType(int position) {
        switch (datas.get(position).notification_type) {

            case "Requested":
                return TYPE_Request;

            case "Public":
                return TYPE_Public;

            case "Rating":
                return TYPE_Rating;

            case "Interest":
                return TYPE_Interest;

            default:
                return -1;
        }
    }


    public void user_notification_list(String UserId, int position) {
        try {
            new RService.api().call(mContext).notification(UserId).enqueue(new Callback<json>() {
                @Override
                public void onResponse(Call<json> call, Response<json> response) {
                    if (response.body().status.equals("1")) {

                        if (response.body().data != null && response.body().data.size() > 0) {
                            new_data = response.body().data;
                            datas.set(position, new_data.get(position));
                            notifyItemChanged(position);
                        } else {

                        }
                    }
                }

                @Override
                public void onFailure(Call<json> call, Throwable t) {
                    //      Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
                    //    Log.d("error", String.valueOf(t.getMessage()));
                }
            });
        } catch (Exception e) {

        }
    }


}
