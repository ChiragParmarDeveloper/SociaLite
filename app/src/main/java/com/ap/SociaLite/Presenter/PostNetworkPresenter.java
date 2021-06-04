package com.ap.SociaLite.Presenter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.ap.SociaLite.Activity.HomeActivity;
import com.ap.SociaLite.Activity.PostNetwork;
import com.ap.SociaLite.Application.RService;
import com.ap.SociaLite.Application.json;
import com.ap.SociaLite.Contract.PostNetworkContrast;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostNetworkPresenter implements PostNetworkContrast {

    public Context mContext;
    public PostNetwork postNetwork;

    public PostNetworkPresenter(Context mContext, PostNetwork postNetwork) {
        this.mContext = mContext;
        this.postNetwork = postNetwork;
    }

    @Override
    public void post(RequestBody user_id, MultipartBody.Part[] post_image, RequestBody description,
                     RequestBody intrest_id, RequestBody in_bussiness_interaction, RequestBody location, RequestBody hide_users,
                     RequestBody share_users, RequestBody schedule_date, RequestBody schedule_time) {

        postNetwork.progressbar.setVisibility(View.VISIBLE);
        try {
            new RService.api().call(mContext).create_post(user_id, post_image, description, intrest_id,
                    in_bussiness_interaction, location, hide_users, share_users, schedule_date, schedule_time).enqueue(new Callback<json>() {
                @Override
                public void onResponse(Call<json> call, Response<json> response) {
                    postNetwork.progressbar.setVisibility(View.GONE);
                    if (response.body().status.equals("1")) {
                        Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                        Intent in = new Intent(mContext, HomeActivity.class);
                        in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                     //   in.putExtra("pass", "network_fragment");
                        mContext.startActivity(in);
                        postNetwork.finish();

                    } else {
                        Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<json> call, Throwable t) {
                    postNetwork.progressbar.setVisibility(View.GONE);
                    Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.d("error", String.valueOf(t.getMessage()));
                }
            });
        } catch (Exception e) {

        }
    }
}
