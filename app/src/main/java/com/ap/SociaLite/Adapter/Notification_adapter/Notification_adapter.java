package com.ap.SociaLite.Adapter.Notification_adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
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

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Notification_adapter extends RecyclerView.Adapter<Notification_adapter.MyHolder> {

    Context mContext;
    Notification notification;
    List<data> datas;
    data item;

    public Notification_adapter(Context mContext, Notification notification, List<data> datas) {
        this.mContext = mContext;
        this.notification = notification;
        this.datas = datas;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

//        String array = Name.toString();
        //  String Type = "accept_decline";
        String Type = datas.get(viewType).notification_type;

//        for(int i = 0 ; i < Name.size() ; i++){
//            Type = (String) Name.get(i);
//            System.out.println("type is : " + i + " : " + Type);

        switch (Type) {
            case "Requested":
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_accept_decline_rs, parent, false);
                Notification_adapter.MyHolder myholder = new Notification_adapter.MyHolder(view);
                return myholder;

            case "connect":
                View view1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_connect_rs, parent, false);
                Notification_adapter.MyHolder holder1 = new Notification_adapter.MyHolder(view1);
                return holder1;

            case "Rat":
                View view2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_post_rs, parent, false);
                Notification_adapter.MyHolder holder2 = new Notification_adapter.MyHolder(view2);
                return holder2;

            case "Interest":
                View view3 = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_interested, parent, false);
                Notification_adapter.MyHolder holder3 = new Notification_adapter.MyHolder(view3);
                return holder3;
        }
//        System.out.println("type is : " + Type);
//        System.out.println("type is : " + viewType);

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        item = datas.get(position);

//        holder.txt_user_name.setText(item.username);
//        holder.txt_request_username.setText(item.request_username);

//        if (item.user_profile_pic.equals("http://the-socialite.com/admin/")) {
//            Drawable upload_img = mContext.getDrawable(R.drawable.ic_user_icon);
//            holder.user_image.setImageDrawable(upload_img);
//        } else {
//            Picasso.get().load(item.user_profile_pic).into(holder.user_image);
//        }


        // for (int i = 0; i < datas.get(i).intrested.size(); i++) {
        //     holder.notification_text.setText(intresteds.get(position).username + " " + "interested in your post");
        //    Picasso.get().load(intresteds.get(position).post_image).into(holder.notification_profile_user_1);
        //   Toast.makeText(mContext, "", Toast.LENGTH_SHORT).show();
        // }


        if (datas.get(position).user_profile_pic.equals("http://the-socialite.com/admin/")) {
            holder.img_pic.setVisibility(View.VISIBLE);
            String avatarTitle = String.valueOf(datas.get(position).username.charAt(0)).toUpperCase();
            ColorGenerator generator = ColorGenerator.MATERIAL;
            int randomcolor = generator.getRandomColor();

            TextDrawable.IBuilder builder = TextDrawable.builder().beginConfig().endConfig().round();

            TextDrawable drawable = builder.build(avatarTitle, randomcolor);
            holder.img_pic.setImageDrawable(drawable);

        } else {
            holder.img_pic.setVisibility(View.GONE);
            Picasso.get().load(item.user_profile_pic).into(holder.user_image);
        }

        if (item.request_user_profile_pic.equals("http://the-socialite.com/admin/")) {
            Drawable upload_img = mContext.getDrawable(R.drawable.ic_user_icon);
            holder.request_user_image.setImageDrawable(upload_img);
        } else {
            Picasso.get().load(item.request_user_profile_pic).into(holder.request_user_image);
        }

        if (datas.get(position).request_user_profile_pic.equals("http://the-socialite.com/admin/")) {
            holder.request_img_pic.setVisibility(View.VISIBLE);
            String avatarTitle = String.valueOf(datas.get(position).request_username.charAt(0)).toUpperCase();
            ColorGenerator generator = ColorGenerator.MATERIAL;
            int randomcolor = generator.getRandomColor();

            TextDrawable.IBuilder builder = TextDrawable.builder().beginConfig().endConfig().round();

            TextDrawable drawable = builder.build(avatarTitle, randomcolor);
            holder.request_img_pic.setImageDrawable(drawable);

        } else {
            holder.request_img_pic.setVisibility(View.GONE);
            Picasso.get().load(item.request_user_profile_pic).into(holder.request_user_image);
        }


        holder.request_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new NotificationPresenter(notification, mContext).request_accept(item.request_id, notification.UserId);
            }
        });

        holder.request_decline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new NotificationPresenter(notification, mContext).request_denied(item.request_id, notification.UserId);
                new NotificationPresenter(notification, mContext).user_notification_list(notification.UserId);
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

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

//        @BindView(R.id.notification_text)
//        TextView notification_text;

//        @BindView(R.id.notification_profile_user_1)
//        CircularImageView notification_profile_user_1;


        public MyHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
