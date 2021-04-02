package com.ap.SociaLite.Presenter;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.ap.SociaLite.Activity.HomeActivity;
import com.ap.SociaLite.Application.RService;
import com.ap.SociaLite.Application.json;
import com.ap.SociaLite.Contract.HomeActivityContract;
import com.ap.SociaLite.R;
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
                                Drawable upload_img = mContext.getDrawable(R.drawable.ic_user_icon);
                                homeActivity.img_dp.setImageDrawable(upload_img);
                            } else {
                                Picasso.get().load(response.body().user_details.profile_pic).into(homeActivity.img_dp);
                            }

                            homeActivity.txt_name.setText(response.body().user_details.username);
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
        } catch (Exception e) {
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
}
