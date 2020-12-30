package com.ap.SociaLite.Presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.ap.SociaLite.Activity.Post;
import com.ap.SociaLite.Application.RService;
import com.ap.SociaLite.Application.json;
import com.ap.SociaLite.Contract.PostContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostPresenter implements PostContract {

    public Context mContext;
    public Post post;

    public PostPresenter(Context context, Post fragment) {
        this.mContext = context;
        this.post = fragment;
    }

    @Override
    public void fetch_all_intrest(String user_id) {
        try {
            new RService.api().call(mContext).interest_list_post(user_id).enqueue(new Callback<json>() {
                @Override
                public void onResponse(Call<json> call, Response<json> response) {

                    if (response.body().status.equals("1")) {

                        if (response.body().interest_details != null) {
                            Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();

//                            List<String> post_image = new ArrayList<>();
//                            for (int i = 0; i < response.body().hide_post.post_hide.size(); i++) {
//                                post_image.add(response.body().hide_post.post_hide.get(i).image);
//                            }
//
//                            List<String> post_description = new ArrayList<>();
//                            for (int i = 0; i < response.body().hide_post.post_hide.size(); i++) {
//                                post_description.add(response.body().hide_post.post_hide.get(i).description);
//                            }
//
//                            hiddedPostDetailActivity.rec_hidedpost_detail.setLayoutManager(new GridLayoutManager(mContext, 1));
//                            hiddedPostDetailActivity.rec_hidedpost_detail.setAdapter(new HiddedPostDetailAdapter(mContext, post_image, post_description, hiddedPostDetailActivity));

                        }
                    } else {
                        Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<json> call, Throwable t) {
                    Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_LONG).show();
                    Log.d("error", String.valueOf(t.getMessage()));
                }
            });
        } catch (Exception e) {

        }
    }
}

