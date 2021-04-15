package com.ap.SociaLite.Presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;

import com.ap.SociaLite.Activity.CommentActivity;
import com.ap.SociaLite.Adapter.FaqListAdapter;
import com.ap.SociaLite.Adapter.view_commentAdapter;
import com.ap.SociaLite.Application.RService;
import com.ap.SociaLite.Application.json;
import com.ap.SociaLite.Contract.CommentContract;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentPresenter implements CommentContract {

    public Context mContext;
    public CommentActivity commentActivity;

    public CommentPresenter(Context context, CommentActivity fragment) {
        this.mContext = context;
        this.commentActivity = fragment;
    }

    @Override
    public void add_comment(String user_id, String post_id, String comment) {
        try {
            new RService.api().call(mContext).add_comment_post(user_id, post_id, comment).enqueue(new Callback<json>() {
                @Override
                public void onResponse(Call<json> call, Response<json> response) {

                    if (response.body().status.equals("1")) {
                        commentActivity.edt_comment.getText().clear();
                    } else {
                        //       Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                    }

                }

                @Override
                public void onFailure(Call<json> call, Throwable t) {
                    //  Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
                    // Log.d("error", String.valueOf(t.getMessage()));
                }
            });
        } catch (Exception e) {

        }
    }

    @Override
    public void view_comment(String post_id) {
        try {
            new RService.api().call(mContext).fetch_comments(post_id).enqueue(new Callback<json>() {
                @Override
                public void onResponse(Call<json> call, Response<json> response) {

                    if (response.body().status.equals("1")) {

                        if(response.body().comments.comments !=null && response.body().comments.comments.size() > 0 )

                        {
                            commentActivity.rv_view_comment.setLayoutManager(new GridLayoutManager(mContext, 1));
                            commentActivity.rv_view_comment.setAdapter(new view_commentAdapter(mContext,response.body().comments.comments, commentActivity));
                        }

                    } else {
                  //      Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<json> call, Throwable t) {
                 //   Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
                  //  Log.d("error", String.valueOf(t.getMessage()));
                }
            });
        } catch (Exception e) {

        }
    }
}