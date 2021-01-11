package com.ap.SociaLite.Presenter;

import android.content.Context;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;

import com.ap.SociaLite.Activity.EditProfileActivity;
import com.ap.SociaLite.Activity.Search;
import com.ap.SociaLite.Adapter.FaqListAdapter;
import com.ap.SociaLite.Adapter.SearchProfileAdapter;
import com.ap.SociaLite.Application.RService;
import com.ap.SociaLite.Application.json;
import com.ap.SociaLite.Contract.SearchContract;
import com.ap.SociaLite.R;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchPresenter implements SearchContract {

    public Context mContext;
    public Search search;

    public SearchPresenter(Context context, Search fragment) {
        this.mContext = context;
        this.search = fragment;
    }

    @Override
    public void all_user() {
        new RService.api().call(mContext).fetch_user().enqueue(new Callback<json>() {
            @Override
            public void onResponse(Call<json> call, Response<json> response) {
                if (response.body().status.equals("1")) {
                    if (response.body().user_list != null && response.body().user_list.size() > 0) {
                        search.rv_search_profile.setLayoutManager(new GridLayoutManager(mContext, 1));
                        search.rv_search_profile.setAdapter(new SearchProfileAdapter(mContext, response.body().user_list, search));
                    }
                } else {
               //     Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
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
