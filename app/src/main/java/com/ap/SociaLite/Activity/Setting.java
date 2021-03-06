package com.ap.SociaLite.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import com.ap.SociaLite.Application.Session;
import com.ap.SociaLite.Presenter.SettingPresenter;
import com.ap.SociaLite.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Setting extends AppCompatActivity {

    @BindView(R.id.togglenotification)
    public ToggleButton togglenotification;

    String user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);

        Session session = new Session(Setting.this);
        user_id = session.getUser_id();
    }

    @OnClick({R.id.img_back, R.id.constraintLayout42, R.id.constraintLayout_saved,
            R.id.constraintLayout_schedulepost, R.id.togglenotification, R.id.constraintLayout_contact})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                onBackPressed();
                break;

            case R.id.constraintLayout42:
                startActivity(new Intent(Setting.this, Privacy.class));
                break;

            case R.id.constraintLayout_saved:
                startActivity(new Intent(Setting.this, SavedPostActivity.class));
                break;

            case R.id.constraintLayout_schedulepost:
                startActivity(new Intent(Setting.this, SchedulePostSettingActivity.class));
                break;

            case R.id.togglenotification:
                if (togglenotification.isChecked()) {
                    new SettingPresenter(this, this).notification_on_off(user_id, "1");
                } else {
                    new SettingPresenter(this, this).notification_on_off(user_id, "0");
                }
                break;

            case R.id.constraintLayout_contact:
           //     startActivity(new Intent(Setting.this, Sync_contact.class));
                Toast.makeText(this, "Coming soon", Toast.LENGTH_SHORT).show();
                break;

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        new SettingPresenter(this, this).fetch_profile(user_id);
    }
}