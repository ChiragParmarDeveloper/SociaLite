package com.ap.SociaLite.Presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.ap.SociaLite.Adapter.InterestListAdapter;
import com.ap.SociaLite.Application.RService;
import com.ap.SociaLite.Application.json;
import com.ap.SociaLite.Contract.InterestFragmentContract;
import com.ap.SociaLite.Fragment.InterestFragment;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InterestFragmentPresenter implements InterestFragmentContract {

    public Context mContext;
    public InterestFragment interestFragment;

    public InterestFragmentPresenter(Context context, InterestFragment fragment) {
        this.mContext = context;
        this.interestFragment = fragment;
    }

    @Override
    public void fetch_all_intrest(String user_id) {
        try {
            new RService.api().call(mContext).interest_list_post_page(user_id).enqueue(new Callback<json>() {
                @Override
                public void onResponse(Call<json> call, Response<json> response) {
                    if (response.body().status.equals("1")) {

                        if (response.body().interest_details != null && response.body().interest_details.size() > 0) {
                            //   Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();

//                            List<String> interest_name = new ArrayList<>();
//                            for (int i = 0; i < response.body().interest_details.size(); i++) {
//                                interest_name.add(response.body().interest_details.get(i).interest_name);
//                            }
//
//                            List<String> interest_img = new ArrayList<>();
//                            for (int i = 0; i < response.body().interest_details.size(); i++) {
//                                interest_img.add(response.body().interest_details.get(i).interest_image);
//                            }

                            //          interestFragment.rv_interestlist.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, true));
                            //            interestFragment.rv_interestlist.setAdapter(new InterestListAdapter(mContext, response.body().interest_details, interestFragment));

                        }
                    } else {
                        //   Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<json> call, Throwable t) {
                    //     Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
                    //    Log.d("error", String.valueOf(t.getMessage()));
                }
            });
        } catch (Exception e) {

        }
    }

    @Override
    public void update_new_intrests(String user_id, String interest_id) {
        try {
            new RService.api().call(mContext).plus_add_interest(user_id, interest_id).enqueue(new Callback<json>() {
                @Override
                public void onResponse(Call<json> call, Response<json> response) {
                    if (response.body().status.equals("1")) {
                        Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
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
    public void fetch_my_intrest(String user_id) {
        try {
            new RService.api().call(mContext).my_interest_list(user_id).enqueue(new Callback<json>() {
                @Override
                public void onResponse(Call<json> call, Response<json> response) {
                    if (response.body().status.equals("1")) {

                        if (response.body().interest_details != null && response.body().interest_details.size() > 0) {
                            //   Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();

//                            List<String> interest_name = new ArrayList<>();
//                            for (int i = 0; i < response.body().interest_details.size(); i++) {
//                                interest_name.add(response.body().interest_details.get(i).interest_name);
//                            }
//
//                            List<String> interest_img = new ArrayList<>();
//                            for (int i = 0; i < response.body().interest_details.size(); i++) {
//                                interest_img.add(response.body().interest_details.get(i).interest_image);
//                            }

                            interestFragment.rv_interestlist.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, true));
                            interestFragment.rv_interestlist.setAdapter(new InterestListAdapter(mContext, response.body().interest_details, interestFragment));

                        }
                    } else {
                        //   Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<json> call, Throwable t) {
                    //     Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
                    //    Log.d("error", String.valueOf(t.getMessage()));
                }
            });
        } catch (Exception e) {

        }
    }
}