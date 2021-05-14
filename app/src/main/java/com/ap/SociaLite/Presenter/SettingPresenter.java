package com.ap.SociaLite.Presenter;

import android.content.Context;
import android.widget.Toast;

import com.ap.SociaLite.Activity.Setting;
import com.ap.SociaLite.Application.RService;
import com.ap.SociaLite.Application.json;
import com.ap.SociaLite.Contract.SettingContrast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingPresenter implements SettingContrast {

    public Context mContext;
    public Setting setting;

    public SettingPresenter(Context mContext, Setting setting) {
        this.mContext = mContext;
        this.setting = setting;
    }

    @Override
    public void fetch_profile(String user_id) {
        try {
            new RService.api().call(mContext).profile(user_id).enqueue(new Callback<json>() {
                @Override
                public void onResponse(Call<json> call, Response<json> response) {

                    if (response.body().status.equals("1")) {
                        if (response.body().user_details != null) {
                            if (response.body().user_details.notification_on_off.equals("0")) {
                                setting.togglenotification.setChecked(false);
                            } else if (response.body().user_details.notification_on_off.equals("1")) {
                                setting.togglenotification.setChecked(true);
                            }
                        }
                    } else {
                        //         Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<json> call, Throwable t) {
                    //       Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_LONG).show();
                    //       Log.d("error", String.valueOf(t.getMessage()));
                }
            });
        } catch (Exception e) {

        }

    }

    @Override
    public void notification_on_off(String user_id, String is_toggle) {
        try {
            new RService.api().call(mContext).on_off_notification(user_id, is_toggle).enqueue(new Callback<json>() {
                @Override
                public void onResponse(Call<json> call, Response<json> response) {

                    if (response.body().status.equals("1")) {
                        //   Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                    } else {
                        //         Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<json> call, Throwable t) {
                    //       Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_LONG).show();
                    //       Log.d("error", String.valueOf(t.getMessage()));
                }
            });
        } catch (Exception e) {

        }
    }


}