package com.ap.SociaLite.Presenter;

import android.content.Context;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.ap.SociaLite.Adapter.BusinessListAdapter;
import com.ap.SociaLite.Adapter.InterestListAdapter;
import com.ap.SociaLite.Application.RService;
import com.ap.SociaLite.Application.json;
import com.ap.SociaLite.Contract.BusinessFragmentContract;
import com.ap.SociaLite.Fragment.BusinessFragment;
import com.ap.SociaLite.Fragment.InterestFragment;

import java.util.ArrayList;
import java.util.List;

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
    public void interest() {
        new RService.api().call(mContext).interest_list().enqueue(new Callback<json>() {
            @Override
            public void onResponse(Call<json> call, Response<json> response) {
                if (response.body().status.equals("1")) {

                    if (response.body().interest_list != null && response.body().interest_list.size() > 0) {

                        List<String> interest_name = new ArrayList<>();
                        for (int i = 0; i < response.body().interest_list.size(); i++) {
                            interest_name.add(response.body().interest_list.get(i).interest_name);
                        }

                        businessFragment.rv_interestlist.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, true));
                        businessFragment.rv_interestlist.setAdapter(new BusinessListAdapter(mContext, interest_name, businessFragment));
                    }
                } else {
                    //     Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<json> call, Throwable t) {
                //   Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
                //   Log.d("error", String.valueOf(t.getMessage()));
            }
        });
    }
}
