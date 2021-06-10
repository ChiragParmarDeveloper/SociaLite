package com.ap.SociaLite.Presenter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;

import com.ap.SociaLite.Activity.HiddedPostDetailActivity;
import com.ap.SociaLite.Adapter.HiddedPostDetailAdapter;
import com.ap.SociaLite.Application.RService;
import com.ap.SociaLite.Application.json;
import com.ap.SociaLite.Contract.HiddedPostDetailContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HiddedPostDetailPresenter implements HiddedPostDetailContract {

    private Context mContext;
    private HiddedPostDetailActivity hiddedPostDetailActivity;

    public HiddedPostDetailPresenter(Context context, HiddedPostDetailActivity hiddedPostDetailActivity) {
        this.mContext = context;
        this.hiddedPostDetailActivity = hiddedPostDetailActivity;
    }

    @Override
    public void view_hided_post(String user_id) {
        hiddedPostDetailActivity.progressbar.setVisibility(View.VISIBLE);
        try {
            new RService.api().call(mContext).hidepost(user_id).enqueue(new Callback<json>() {
                @Override
                public void onResponse(Call<json> call, Response<json> response) {
                    hiddedPostDetailActivity.progressbar.setVisibility(View.GONE);
                    if (response.body().status.equals("1")) {
                        if (response.body().hide_post != null) {
                            hiddedPostDetailActivity.rec_hidedpost_detail.setLayoutManager(new GridLayoutManager(mContext, 1));
                            hiddedPostDetailActivity.rec_hidedpost_detail.setAdapter(new HiddedPostDetailAdapter(mContext, response.body().hide_post, hiddedPostDetailActivity));
                        }
                    } else {

                        //     Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<json> call, Throwable t) {
                    hiddedPostDetailActivity.progressbar.setVisibility(View.GONE);
                    //    Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_LONG).show();
                    //  Log.d("error", String.valueOf(t.getMessage()));
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
               //         Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                    } else {
                //        Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
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
    public void unhide_post(String user_id, String post_id) {
        try {
            new RService.api().call(mContext).unhide(user_id, post_id).enqueue(new Callback<json>() {
                @Override
                public void onResponse(Call<json> call, Response<json> response) {
                    if (response.body().status.equals("1")) {
              //          Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                    } else {
                        //       Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<json> call, Throwable t) {
                    //  Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
                    //   Log.d("error", String.valueOf(t.getMessage()));
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
              //          Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                    } else {
               //         Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
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
}

