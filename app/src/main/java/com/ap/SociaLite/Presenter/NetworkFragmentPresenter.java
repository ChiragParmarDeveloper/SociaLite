package com.ap.SociaLite.Presenter;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.ap.SociaLite.Application.RService;
import com.ap.SociaLite.Application.json;
import com.ap.SociaLite.Contract.NetworkFragmentContrast;
import com.ap.SociaLite.Fragment.NetworkFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetworkFragmentPresenter implements NetworkFragmentContrast {

    Context mContext;
    NetworkFragment networkFragment;

    public NetworkFragmentPresenter(Context mcontext, NetworkFragment networkFragment) {
        this.mContext = mContext;
        this.networkFragment = networkFragment;
    }

    @Override
    public void my_network_post(String UserId) {
        networkFragment.progressbar.setVisibility(View.VISIBLE);
        new RService.api().call(mContext).netork_post(UserId).enqueue(new Callback<json>() {
            @Override
            public void onResponse(Call<json> call, Response<json> response) {
                networkFragment.progressbar.setVisibility(View.GONE);
                if (response.body().status.equals("1")) {
                    if (response.body().post_list != null && response.body().post_list.size() > 0) {
                        Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                        //              FaqActivity.rv_faq.setLayoutManager(new GridLayoutManager(mContext, 1));
                        //              FaqActivity.rv_faq.setAdapter(new FaqListAdapter(mContext, response.body().faq_list, FaqActivity));
                    }
                } else {
                    //         Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<json> call, Throwable t) {
                networkFragment.progressbar.setVisibility(View.GONE);
                //    Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
                //    Log.d("error", String.valueOf(t.getMessage()));
            }
        });
    }
}
