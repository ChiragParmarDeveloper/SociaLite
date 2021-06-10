package com.ap.SociaLite.Presenter;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;

import com.ap.SociaLite.Activity.ShareToFriend;
import com.ap.SociaLite.Adapter.SharetoFrndAdapter;
import com.ap.SociaLite.Application.RService;
import com.ap.SociaLite.Application.json;
import com.ap.SociaLite.Contract.ShareToFriendContrast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShareToFriendPresenter implements ShareToFriendContrast {

    ShareToFriend shareToFriend;
    Context mContext;

    public ShareToFriendPresenter(ShareToFriend shareToFriend, Context mContext) {
        this.shareToFriend = shareToFriend;
        this.mContext = mContext;
    }

    @Override
    public void friend_list(String UserId) {
        shareToFriend.progressbar.setVisibility(View.VISIBLE);
        try {
            new RService.api().call(mContext).frnd_list(UserId).enqueue(new Callback<json>() {
                @Override
                public void onResponse(Call<json> call, Response<json> response) {
                    shareToFriend.progressbar.setVisibility(View.GONE);
                    if (response.body().status.equals("1")) {
                        if (response.body().data != null && response.body().data.size() > 0) {
                            shareToFriend.datas = response.body().data;
                            shareToFriend.sharetoFrndAdapter = new SharetoFrndAdapter(mContext, shareToFriend.datas, shareToFriend);
                            shareToFriend.rv_sharetofrnd.setLayoutManager(new GridLayoutManager(mContext, 1));
                            shareToFriend.rv_sharetofrnd.setAdapter(shareToFriend.sharetoFrndAdapter);
                        }
                    } else {
                        //    Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<json> call, Throwable t) {
                    shareToFriend.progressbar.setVisibility(View.GONE);
                    //     Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
                    //    Log.d("error", String.valueOf(t.getMessage()));
                }
            });
        } catch (Exception e) {

        }
    }

    @Override
    public void share_link_api(String user_id, ArrayList<String> shared_id, String url,
                               String type, String profile_share_id, String post_id) {

        shareToFriend.progressbar.setVisibility(View.VISIBLE);
        try {
            new RService.api().call(mContext).link_sharing(user_id, shared_id, url, type,
                    profile_share_id, post_id).enqueue(new Callback<json>() {
                @Override
                public void onResponse(Call<json> call, Response<json> response) {
                    shareToFriend.progressbar.setVisibility(View.GONE);
                    if (response.body().status.equals("1")) {
                        Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                        SharetoFrndAdapter.sharefrnd_id.clear();
                        shareToFriend.onBackPressed();


                    } else {
                        Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<json> call, Throwable t) {
                    shareToFriend.progressbar.setVisibility(View.GONE);
                    Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
                    //    Log.d("error", String.valueOf(t.getMessage()));
                }
            });
        } catch (Exception e) {

        }
    }
}
