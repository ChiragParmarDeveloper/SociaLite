package com.ap.SociaLite.Presenter;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ap.SociaLite.Activity.contact_us;
import com.ap.SociaLite.Application.RService;
import com.ap.SociaLite.Application.json;
import com.ap.SociaLite.Contract.contact_usContrast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class contact_usPresenter implements contact_usContrast {

    public Context mContext;
    public contact_us contact_us;

    public contact_usPresenter(Context mContext, com.ap.SociaLite.Activity.contact_us contact_us) {
        this.mContext = mContext;
        this.contact_us = contact_us;
    }

    @Override
    public void fetch_profile(String user_id) {
        contact_us.progressbar.setVisibility(View.VISIBLE);
        try {
            new RService.api().call(mContext).profile(user_id).enqueue(new Callback<json>() {
                @Override
                public void onResponse(Call<json> call, Response<json> response) {
                    contact_us.progressbar.setVisibility(View.GONE);
                    if (response.body().status.equals("1")) {
                        if (response.body().user_details != null) {
                            contact_us.txt_name.setText(response.body().user_details.username);
                            contact_us.txt_mail.setText(response.body().user_details.email);
                        }
                    } else {
                        //         Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<json> call, Throwable t) {
                    contact_us.progressbar.setVisibility(View.GONE);
                    //       Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_LONG).show();
                    //       Log.d("error", String.valueOf(t.getMessage()));
                }
            });
        } catch (Exception e) {

        }

    }

    @Override
    public boolean validate(TextView txt_name, TextView txt_mail, EditText edt_msgs) {

        if (txt_name.getText().toString().isEmpty()) {
            txt_name.setError("Please enter username");
            return false;
        }

        if (txt_mail.getText().toString().isEmpty()) {
            txt_mail.setError("Please enter email");
            return false;
        }

        if (edt_msgs.getText().toString().isEmpty()) {
            edt_msgs.setError("Please enter description");
            return false;
        }
        return true;

    }

    @Override
    public void contact_us_api(String name, String email, String message) {
        contact_us.progressbar.setVisibility(View.VISIBLE);
        try {
            new RService.api().call(mContext).contact(name,email,message).enqueue(new Callback<json>() {
                @Override
                public void onResponse(Call<json> call, Response<json> response) {
                    contact_us.progressbar.setVisibility(View.GONE);
                    if (response.body().status.equals("1")) {
                        Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                        contact_us.onBackPressed();
                        contact_us.finish();

                    } else {
                        //         Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<json> call, Throwable t) {
                    contact_us.progressbar.setVisibility(View.GONE);
                    //       Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_LONG).show();
                    //       Log.d("error", String.valueOf(t.getMessage()));
                }
            });
        } catch (Exception e) {

        }
    }
}
