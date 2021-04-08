package com.ap.SociaLite.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
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
import com.ap.SociaLite.Application.RService;
import com.ap.SociaLite.Application.json;
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
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BusinessInteractionAdapter extends RecyclerView.Adapter<BusinessInteractionAdapter.MyHolder>{

    Boolean click = true;
    String rate = "";

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

        Drawable star1 = mContext.getDrawable(R.drawable.ic_rating_star1);
        Drawable star2 = mContext.getDrawable(R.drawable.ic_rating_star2);
        Drawable star3 = mContext.getDrawable(R.drawable.ic_rating_star3);
        Drawable star4 = mContext.getDrawable(R.drawable.ic_rating_star4);
        Drawable star5 = mContext.getDrawable(R.drawable.ic_rating_star5);

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

        if(post_lists.get(position).rate.equals("0")){
            holder.img_star.setImageDrawable(star1);
        }
        if(post_lists.get(position).rate.equals("1")){
            holder.img_star.setImageDrawable(star1);
        }
        if(post_lists.get(position).rate.equals("2")){
            holder.img_star.setImageDrawable(star2);
        }
        if(post_lists.get(position).rate.equals("3")){
            holder.img_star.setImageDrawable(star3);
        }
        if(post_lists.get(position).rate.equals("4")){
            holder.img_star.setImageDrawable(star4);
        }
        if(post_lists.get(position).rate.equals("5")){
            holder.img_star.setImageDrawable(star5);
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
                in.putExtra("post_id", id);
                view.getContext().startActivity(in);
            }
        });

        holder.textView15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(v.getContext(), CommentActivity.class);
                in.putExtra("post_id", id);
                v.getContext().startActivity(in);
            }
        });


        holder.rating_star1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                rate = "1";
                new BusinessFragmentPresenter(mContext, businessFragment).rating_post(businessFragment.user_id, id,rate);
                holder.rating_bar.setVisibility(View.GONE);
                holder.img_star.setImageDrawable(star1);
                click = true;
            }
        });

        holder.rating_star2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rate = "2";
                new BusinessFragmentPresenter(mContext, businessFragment).rating_post(businessFragment.user_id, id,rate);
                holder.rating_bar.setVisibility(View.GONE);
                holder.img_star.setImageDrawable(star2);
                click = true;
            }
        });

        holder.rating_star3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rate = "3";
                new BusinessFragmentPresenter(mContext, businessFragment).rating_post(businessFragment.user_id, id,rate);
                holder.rating_bar.setVisibility(View.GONE);
                holder.img_star.setImageDrawable(star3);
                click = true;
            }
        });

        holder.rating_star4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rate = "4";
                new BusinessFragmentPresenter(mContext, businessFragment).rating_post(businessFragment.user_id, id,rate);
                holder.rating_bar.setVisibility(View.GONE);
                holder.img_star.setImageDrawable(star4);
                click = true;
            }
        });

        holder.rating_star5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rate = "5";
                new BusinessFragmentPresenter(mContext, businessFragment).rating_post(businessFragment.user_id, id,rate);
                holder.rating_bar.setVisibility(View.GONE);
                holder.img_star.setImageDrawable(star5);
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

                Toast.makeText(mContext, "Coming Soon...", Toast.LENGTH_SHORT).show();
            }
        });

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user_id = post_lists.get(position).user_id;

                holder.card.setBackground(mContext.getResources().getDrawable(R.drawable.button_5dp_corner_rs));
                holder.card.setTextColor(Color.WHITE);
                holder.intrested.setBackground(mContext.getResources().getDrawable(R.drawable.border_rs));
                holder.intrested.setTextColor(Color.BLACK);
                holder.message.setBackground(mContext.getResources().getDrawable(R.drawable.border_rs));
                holder.message.setTextColor(Color.BLACK);

                Intent c = new Intent(view.getContext(), ViewCardActivity.class);
                c.putExtra("user_id",user_id);
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

        try {
            new RService.api().call(mContext).fetch_comments(id).enqueue(new Callback<json>() {
                @Override
                public void onResponse(Call<json> call, Response<json> response) {

                    if (response.body().status.equals("1")) {

                        if (response.body().comments.comments != null && response.body().comments.comments.size() > 0) {

                            Log.d("commnet_new", String.valueOf(response.body().comments.comments.size()));
                            holder.textView12.setText(response.body().comments.comments.get(response.body().comments.comments.size() - 1).user_name);

                            holder.textView11.setText(response.body().comments.comments.get(response.body().comments.comments.size() - 1).comment);

                            String img = response.body().comments.comments.get(response.body().comments.comments.size() - 1).profile_pic;
                            Picasso.get().load(img).into(holder.circularImageView3);

                        } else {
                            holder.layout.setVisibility(View.GONE);
                        }

                        if (response.body().comments.comments != null && response.body().comments.comments.size() > 1) {

                            holder.textView14.setText(response.body().comments.comments.get(response.body().comments.comments.size() - 2).user_name);
                            holder.textView13.setText(response.body().comments.comments.get(response.body().comments.comments.size() - 2).comment);

                            String img = response.body().comments.comments.get(response.body().comments.comments.size() - 2).profile_pic;
                            Picasso.get().load(img).into(holder.circularImageView5);

                        } else {
                            holder.layout1.setVisibility(View.GONE);
                        }

                    } else {
                        holder.layout.setVisibility(View.GONE);
                        holder.layout1.setVisibility(View.GONE);
                        //      Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<json> call, Throwable t) {
                    //   Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
                    //  Log.d("error", String.valueOf(t.getMessage()));
                }
            });
        } catch (Exception e) {

        }

    }




    @Override
    public int getItemCount() {
        return post_lists.size();
    }


    public class MyHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txt_name)
        TextView txt_name;

        @BindView(R.id.textView11)
        TextView textView11;

        @BindView(R.id.textView12)
        TextView textView12;


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

        @BindView(R.id.layout)
        ConstraintLayout layout;

        @BindView(R.id.layout1)
        ConstraintLayout layout1;

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

        @BindView(R.id.circularImageView)
        CircularImageView circularImageView;

        @BindView(R.id.circularImageView5)
        CircularImageView circularImageView5;

        @BindView(R.id.circularImageView3)
        CircularImageView circularImageView3;


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
        @BindView(R.id.textView15)
        TextView textView15;
        @BindView(R.id.textView14)
        TextView textView14;

        @BindView(R.id.textView13)
        TextView textView13;


        public MyHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
