package com.ap.SociaLite.Adapter.Profile_connection_adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
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
import com.ap.SociaLite.Application.RService;
import com.ap.SociaLite.Application.json;
import com.ap.SociaLite.Fragment.profile_connection_fragments.ProfileConnectionTimelineFragment;
import com.ap.SociaLite.Pojo.post_list;
import com.ap.SociaLite.Presenter.ProfileConnectionTimelineFragmentPresenter;
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

public class ProfileConnectionTimelineAdapter extends RecyclerView.Adapter<ProfileConnectionTimelineAdapter.MyHolder> {

    Boolean click = true;
    String rate = "";

    Context mContext;
    ProfileConnectionTimelineFragment profileConnectionTimelineFragment;
    List<post_list> post_lists = new ArrayList<>();
    post_list item;

    public ProfileConnectionTimelineAdapter(Context mContext, ProfileConnectionTimelineFragment profileConnectionTimelineFragment, List<post_list> post_lists) {
        this.mContext = mContext;
        this.profileConnectionTimelineFragment = profileConnectionTimelineFragment;
        this.post_lists = post_lists;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_caterory_post_adapter, parent, false);
        ProfileConnectionTimelineAdapter.MyHolder myHolder = new ProfileConnectionTimelineAdapter.MyHolder(view);
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
        Picasso.get().load(item.image).into(holder.img_category);
        holder.txt_description.setText(item.description);
        holder.txt_rating.setText(item.rate);
        holder.txt_time.setText(item.post_time);
        holder.txt_name.setText(item.username);

        if (post_lists.get(position).rate.equals("0")) {
            holder.img_star.setImageDrawable(star1);
        }
        if (post_lists.get(position).rate.equals("1")) {
            holder.img_star.setImageDrawable(star1);
        }
        if (post_lists.get(position).rate.equals("2")) {
            holder.img_star.setImageDrawable(star2);
        }
        if (post_lists.get(position).rate.equals("3")) {
            holder.img_star.setImageDrawable(star3);
        }
        if (post_lists.get(position).rate.equals("4")) {
            holder.img_star.setImageDrawable(star4);
        }
        if (post_lists.get(position).rate.equals("5")) {
            holder.img_star.setImageDrawable(star5);
        }

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
                                new ProfileConnectionTimelineFragmentPresenter(mContext, profileConnectionTimelineFragment).hide_post(profileConnectionTimelineFragment.user_id, id);
                                new ProfileConnectionTimelineFragmentPresenter(mContext, profileConnectionTimelineFragment).time_line_post(profileConnectionTimelineFragment.user_id);
                                break;

                            case R.id.save:
                                new ProfileConnectionTimelineFragmentPresenter(mContext, profileConnectionTimelineFragment).category_save_post(profileConnectionTimelineFragment.user_id, id);
                                break;

                            case R.id.report:
                                Intent in = new Intent(view.getContext(), Report.class);
                                in.putExtra("post_id", id);
                                view.getContext().startActivity(in);
                                break;

                            case R.id.copylink:
                                Toast.makeText(view.getContext(), "Coming Soon", Toast.LENGTH_SHORT).show();
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

                if (click == true) {
                    holder.rating_bar.setVisibility(View.VISIBLE);
                    click = false;
                } else {
                    holder.rating_bar.setVisibility(View.GONE);
                    click = true;
                }

            }
        });

        holder.rating_star1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                rate = "1";
                new ProfileConnectionTimelineFragmentPresenter(mContext, profileConnectionTimelineFragment).rating_post(profileConnectionTimelineFragment.user_id, id, rate);
                new ProfileConnectionTimelineFragmentPresenter(mContext, profileConnectionTimelineFragment).time_line_post(profileConnectionTimelineFragment.user_id);
                holder.rating_bar.setVisibility(View.GONE);
                holder.img_star.setImageDrawable(star1);
                click = true;

            }
        });

        holder.rating_star2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rate = "2";
                new ProfileConnectionTimelineFragmentPresenter(mContext, profileConnectionTimelineFragment).rating_post(profileConnectionTimelineFragment.user_id, id, rate);
                new ProfileConnectionTimelineFragmentPresenter(mContext, profileConnectionTimelineFragment).time_line_post(profileConnectionTimelineFragment.user_id);
                holder.rating_bar.setVisibility(View.GONE);
                holder.img_star.setImageDrawable(star1);
                click = true;
            }
        });

        holder.rating_star3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rate = "3";
                new ProfileConnectionTimelineFragmentPresenter(mContext, profileConnectionTimelineFragment).rating_post(profileConnectionTimelineFragment.user_id, id, rate);
                new ProfileConnectionTimelineFragmentPresenter(mContext, profileConnectionTimelineFragment).time_line_post(profileConnectionTimelineFragment.user_id);
                holder.rating_bar.setVisibility(View.GONE);
                holder.img_star.setImageDrawable(star1);
                click = true;
            }
        });

        holder.rating_star4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rate = "4";
                new ProfileConnectionTimelineFragmentPresenter(mContext, profileConnectionTimelineFragment).rating_post(profileConnectionTimelineFragment.user_id, id, rate);
                new ProfileConnectionTimelineFragmentPresenter(mContext, profileConnectionTimelineFragment).time_line_post(profileConnectionTimelineFragment.user_id);
                holder.rating_bar.setVisibility(View.GONE);
                holder.img_star.setImageDrawable(star1);
                click = true;
            }
        });

        holder.rating_star5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rate = "5";
                new ProfileConnectionTimelineFragmentPresenter(mContext, profileConnectionTimelineFragment).rating_post(profileConnectionTimelineFragment.user_id, id, rate);
                new ProfileConnectionTimelineFragmentPresenter(mContext, profileConnectionTimelineFragment).time_line_post(profileConnectionTimelineFragment.user_id);
                holder.rating_bar.setVisibility(View.GONE);
                holder.img_star.setImageDrawable(star1);
                click = true;
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

        holder.txt_allcomment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(view.getContext(), CommentActivity.class);
                in.putExtra("post_id", id);
                view.getContext().startActivity(in);
            }
        });

        try {
            new RService.api().call(mContext).fetch_comments(id).enqueue(new Callback<json>() {
                @Override
                public void onResponse(Call<json> call, Response<json> response) {

                    if (response.body().status.equals("1")) {

                        if (response.body().comments.comments != null && response.body().comments.comments.size() > 0) {

                            holder.txt_name_position_0.setText(response.body().comments.comments.get(response.body().comments.comments.size() - 1).user_name);
                            holder.txt_comment_pos_0.setText(response.body().comments.comments.get(response.body().comments.comments.size() - 1).comment);

                            String img = response.body().comments.comments.get(response.body().comments.comments.size() - 1).profile_pic;

                            if (img.equals("http://the-socialite.com/admin/")) {
                                Drawable upload_img = mContext.getDrawable(R.drawable.ic_user_icon);
                                holder.circularImageView3.setImageDrawable(upload_img);
                            } else {
                                Picasso.get().load(img).into(holder.circularImageView3);
                            }

                        } else {
                            holder.layout.setVisibility(View.GONE);
                        }

                        if (response.body().comments.comments != null && response.body().comments.comments.size() > 1) {

                            holder.txt_name_pos_1.setText(response.body().comments.comments.get(response.body().comments.comments.size() - 2).user_name);
                            holder.txt_comment_pos_1.setText(response.body().comments.comments.get(response.body().comments.comments.size() - 2).comment);

                            String img = response.body().comments.comments.get(response.body().comments.comments.size() - 2).profile_pic;

                            if (img.equals("http://the-socialite.com/admin/")) {
                                Drawable upload_img = mContext.getDrawable(R.drawable.ic_user_icon);
                                holder.circular.setImageDrawable(upload_img);
                            } else {
                                Picasso.get().load(img).into(holder.circular);
                            }

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

    public static class MyHolder extends RecyclerView.ViewHolder {

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

        @BindView(R.id.img_category)
        ImageView img_category;

        @BindView(R.id.txt_description)
        TextView txt_description;

        @BindView(R.id.txt_rating)
        TextView txt_rating;

        @BindView(R.id.txt_allcomment)
        TextView txt_allcomment;

        @BindView(R.id.txt_time)
        TextView txt_time;

        @BindView(R.id.txt_name_position_0)
        TextView txt_name_position_0;

        @BindView(R.id.txt_comment_pos_0)
        TextView txt_comment_pos_0;

        @BindView(R.id.txt_name_pos_1)
        TextView txt_name_pos_1;

        @BindView(R.id.txt_comment_pos_1)
        TextView txt_comment_pos_1;

        @BindView(R.id.layout)
        LinearLayout layout;

        @BindView(R.id.layout1)
        LinearLayout layout1;

        @BindView(R.id.circularImageView)
        CircularImageView circularImageView;

        @BindView(R.id.circular)
        CircularImageView circular;

        @BindView(R.id.circularImageView3)
        CircularImageView circularImageView3;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
