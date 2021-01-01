package com.ap.SociaLite.Presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.ap.SociaLite.Activity.ProfileActivity;
import com.ap.SociaLite.Application.RService;
import com.ap.SociaLite.Application.json;
import com.ap.SociaLite.Contract.ProfileActivityContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivityPresenter implements ProfileActivityContract {

    public Context mContext;
    public ProfileActivity profileActivity;

    public ProfileActivityPresenter(Context context, ProfileActivity fragment) {
        this.mContext = context;
        this.profileActivity = fragment;
    }

    @Override
    public void profile_my_profile(String user_id) {
        try {
            new RService.api().call(mContext).my_profileActivity(user_id).enqueue(new Callback<json>() {
                @Override
                public void onResponse(Call<json> call, Response<json> response) {

                    if (response.body().status.equals("1")) {
                        if (response.body().user_details != null) {
                            Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();

                            //    LoginActivity.account =response.body().user_details.is_private_account;
//
//                            Session session = new Session(mContext);
//                            Intent in = new Intent(mContext, HomeActivity.class);
//                            session.setEmail_or_mobile(loginActivity.edt_email.getText().toString().trim());
//                            session.setUser_id(response.body().user_details.user_id);
//                            mContext.startActivity(in);
//                            loginActivity.finish();
                        }
                    } else {
                        Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
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