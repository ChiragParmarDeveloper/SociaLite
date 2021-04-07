package com.ap.SociaLite.Adapter;

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
import com.ap.SociaLite.Fragment.BusinessFragment;
import com.ap.SociaLite.Fragment.CategoryFragment;
import com.ap.SociaLite.Pojo.post_list;
import com.ap.SociaLite.Presenter.BusinessFragmentPresenter;
import com.ap.SociaLite.Presenter.CategoryFragmentPresenter;
import com.ap.SociaLite.R;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BusinessInteractionAdapter extends RecyclerView.Adapter<BusinessInteractionAdapter.MyHolder>{

    Boolean click = true;
    String rating = "";

    Context mContext;
    BusinessFragment businessFragment;
    List<post_list> post_lists = new ArrayList<>();
    post_list item;

    public BusinessInteractionAdapter(Context mContext, BusinessFragment businessFragment, List<post_list> post_lists) {
        this.mContext = mContext;
        this.businessFragment = businessFragment;
        this.post_lists = post_lists;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.business_interaction_post_rs, parent, false);
        BusinessInteractionAdapter.MyHolder myHolder = new BusinessInteractionAdapter.MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

        item = post_lists.get(position);
        String id = post_lists.get(position).post_id;

        Picasso.get().load(item.image).into(holder.post_image);
        holder.description_post.setText(item.description);
        holder.address_post.setText(item.location);
        holder.txt_rating.setText(item.rate);
        holder.txt_time.setText(item.post_time);
        holder.txt_name.setText(item.username);

        if (item.profile_pic.equals("http://the-socialite.com/admin/")) {
            Drawable upload_img = mContext.getDrawable(R.drawable.ic_user_icon);
            holder.circularImageView.setImageDrawable(upload_img);
        } else {
            Picasso.get().load(item.profile_pic).into(holder.circularImageView);
        }

        holder.constraint_popup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PopupMenu popup = new PopupMenu(mContext, holder.img_popup);
                //Inflating the Popup using xml file
                popup.getMenuInflater()
                        .inflate(R.menu.popup_menu, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()) {
                            case R.id.hide:
                                new BusinessFragmentPresenter(mContext, businessFragment).hide_post(businessFragment.user_id, id);
                         //       new BusinessFragmentPresenter(mContext, businessFragment).business_post(categoryFragment.user_id);
                                break;

                            case R.id.save:
                                new BusinessFragmentPresenter(mContext, businessFragment).category_save_post(businessFragment.user_id, id);
                                break;

                            case R.id.report:
                                Intent in = new Intent(view.getContext(), Report.class);
                                in.putExtra("post_id", id);
                                view.getContext().startActivity(in);
                                break;

                            case R.id.copylink:
                                Toast.makeText(view.getContext(), "Coming Soon...", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(mContext, "rating : " + rating, Toast.LENGTH_SHORT).show();
                holder.rating_bar.setVisibility(View.GONE);
                click = true;
            }
        });

        holder.rating_star2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rating = "2";
                Toast.makeText(mContext, "rating : " + rating, Toast.LENGTH_SHORT).show();
                holder.rating_bar.setVisibility(View.GONE);
                click = true;
            }
        });

        holder.rating_star3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rating = "3";
                Toast.makeText(mContext, "rating : " + rating, Toast.LENGTH_SHORT).show();
                holder.rating_bar.setVisibility(View.GONE);
                click = true;
            }
        });

        holder.rating_star4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rating = "4";
                Toast.makeText(mContext, "rating : " + rating, Toast.LENGTH_SHORT).show();
                holder.rating_bar.setVisibility(View.GONE);
                click = true;
            }
        });

        holder.rating_star5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rating = "5";
                Toast.makeText(mContext, "rating : " + rating, Toast.LENGTH_SHORT).show();
                holder.rating_bar.setVisibility(View.GONE);
                click = true;
            }
        });

        holder.intrested.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.intrested.setBackground(mContext.getResources().getDrawable(R.drawable.button_5dp_corner_rs));
                holder.intrested.setTextColor(Color.WHITE);
                holder.card.setBackground(mContext.getResources().getDrawable(R.drawable.border_rs));
                holder.card.setTextColor(Color.BLACK);
                holder.message.setBackground(mContext.getResources().getDrawable(R.drawable.border_rs));
                holder.message.setTextColor(Color.BLACK);
            }
        });

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                holder.card.setBackground(mContext.getResources().getDrawable(R.drawable.button_5dp_corner_rs));
                holder.card.setTextColor(Color.WHITE);
                holder.intrested.setBackground(mContext.getResources().getDrawable(R.drawable.border_rs));
                holder.intrested.setTextColor(Color.BLACK);
                holder.message.setBackground(mContext.getResources().getDrawable(R.drawable.border_rs));
                holder.message.setTextColor(Color.BLACK);

                Intent c = new Intent(view.getContext(), ViewCardActivity.class);
                view.getContext().startActivity(c);
            }
        });

        holder.message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                holder.message.setBackground(mContext.getResources().getDrawable(R.drawable.button_5dp_corner_rs));
                holder.message.setTextColor(Color.WHITE);
                holder.intrested.setBackground(mContext.getResources().getDrawable(R.drawable.border_rs));
                holder.intrested.setTextColor(Color.BLACK);
                holder.card.setBackground(mContext.getResources().getDrawable(R.drawable.border_rs));
                holder.card.setTextColor(Color.BLACK);

                Toast.makeText(mContext, "Coming Soon...", Toast.LENGTH_SHORT).show();
            }
        });


    }




    @Override
    public int getItemCount() {
        return post_lists.size();
    }


    public class MyHolder extends RecyclerView.ViewHolder {

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

        @BindView(R.id.circularImageView)
        CircularImageView circularImageView;

        @BindView(R.id.post_image)
        ImageView post_image;

        @BindView(R.id.description_post)
        TextView description_post;

        @BindView(R.id.address_post)
        TextView address_post;
        @BindView(R.id.txt_rating)
        TextView txt_rating;
        @BindView(R.id.txt_time)
        TextView txt_time;


        public MyHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
