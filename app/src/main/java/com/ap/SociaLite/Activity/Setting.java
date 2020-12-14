package com.ap.SociaLite.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ToggleButton;

import com.ap.SociaLite.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Setting extends AppCompatActivity {

    @BindView(R.id.img_back)
    ImageView img_back;

    @BindView(R.id.togglenotification)
    ToggleButton togglenotification;

    @BindView(R.id.togglecontact)
    ToggleButton togglecontact;

    @BindView(R.id.constraintLayout42)
    ConstraintLayout constraintLayout42;

    @BindView(R.id.constraintLayout_saved)
    ConstraintLayout constraintLayout_saved;

    @BindView(R.id.constraintLayout_schedulepost)
    ConstraintLayout constraintLayout_schedulepost;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);

        togglenotification.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton arg0, boolean isChecked) {
              //  text.setText("Status: " + isChecked);
            }
        });

        togglecontact.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton arg0, boolean isChecked) {
                //  text.setText("Status: " + isChecked);
            }
        });

    }
    @OnClick({R.id.img_back,R.id.constraintLayout42, R.id.constraintLayout_saved, R.id.constraintLayout_schedulepost})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                onBackPressed();
                break;

            case R.id.constraintLayout42:
                startActivity(new Intent(Setting.this,Privacy.class));
                break;

            case R.id.constraintLayout_saved:
                startActivity(new Intent(Setting.this,SavedPostActivity.class));
                break;

            case R.id.constraintLayout_schedulepost:
                startActivity(new Intent(Setting.this,SchedulePostSettingActivity.class));
                break;
        }
    }
}