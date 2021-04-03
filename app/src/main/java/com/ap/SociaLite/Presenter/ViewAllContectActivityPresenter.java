package com.ap.SociaLite.Presenter;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;

import com.ap.SociaLite.Activity.ViewAllContectActivity;
import com.ap.SociaLite.Adapter.viewallshareAdapter;
import com.ap.SociaLite.Application.RService;
import com.ap.SociaLite.Application.json;
import com.ap.SociaLite.Contract.ViewAllContectActivityContrast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewAllContectActivityPresenter implements ViewAllContectActivityContrast {

    ViewAllContectActivity viewAllContectActivity;
    Context mContext;

    public ViewAllContectActivityPresenter(ViewAllContectActivity viewAllContectActivity, Context mContext) {
        this.viewAllContectActivity = viewAllContectActivity;
        this.mContext = mContext;
    }

    @Override
    public void friend_list(String UserId) {
        viewAllContectActivity.progressbar.setVisibility(View.VISIBLE);
        try {
            new RService.api().call(mContext).frnd_list(UserId).enqueue(new Callback<json>() {
                @Override
                public void onResponse(Call<json> call, Response<json> response) {
                    viewAllContectActivity.progressbar.setVisibility(View.GONE);
                    if (response.body().status.equals("1")) {
                        if (response.body().data != null && response.body().data.size() > 0) {
                            Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                            viewAllContectActivity.datas = response.body().data;
                            viewAllContectActivity.viewallshareAdapter = new viewallshareAdapter(mContext, viewAllContectActivity.datas, viewAllContectActivity);
                            viewAllContectActivity.rv_sharetofrnd.setLayoutManager(new GridLayoutManager(mContext, 1));
                            viewAllContectActivity.rv_sharetofrnd.setAdapter(viewAllContectActivity.viewallshareAdapter);
                        }
                    } else {
                        //    Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<json> call, Throwable t) {
                    viewAllContectActivity.progressbar.setVisibility(View.GONE);
                    //     Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
                    //    Log.d("error", String.valueOf(t.getMessage()));
                }
            });
        } catch (Exception e) {

        }
    }
}
