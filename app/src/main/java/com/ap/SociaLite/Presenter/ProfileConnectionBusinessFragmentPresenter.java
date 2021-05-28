package com.ap.SociaLite.Presenter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;

import com.ap.SociaLite.Adapter.Profile_connection_adapters.ProfileConnectionBusinessAdapter;
import com.ap.SociaLite.Application.RService;
import com.ap.SociaLite.Application.json;
import com.ap.SociaLite.Contract.ProfileConnectionBusinessFragmentContrast;
import com.ap.SociaLite.Fragment.profile_connection_fragments.ProfileConnectionBusinessFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileConnectionBusinessFragmentPresenter implements ProfileConnectionBusinessFragmentContrast {

    public Context mContext;
    public ProfileConnectionBusinessFragment profileConnectionBusinessFragment;

    public ProfileConnectionBusinessFragmentPresenter(Context mContext, ProfileConnectionBusinessFragment profileConnectionBusinessFragment) {
        this.mContext = mContext;
        this.profileConnectionBusinessFragment = profileConnectionBusinessFragment;
    }

    @Override
    public void my_post_business_intrection(String user_id) {
        profileConnectionBusinessFragment.progressbar.setVisibility(View.VISIBLE);
        try {
            new RService.api().call(mContext).my_bussiness_post(user_id).enqueue(new Callback<json>() {
                @Override
                public void onResponse(Call<json> call, Response<json> response) {
                    profileConnectionBusinessFragment.progressbar.setVisibility(View.GONE);

                    if (response.body().status.equals("1")) {

                        if (response.body().post_list != null && response.body().post_list.size() > 0) {
                            profileConnectionBusinessFragment.recycleview_business_interaction.setLayoutManager(new GridLayoutManager(mContext, 1));
                            profileConnectionBusinessFragment.recycleview_business_interaction.setAdapter(new ProfileConnectionBusinessAdapter(mContext,profileConnectionBusinessFragment,response.body().post_list));
                        }
                    } else {
                        //       Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<json> call, Throwable t) {
                    profileConnectionBusinessFragment.progressbar.setVisibility(View.GONE);

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
                //        Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();

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
                //        Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                    } else {
                 //       Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<json> call, Throwable t) {
             //       Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
              //      Log.d("error", String.valueOf(t.getMessage()));
                }
            });
        } catch (Exception e) {

        }
    }
}
