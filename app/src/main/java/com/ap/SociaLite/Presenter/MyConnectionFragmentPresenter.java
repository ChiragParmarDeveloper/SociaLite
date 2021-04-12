package com.ap.SociaLite.Presenter;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;

import com.ap.SociaLite.Adapter.ConnectionAdapter.MyConnectionAdapter;
import com.ap.SociaLite.Application.RService;
import com.ap.SociaLite.Application.json;
import com.ap.SociaLite.Contract.MyConnectionFragmentContrast;
import com.ap.SociaLite.Fragment.Connection.MyConnectionFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyConnectionFragmentPresenter implements MyConnectionFragmentContrast {

    MyConnectionFragment myConnectionFragment;
    Context mContext;

    public MyConnectionFragmentPresenter(MyConnectionFragment myConnectionFragment, Context mContext) {
        this.myConnectionFragment = myConnectionFragment;
        this.mContext = mContext;
    }

    @Override
    public void following(String UserId) {
        myConnectionFragment.progressbar.setVisibility(View.VISIBLE);
        try {
            new RService.api().call(mContext).my_connection(UserId).enqueue(new Callback<json>() {
                @Override
                public void onResponse(Call<json> call, Response<json> response) {
                    myConnectionFragment.progressbar.setVisibility(View.GONE);
                    if (response.body().status.equals("1")) {
                        if (response.body().user_connection != null && response.body().user_connection.size() > 0) {
                            myConnectionFragment.user_connections = response.body().user_connection;
                            myConnectionFragment.myConnectionAdapter = new MyConnectionAdapter(mContext, myConnectionFragment.user_connections, myConnectionFragment);
                            myConnectionFragment.myconnections_recyclerview.setLayoutManager(new GridLayoutManager(mContext, 1));
                            myConnectionFragment.myconnections_recyclerview.setAdapter(myConnectionFragment.myConnectionAdapter);
                        }
                    } else {
                        myConnectionFragment.constraint_myconnection.setVisibility(View.GONE);
                        //    Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<json> call, Throwable t) {
                    myConnectionFragment.progressbar.setVisibility(View.GONE);
                    //     Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
                    //    Log.d("error", String.valueOf(t.getMessage()));
                }
            });
        } catch (Exception e) {

        }
    }

    @Override
    public void remove_frnd(String UserId, String RequestId) {
        myConnectionFragment.progressbar.setVisibility(View.VISIBLE);
        try {
            new RService.api().call(mContext).disconnect(UserId, RequestId).enqueue(new Callback<json>() {
                @Override
                public void onResponse(Call<json> call, Response<json> response) {
                    myConnectionFragment.progressbar.setVisibility(View.GONE);
                    if (response.body().status.equals("1")) {
                        Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                    } else {
                        //    Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<json> call, Throwable t) {
                    myConnectionFragment.progressbar.setVisibility(View.GONE);
                    //     Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
                    //    Log.d("error", String.valueOf(t.getMessage()));
                }
            });
        } catch (Exception e) {

        }
    }
}