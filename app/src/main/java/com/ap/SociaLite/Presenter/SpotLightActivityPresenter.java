package com.ap.SociaLite.Presenter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.ap.SociaLite.Activity.SpotLightActivity;
import com.ap.SociaLite.Adapter.SpotlightAdapter;
import com.ap.SociaLite.Application.RService;
import com.ap.SociaLite.Application.json;
import com.ap.SociaLite.Contract.SpotLightActivityContract;
import com.ap.SociaLite.R;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SpotLightActivityPresenter implements SpotLightActivityContract {

    public Context mContext;
    public SpotLightActivity spotLightActivity;

    public SpotLightActivityPresenter(Context context, SpotLightActivity fragment) {
        this.mContext = context;
        this.spotLightActivity = fragment;
    }

    @Override
    public void fetch_profile(String user_id) {
        try {
            new RService.api().call(mContext).profile(user_id).enqueue(new Callback<json>() {
                @Override
                public void onResponse(Call<json> call, Response<json> response) {

                    if (response.body().status.equals("1")) {
                        if (response.body().user_details != null) {

//                            if (response.body().user_details.profile_pic.equals("http://the-socialite.com/admin/")) {
//                                Drawable upload_img = mContext.getDrawable(R.drawable.ic_user_icon);
//                                spotLightActivity.user_profile.setImageDrawable(upload_img);
//                            } else {
//                                Picasso.get().load(response.body().user_details.profile_pic).into(spotLightActivity.user_profile);
//                            }
                            if (response.body().user_details.profile_pic.equals("http://the-socialite.com/admin/")) {

                                spotLightActivity.viewer_profile.setVisibility(View.VISIBLE);
                                String avatarTitle = String.valueOf(response.body().user_details.username.charAt(0)).toUpperCase();
                                ColorGenerator generator = ColorGenerator.MATERIAL;
                                int randomcolor = generator.getRandomColor();

                                TextDrawable.IBuilder builder = TextDrawable.builder().beginConfig().endConfig().round();

                                TextDrawable drawable = builder.build(avatarTitle, randomcolor);
                                spotLightActivity.viewer_profile.setImageDrawable(drawable);
                            } else {
                                spotLightActivity.viewer_profile.setVisibility(View.GONE);
                                Picasso.get().load(response.body().user_details.profile_pic).into(spotLightActivity.user_profile);
                            }

                            spotLightActivity.user_name.setText(response.body().user_details.username);
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
    public void frnd_story(String UserId) {
        try {
            new RService.api().call(mContext).allfrnd_story(UserId).enqueue(new Callback<json>() {
                @Override
                public void onResponse(Call<json> call, Response<json> response) {

                    if (response.body().status.equals("1")) {
                        if (response.body().data != null && response.body().data.size() > 0) {
                            spotLightActivity.friends_spotlight.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
                            spotLightActivity.friends_spotlight.setAdapter(new SpotlightAdapter(mContext, spotLightActivity, response.body().data));
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
    public void story_view_added(String user_id, String story_id) {
        try {
            new RService.api().call(mContext).added_story_view(user_id, story_id).enqueue(new Callback<json>() {
                @Override
                public void onResponse(Call<json> call, Response<json> response) {

                    if (response.body().status.equals("1")) {
                //        Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                    } else {
                //        Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
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