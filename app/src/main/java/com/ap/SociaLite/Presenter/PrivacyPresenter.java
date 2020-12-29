package com.ap.SociaLite.Presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.ap.SociaLite.Activity.Privacy;
import com.ap.SociaLite.Application.RService;
import com.ap.SociaLite.Application.json;
import com.ap.SociaLite.Contract.PrivacyContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PrivacyPresenter implements PrivacyContract {

    public Context mContext;
    public Privacy privacy;

    public PrivacyPresenter(Context context, Privacy fragment) {
        this.mContext = context;
        this.privacy = fragment;
    }

    @Override
    public void private_account(String user_id, String is_private_account) {
        try {
            new RService.api().call(mContext).account(user_id, is_private_account).enqueue(new Callback<json>() {
                @Override
                public void onResponse(Call<json> call, Response<json> response) {

                    if (response.body().status.equals("1")) {
                        Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                    } else {
             //           Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<json> call, Throwable t) {
//                    Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_LONG).show();
//                    Log.d("error", String.valueOf(t.getMessage()));
                }
            });
        } catch (Exception e) {
        }
    }
}
