package com.ap.SociaLite.Presenter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.ap.SociaLite.Activity.HomeActivity;
import com.ap.SociaLite.Activity.LoginActivity;
import com.ap.SociaLite.Application.RService;
import com.ap.SociaLite.Application.json;
import com.ap.SociaLite.Contract.LoginContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter implements LoginContract {

    private Context mContext;
    private LoginActivity loginActivity;

    public LoginPresenter(Context context, LoginActivity loginActivity) {
        this.mContext = context;
        this.loginActivity = loginActivity;
    }

    @Override
    public boolean validate(EditText edt_email, EditText password) {

        if (edt_email.getText().toString().isEmpty()) {
            edt_email.setError("Please enter email/phone number");
            return false;
        }
        if (password.getText().toString().isEmpty()) {
            password.setError("Please enter password");
            return false;
        }
        return true;
    }

    @Override
    public void login(String edt_email, String password) {
        new RService.api().call(mContext).login(edt_email, password).enqueue(new Callback<json>() {
            @Override
            public void onResponse(Call<json> call, Response<json> response) {
                if (response.body().message.equals("Login Successfully")) {
                    loginActivity.startActivity(new Intent(mContext, HomeActivity.class));
                    loginActivity.finish();
                } else {
                    Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<json> call, Throwable t) {
           //     Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("error", String.valueOf(t.getMessage()));
            }
        });


    }
}
