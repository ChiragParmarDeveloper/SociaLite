package com.ap.SociaLite.Presenter;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.ap.SociaLite.Activity.CommentActivity;
import com.ap.SociaLite.Activity.Report;
import com.ap.SociaLite.Activity.ShareToFriend;
import com.ap.SociaLite.Activity.ViewCardActivity;
import com.ap.SociaLite.Activity.business_post_link;
import com.ap.SociaLite.Application.RService;
import com.ap.SociaLite.Application.json;
import com.ap.SociaLite.Contract.business_post_link_Contrast;
import com.ap.SociaLite.R;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class business_post_link_Presenter implements business_post_link_Contrast {

    public Context mContext;
    public business_post_link businessPostLink;

    public business_post_link_Presenter(Context mContext, business_post_link businessPostLink) {
        this.mContext = mContext;
        this.businessPostLink = businessPostLink;
    }

    public void business_post_link(String user_id, String post_id) {
        new RService.api().call(mContext).business_link_post(user_id, post_id).enqueue(new Callback<json>() {
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

                        Picasso.get().load(response.body().post_list.get(0).image).into(businessPostLink.post_image);
                        businessPostLink.description_post.setText(response.body().post_list.get(0).description);
                        businessPostLink.address_post.setText(response.body().post_list.get(0).location);
                        businessPostLink.txt_rating.setText(response.body().post_list.get(0).rate);
                        businessPostLink.txt_time.setText(response.body().post_list.get(0).post_time);
                        businessPostLink.txt_name.setText(response.body().post_list.get(0).username);


                        if (response.body().post_list.get(0).profile_pic.equals("http://the-socialite.com/admin/")) {
                            businessPostLink.img_pic.setVisibility(View.VISIBLE);

                            String avatarTitle = String.valueOf(response.body().post_list.get(0).username.charAt(0)).toUpperCase();
                            ColorGenerator generator = ColorGenerator.MATERIAL;
                            int randomcolor = generator.getRandomColor();

                            TextDrawable.IBuilder builder = TextDrawable.builder().beginConfig().endConfig().round();

                            TextDrawable drawable = builder.build(avatarTitle, randomcolor);
                            businessPostLink.img_pic.setImageDrawable(drawable);
                        } else {
                            businessPostLink.img_pic.setVisibility(View.GONE);
                            Picasso.get().load(response.body().post_list.get(0).profile_pic).into(businessPostLink.circularImageView);
                        }

                        if (response.body().post_list.get(0).rate.equals("0")) {
                            businessPostLink.img_star.setImageDrawable(star1);
                        }
                        if (response.body().post_list.get(0).rate.equals("1")) {
                            businessPostLink.img_star.setImageDrawable(star1);
                        }
                        if (response.body().post_list.get(0).rate.equals("2")) {
                            businessPostLink.img_star.setImageDrawable(star2);
                        }
                        if (response.body().post_list.get(0).rate.equals("3")) {
                            businessPostLink.img_star.setImageDrawable(star3);
                        }
                        if (response.body().post_list.get(0).rate.equals("4")) {
                            businessPostLink.img_star.setImageDrawable(star4);
                        }
                        if (response.body().post_list.get(0).rate.equals("5")) {
                            businessPostLink.img_star.setImageDrawable(star5);
                        }

                        businessPostLink.constraint_popup.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                PopupMenu popup = new PopupMenu(mContext, businessPostLink.img_popup);
                                //Inflating the Popup using xml file
                                popup.getMenuInflater()
                                        .inflate(R.menu.popup_menu, popup.getMenu());

                                //registering popup with OnMenuItemClickListener
                                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                                    @Override
                                    public boolean onMenuItemClick(MenuItem item) {

                                        switch (item.getItemId()) {
                                            case R.id.hide:
                                                new business_post_link_Presenter(mContext, businessPostLink).hide_post(businessPostLink.user_id, id);
                                                break;

                                            case R.id.save:
                                                new business_post_link_Presenter(mContext, businessPostLink).category_save_post(businessPostLink.user_id, id);
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
                                                        .appendPath("businesspost/")
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

                        businessPostLink.layout_share.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String post_id = response.body().post_list.get(0).post_id;

                                Uri.Builder builder = new Uri.Builder();
                                builder.scheme("http")
                                        .authority("the-socialite.com")
                                        .appendPath("businesspost/")
                                        .appendQueryParameter("post", post_id);
                                //.appendQueryParameter("sort", "relevance")
                                //.fragment("section-name");
                                String myUrl = builder.build().toString();

                                Intent in = new Intent(view.getContext(), ShareToFriend.class);
                                in.putExtra("url", myUrl);
                                view.getContext().startActivity(in);
                            }
                        });


                        businessPostLink.layout_star.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                if (businessPostLink.click == true) {
                                    businessPostLink.rating_bar.setVisibility(View.VISIBLE);
                                    businessPostLink.click = false;
                                } else {
                                    businessPostLink.rating_bar.setVisibility(View.GONE);
                                    businessPostLink.click = true;
                                }
                            }
                        });


                        businessPostLink.layout_comment.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent in = new Intent(view.getContext(), CommentActivity.class);
                                in.putExtra("post_id", id);
                                view.getContext().startActivity(in);
                            }
                        });

                        businessPostLink.textView15.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent in = new Intent(v.getContext(), CommentActivity.class);
                                in.putExtra("post_id", id);
                                v.getContext().startActivity(in);
                            }
                        });


                        businessPostLink.rating_star1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                businessPostLink.rate = "1";
                                new business_post_link_Presenter(mContext, businessPostLink).rating_post(businessPostLink.user_id, id, businessPostLink.rate);
                                businessPostLink.rating_bar.setVisibility(View.GONE);
                                businessPostLink.img_star.setImageDrawable(star1);
                                businessPostLink.click = true;
                            }
                        });

                        businessPostLink.rating_star2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                businessPostLink.rate = "2";
                                new business_post_link_Presenter(mContext, businessPostLink).rating_post(businessPostLink.user_id, id, businessPostLink.rate);

                                businessPostLink.rating_bar.setVisibility(View.GONE);
                                businessPostLink.img_star.setImageDrawable(star2);
                                businessPostLink.click = true;
                            }
                        });

                        businessPostLink.rating_star3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                businessPostLink.rate = "3";
                                new business_post_link_Presenter(mContext, businessPostLink).rating_post(businessPostLink.user_id, id, businessPostLink.rate);

                                businessPostLink.rating_bar.setVisibility(View.GONE);
                                businessPostLink.img_star.setImageDrawable(star3);
                                businessPostLink.click = true;
                            }
                        });

                        businessPostLink.rating_star4.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                businessPostLink.rate = "4";
                                new business_post_link_Presenter(mContext, businessPostLink).rating_post(businessPostLink.user_id, id, businessPostLink.rate);

                                businessPostLink.rating_bar.setVisibility(View.GONE);
                                businessPostLink.img_star.setImageDrawable(star4);
                                businessPostLink.click = true;
                            }
                        });

                        businessPostLink.rating_star5.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                businessPostLink.rate = "5";
                                new business_post_link_Presenter(mContext, businessPostLink).rating_post(businessPostLink.user_id, id, businessPostLink.rate);

                                businessPostLink.rating_bar.setVisibility(View.GONE);
                                businessPostLink.img_star.setImageDrawable(star5);
                                businessPostLink.click = true;
                            }
                        });

                        if (response.body().post_list.get(0).is_interest == null) {
                            //    Toast.makeText(mContext, "0", Toast.LENGTH_SHORT).show();
                        } else if (response.body().post_list.get(0).is_interest.equals("1")) {
                            businessPostLink.intrested.setBackground(mContext.getResources().getDrawable(R.drawable.button_5dp_corner_rs));
                            businessPostLink.intrested.setTextColor(Color.WHITE);
                        }

                        businessPostLink.intrested.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (response.body().post_list.get(0).is_interest == null) {
                                    // int pos = position;
                                    businessPostLink.intrested.setBackground(mContext.getResources().getDrawable(R.drawable.button_5dp_corner_rs));
                                    businessPostLink.intrested.setTextColor(Color.WHITE);
                                    new business_post_link_Presenter(mContext, businessPostLink).interest_button(businessPostLink.user_id, response.body().post_list.get(0).post_id);
                                } else if (response.body().post_list.get(0).is_interest.equals("1")) {
                                    //      int pos = position;
                                    businessPostLink.intrested.setBackground(mContext.getResources().getDrawable(R.drawable.border_rs));
                                    businessPostLink.intrested.setTextColor(Color.BLACK);
                                    //   Toast.makeText(mContext, "not null", Toast.LENGTH_SHORT).show();
                                    new business_post_link_Presenter(mContext, businessPostLink).remove_interest_button(businessPostLink.user_id, response.body().post_list.get(0).post_id);

                                }
                            }
                        });

                        businessPostLink.card.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                String user_id = response.body().post_list.get(0).user_id;

                                businessPostLink.card.setBackground(mContext.getResources().getDrawable(R.drawable.button_5dp_corner_rs));
                                businessPostLink.card.setTextColor(Color.WHITE);
//                holder.intrested.setBackground(mContext.getResources().getDrawable(R.drawable.border_rs));
//                holder.intrested.setTextColor(Color.BLACK);
                                businessPostLink.message.setBackground(mContext.getResources().getDrawable(R.drawable.border_rs));
                                businessPostLink.message.setTextColor(Color.BLACK);

                                Intent c = new Intent(view.getContext(), ViewCardActivity.class);
                                c.putExtra("user_id", user_id);
                                view.getContext().startActivity(c);
                            }
                        });

                        businessPostLink.message.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                businessPostLink.message.setBackground(mContext.getResources().getDrawable(R.drawable.button_5dp_corner_rs));
                                businessPostLink.message.setTextColor(Color.WHITE);
//                holder.intrested.setBackground(mContext.getResources().getDrawable(R.drawable.border_rs));
//                holder.intrested.setTextColor(Color.BLACK);
                                businessPostLink.card.setBackground(mContext.getResources().getDrawable(R.drawable.border_rs));
                                businessPostLink.card.setTextColor(Color.BLACK);

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
                                            businessPostLink.textView12.setText(response.body().comments.comments.get(response.body().comments.comments.size() - 1).user_name);

                                            businessPostLink.textView11.setText(response.body().comments.comments.get(response.body().comments.comments.size() - 1).comment);

                                            String img = response.body().comments.comments.get(response.body().comments.comments.size() - 1).profile_pic;
                                            String username = response.body().comments.comments.get(response.body().comments.comments.size() - 1).user_name;

                                            if (img.equals("http://the-socialite.com/admin/")) {

                                                String avatarTitle = String.valueOf(username.charAt(0)).toUpperCase();
                                                ColorGenerator generator = ColorGenerator.MATERIAL;
                                                int randomcolor = generator.getRandomColor();

                                                TextDrawable.IBuilder builder = TextDrawable.builder().beginConfig().endConfig().round();

                                                TextDrawable drawable = builder.build(avatarTitle, randomcolor);
                                                businessPostLink.viewer.setImageDrawable(drawable);
                                            } else {
                                                Picasso.get().load(img).into(businessPostLink.circularImageView3);
                                            }


//
//                            if (img.equals("http://the-socialite.com/admin/")) {
//                                Drawable upload_img = mContext.getDrawable(R.drawable.ic_user_icon);
//                                holder.circularImageView3.setImageDrawable(upload_img);
//                            } else {
//                          //      String img = response.body().comments.comments.get(response.body().comments.comments.size() - 1).profile_pic;
//                                Picasso.get().load(img).into(holder.circularImageView3);
//                            }

                                        } else {
                                            businessPostLink.layout.setVisibility(View.GONE);
                                        }

                                        if (response.body().comments.comments != null && response.body().comments.comments.size() > 1) {

                                            businessPostLink.textView14.setText(response.body().comments.comments.get(response.body().comments.comments.size() - 2).user_name);
                                            businessPostLink.textView13.setText(response.body().comments.comments.get(response.body().comments.comments.size() - 2).comment);

                                            String img = response.body().comments.comments.get(response.body().comments.comments.size() - 2).profile_pic;
                                            String username = response.body().comments.comments.get(response.body().comments.comments.size() - 2).user_name;

                                            if (img.equals("http://the-socialite.com/admin/")) {

                                                String avatarTitle = String.valueOf(username.charAt(0)).toUpperCase();
                                                ColorGenerator generator = ColorGenerator.MATERIAL;
                                                int randomcolor = generator.getRandomColor();

                                                TextDrawable.IBuilder builder = TextDrawable.builder().beginConfig().endConfig().round();

                                                TextDrawable drawable = builder.build(avatarTitle, randomcolor);
                                                businessPostLink.viewer_profile.setImageDrawable(drawable);
                                            } else {
                                                Picasso.get().load(img).into(businessPostLink.circularImageView5);
                                            }

//                            if (img.equals("http://the-socialite.com/admin/")) {
//                                Drawable upload_img = mContext.getDrawable(R.drawable.ic_user_icon);
//                                holder.circularImageView5.setImageDrawable(upload_img);
//                            } else {
//                           //     String img = response.body().comments.comments.get(response.body().comments.comments.size() - 2).profile_pic;
//                                Picasso.get().load(img).into(holder.circularImageView5);
//                            }

                                        } else {
                                            businessPostLink.layout1.setVisibility(View.GONE);
                                        }

                                    } else {
                                        businessPostLink.layout.setVisibility(View.GONE);
                                        businessPostLink.layout1.setVisibility(View.GONE);
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

    @Override
    public void hide_post(String user_id, String post_id) {
        try {
            new RService.api().call(mContext).dashboard_hidepost(user_id, post_id).enqueue(new Callback<json>() {
                @Override
                public void onResponse(Call<json> call, Response<json> response) {
                    if (response.body().status.equals("1")) {
                        //        Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();

                    } else {
                        //      Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
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
                        //           Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                    } else {
                        //           Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
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
    public void rating_post(String user_id, String post_id, String rate) {
        try {
            new RService.api().call(mContext).give_rating(user_id, post_id, rate).enqueue(new Callback<json>() {
                @Override
                public void onResponse(Call<json> call, Response<json> response) {
                    if (response.body().status.equals("1")) {
                        //        Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                        //        new BusinessFragmentPresenter(mContext, businessFragment).business_post(businessFragment.interest_id,businessFragment.user_id);
                    } else {
                        //      Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<json> call, Throwable t) {
                    //    Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
                    //    Log.d("error", String.valueOf(t.getMessage()));
                }
            });
        } catch (Exception e) {

        }
    }

    @Override
    public void interest_button(String user_id, String post_id) {
        try {
            new RService.api().call(mContext).like_interest_btn(user_id, post_id).enqueue(new Callback<json>() {
                @Override
                public void onResponse(Call<json> call, Response<json> response) {
                    if (response.body().status.equals("1")) {
                        //        Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                        //    business_post(businessFragment.interest_id, user_id);
                    } else {
                        //     Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<json> call, Throwable t) {
                    //     Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
                    //    Log.d("error", String.valueOf(t.getMessage()));
                }
            });
        } catch (Exception e) {

        }
    }

    @Override
    public void remove_interest_button(String user_id, String post_id) {
        try {
            new RService.api().call(mContext).remove_interest_btn(user_id, post_id).enqueue(new Callback<json>() {
                @Override
                public void onResponse(Call<json> call, Response<json> response) {
                    if (response.body().status.equals("1")) {
                        //         Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                        //    business_post(businessFragment.interest_id, user_id);
                    } else {
                        //     Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<json> call, Throwable t) {
                    //    Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
                    //    Log.d("error", String.valueOf(t.getMessage()));
                }
            });
        } catch (Exception e) {

        }
    }
}
