package com.ap.SociaLite.Presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.ap.SociaLite.Activity.AddSpotlightActivity_2;
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
    public void put_my_story(RequestBody user_id, MultipartBody.Part story_image) {
        try {
            new RService.api().call(mContext).put_story(user_id, story_image)
                    .enqueue(new Callback<json>() {
                        @Override
                        public void onResponse(Call<json> call, Response<json> response) {

                            if (response.body().status.equals("1")) {
                                Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                                addSpotlightActivity_2.onBackPressed();
//                                Intent in = new Intent(mContext, SpotLightActivity.class);
//                                mContext.startActivity(in);
//                                addSpotlightActivity_2.finish();
                            } else {
                          //      Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
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
