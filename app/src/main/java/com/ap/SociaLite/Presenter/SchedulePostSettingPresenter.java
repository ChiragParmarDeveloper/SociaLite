package com.ap.SociaLite.Presenter;

import android.content.Context;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;

import com.ap.SociaLite.Activity.SchedulePostSettingActivity;
import com.ap.SociaLite.Adapter.SchedulePostAdapter.SchedulePostSettingAdapter;
import com.ap.SociaLite.Application.RService;
import com.ap.SociaLite.Application.json;
import com.ap.SociaLite.Contract.SchedulePostSettingContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SchedulePostSettingPresenter implements SchedulePostSettingContract {

    public Context mContext;
    public SchedulePostSettingActivity SchedulePostSettingActivity;

    public SchedulePostSettingPresenter(Context context, SchedulePostSettingActivity fragment) {
        this.mContext = context;
        this.SchedulePostSettingActivity = fragment;
    }

    @Override
    public void schedule_posts(String user_id) {
        try {
            new RService.api().call(mContext).my_schedule_post(user_id).enqueue(new Callback<json>() {
                @Override
                public void onResponse(Call<json> call, Response<json> response) {
                    if (response.body().status.equals("1")) {

                        if (response.body().post_list != null && response.body().post_list.size() > 0) {
                            SchedulePostSettingActivity.rv_schedulepost.setLayoutManager(new GridLayoutManager(mContext, 1));
                            SchedulePostSettingActivity.rv_schedulepost.setAdapter(new SchedulePostSettingAdapter(mContext, response.body().post_list, SchedulePostSettingActivity));
                        }
                    } else {
                        //       Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<json> call, Throwable t) {
                    //     Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
                    //     Log.d("error", String.valueOf(t.getMessage()));
                }
            });
        } catch (Exception e) {

        }
    }

    @Override
    public void delete_post(String post_id) {
        try {
            new RService.api().call(mContext).delete_post(post_id).enqueue(new Callback<json>() {
                @Override
                public void onResponse(Call<json> call, Response<json> response) {
                    if (response.body().status.equals("1")) {
                        Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                    } else {
                    //    Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<json> call, Throwable t) {
                    //     Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
                    //     Log.d("error", String.valueOf(t.getMessage()));
                }
            });
        } catch (Exception e) {

        }
    }
}