package com.ap.SociaLite.Presenter;

import android.content.Context;

import com.ap.SociaLite.Activity.SpotLightActivity;
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

                            if (response.body().user_details.profile_pic.length() > 0) {
                                Picasso.get().load(response.body().user_details.profile_pic).placeholder(R.mipmap.ic_launcher).into(spotLightActivity.user_profile);
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
}