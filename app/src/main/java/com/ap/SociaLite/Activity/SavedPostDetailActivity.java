package com.ap.SociaLite.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.ap.SociaLite.Application.Session;
import com.ap.SociaLite.Presenter.SavedPostDetailActivityPresenter;
import com.ap.SociaLite.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SavedPostDetailActivity extends AppCompatActivity {

    @BindView(R.id.progressbar)
    public ProgressBar progressbar;

    @BindView(R.id.rec_savedpost_detail)
    public RecyclerView rec_savedpost_detail;

    public String user_id;
    public int adapter_position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_post_detail);
        ButterKnife.bind(this);

        Session session = new Session(SavedPostDetailActivity.this);
        user_id = session.getUser_id();

        adapter_position = getIntent().getIntExtra("click_position", 0);
        Log.d("Clicked position is : ", String.valueOf(adapter_position));

        new SavedPostDetailActivityPresenter(this, this).save_post(user_id);
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