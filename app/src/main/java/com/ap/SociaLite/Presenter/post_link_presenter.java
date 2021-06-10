package com.ap.SociaLite.Presenter;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.widget.PopupMenu;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.ap.SociaLite.Activity.CommentActivity;
import com.ap.SociaLite.Activity.Report;
import com.ap.SociaLite.Activity.ShareToFriend;
import com.ap.SociaLite.Application.RService;
import com.ap.SociaLite.Application.json;
import com.ap.SociaLite.Contract.post_link_Contrast;
import com.ap.SociaLite.R;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class post_link_presenter implements post_link_Contrast {

    public Context mContext;
    public com.ap.SociaLite.Activity.post_link post_link;

    public post_link_presenter(Context mContext, com.ap.SociaLite.Activity.post_link post_link) {
        this.mContext = mContext;
        this.post_link = post_link;
    }


    @Override
    public void rating_post(String user_id, String post_id, String rate) {
        try {
            new RService.api().call(mContext).give_rating(user_id, post_id, rate).enqueue(new Callback<json>() {
                @Override
                public void onResponse(Call<json> call, Response<json> response) {
                    if (response.body().status.equals("1")) {
                        //        Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                        post(post_link.post_id);

                    } else {
                        //       Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<json> call, Throwable t) {
                    //     Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
                    //     Log.d("error", String.valueOf(t.getMessage()));
                }
            });
        } catch (Exception e) {

        }
    }

    @Override
    public void hide_post(String user_id, String post_id) {

        try {
            new RService.api().call(mContext).dashboard_hidepost(user_id, post_id).enqueue(new Callback<json>() {
                @Override
                public void onResponse(Call<json> call, Response<json> response) {
                    if (response.body().status.equals("1")) {
                        //          Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();

                    } else {
                        //   Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<json> call, Throwable t) {
                    // Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
                    // Log.d("error", String.valueOf(t.getMessage()));
                }
            });
        } catch (Exception e) {

        }
    }

    @Override
    public void category_save_post(String user_id, String post_id) {
        try {
            new RService.api().call(mContext).dashboard_savepost(user_id, post_id).enqueue(new Callback<json>() {
                @Override
                public void onResponse(Call<json> call, Response<json> response) {
                    if (response.body().status.equals("1")) {
                        //             Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                    } else {
                        //             Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<json> call, Throwable t) {
                    // Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
                    // Log.d("error", String.valueOf(t.getMessage()));
                }
            });
        } catch (Exception e) {

        }
    }

    @Override
    public void post(String post_id) {
        new RService.api().call(mContext).linking_post(post_id).enqueue(new Callback<json>() {
            @Override
            public void onResponse(Call<json> call, Response<json> response) {
                if (response.body().status.equals("1")) {
                    if (response.body().post_list != null && response.body().post_list.size() > 0) {

                        String id = response.body().post_list.get(0).post_id;

                        Drawable star1 = mContext.getDrawable(R.drawable.ic_rating_star1);
                        Drawable star2 = mContext.getDrawable(R.drawable.ic_rating_star2);
                        Drawable star3 = mContext.getDrawable(R.drawable.ic_rating_star3);
                        Drawable star4 = mContext.getDrawable(R.drawable.ic_rating_star4);
                        Drawable star5 = mContext.getDrawable(R.drawable.ic_rating_star5);

                        if (response.body().post_list.get(0).profile_pic.equals("http://the-socialite.com/admin/")) {
                            post_link.img_pic.setVisibility(View.VISIBLE);

                            String avatarTitle = String.valueOf(response.body().post_list.get(0).username.charAt(0)).toUpperCase();
                            ColorGenerator generator = ColorGenerator.MATERIAL;
                            int randomcolor = generator.getRandomColor();

                            TextDrawable.IBuilder builder = TextDrawable.builder().beginConfig().endConfig().round();

                            TextDrawable drawable = builder.build(avatarTitle, randomcolor);
                            post_link.img_pic.setImageDrawable(drawable);
                        } else {
                            post_link.img_pic.setVisibility(View.GONE);
                            Picasso.get().load(response.body().post_list.get(0).profile_pic).into(post_link.circularImageView);
                        }

                        Picasso.get().load(response.body().post_list.get(0).image).into(post_link.img_category);
                        post_link.txt_description.setText(response.body().post_list.get(0).description);
                        post_link.txt_rating.setText(response.body().post_list.get(0).rate);
                        post_link.txt_time.setText(response.body().post_list.get(0).post_time);
                        post_link.txt_name.setText(response.body().post_list.get(0).username);

                        if (response.body().post_list.get(0).rate.equals("0")) {
                            post_link.img_star.setImageDrawable(star1);
                        }
                        if (response.body().post_list.get(0).rate.equals("1")) {
                            post_link.img_star.setImageDrawable(star1);
                        }
                        if (response.body().post_list.get(0).rate.equals("2")) {
                            post_link.img_star.setImageDrawable(star2);
                        }
                        if (response.body().post_list.get(0).rate.equals("3")) {
                            post_link.img_star.setImageDrawable(star3);
                        }
                        if (response.body().post_list.get(0).rate.equals("4")) {
                            post_link.img_star.setImageDrawable(star4);
                        }
                        if (response.body().post_list.get(0).rate.equals("5")) {
                            post_link.img_star.setImageDrawable(star5);
                        }

                        post_link.layout_share.setOnClickListener(new View.OnClickListener() {
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
                                in.putExtra("url",myUrl);
                                in.putExtra("share_post","share_post");
                                in.putExtra("post_id",post_id);
                                view.getContext().startActivity(in);


                            }
                        });

                        post_link.layout_star.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                if (post_link.click == true) {
                                    post_link.rating_bar.setVisibility(View.VISIBLE);
                                    post_link.click = false;
                                } else {
                                    post_link.rating_bar.setVisibility(View.GONE);
                                    post_link.click = true;
                                }
                            }
                        });

                        post_link.rating_star1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                post_link.rate = "1";
                                new post_link_presenter(mContext, post_link).rating_post(post_link.user_id, id, post_link.rate);
                                //  refresh(post_id, position);
                                post_link.rating_bar.setVisibility(View.GONE);
                                post_link.img_star.setImageDrawable(star1);
                                post_link.click = true;

                            }
                        });

                        post_link.rating_star2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                post_link.rate = "2";
                                new post_link_presenter(mContext, post_link).rating_post(post_link.user_id, id, post_link.rate);
                                post_link.rating_bar.setVisibility(View.GONE);
                                post_link.img_star.setImageDrawable(star2);
                                post_link.click = true;
                            }
                        });

                        post_link.rating_star3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                post_link.rate = "3";
                                new post_link_presenter(mContext, post_link).rating_post(post_link.user_id, id, post_link.rate);
                                post_link.rating_bar.setVisibility(View.GONE);
                                post_link.img_star.setImageDrawable(star3);
                                post_link.click = true;
                            }
                        });

                        post_link.rating_star4.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                post_link.rate = "4";
                                new post_link_presenter(mContext, post_link).rating_post(post_link.user_id, id, post_link.rate);
                                post_link.rating_bar.setVisibility(View.GONE);
                                post_link.img_star.setImageDrawable(star4);
                                post_link.click = true;
                            }
                        });

                        post_link.rating_star5.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                post_link.rate = "5";
                                new post_link_presenter(mContext, post_link).rating_post(post_link.user_id, id, post_link.rate);
                                post_link.rating_bar.setVisibility(View.GONE);
                                post_link.img_star.setImageDrawable(star5);
                                post_link.click = true;
                            }
                        });

                        post_link.layout_comment.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent in = new Intent(view.getContext(), CommentActivity.class);
                                in.putExtra("post_id", id);
                                view.getContext().startActivity(in);
                            }
                        });

                        post_link.txt_allcomment.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent in = new Intent(view.getContext(), CommentActivity.class);
                                in.putExtra("post_id", id);
                                view.getContext().startActivity(in);
                            }
                        });


                        post_link.constraint_popup.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                PopupMenu popup = new PopupMenu(mContext, post_link.img_popup);
                                //Inflating the Popup using xml file
                                popup.getMenuInflater()
                                        .inflate(R.menu.popup_menu, popup.getMenu());

                                //registering popup with OnMenuItemClickListener
                                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                                    @Override
                                    public boolean onMenuItemClick(MenuItem item) {

                                        switch (item.getItemId()) {
                                            case R.id.hide:
                                                new post_link_presenter(mContext, post_link).hide_post(post_link.user_id, id);
                                                break;

                                            case R.id.save:
                                                new post_link_presenter(mContext, post_link).category_save_post(post_link.user_id, id);
                                                break;

                                            case R.id.report:
                                                Intent in = new Intent(view.getContext(), Report.class);
                                                in.putExtra("post_id", id);
                                                view.getContext().startActivity(in);
                                                break;

                                            case R.id.copylink:
                                                String post_id = response.body().post_list.get(0).post_id;

                                                Uri.Builder builder = new Uri.Builder();
                                                builder.scheme("http")
                                                        .authority("the-socialite.com")
                                                        .appendPath("post/")
                                                        .appendQueryParameter("post", post_id);
                                                //.appendQueryParameter("sort", "relevance")
                                                //.fragment("section-name");
                                                String myUrl = builder.build().toString();

                                                ClipboardManager cm = (ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
                                                cm.setText(myUrl);
                                                Toast.makeText(mContext, "Copied", Toast.LENGTH_SHORT).show();

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
                            new RService.api().call(mContext).fetch_comments(id).enqueue(new Callback<json>() {
                                @Override
                                public void onResponse(Call<json> call, Response<json> response) {

                                    if (response.body().status.equals("1")) {

                                        if (response.body().comments.comments != null && response.body().comments.comments.size() > 0) {

                                            Log.d("commnet_new", String.valueOf(response.body().comments.comments.size()));
                                            post_link.txt_name_position_0.setText(response.body().comments.comments.get(response.body().comments.comments.size() - 1).user_name);

                                            post_link.txt_comment_pos_0.setText(response.body().comments.comments.get(response.body().comments.comments.size() - 1).comment);

                                            String img = response.body().comments.comments.get(response.body().comments.comments.size() - 1).profile_pic;

                                            String username = response.body().comments.comments.get(response.body().comments.comments.size() - 1).user_name;

                                            if (img.equals("http://the-socialite.com/admin/")) {

                                                String avatarTitle = String.valueOf(username.charAt(0)).toUpperCase();
                                                ColorGenerator generator = ColorGenerator.MATERIAL;
                                                int randomcolor = generator.getRandomColor();

                                                TextDrawable.IBuilder builder = TextDrawable.builder().beginConfig().endConfig().round();

                                                TextDrawable drawable = builder.build(avatarTitle, randomcolor);
                                                post_link.viewer.setImageDrawable(drawable);
                                            } else {
                                                Picasso.get().load(img).into(post_link.circularImageView3);
                                            }

//
//                            if (img.equals("http://the-socialite.com/admin/")) {
//                                Drawable upload_img = mContext.getDrawable(R.drawable.ic_user_icon);
//                                holder.circularImageView3.setImageDrawable(upload_img);
//                            } else {
//                                Picasso.get().load(img).into(holder.circularImageView3);
//                            }

                                        } else {
                                            post_link.layout.setVisibility(View.GONE);
                                        }

                                        if (response.body().comments.comments != null && response.body().comments.comments.size() > 1) {

                                            post_link.txt_name_pos_1.setText(response.body().comments.comments.get(response.body().comments.comments.size() - 2).user_name);
                                            post_link.txt_comment_pos_1.setText(response.body().comments.comments.get(response.body().comments.comments.size() - 2).comment);

                                            String img = response.body().comments.comments.get(response.body().comments.comments.size() - 2).profile_pic;
                                            String username = response.body().comments.comments.get(response.body().comments.comments.size() - 2).user_name;

                                            if (img.equals("http://the-socialite.com/admin/")) {

                                                String avatarTitle = String.valueOf(username.charAt(0)).toUpperCase();
                                                ColorGenerator generator = ColorGenerator.MATERIAL;
                                                int randomcolor = generator.getRandomColor();

                                                TextDrawable.IBuilder builder = TextDrawable.builder().beginConfig().endConfig().round();

                                                TextDrawable drawable = builder.build(avatarTitle, randomcolor);
                                                post_link.viewer_profile.setImageDrawable(drawable);
                                            } else {
                                                Picasso.get().load(img).into(post_link.circular);
                                            }


//                            if (img.equals("http://the-socialite.com/admin/")) {
//                                Drawable upload_img = mContext.getDrawable(R.drawable.ic_user_icon);
//                                holder.circular.setImageDrawable(upload_img);
//                            } else {
//                                Picasso.get().load(img).into(holder.circular);
//                            }

                                        } else {
                                            post_link.layout1.setVisibility(View.GONE);
                                        }

                                    } else {
                                        post_link.layout.setVisibility(View.GONE);
                                        post_link.layout1.setVisibility(View.GONE);
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
}
