package com.ap.SociaLite.Activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.ap.SociaLite.Adapter.SharetoFrndAdapter;
import com.ap.SociaLite.Application.Session;
import com.ap.SociaLite.Pojo.data;
import com.ap.SociaLite.Presenter.ShareToFriendPresenter;
import com.ap.SociaLite.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShareToFriend extends AppCompatActivity {

    @BindView(R.id.progressbar)
    public ProgressBar progressbar;

    @BindView(R.id.rv_sharetofrnd)
    public RecyclerView rv_sharetofrnd;

    @BindView(R.id.edt_search)
    public EditText edt_search;


    public SharetoFrndAdapter sharetoFrndAdapter;
    String UserId, myUrl, share_post, share_profile, post_id, profile_share_id;
    public List<data> datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_to_friend);
        ButterKnife.bind(this);

        Session session = new Session(getApplicationContext());
        UserId = session.getUser_id();

        myUrl = getIntent().getStringExtra("url");
        post_id = getIntent().getStringExtra("post_id");
        share_post = getIntent().getStringExtra("share_post");
        share_profile = getIntent().getStringExtra("share_profile");
        profile_share_id = getIntent().getStringExtra("profile_share_id");

    //    Log.d("share_profile", profile_share_id);

        new ShareToFriendPresenter(this, this).friend_list(UserId);
        filter();
    }

    @OnClick({R.id.img_back, R.id.btn_share})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                onBackPressed();
                break;

            case R.id.btn_share:

                if (share_post != null) {

                    new ShareToFriendPresenter(this, this).share_link_api(UserId, SharetoFrndAdapter.sharefrnd_id, myUrl,
                            "share_post", "", post_id);
                } else if (share_profile != null) {
                    new ShareToFriendPresenter(this, this).share_link_api(UserId, SharetoFrndAdapter.sharefrnd_id, myUrl,
                            "share_profile", profile_share_id, "");
                }
                break;
        }
    }

    private void filter() {

        edt_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ShareToFriend.this.sharetoFrndAdapter.filter(String.valueOf(s));
                sharetoFrndAdapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}