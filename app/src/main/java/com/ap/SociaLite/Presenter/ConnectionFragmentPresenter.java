package com.ap.SociaLite.Presenter;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;

import com.ap.SociaLite.Adapter.ConnectionAdapter.ConnectionAdapter;
import com.ap.SociaLite.Application.RService;
import com.ap.SociaLite.Application.json;
import com.ap.SociaLite.Contract.ConnectionFragmentContrast;
import com.ap.SociaLite.Fragment.Connection.ConnectionFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConnectionFragmentPresenter implements ConnectionFragmentContrast {

    ConnectionFragment connectionFragment;
    Context mContext;

    public ConnectionFragmentPresenter(ConnectionFragment connectionFragment, Context mContext) {
        this.connectionFragment = connectionFragment;
        this.mContext = mContext;
    }

    @Override
    public void followers(String UserId) {
        connectionFragment.progressbar.setVisibility(View.VISIBLE);
        try {
            new RService.api().call(mContext).connection(UserId).enqueue(new Callback<json>() {
                @Override
                public void onResponse(Call<json> call, Response<json> response) {
                    connectionFragment.progressbar.setVisibility(View.GONE);
                    if (response.body().status.equals("1")) {
                        if (response.body().user_connection != null && response.body().user_connection.size() > 0) {
                            connectionFragment.user_connections = response.body().user_connection;
                            connectionFragment.connectionAdapter = new ConnectionAdapter(mContext, connectionFragment.user_connections, connectionFragment);
                            connectionFragment.connections_recyclerview.setLayoutManager(new GridLayoutManager(mContext, 1));
                            connectionFragment.connections_recyclerview.setAdapter(connectionFragment.connectionAdapter);
                            connectionFragment.connections_recyclerview.scrollToPosition(connectionFragment.position);
                        }
                    } else {
                        connectionFragment.layout_connection.setVisibility(View.GONE);
                        //    Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<json> call, Throwable t) {
                    connectionFragment.progressbar.setVisibility(View.GONE);
                    //     Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
                    //    Log.d("error", String.valueOf(t.getMessage()));
                }
            });
        } catch (Exception e) {

        }
    }


    @Override
    public void send_request(String UserId, String RequestId) {
        new RService.api().call(mContext).connection_request(UserId,RequestId).enqueue(new Callback<json>() {
            @Override
            public void onResponse(Call<json> call, Response<json> response) {
                if (response.body().status.equals("1")) {
                    Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                    followers(UserId);
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



