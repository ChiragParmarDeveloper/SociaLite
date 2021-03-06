package com.ap.SociaLite.Presenter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.ap.SociaLite.Activity.SpotlightActivityForUser;
import com.ap.SociaLite.Adapter.MyAllStoryAdapter;
import com.ap.SociaLite.Adapter.SpotlightViewerAdapter;
import com.ap.SociaLite.Application.RService;
import com.ap.SociaLite.Application.json;
import com.ap.SociaLite.Contract.SpotlightActivityForUserContract;
import com.ap.SociaLite.R;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SpotlightActivityForUserPresenter implements SpotlightActivityForUserContract {

    public Context mContext;
    public SpotlightActivityForUser spotlightActivityForUser;

    public SpotlightActivityForUserPresenter(Context context, SpotlightActivityForUser fragment) {
        this.mContext = context;
        this.spotlightActivityForUser = fragment;
    }

    int abc;
    Handler handler;

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
//                                spotlightActivityForUser.user_profile.setImageDrawable(upload_img);
//                            } else {
//                                Picasso.get().load(response.body().user_details.profile_pic).into(spotlightActivityForUser.user_profile);
//                            }
                            if (response.body().user_details.profile_pic.equals("http://the-socialite.com/admin/")) {

                                spotlightActivityForUser.viewer_profile.setVisibility(View.VISIBLE);
                                String avatarTitle = String.valueOf(response.body().user_details.username.charAt(0)).toUpperCase();
                                ColorGenerator generator = ColorGenerator.MATERIAL;
                                int randomcolor = generator.getRandomColor();

                                TextDrawable.IBuilder builder = TextDrawable.builder().beginConfig().endConfig().round();

                                TextDrawable drawable = builder.build(avatarTitle, randomcolor);
                                spotlightActivityForUser.viewer_profile.setImageDrawable(drawable);
                            } else {
                                spotlightActivityForUser.viewer_profile.setVisibility(View.GONE);
                                Picasso.get().load(response.body().user_details.profile_pic).into(spotlightActivityForUser.user_profile);
                            }



                            spotlightActivityForUser.user_name.setText(response.body().user_details.username);
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
    public void my_all_storys(String user_id) {
        try {
            new RService.api().call(mContext).my_all_story(user_id).enqueue(new Callback<json>() {
                @Override
                public void onResponse(Call<json> call, Response<json> response) {
                    if (response.body().status.equals("1")) {
                        if (response.body().story_data != null && response.body().story_data.size() > 0) {
                            spotlightActivityForUser.rv_mystory.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
                            spotlightActivityForUser.rv_mystory.setAdapter(new MyAllStoryAdapter(mContext, response.body().story_data, spotlightActivityForUser));

                            abc = 0;
                            handler = new Handler();
                            for (int i = 0; i < response.body().story_data.size(); i++) {
                                int finalI = i;

                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        Picasso.get().load(response.body().story_data.get(finalI).story_image).into(spotlightActivityForUser.img_status);
                                    }
                                }, 2000 * i);
                            }
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
    public void view_story_viewer(String user_id, String story_id) {
        try {
            new RService.api().call(mContext).story_viewers(user_id, story_id).enqueue(new Callback<json>() {
                @Override
                public void onResponse(Call<json> call, Response<json> response) {

                    if (response.body().status.equals("1")) {

                        if (response.body().story_view != null && response.body().story_view.size() > 0) {

                            spotlightActivityForUser.view_count.setVisibility(View.VISIBLE);
                            spotlightActivityForUser.view_count.setText(response.body().count + " " + "views");
                            spotlightActivityForUser.views_recycler.setLayoutManager(new GridLayoutManager(mContext, 1));
                            spotlightActivityForUser.views_recycler.setAdapter(new SpotlightViewerAdapter(mContext, response.body().story_view, spotlightActivityForUser));
                        }
                    } else {
                        spotlightActivityForUser.view_count.setVisibility(View.VISIBLE);
                        spotlightActivityForUser.view_count.setText("0 views");
                        spotlightActivityForUser.views_recycler.setLayoutManager(new GridLayoutManager(mContext, 1));
                        spotlightActivityForUser.views_recycler.setAdapter(new SpotlightViewerAdapter(mContext, response.body().story_view, spotlightActivityForUser));
                    }
                }

                @Override
                public void onFailure(Call<json> call, Throwable t) {
                    //    Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_LONG).show();
                    //     Log.d("error", String.valueOf(t.getMessage()));
                }
            });
        } catch (Exception e) {

        }
    }
}