package com.ap.SociaLite.Presenter;

import android.content.Context;

import com.ap.SociaLite.Activity.post_link;
import com.ap.SociaLite.Application.RService;
import com.ap.SociaLite.Application.json;
import com.ap.SociaLite.Contract.post_link_Contrast;
import com.ap.SociaLite.Fragment.CategoryFragment;

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
                        //      new CategoryFragmentPresenter(mContext, categoryFragment).Category_post_fragment(categoryFragment.interest_ids);


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


}
