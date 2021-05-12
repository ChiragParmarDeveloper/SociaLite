package com.ap.SociaLite.Presenter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;

import com.ap.SociaLite.Activity.Notification;
import com.ap.SociaLite.Adapter.Notification_adapter.Notification_adapter;
import com.ap.SociaLite.Application.RService;
import com.ap.SociaLite.Application.json;
import com.ap.SociaLite.Contract.NotificationContrast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationPresenter implements NotificationContrast {

    Notification notification;
    Context mContext;

    public NotificationPresenter(Notification notification, Context mContext) {
        this.notification = notification;
        this.mContext = mContext;
    }

    @Override
    public void user_notification_list(String UserId) {
        notification.progressbar.setVisibility(View.VISIBLE);
        try {
            new RService.api().call(mContext).notification(UserId).enqueue(new Callback<json>() {
                @Override
                public void onResponse(Call<json> call, Response<json> response) {
                    notification.progressbar.setVisibility(View.GONE);

                    if (response.body().status.equals("1")) {

                        if (response.body().data != null && response.body().data.size() > 0) {

                            Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                            notification.recycleview_notification.setLayoutManager(new GridLayoutManager(mContext, 1));
                            notification.recycleview_notification.setAdapter(new Notification_adapter(mContext, notification, response.body().data));

                        } else {
                            notification.recycleview_notification.setLayoutManager(new GridLayoutManager(mContext, 1));
                            notification.recycleview_notification.setAdapter(new Notification_adapter(mContext, notification, response.body().data));
                            Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<json> call, Throwable t) {
                    notification.progressbar.setVisibility(View.GONE);
                    Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.d("error", String.valueOf(t.getMessage()));
                }
            });
        } catch (Exception e) {

        }
    }

    @Override
    public void request_accept(String UserId, String RequestId) {
        notification.progressbar.setVisibility(View.VISIBLE);
        try {
            new RService.api().call(mContext).accept(UserId, RequestId).enqueue(new Callback<json>() {
                @Override
                public void onResponse(Call<json> call, Response<json> response) {
                    notification.progressbar.setVisibility(View.GONE);
                    if (response.body().status.equals("1")) {
                        Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                        new NotificationPresenter(notification, mContext).user_notification_list(notification.UserId);
                    } else {
                        //    Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<json> call, Throwable t) {
                    notification.progressbar.setVisibility(View.GONE);
                    //     Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
                    //    Log.d("error", String.valueOf(t.getMessage()));
                }
            });
        } catch (Exception e) {

        }
    }

    @Override
    public void request_denied(String UserId, String RequestId) {
        notification.progressbar.setVisibility(View.VISIBLE);
        try {
            new RService.api().call(mContext).denied(UserId, RequestId).enqueue(new Callback<json>() {
                @Override
                public void onResponse(Call<json> call, Response<json> response) {
                    notification.progressbar.setVisibility(View.GONE);
                    if (response.body().status.equals("1")) {
                        Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                    } else {
                        //    Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<json> call, Throwable t) {
                    notification.progressbar.setVisibility(View.GONE);
                    //     Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
                    //    Log.d("error", String.valueOf(t.getMessage()));
                }
            });
        } catch (Exception e) {

        }
    }
}

