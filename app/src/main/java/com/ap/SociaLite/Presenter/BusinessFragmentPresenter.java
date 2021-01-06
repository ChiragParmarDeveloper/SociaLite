package com.ap.SociaLite.Presenter;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.ap.SociaLite.Adapter.BusinessListAdapter;
import com.ap.SociaLite.Application.RService;
import com.ap.SociaLite.Application.json;
import com.ap.SociaLite.Contract.BusinessFragmentContract;
import com.ap.SociaLite.Fragment.BusinessFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BusinessFragmentPresenter implements BusinessFragmentContract {

    public Context mContext;
    public BusinessFragment businessFragment;

    public BusinessFragmentPresenter(Context context, BusinessFragment fragment) {
        this.mContext = context;
        this.businessFragment = fragment;
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

                            businessFragment.rv_interestlist.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, true));
                            businessFragment.rv_interestlist.setAdapter(new BusinessListAdapter(mContext, response.body().interest_details, businessFragment));
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

