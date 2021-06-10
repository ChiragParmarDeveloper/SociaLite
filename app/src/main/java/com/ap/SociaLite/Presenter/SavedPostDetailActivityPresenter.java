package com.ap.SociaLite.Presenter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;

import com.ap.SociaLite.Activity.SavedPostDetailActivity;
import com.ap.SociaLite.Adapter.SavedPostDetailAdapter;
import com.ap.SociaLite.Application.RService;
import com.ap.SociaLite.Application.json;
import com.ap.SociaLite.Contract.SavedPostDetailActivityContrast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SavedPostDetailActivityPresenter implements SavedPostDetailActivityContrast {

    private Context mContext;
    private SavedPostDetailActivity savedPostDetailActivity;

    public SavedPostDetailActivityPresenter(Context mContext, SavedPostDetailActivity savedPostDetailActivity) {
        this.mContext = mContext;
        this.savedPostDetailActivity = savedPostDetailActivity;
    }

    @Override
    public void save_post(String user_id) {
        savedPostDetailActivity.progressbar.setVisibility(View.VISIBLE);
        try {
            new RService.api().call(mContext).savepost(user_id).enqueue(new Callback<json>() {
                @Override
                public void onResponse(Call<json> call, Response<json> response) {
                    savedPostDetailActivity.progressbar.setVisibility(View.GONE);
                    if (response.body().status.equals("1")) {
                        if (response.body().hide_post != null) {
                            savedPostDetailActivity.rec_savedpost_detail.setLayoutManager(new GridLayoutManager(mContext, 1));
                            savedPostDetailActivity.rec_savedpost_detail.setAdapter(new SavedPostDetailAdapter(mContext, response.body().hide_post, savedPostDetailActivity));
                        }
                    } else {
                        //Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<json> call, Throwable t) {
                    savedPostDetailActivity.progressbar.setVisibility(View.GONE);
                    //    Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_LONG).show();
                    //  Log.d("error", String.valueOf(t.getMessage()));
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
               //         Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                    } else {
                //        Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
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
                 //       Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                    } else {
                  //      Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<json> call, Throwable t) {
                   // Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
                  //  Log.d("error", String.valueOf(t.getMessage()));
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
                 //       Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
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

}
