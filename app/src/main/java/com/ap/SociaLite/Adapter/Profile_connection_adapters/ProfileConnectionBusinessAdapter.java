package com.ap.SociaLite.Adapter.Profile_connection_adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.ap.SociaLite.Activity.CommentActivity;
import com.ap.SociaLite.Activity.Report;
import com.ap.SociaLite.Activity.ShareToFriend;
import com.ap.SociaLite.Activity.ViewCardActivity;
import com.ap.SociaLite.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileConnectionBusinessAdapter extends RecyclerView.Adapter<ProfileConnectionBusinessAdapter.MyHolder> {

    Boolean click = true;
    String rating = "";

    ArrayList Name;
    Context context;

    public ProfileConnectionBusinessAdapter(ArrayList name, Context context) {
        Name = name;
        this.context = context;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.business_interaction_post_rs, parent, false);
        ProfileConnectionBusinessAdapter.MyHolder myHolder = new ProfileConnectionBusinessAdapter.MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

        //remove comment only to make rating enable

//        Drawable star1 = mContext.getDrawable(R.drawable.ic_rating_star1);
//        Drawable star2 = mContext.getDrawable(R.drawable.ic_rating_star2);
//        Drawable star3 = mContext.getDrawable(R.drawable.ic_rating_star3);
//        Drawable star4 = mContext.getDrawable(R.drawable.ic_rating_star4);
//        Drawable star5 = mContext.getDrawable(R.drawable.ic_rating_star5);

        holder.txt_name.setText((CharSequence) Name.get(position));

        //this comment also

//        if(post_lists.get(position).rate.equals("0")){
//            holder.img_star.setImageDrawable(star1);
//        }
//        if(post_lists.get(position).rate.equals("1")){
//            holder.img_star.setImageDrawable(star1);
//        }
//        if(post_lists.get(position).rate.equals("2")){
//            holder.img_star.setImageDrawable(star2);
//        }
//        if(post_lists.get(position).rate.equals("3")){
//            holder.img_star.setImageDrawable(star3);
//        }
//        if(post_lists.get(position).rate.equals("4")){
//            holder.img_star.setImageDrawable(star4);
//        }
//        if(post_lists.get(position).rate.equals("5")){
//            holder.img_star.setImageDrawable(star5);
//        }

        holder.constraint_popup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PopupMenu popup = new PopupMenu(context, holder.img_popup);
                //Inflating the Popup using xml file
                popup.getMenuInflater()
                        .inflate(R.menu.popup_menu, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()) {
                            case R.id.hide:
                                Toast.makeText(view.getContext(), "Clicked hide", Toast.LENGTH_SHORT).show();
                                //startActivity(new Intent(App.this, App_Main.class));
                                break;

                            case R.id.help:
                                Toast.makeText(view.getContext(), "Clicked help", Toast.LENGTH_SHORT).show();
                                break;

                            case R.id.report:
                                Intent in = new Intent(view.getContext(), Report.class);
                                view.getContext().startActivity(in);
                                break;

                            case R.id.copylink:
                                Toast.makeText(view.getContext(), "Clicked copy", Toast.LENGTH_SHORT).show();
                                break;

                            default:
                                return false;
                        }
                        // return true;
                        return false;
                    }
                });
                popup.show(); //showing popup menu
                //  context.startActivity(new Intent(context, LoginActivity.class));
            }
        });

        holder.layout_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(view.getContext(), ShareToFriend.class);
                view.getContext().startActivity(in);
            }
        });

        holder.layout_star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(click == true)
                {
                    holder.rating_bar.setVisibility(View.VISIBLE);
                    click = false;
                }
                else
                {
                    holder.rating_bar.setVisibility(View.GONE);
                    click = true;
                }
            }
        });


        holder.layout_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(view.getContext(), CommentActivity.class);
                view.getContext().startActivity(in);
            }
        });

        holder.rating_star1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rating = "1";
                Toast.makeText(context, "rating : " + rating, Toast.LENGTH_SHORT).show();
                holder.rating_bar.setVisibility(View.GONE);
                //remove belove comment
                //    holder.img_star.setImageDrawable(star1);
                click = true;
            }
        });

        holder.rating_star2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rating = "2";
                Toast.makeText(context, "rating : " + rating, Toast.LENGTH_SHORT).show();
                holder.rating_bar.setVisibility(View.GONE);
                //remove belove comment
                //    holder.img_star.setImageDrawable(star2);
                click = true;
            }
        });

        holder.rating_star3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rating = "3";
                Toast.makeText(context, "rating : " + rating, Toast.LENGTH_SHORT).show();
                holder.rating_bar.setVisibility(View.GONE);
                //remove belove comment
                //    holder.img_star.setImageDrawable(star3);
                click = true;
            }
        });

        holder.rating_star4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rating = "4";
                Toast.makeText(context, "rating : " + rating, Toast.LENGTH_SHORT).show();
                holder.rating_bar.setVisibility(View.GONE);
                //remove belove comment
                //    holder.img_star.setImageDrawable(star4);
                click = true;
            }
        });

        holder.rating_star5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rating = "5";
                Toast.makeText(context, "rating : " + rating, Toast.LENGTH_SHORT).show();
                holder.rating_bar.setVisibility(View.GONE);
                //remove belove comment
                //    holder.img_star.setImageDrawable(star5);
                click = true;
            }
        });

        holder.intrested.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.intrested.setBackground(context.getResources().getDrawable(R.drawable.button_5dp_corner_rs));
                holder.intrested.setTextColor(Color.WHITE);
                holder.card.setBackground(context.getResources().getDrawable(R.drawable.border_rs));
                holder.card.setTextColor(Color.BLACK);
                holder.message.setBackground(context.getResources().getDrawable(R.drawable.border_rs));
                holder.message.setTextColor(Color.BLACK);
            }
        });

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                holder.card.setBackground(context.getResources().getDrawable(R.drawable.button_5dp_corner_rs));
                holder.card.setTextColor(Color.WHITE);
                holder.intrested.setBackground(context.getResources().getDrawable(R.drawable.border_rs));
                holder.intrested.setTextColor(Color.BLACK);
                holder.message.setBackground(context.getResources().getDrawable(R.drawable.border_rs));
                holder.message.setTextColor(Color.BLACK);

                Intent c = new Intent(view.getContext(), ViewCardActivity.class);
                view.getContext().startActivity(c);
            }
        });

        holder.message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                holder.message.setBackground(context.getResources().getDrawable(R.drawable.button_5dp_corner_rs));
                holder.message.setTextColor(Color.WHITE);
                holder.intrested.setBackground(context.getResources().getDrawable(R.drawable.border_rs));
                holder.intrested.setTextColor(Color.BLACK);
                holder.card.setBackground(context.getResources().getDrawable(R.drawable.border_rs));
                holder.card.setTextColor(Color.BLACK);
            }
        });
    }

    @Override
    public int getItemCount() {
        return Name.size();
    }

    public static class MyHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.txt_name)
        TextView txt_name;

        @BindView(R.id.img_popup)
        ImageView img_popup;

        @BindView(R.id.constraint_popup)
        ConstraintLayout constraint_popup;

        @BindView(R.id.layout_share)
        LinearLayout layout_share;

        @BindView(R.id.layout_comment)
        LinearLayout layout_comment;

        @BindView(R.id.layout_star)
        LinearLayout layout_star;

        @BindView(R.id.intrested)
        Button intrested;

        @BindView(R.id.card)
        Button card;

        @BindView(R.id.message)
        Button message;

        @BindView(R.id.rating_bar)
        CardView rating_bar;

        @BindView(R.id.img_star)
        ImageView img_star;

        @BindView(R.id.rating_star1)
        ImageView rating_star1;

        @BindView(R.id.rating_star2)
        ImageView rating_star2;

        @BindView(R.id.rating_star3)
        ImageView rating_star3;

        @BindView(R.id.rating_star4)
        ImageView rating_star4;

        @BindView(R.id.rating_star5)
        ImageView rating_star5;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
