package com.ap.SociaLite.Presenter;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;

import com.ap.SociaLite.Activity.Report;
import com.ap.SociaLite.Activity.Report_Status;
import com.ap.SociaLite.Adapter.ReportListAdapter;
import com.ap.SociaLite.Application.RService;
import com.ap.SociaLite.Application.json;
import com.ap.SociaLite.Contract.Report_Contract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Report_Presenter implements Report_Contract {

    public Context mContext;
    public Report report;

    public Report_Presenter(Context context, Report fragment) {
        this.mContext = context;
        this.report = fragment;
    }

    @Override
    public void report_list() {
        new RService.api().call(mContext).report().enqueue(new Callback<json>() {
            @Override
            public void onResponse(Call<json> call, Response<json> response) {
                if (response.body().status.equals("1")) {
                    if (response.body().Report_details != null && response.body().Report_details.size() > 0) {
                        report.rv_report_list.setLayoutManager(new GridLayoutManager(mContext, 1));
                        report.rv_report_list.setAdapter(new ReportListAdapter(mContext, response.body().Report_details, report));
                    }
                } else {
                    //      Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<json> call, Throwable t) {
                //   Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
                //   Log.d("error", String.valueOf(t.getMessage()));
            }
        });
    }

    @Override
    public void report_post(String user_id, String post_id, String reason) {
        new RService.api().call(mContext).post_report(user_id, post_id, reason).enqueue(new Callback<json>() {
            @Override
            public void onResponse(Call<json> call, Response<json> response) {
                if (response.body().status.equals("1")) {
                    Intent in = new Intent(mContext, Report_Status.class);
                    mContext.startActivity(in);
                    report.finish();
                } else {
                    Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<json> call, Throwable t) {
                  Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
                // Log.d("error", String.valueOf(t.getMessage()));
            }
        });
    }
}