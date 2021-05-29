package com.ap.SociaLite.Presenter;

import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;

import com.ap.SociaLite.Activity.Sync_contact;
import com.ap.SociaLite.Adapter.SyncContactAdapter;
import com.ap.SociaLite.Application.RService;
import com.ap.SociaLite.Application.json;
import com.ap.SociaLite.Contract.Sync_contactContrats;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Sync_contactPresenter implements Sync_contactContrats {

    public Context mContext;
    public Sync_contact sync_contact;

    public Sync_contactPresenter(Context mContext, Sync_contact sync_contact) {
        this.mContext = mContext;
        this.sync_contact = sync_contact;
    }

    //  @Override
//    public void contact_sync(ArrayList<String> contacts) {
//        sync_contact.progressbar.setVisibility(View.VISIBLE);
//        new RService.api().call(mContext).contact(contacts).enqueue(new Callback<json>() {
//            @Override
//            public void onResponse(Call<json> call, Response<json> response) {
//                sync_contact.progressbar.setVisibility(View.GONE);
//                if (response.body().status.equals("1")) {
//                    if (response.body().data != null && response.body().data.size() > 0) {
//                        Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
////                        FaqActivity.rv_faq.setLayoutManager(new GridLayoutManager(mContext, 1));
////                        FaqActivity.rv_faq.setAdapter(new FaqListAdapter(mContext, response.body().faq_list, FaqActivity));
//                    }
//                } else {
//                    Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
//                    //         Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<json> call, Throwable t) {
//                sync_contact.progressbar.setVisibility(View.GONE);
//                Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
//                //    Log.d("error", String.valueOf(t.getMessage()));
//            }
//        });
//    }

    @Override
    public void contact_sync(String contacts) {
        sync_contact.progressbar.setVisibility(View.VISIBLE);
        new RService.api().call(mContext).contact(contacts).enqueue(new Callback<json>() {
            @Override
            public void onResponse(Call<json> call, Response<json> response) {
                sync_contact.progressbar.setVisibility(View.GONE);
                if (response.body().status.equals("1")) {
                    if (response.body().data != null && response.body().data.size() > 0) {
                        //   Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                        sync_contact.rv_contact.setLayoutManager(new GridLayoutManager(mContext, 1));
                        sync_contact.rv_contact.setAdapter(new SyncContactAdapter(mContext, response.body().data, sync_contact));
                    }
                } else {
                    //    Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                    //         Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<json> call, Throwable t) {
                sync_contact.progressbar.setVisibility(View.GONE);
                // Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
                //    Log.d("error", String.valueOf(t.getMessage()));
            }
        });
    }

}
