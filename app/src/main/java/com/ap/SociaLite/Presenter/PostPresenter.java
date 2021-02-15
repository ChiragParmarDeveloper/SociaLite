package com.ap.SociaLite.Presenter;

import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.ap.SociaLite.Activity.Post;
import com.ap.SociaLite.Application.RService;
import com.ap.SociaLite.Application.json;
import com.ap.SociaLite.Contract.PostContract;
import com.ap.SociaLite.R;

import java.util.ArrayList;
import java.util.List;

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
    public void fetch_my_intrest(String user_id) {
        try {
            new RService.api().call(mContext).my_interest_list(user_id).enqueue(new Callback<json>() {
                @Override
                public void onResponse(Call<json> call, Response<json> response) {

                    if (response.body().status.equals("1")) {

                        if (response.body().interest_details != null && response.body().interest_details.size() > 0) {

                            List<String> category = new ArrayList<>();
                            for (int i = 0; i < response.body().interest_details.size(); i++) {
                                Log.d(" category", response.body().interest_details.get(i).interest_name);
                                category.add(response.body().interest_details.get(i).interest_name);
                            }

                            ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(mContext, R.layout.spinner_style, category);
                            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            post.spinner.setAdapter(spinnerAdapter);

                        }
                    } else {
                        //    Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<json> call, Throwable t) {
                    //     Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_LONG).show();
                    //     Log.d("error", String.valueOf(t.getMessage()));
                }
            });
        } catch (Exception e) {

        }
    }
}

