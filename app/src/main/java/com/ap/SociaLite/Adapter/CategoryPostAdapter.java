package com.ap.SociaLite.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
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
import com.ap.SociaLite.Fragment.CategoryFragment;
import com.ap.SociaLite.Pojo.post_list;
import com.ap.SociaLite.Presenter.CategoryFragmentPresenter;
import com.ap.SociaLite.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryPostAdapter extends RecyclerView.Adapter<CategoryPostAdapter.MyHolder> {

    Boolean click = true;
    String rate = "";

    Context mContext;
    CategoryFragment categoryFragment;
    List<post_list> post_lists = new ArrayList<>();
    post_list item;

    public CategoryPostAdapter(Context context, List<post_list> list, CategoryFragment fragment) {
        this.mContext = context;
        this.post_lists = list;
        this.categoryFragment = fragment;
    }

    @NonNull
    @Override
    public CategoryPostAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_caterory_post_adapter, parent, false);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryPostAdapter.MyHolder holder, int position) {
        item = post_lists.get(position);
        String id = post_lists.get(position).post_id;
        //    holder.txt_name.setText((CharSequence) Name.get(position));
        Picasso.get().load(item.image).placeholder(R.mipmap.ic_launcher).into(holder.img_category);
        holder.txt_description.setText(item.description);
        holder.txt_rating.setText(item.rate);
        //   String rating = item.rate;

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
                                new CategoryFragmentPresenter(mContext, categoryFragment).hide_post(categoryFragment.user_id, id);
                                new CategoryFragmentPresenter(mContext, categoryFragment).Category_post_fragment(categoryFragment.user_id);
                                break;

                            case R.id.save:
                                new CategoryFragmentPresenter(mContext, categoryFragment).category_save_post(categoryFragment.user_id, id);
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
                new CategoryFragmentPresenter(mContext, categoryFragment).rating_post(categoryFragment.user_id, id, rate);
                new CategoryFragmentPresenter(mContext, categoryFragment).Category_post_fragment(categoryFragment.user_id);
                holder.rating_bar.setVisibility(View.GONE);
                click = true;
            }
        });

        holder.rating_star2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rate = "2";
                new CategoryFragmentPresenter(mContext, categoryFragment).rating_post(categoryFragment.user_id, id, rate);
                new CategoryFragmentPresenter(mContext, categoryFragment).Category_post_fragment(categoryFragment.user_id);
                holder.rating_bar.setVisibility(View.GONE);
                click = true;
            }
        });

        holder.rating_star3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rate = "3";
                new CategoryFragmentPresenter(mContext, categoryFragment).rating_post(categoryFragment.user_id, id, rate);
                new CategoryFragmentPresenter(mContext, categoryFragment).Category_post_fragment(categoryFragment.user_id);

                holder.rating_bar.setVisibility(View.GONE);
                click = true;
            }
        });

        holder.rating_star4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rate = "4";
                new CategoryFragmentPresenter(mContext, categoryFragment).rating_post(categoryFragment.user_id, id, rate);
                new CategoryFragmentPresenter(mContext, categoryFragment).Category_post_fragment(categoryFragment.user_id);

                holder.rating_bar.setVisibility(View.GONE);
                click = true;
            }
        });

        holder.rating_star5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rate = "5";
                new CategoryFragmentPresenter(mContext, categoryFragment).rating_post(categoryFragment.user_id, id, rate);
                new CategoryFragmentPresenter(mContext, categoryFragment).Category_post_fragment(categoryFragment.user_id);
                holder.rating_bar.setVisibility(View.GONE);
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

                            Log.d("commnet_new", String.valueOf(response.body().comments.comments.size()));
                            holder.txt_name_position_0.setText(response.body().comments.comments.get(response.body().comments.comments.size()-1).user_name);

                            holder.txt_comment_pos_0.setText(response.body().comments.comments.get(response.body().comments.comments.size()-1).comment);

                         //   holder.txt_comment_pos_0.setText(response.body().comments.comments.get(0).comment);

                        }
                        else
                        {
                            holder.layout.setVisibility(View.GONE);
                        }

                        if (response.body().comments.comments != null && response.body().comments.comments.size() > 1) {

                            holder.txt_name_pos_1.setText(response.body().comments.comments.get(response.body().comments.comments.size()-2).user_name);
                            holder.txt_comment_pos_1.setText(response.body().comments.comments.get(response.body().comments.comments.size()-2).comment);

                        }
                        else
                        {
                            holder.layout1.setVisibility(View.GONE);
                        }

                    } else {
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

        @BindView(R.id.img_category)
        ImageView img_category;

        @BindView(R.id.txt_description)
        TextView txt_description;

        @BindView(R.id.txt_rating)
        TextView txt_rating;

        @BindView(R.id.txt_allcomment)
        TextView txt_allcomment;

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





        public MyHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}