package com.ap.SociaLite.Presenter;

import android.content.Context;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.ap.SociaLite.Activity.AddCardActivity;
import com.ap.SociaLite.Application.RService;
import com.ap.SociaLite.Application.json;
import com.ap.SociaLite.Contract.AddCardActivityContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddCardActivityPresenter implements AddCardActivityContract {

    private Context mContext;
    private AddCardActivity addCardActivity;

    public AddCardActivityPresenter(Context context, AddCardActivity fragment) {
        this.mContext = context;
        this.addCardActivity = fragment;
    }

    @Override
    public boolean validate(EditText card_name, EditText card_email, EditText mobile_view) {
        if (card_name.getText().toString().isEmpty()) {
            card_name.setError("Please enter name");
            return false;
        }
        if (card_email.getText().toString().isEmpty()) {
            card_email.setError("Please enter website");
            return false;
        }
        if (mobile_view.getText().toString().isEmpty()) {
            mobile_view.setError("Please enter email or mobile number");
            return false;
        }
        return true;
    }

    @Override
    public void business_create_card(String user_id, String name, String website, String mobile) {
        try {
            new RService.api().call(mContext).create_card(user_id, name, website, mobile).enqueue(new Callback<json>() {
                @Override
                public void onResponse(Call<json> call, Response<json> response) {

                    if (response.body().status.equals("1")) {

                        Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                        addCardActivity.onBackPressed();

                    } else {
                        Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<json> call, Throwable t) {
                   // Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_LONG).show();
                   // Log.d("error", String.valueOf(t.getMessage()));
                }
            });
        } catch (Exception e) {

        }
    }
}

