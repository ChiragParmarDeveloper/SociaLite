package com.ap.SociaLite.Activity;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.ap.SociaLite.Application.RService;
import com.ap.SociaLite.Application.Session;
import com.ap.SociaLite.Application.json;
import com.ap.SociaLite.Pojo.post_list;
import com.ap.SociaLite.Presenter.post_link_presenter;
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

public class post_link extends AppCompatActivity {

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

    @BindView(R.id.txt_name_position_0)
    TextView txt_name_position_0;

    @BindView(R.id.txt_comment_pos_0)
    TextView txt_comment_pos_0;


    @BindView(R.id.txt_name_pos_1)
    TextView txt_name_pos_1;

    @BindView(R.id.txt_comment_pos_1)
    TextView txt_comment_pos_1;

    @BindView(R.id.txt_time)
    TextView txt_time;

    @BindView(R.id.layout)
    RelativeLayout layout;

    @BindView(R.id.layout1)
    RelativeLayout layout1;

    @BindView(R.id.circularImageView)
    CircularImageView circularImageView;

    @BindView(R.id.circular)
    CircularImageView circular;

    @BindView(R.id.circularImageView3)
    CircularImageView circularImageView3;

    @BindView(R.id.viewer)
    ImageView viewer;

    @BindView(R.id.text_avatar_title)
    TextView text_avatar_title;

    @BindView(R.id.viewer_profile)
    ImageView viewer_profile;

    @BindView(R.id.img_pic)
    ImageView img_pic;

    String post_id;
    Boolean click = true;
    String rate = "";
    int position;
    public String user_id;
    List<post_list> new_post_list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_link);
        ButterKnife.bind(this);
        Session session = new Session(post_link.this);
        user_id = session.getUser_id();
        handleIntent();

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        handleIntent();
    }

    private void handleIntent() {
        // ATTENTION: This was auto-generated to handle app links.
        Intent appLinkIntent = getIntent();
        String appLinkAction = appLinkIntent.getAction();
        Uri appLinkData = appLinkIntent.getData();

        if (appLinkData != null) {

            post_id = appLinkData.getQueryParameter("post");
            Log.d("post_id", post_id);

            post(post_id);

        }
    }

    private void post(String post_id) {
        new RService.api().call(post_link.this).linking_post(post_id).enqueue(new Callback<json>() {
            @Override
            public void onResponse(Call<json> call, Response<json> response) {
                if (response.body().status.equals("1")) {
                    if (response.body().post_list != null && response.body().post_list.size() > 0) {
                        Toast.makeText(post_link.this, response.body().message, Toast.LENGTH_LONG).show();

                        String id = response.body().post_list.get(0).post_id;

                        Drawable star1 = getDrawable(R.drawable.ic_rating_star1);
                        Drawable star2 = getDrawable(R.drawable.ic_rating_star2);
                        Drawable star3 = getDrawable(R.drawable.ic_rating_star3);
                        Drawable star4 = getDrawable(R.drawable.ic_rating_star4);
                        Drawable star5 = getDrawable(R.drawable.ic_rating_star5);

                        if (response.body().post_list.get(0).profile_pic.equals("http://the-socialite.com/admin/")) {
                            img_pic.setVisibility(View.VISIBLE);

                            String avatarTitle = String.valueOf(response.body().post_list.get(0).username.charAt(0)).toUpperCase();
                            ColorGenerator generator = ColorGenerator.MATERIAL;
                            int randomcolor = generator.getRandomColor();

                            TextDrawable.IBuilder builder = TextDrawable.builder().beginConfig().endConfig().round();

                            TextDrawable drawable = builder.build(avatarTitle, randomcolor);
                            img_pic.setImageDrawable(drawable);
                        } else {
                            img_pic.setVisibility(View.GONE);
                            Picasso.get().load(response.body().post_list.get(0).profile_pic).into(circularImageView);
                        }

                        Picasso.get().load(response.body().post_list.get(0).image).into(img_category);
                        txt_description.setText(response.body().post_list.get(0).description);
                        txt_rating.setText(response.body().post_list.get(0).rate);
                        txt_time.setText(response.body().post_list.get(0).post_time);
                        txt_name.setText(response.body().post_list.get(0).username);

                        if (response.body().post_list.get(0).rate.equals("0")) {
                            img_star.setImageDrawable(star1);
                        }
                        if (response.body().post_list.get(0).rate.equals("1")) {
                            img_star.setImageDrawable(star1);
                        }
                        if (response.body().post_list.get(0).rate.equals("2")) {
                            img_star.setImageDrawable(star2);
                        }
                        if (response.body().post_list.get(0).rate.equals("3")) {
                            img_star.setImageDrawable(star3);
                        }
                        if (response.body().post_list.get(0).rate.equals("4")) {
                            img_star.setImageDrawable(star4);
                        }
                        if (response.body().post_list.get(0).rate.equals("5")) {
                            img_star.setImageDrawable(star5);
                        }

                        layout_share.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                String post_id = response.body().post_list.get(0).post_id;

                                Uri.Builder builder = new Uri.Builder();
                                builder.scheme("http")
                                        .authority("the-socialite.com")
                                        .appendPath("post/")
                                        .appendQueryParameter("post", post_id);

                                String myUrl = builder.build().toString();

                                Intent in = new Intent(view.getContext(), ShareToFriend.class);
                                in.putExtra("url", myUrl);
                                view.getContext().startActivity(in);
                            }
                        });

                        layout_star.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                if (click == true) {
                                    rating_bar.setVisibility(View.VISIBLE);
                                    click = false;
                                } else {
                                    rating_bar.setVisibility(View.GONE);
                                    click = true;
                                }
                            }
                        });

                        rating_star1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                rate = "1";
                                new post_link_presenter(post_link.this, post_link.this).rating_post(user_id, id, rate);
                                //  refresh(post_id, position);
                                rating_bar.setVisibility(View.GONE);
                                img_star.setImageDrawable(star1);
                                click = true;

                            }
                        });

                        rating_star2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                rate = "2";
                                new post_link_presenter(post_link.this, post_link.this).rating_post(user_id, id, rate);
                                //    Category_post_fragment(categoryFragment.interest_ids, position);
                                rating_bar.setVisibility(View.GONE);
                                img_star.setImageDrawable(star2);
                                click = true;
                            }
                        });

                        rating_star3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                rate = "3";
                                new post_link_presenter(post_link.this, post_link.this).rating_post(user_id, id, rate);
                                //  Category_post_fragment(categoryFragment.interest_ids, position);
                                rating_bar.setVisibility(View.GONE);
                                img_star.setImageDrawable(star3);
                                click = true;
                            }
                        });

                        rating_star4.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                rate = "4";
                                new post_link_presenter(post_link.this, post_link.this).rating_post(user_id, id, rate);
                                // Category_post_fragment(categoryFragment.interest_ids, position);
                                rating_bar.setVisibility(View.GONE);
                                img_star.setImageDrawable(star4);
                                click = true;
                            }
                        });

                        rating_star5.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                rate = "5";
                                new post_link_presenter(post_link.this, post_link.this).rating_post(user_id, id, rate);
                                //  Category_post_fragment(categoryFragment.interest_ids, position);
                                rating_bar.setVisibility(View.GONE);
                                img_star.setImageDrawable(star5);
                                click = true;
                            }
                        });

                        layout_comment.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent in = new Intent(view.getContext(), CommentActivity.class);
                                in.putExtra("post_id", id);
                                view.getContext().startActivity(in);
                            }
                        });

                        txt_allcomment.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent in = new Intent(view.getContext(), CommentActivity.class);
                                in.putExtra("post_id", id);
                                view.getContext().startActivity(in);
                            }
                        });


                        constraint_popup.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                PopupMenu popup = new PopupMenu(post_link.this, img_popup);
                                //Inflating the Popup using xml file
                                popup.getMenuInflater()
                                        .inflate(R.menu.popup_menu, popup.getMenu());

                                //registering popup with OnMenuItemClickListener
                                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                                    @Override
                                    public boolean onMenuItemClick(MenuItem item) {

                                        switch (item.getItemId()) {
                                            case R.id.hide:
                                                new post_link_presenter(post_link.this, post_link.this).hide_post(user_id, id);
                                                break;

                                            case R.id.save:
                                                new post_link_presenter(post_link.this, post_link.this).category_save_post(user_id, id);
                                                break;

                                            case R.id.report:
                                                Intent in = new Intent(view.getContext(), Report.class);
                                                in.putExtra("post_id", id);
                                                view.getContext().startActivity(in);
                                                break;

                                            case R.id.copylink:
                                                String post_id = response.body().post_list.get(position).post_id;

                                                Uri.Builder builder = new Uri.Builder();
                                                builder.scheme("http")
                                                        .authority("the-socialite.com")
                                                        .appendPath("post/")
                                                        .appendQueryParameter("post", post_id);
                                                //.appendQueryParameter("sort", "relevance")
                                                //.fragment("section-name");
                                                String myUrl = builder.build().toString();

                                                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                                                cm.setText(myUrl);
                                                Toast.makeText(post_link.this, "Copied", Toast.LENGTH_SHORT).show();

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


                        try {
                            new RService.api().call(post_link.this).fetch_comments(id).enqueue(new Callback<json>() {
                                @Override
                                public void onResponse(Call<json> call, Response<json> response) {

                                    if (response.body().status.equals("1")) {

                                        if (response.body().comments.comments != null && response.body().comments.comments.size() > 0) {

                                            Log.d("commnet_new", String.valueOf(response.body().comments.comments.size()));
                                            txt_name_position_0.setText(response.body().comments.comments.get(response.body().comments.comments.size() - 1).user_name);

                                            txt_comment_pos_0.setText(response.body().comments.comments.get(response.body().comments.comments.size() - 1).comment);

                                            String img = response.body().comments.comments.get(response.body().comments.comments.size() - 1).profile_pic;

                                            String username = response.body().comments.comments.get(response.body().comments.comments.size() - 1).user_name;

                                            if (img.equals("http://the-socialite.com/admin/")) {

                                                String avatarTitle = String.valueOf(username.charAt(0)).toUpperCase();
                                                ColorGenerator generator = ColorGenerator.MATERIAL;
                                                int randomcolor = generator.getRandomColor();

                                                TextDrawable.IBuilder builder = TextDrawable.builder().beginConfig().endConfig().round();

                                                TextDrawable drawable = builder.build(avatarTitle, randomcolor);
                                                viewer.setImageDrawable(drawable);
                                            } else {
                                                Picasso.get().load(img).into(circularImageView3);
                                            }

//
//                            if (img.equals("http://the-socialite.com/admin/")) {
//                                Drawable upload_img = mContext.getDrawable(R.drawable.ic_user_icon);
//                                holder.circularImageView3.setImageDrawable(upload_img);
//                            } else {
//                                Picasso.get().load(img).into(holder.circularImageView3);
//                            }

                                        } else {
                                            layout.setVisibility(View.GONE);
                                        }

                                        if (response.body().comments.comments != null && response.body().comments.comments.size() > 1) {

                                            txt_name_pos_1.setText(response.body().comments.comments.get(response.body().comments.comments.size() - 2).user_name);
                                            txt_comment_pos_1.setText(response.body().comments.comments.get(response.body().comments.comments.size() - 2).comment);

                                            String img = response.body().comments.comments.get(response.body().comments.comments.size() - 2).profile_pic;
                                            String username = response.body().comments.comments.get(response.body().comments.comments.size() - 2).user_name;

                                            if (img.equals("http://the-socialite.com/admin/")) {

                                                String avatarTitle = String.valueOf(username.charAt(0)).toUpperCase();
                                                ColorGenerator generator = ColorGenerator.MATERIAL;
                                                int randomcolor = generator.getRandomColor();

                                                TextDrawable.IBuilder builder = TextDrawable.builder().beginConfig().endConfig().round();

                                                TextDrawable drawable = builder.build(avatarTitle, randomcolor);
                                                viewer_profile.setImageDrawable(drawable);
                                            } else {
                                                Picasso.get().load(img).into(circular);
                                            }


//                            if (img.equals("http://the-socialite.com/admin/")) {
//                                Drawable upload_img = mContext.getDrawable(R.drawable.ic_user_icon);
//                                holder.circular.setImageDrawable(upload_img);
//                            } else {
//                                Picasso.get().load(img).into(holder.circular);
//                            }

                                        } else {
                                            layout1.setVisibility(View.GONE);
                                        }

                                    } else {
                                        layout.setVisibility(View.GONE);
                                        layout1.setVisibility(View.GONE);
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
                } else {
                    //         Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<json> call, Throwable t) {
                //    Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
                //    Log.d("error", String.valueOf(t.getMessage()));
            }
        });
    }

    public void refresh(String post_id, int position) {
        try {
            new RService.api().call(post_link.this).linking_post(post_id).enqueue(new Callback<json>() {
                @Override
                public void onResponse(Call<json> call, Response<json> response) {
                    if (response.body().status.equals("1")) {

                        if (response.body().post_list != null && response.body().post_list.size() > 0) {

                            new_post_list = response.body().post_list;
                            response.body().post_list.set(position, new_post_list.get(position));
                            //  notifyItemChanged(position);

                        }
                    } else {
                    }
                }

                @Override
                public void onFailure(Call<json> call, Throwable t) {
                    //     progressbar.setVisibility(View.GONE);
                    //   Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
                    //     Log.d("error", String.valueOf(t.getMessage()));
                }
            });
        } catch (Exception e) {

        }
    }

}

