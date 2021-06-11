package com.ap.SociaLite.Presenter;

import android.content.Context;
import android.view.View;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.ap.SociaLite.Activity.HomeActivity;
import com.ap.SociaLite.Application.RService;
import com.ap.SociaLite.Application.json;
import com.ap.SociaLite.Contract.HomeActivityContract;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivityPresenter implements HomeActivityContract {

    public Context mContext;
    public HomeActivity homeActivity;

    public HomeActivityPresenter(Context context, HomeActivity fragment) {
        this.mContext = context;
        this.homeActivity = fragment;
    }

    @Override
    public void fetch_profile(String user_id) {
        try {
            new RService.api().call(mContext).profile(user_id).enqueue(new Callback<json>() {
                @Override
                public void onResponse(Call<json> call, Response<json> response) {

                    if (response.body().status.equals("1")) {

                        if (response.body().user_details != null) {

                            if (response.body().user_details.profile_pic.equals("http://the-socialite.com/admin/")) {
                                homeActivity.home_latter.setVisibility(View.VISIBLE);
                                String avatarTitle = String.valueOf(response.body().user_details.username.charAt(0)).toUpperCase();
                                ColorGenerator generator = ColorGenerator.MATERIAL;
                                int randomcolor = generator.getRandomColor();

                                TextDrawable.IBuilder builder = TextDrawable.builder().beginConfig().endConfig().round();

                                TextDrawable drawable = builder.build(avatarTitle, randomcolor);
                                homeActivity.home_latter.setImageDrawable(drawable);
                            } else {
                                homeActivity.home_latter.setVisibility(View.GONE);
                                Picasso.get().load(response.body().user_details.profile_pic).into(homeActivity.img_dp);
                                //          Picasso.get().load(datas.get(position).profile_pic).into(holder.profile);
                            }


//                            if (response.body().user_details.profile_pic.equals("http://the-socialite.com/admin/")) {
//                                Drawable upload_img = mContext.getDrawable(R.drawable.ic_user_icon);
//                                homeActivity.img_dp.setImageDrawable(upload_img);
//                            } else {
//                                Picasso.get().load(response.body().user_details.profile_pic).into(homeActivity.img_dp);
//                            }

                            String input = response.body().user_details.username;
                            String output = input.substring(0, 1).toUpperCase() + input.substring(1);
                            homeActivity.txt_name.setText(output);

                            homeActivity.txt_email.setText(response.body().user_details.email);


                        }
                    } else {
                        //         Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<json> call, Throwable t) {
                    //       Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_LONG).show();
                    //       Log.d("error", String.valueOf(t.getMessage()));
                }
            });
        } catch (
                Exception e) {
        }

    }

    @Override
    public void save_token(String user_id, String token_id) {
        try {
            new RService.api().call(mContext).token(user_id, token_id).enqueue(new Callback<json>() {
                @Override
                public void onResponse(Call<json> call, Response<json> response) {

                    if (response.body().status.equals("1")) {
                        //       Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                    } else {
                        //         Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<json> call, Throwable t) {
                    //       Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_LONG).show();
                    //       Log.d("error", String.valueOf(t.getMessage()));
                }
            });
        } catch (Exception e) {
        }

    }

    @Override
    public void user_notification_list(String UserId) {
        try {
            new RService.api().call(mContext).notification(UserId).enqueue(new Callback<json>() {
                @Override
                public void onResponse(Call<json> call, Response<json> response) {

                    if (response.body().status.equals("1")) {
                        if (response.body().Total_count != "") {
                            homeActivity.cart_badge.setVisibility(View.VISIBLE);
                            homeActivity.cart_badge.setText(response.body().Total_count);
                            //      Toast.makeText(getApplicationContext(), response.body().message, Toast.LENGTH_SHORT).show();
                        } else {
                            homeActivity.cart_badge.setVisibility(View.GONE);
                            //     Toast.makeText(getApplicationContext(), response.body().message, Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<json> call, Throwable t) {
                    //      Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
                    //    Log.d("error", String.valueOf(t.getMessage()));
                }
            });
        } catch (Exception e) {

        }
    }
}
