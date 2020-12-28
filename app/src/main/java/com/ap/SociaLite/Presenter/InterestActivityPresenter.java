package com.ap.SociaLite.Presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;

import com.ap.SociaLite.Activity.InterestActivity;
import com.ap.SociaLite.Adapter.MyInterestAdapter;
import com.ap.SociaLite.Application.RService;
import com.ap.SociaLite.Application.json;
import com.ap.SociaLite.Contract.InterestActivityContract;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InterestActivityPresenter implements InterestActivityContract {

    public Context mContext;
    public InterestActivity interestActivity;

    public InterestActivityPresenter(Context context, InterestActivity fragment) {
        this.mContext = context;
        this.interestActivity = fragment;
    }

    @Override
    public void interest() {
        try {
            new RService.api().call(mContext).interest_list().enqueue(new Callback<json>() {
                @Override
                public void onResponse(Call<json> call, Response<json> response) {
                    if (response.body().status.equals("1")) {

                        if (response.body().interest_list != null && response.body().interest_list.size() > 0) {

                            List<String> interest_name = new ArrayList<>();
                            for (int i = 0; i < response.body().interest_list.size(); i++) {
                                interest_name.add(response.body().interest_list.get(i).interest_name);
                            }
                            interestActivity.rv_interest.setLayoutManager(new GridLayoutManager(mContext, 1));
                            interestActivity.rv_interest.setAdapter(new MyInterestAdapter(mContext, interest_name, interestActivity));
                        }
                    } else {
              //          Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<json> call, Throwable t) {
                //    Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
                //    Log.d("error", String.valueOf(t.getMessage()));
                }
            });
        } catch (Exception e) {

        }
    }
}
