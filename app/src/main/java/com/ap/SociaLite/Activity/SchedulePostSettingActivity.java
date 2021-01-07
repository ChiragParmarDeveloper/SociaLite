package com.ap.SociaLite.Activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.ap.SociaLite.Application.Session;
import com.ap.SociaLite.Presenter.SchedulePostSettingPresenter;
import com.ap.SociaLite.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SchedulePostSettingActivity extends AppCompatActivity {

    @BindView(R.id.rv_schedulepost)
    public RecyclerView rv_schedulepost;

    public String user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_post_setting);
        ButterKnife.bind(this);
        Session session = new Session(this);
        user_id = session.getUser_id();

        new SchedulePostSettingPresenter(this, this).schedule_posts(user_id);
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