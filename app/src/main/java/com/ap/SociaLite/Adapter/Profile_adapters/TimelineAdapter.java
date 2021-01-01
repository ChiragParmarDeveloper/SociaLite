package com.ap.SociaLite.Adapter.Profile_adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
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
import com.ap.SociaLite.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TimelineAdapter extends RecyclerView.Adapter<TimelineAdapter.MyHolder> {

    Boolean click = true;
    String rating = "";

    ArrayList Name;
    Context context;

    public TimelineAdapter(ArrayList name, Context context) {
        Name = name;
        this.context = context;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_caterory_post_adapter, parent, false);
        TimelineAdapter.MyHolder myHolder = new TimelineAdapter.MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

        holder.txt_name.setText((CharSequence) Name.get(position));

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

        holder.rating_star1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rating = "1";
                Toast.makeText(context, "rating : " + rating, Toast.LENGTH_SHORT).show();
                holder.rating_bar.setVisibility(View.GONE);
                click = true;
            }
        });

        holder.rating_star2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rating = "2";
                Toast.makeText(context, "rating : " + rating, Toast.LENGTH_SHORT).show();
                holder.rating_bar.setVisibility(View.GONE);
                click = true;
            }
        });

        holder.rating_star3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rating = "3";
                Toast.makeText(context, "rating : " + rating, Toast.LENGTH_SHORT).show();
                holder.rating_bar.setVisibility(View.GONE);
                click = true;
            }
        });

        holder.rating_star4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rating = "4";
                Toast.makeText(context, "rating : " + rating, Toast.LENGTH_SHORT).show();
                holder.rating_bar.setVisibility(View.GONE);
                click = true;
            }
        });

        holder.rating_star5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rating = "5";
                Toast.makeText(context, "rating : " + rating, Toast.LENGTH_SHORT).show();
                holder.rating_bar.setVisibility(View.GONE);
                click = true;
            }
        });

        holder.layout_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(view.getContext(), CommentActivity.class);
                view.getContext().startActivity(in);
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

        @BindView(R.id.rating_bar)
        CardView rating_bar;

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
