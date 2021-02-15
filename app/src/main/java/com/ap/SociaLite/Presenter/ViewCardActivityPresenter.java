package com.ap.SociaLite.Presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;

import com.ap.SociaLite.Activity.Faq;
import com.ap.SociaLite.Activity.ViewCardActivity;
import com.ap.SociaLite.Adapter.HiddenPostAdapter;
import com.ap.SociaLite.Application.RService;
import com.ap.SociaLite.Application.json;
import com.ap.SociaLite.Contract.ViewCardActivityContract;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewCardActivityPresenter implements ViewCardActivityContract {

    public Context mContext;
    public ViewCardActivity viewCardActivity;

    public ViewCardActivityPresenter(Context context, ViewCardActivity fragment) {
        this.mContext = context;
        this.viewCardActivity = fragment;
    }

    @Override
    public void business_view_card(String user_id) {
        try {
            new RService.api().call(mContext).card_view(user_id).enqueue(new Callback<json>() {
                @Override
                public void onResponse(Call<json> call, Response<json> response) {

                    if (response.body().status.equals("Card details fetch successfully")) {

                        if (response.body().user_card != null && response.body().user_card.size() > 0 ) {

                            viewCardActivity.card_name.setText(response.body().user_card.get(0).name);
                            viewCardActivity.card_email.setText(response.body().user_card.get(0).website);
                            viewCardActivity.mobile_view.setText(response.body().user_card.get(0).mobile_or_email);
                        }
                    } else {
                          //    Toast.makeText(mContext, response.body().status, Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<json> call, Throwable t) {
                 //     Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_LONG).show();
                  //    Log.d("error", String.valueOf(t.getMessage()));
                }
            });

        } catch (Exception e) {

        }
    }
}