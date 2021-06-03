package com.ap.SociaLite.Presenter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.ap.SociaLite.Activity.HomeActivity;
import com.ap.SociaLite.Activity.Post;
import com.ap.SociaLite.Application.RService;
import com.ap.SociaLite.Application.json;
import com.ap.SociaLite.Contract.PostContract;
import com.ap.SociaLite.R;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
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

                            post.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    String itemvalue = parent.getItemAtPosition(position).toString();
                                    post.spinner.setPrompt(itemvalue);
                                    post.selected_id = response.body().interest_details.get(position).interest_id;
                                    Log.d("selected_id", post.selected_id);

                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {

                                }
                            });

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

    @Override
    public void post(RequestBody user_id, MultipartBody.Part[] post_image, RequestBody description,
                     RequestBody intrest_id, RequestBody in_bussiness_interaction, RequestBody location,
                     RequestBody hide_users, RequestBody share_users, RequestBody schedule_date, RequestBody schedule_time) {

        post.progressbar.setVisibility(View.VISIBLE);
        try {
            new RService.api().call(mContext).create_post(user_id, post_image, description, intrest_id,
                    in_bussiness_interaction, location, hide_users, share_users, schedule_date, schedule_time).enqueue(new Callback<json>() {
                @Override
                public void onResponse(Call<json> call, Response<json> response) {
                    post.progressbar.setVisibility(View.GONE);
                    if (response.body().status.equals("1")) {
                        Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                        Intent in = new Intent(mContext, HomeActivity.class);
                        in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        in.putExtra("pass", "category_fragment");
                        mContext.startActivity(in);
                        post.finish();

                    } else {
                        Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<json> call, Throwable t) {
                    post.progressbar.setVisibility(View.GONE);
                   // Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
                   // Log.d("error", String.valueOf(t.getMessage()));
                }
            });
        } catch (Exception e) {

        }
    }
}


