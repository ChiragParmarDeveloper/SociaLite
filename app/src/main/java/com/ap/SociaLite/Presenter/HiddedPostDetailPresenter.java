package com.ap.SociaLite.Presenter;

import android.content.Context;

import androidx.recyclerview.widget.GridLayoutManager;

import com.ap.SociaLite.Activity.HiddedPostDetailActivity;
import com.ap.SociaLite.Adapter.HiddedPostDetailAdapter;
import com.ap.SociaLite.Application.RService;
import com.ap.SociaLite.Application.json;
import com.ap.SociaLite.Contract.HiddedPostDetailContract;

import java.util.ArrayList;
import java.util.List;

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
        try {
            new RService.api().call(mContext).hidepost(user_id).enqueue(new Callback<json>() {
                @Override
                public void onResponse(Call<json> call, Response<json> response) {

                    if (response.body().status.equals("1")) {
                        if (response.body().hide_post != null) {

                            List<String> post_image = new ArrayList<>();
                            for (int i = 0; i < response.body().hide_post.post_hide.size(); i++) {
                                post_image.add(response.body().hide_post.post_hide.get(i).image);
                            }

                            List<String> post_description = new ArrayList<>();
                            for (int i = 0; i < response.body().hide_post.post_hide.size(); i++) {
                                post_description.add(response.body().hide_post.post_hide.get(i).description);
                            }

                            hiddedPostDetailActivity.rec_hidedpost_detail.setLayoutManager(new GridLayoutManager(mContext, 1));
                            hiddedPostDetailActivity.rec_hidedpost_detail.setAdapter(new HiddedPostDetailAdapter(mContext, post_image, post_description, hiddedPostDetailActivity));

                        }
                    } else {
                        //     Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<json> call, Throwable t) {
                    //    Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_LONG).show();
                    //  Log.d("error", String.valueOf(t.getMessage()));
                }
            });
        } catch (Exception e) {

        }
    }
}

