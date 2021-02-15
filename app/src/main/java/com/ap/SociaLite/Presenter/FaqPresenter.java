package com.ap.SociaLite.Presenter;

import android.content.Context;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;

import com.ap.SociaLite.Activity.Faq;
import com.ap.SociaLite.Adapter.FaqListAdapter;
import com.ap.SociaLite.Application.RService;
import com.ap.SociaLite.Application.json;
import com.ap.SociaLite.Contract.FaqContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FaqPresenter implements FaqContract {

    public Context mContext;
    public Faq FaqActivity;

    public FaqPresenter(Context context, Faq fragment) {
        this.mContext = context;
        this.FaqActivity = fragment;
    }

    @Override
    public void faq_listdata() {
        new RService.api().call(mContext).faq().enqueue(new Callback<json>() {
            @Override
            public void onResponse(Call<json> call, Response<json> response) {
                if (response.body().status.equals("1")) {
                    if (response.body().faq_list != null && response.body().faq_list.size() > 0) {
                        FaqActivity.rv_faq.setLayoutManager(new GridLayoutManager(mContext, 1));
                        FaqActivity.rv_faq.setAdapter(new FaqListAdapter(mContext, response.body().faq_list, FaqActivity));
                    }
                } else {
           //         Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<json> call, Throwable t) {
                //    Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
                //    Log.d("error", String.valueOf(t.getMessage()));
            }
        });
    }
}
