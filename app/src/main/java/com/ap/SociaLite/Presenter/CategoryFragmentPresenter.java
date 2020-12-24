package com.ap.SociaLite.Presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.ap.SociaLite.Application.RService;
import com.ap.SociaLite.Application.json;
import com.ap.SociaLite.Contract.CategoryFragmentContract;
import com.ap.SociaLite.Fragment.CategoryFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryFragmentPresenter implements CategoryFragmentContract {

    public Context mContext;
    public CategoryFragment categoryFragment;

    public CategoryFragmentPresenter(Context context, CategoryFragment fragment) {
        this.mContext = context;
        this.categoryFragment = fragment;
    }

    @Override
    public void interest() {
        new RService.api().call(mContext).interest_list().enqueue(new Callback<json>() {
            @Override
            public void onResponse(Call<json> call, Response<json> response) {
                if (response.body().status.equals("1")) {
                    Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();

//                    if (response.body().faq_list != null && response.body().faq_list.size() > 0) {
//                        FaqActivity.rv_faq.setLayoutManager(new GridLayoutManager(mContext, 1));
//                        FaqActivity.rv_faq.setAdapter(new FaqListAdapter(mContext, response.body().faq_list, FaqActivity));
//                    }
                } else {
                    Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<json> call, Throwable t) {
                Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("error", String.valueOf(t.getMessage()));
            }
        });
    }
}

