package com.ap.SociaLite.Presenter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;

import com.ap.SociaLite.Adapter.MyNetworkAdapter;
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

    public NetworkFragmentPresenter(Context mContext, NetworkFragment networkFragment) {
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
                        //    Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                        networkFragment.recycleview_network_post.setLayoutManager(new GridLayoutManager(mContext, 1));
                        networkFragment.recycleview_network_post.setAdapter(new MyNetworkAdapter(mContext, networkFragment, response.body().post_list));
                    }
                } else {
                    // Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
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

    @Override
    public void hide_post(String user_id, String post_id) {

        try {
            new RService.api().call(mContext).dashboard_hidepost(user_id, post_id).enqueue(new Callback<json>() {
                @Override
                public void onResponse(Call<json> call, Response<json> response) {
                    if (response.body().status.equals("1")) {
                        //         Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                    } else {
                        //   Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
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
    public void category_save_post(String user_id, String post_id) {
        try {
            new RService.api().call(mContext).dashboard_savepost(user_id, post_id).enqueue(new Callback<json>() {
                @Override
                public void onResponse(Call<json> call, Response<json> response) {
                    if (response.body().status.equals("1")) {
                        //             Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                    } else {
                        //           Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
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
    public void rating_post(String user_id, String post_id, String rate) {
        try {
            new RService.api().call(mContext).give_rating(user_id, post_id, rate).enqueue(new Callback<json>() {
                @Override
                public void onResponse(Call<json> call, Response<json> response) {
                    if (response.body().status.equals("1")) {
                        //         Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                    } else {
                        //       Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<json> call, Throwable t) {
                    Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.d("error", String.valueOf(t.getMessage()));
                }
            });
        } catch (Exception e) {

        }
    }
}
