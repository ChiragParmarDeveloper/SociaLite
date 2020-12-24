package com.ap.SociaLite.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.ap.SociaLite.Adapter.HiddedPostDetailAdapter;
import com.ap.SociaLite.Adapter.MyNetworkAdapter;
import com.ap.SociaLite.Application.Session;
import com.ap.SociaLite.Presenter.HiddedPostDetailPresenter;
import com.ap.SociaLite.Presenter.HidedPostPresenter;
import com.ap.SociaLite.R;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HiddedPostDetailActivity extends AppCompatActivity {

    @BindView(R.id.img_back)
    ImageView img_back;

    @BindView(R.id.rec_hidedpost_detail)
    public RecyclerView rec_hidedpost_detail;

    String user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hidded_post_detail);
        ButterKnife.bind(this);

        Session session = new Session(HiddedPostDetailActivity.this);
        user_id = session.getUser_id();

        new HiddedPostDetailPresenter(this, this).view_hided_post(user_id);
    }

    @OnClick({R.id.img_back})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                onBackPressed();
                break;
        }
    }

}