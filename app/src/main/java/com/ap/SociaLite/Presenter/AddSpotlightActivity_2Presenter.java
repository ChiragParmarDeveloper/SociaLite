package com.ap.SociaLite.Presenter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.ap.SociaLite.Activity.AddSpotlightActivity_2;
import com.ap.SociaLite.Activity.ProfileActivity;
import com.ap.SociaLite.Activity.SpotLightActivity;
import com.ap.SociaLite.Activity.SpotlightActivityForUser;
import com.ap.SociaLite.Application.RService;
import com.ap.SociaLite.Application.json;
import com.ap.SociaLite.Contract.AddSpotlightActivity_2Contract;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddSpotlightActivity_2Presenter implements AddSpotlightActivity_2Contract {

    public Context mContext;
    public AddSpotlightActivity_2 addSpotlightActivity_2;

    public AddSpotlightActivity_2Presenter(Context context, AddSpotlightActivity_2 fragment) {
        this.mContext = context;
        this.addSpotlightActivity_2 = fragment;
    }

    @Override
    public void put_my_story(RequestBody user_id, MultipartBody.Part story_file) {
        addSpotlightActivity_2.progressbar.setVisibility(View.VISIBLE);
        try {
            new RService.api().call(mContext).put_story(user_id, story_file)
                    .enqueue(new Callback<json>() {
                        @Override
                        public void onResponse(Call<json> call, Response<json> response) {
                            addSpotlightActivity_2.progressbar.setVisibility(View.GONE);
                            if (response.body().status.equals("1")) {


                                if(addSpotlightActivity_2.my_network_user_story !=null)
                                {
                                    Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                                    Intent spotlight = new Intent(mContext, SpotLightActivity.class);
                                    spotlight.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    mContext.startActivity(spotlight);
                                    addSpotlightActivity_2.finish();
                                }
                                else
                                {
                                    Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                                    Intent spotlight = new Intent(mContext, SpotlightActivityForUser.class);
                                    spotlight.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    mContext.startActivity(spotlight);
                                    addSpotlightActivity_2.finish();
                                }



                            } else {
                                //      Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<json> call, Throwable t) {
                            addSpotlightActivity_2.progressbar.setVisibility(View.GONE);
                            //    Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_LONG).show();
                            //     Log.d("error", String.valueOf(t.getMessage()));
                        }
                    });
        } catch (Exception e) {

        }
    }

}
