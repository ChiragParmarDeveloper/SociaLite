package com.ap.SociaLite.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ap.SociaLite.Adapter.HiddenPostAdapter;
import com.ap.SociaLite.Adapter.SavedPostAdapter;
import com.ap.SociaLite.Application.RService;
import com.ap.SociaLite.Application.Session;
import com.ap.SociaLite.Application.json;
import com.ap.SociaLite.R;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SavedPostActivity extends AppCompatActivity {

    @BindView(R.id.progressbar)
    public ProgressBar progressbar;

    @BindView(R.id.rec_savedpost)
    RecyclerView rec_savedpost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_post);
        ButterKnife.bind(this);

        Session session = new Session(this);

        save_post(session.getUser_id());
    }


    @OnClick({R.id.img_back})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                onBackPressed();
                break;
        }
    }

    private void save_post(String user_id) {
        progressbar.setVisibility(View.VISIBLE);
        try {
            new RService.api().call(this).savepost(user_id).enqueue(new Callback<json>() {
                @Override
                public void onResponse(Call<json> call, Response<json> response) {
                    progressbar.setVisibility(View.GONE);
                    if (response.body().status.equals("1")) {
                        if (response.body().hide_post != null) {
                            rec_savedpost.setLayoutManager(new GridLayoutManager(SavedPostActivity.this, 3));
                            rec_savedpost.setAdapter(new SavedPostAdapter(SavedPostActivity.this, response.body().hide_post));
                        }
                    } else {
                        //         Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<json> call, Throwable t) {
                    progressbar.setVisibility(View.GONE);
                    //  Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_LONG).show();
                    //  Log.d("error", String.valueOf(t.getMessage()));
                }
            });

        } catch (Exception e) {

        }
    }
}
