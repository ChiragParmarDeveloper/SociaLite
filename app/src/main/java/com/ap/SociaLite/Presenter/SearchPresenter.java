package com.ap.SociaLite.Presenter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;

import com.ap.SociaLite.Activity.Search;
import com.ap.SociaLite.Adapter.SearchProfileAdapter;
import com.ap.SociaLite.Application.RService;
import com.ap.SociaLite.Application.json;
import com.ap.SociaLite.Contract.SearchContract;

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
    public void all_user(String user_id) {
        search.progressbar.setVisibility(View.VISIBLE);
        Log.d("requested_data","hjdsjfdsfd");
        new RService.api().call(mContext).fetch_user(user_id).enqueue(new Callback<json>() {
            @Override
            public void onResponse(Call<json> call, Response<json> response) {
                search.progressbar.setVisibility(View.GONE);
                Log.d("response_all user",response.toString());
                if (response.body().status.equals("1")) {
                    if (response.body().data != null && response.body().data.size() > 0) {

                        search.datas = response.body().data;
                        search.searchProfileAdapter = new SearchProfileAdapter(mContext, search.datas, search);
                        search.rv_search_profile.setLayoutManager(new GridLayoutManager(mContext, 1));
                        search.rv_search_profile.setAdapter(search.searchProfileAdapter);
                        search.searchProfileAdapter.notifyDataSetChanged();
                        search.rv_search_profile.scrollToPosition(search.position);
                        //    search.searchProfileAdapter.notifyItemChanged(2);

                    }
                } else {
                    Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<json> call, Throwable t) {
                search.progressbar.setVisibility(View.GONE);
//                Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
//                Log.d("error", String.valueOf(t.getMessage()));
            }
        });
    }

    @Override
    public void send_request(String UserId, String RequestId) {

        new RService.api().call(mContext).connection_request(UserId, RequestId).enqueue(new Callback<json>() {
            @Override
            public void onResponse(Call<json> call, Response<json> response) {
                if (response.body().status.equals("1")) {
                    Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                   all_user(UserId);
                 //   search.searchProfileAdapter.notifyDataSetChanged();
                } else {
                    //    Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<json> call, Throwable t) {
                //      Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
//                Log.d("error", String.valueOf(t.getMessage()));
            }
        });
    }
}
