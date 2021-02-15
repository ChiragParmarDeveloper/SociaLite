package com.ap.SociaLite.Presenter;

import android.content.Context;

import androidx.recyclerview.widget.GridLayoutManager;

import com.ap.SociaLite.Activity.HidedPost;
import com.ap.SociaLite.Adapter.HiddenPostAdapter;
import com.ap.SociaLite.Application.RService;
import com.ap.SociaLite.Application.json;
import com.ap.SociaLite.Contract.HidedPostContract;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HidedPostPresenter implements HidedPostContract {

    private Context mContext;
    private HidedPost hidedPost;

    public HidedPostPresenter(Context context, HidedPost hidedPost) {
        this.mContext = context;
        this.hidedPost = hidedPost;
    }

    @Override
    public void view_hided_post(String user_id) {
        try {
            new RService.api().call(mContext).hidepost(user_id).enqueue(new Callback<json>() {
                @Override
                public void onResponse(Call<json> call, Response<json> response) {

                    if (response.body().status.equals("1")) {
                        if (response.body().hide_post != null) {

                            List<String> post = new ArrayList<>();
                            for (int i = 0; i < response.body().hide_post.post_hide.size(); i++) {
                                post.add(response.body().hide_post.post_hide.get(i).image);
                            }

                            hidedPost.rv_hidedpost.setLayoutManager(new GridLayoutManager(mContext, 3));
                            hidedPost.rv_hidedpost.setAdapter(new HiddenPostAdapter(mContext, post, hidedPost));

                        }
                    } else {
                        //      Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<json> call, Throwable t) {
                    //  Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_LONG).show();
                    //  Log.d("error", String.valueOf(t.getMessage()));
                }
            });

        } catch (Exception e) {

        }
    }
}

