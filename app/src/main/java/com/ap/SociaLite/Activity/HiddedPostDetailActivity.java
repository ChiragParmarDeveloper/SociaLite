package com.ap.SociaLite.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.ap.SociaLite.Adapter.HiddedPostDetailAdapter;
import com.ap.SociaLite.Application.Session;
import com.ap.SociaLite.Pojo.hide_post;
import com.ap.SociaLite.Presenter.HiddedPostDetailPresenter;
import com.ap.SociaLite.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HiddedPostDetailActivity extends AppCompatActivity {

    @BindView(R.id.progressbar)
    public ProgressBar progressbar;

    @BindView(R.id.rec_hidedpost_detail)
    public RecyclerView rec_hidedpost_detail;

    public String user_id;
    public List<hide_post> mList;
    public HiddedPostDetailAdapter hiddedPostDetailAdapter;

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

    @Override
    protected void onResume() {
        super.onResume();
        new HiddedPostDetailPresenter(this, this).view_hided_post(user_id);
    }
}