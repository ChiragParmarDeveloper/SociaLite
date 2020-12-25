package com.ap.SociaLite.Presenter;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.ap.SociaLite.Activity.InterestActivity;
import com.ap.SociaLite.Activity.RegistrationVerificationActivity;
import com.ap.SociaLite.Application.RService;
import com.ap.SociaLite.Application.json;
import com.ap.SociaLite.Contract.RegistrationVerificationContract;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationVerificationPresenter implements RegistrationVerificationContract {


    public Context mContext;
    public RegistrationVerificationActivity registrationVerificationActivity;

    public RegistrationVerificationPresenter(Context context, RegistrationVerificationActivity fragment) {
        this.mContext = context;
        this.registrationVerificationActivity = fragment;
    }

    @Override
    public void register(MultipartBody.Part profile_pic, RequestBody username, RequestBody email, RequestBody mobile_number,
                         RequestBody bio, RequestBody dob, RequestBody location, RequestBody password) {

        new RService.api().call(mContext).register(username, mobile_number, email, bio, dob, location, password, profile_pic).enqueue(new Callback<json>() {
            @Override
            public void onResponse(Call<json> call, Response<json> response) {

                if (response.body().status.equals("1")) {
                    Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();

                    Intent in = new Intent(mContext, InterestActivity.class);
                    mContext.startActivity(in);
                    registrationVerificationActivity.finish();

                } else {
                    Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<json> call, Throwable t) {
                //       Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_LONG).show();
                //      Log.d("error", String.valueOf(t.getMessage()));
            }
        });
    }
}