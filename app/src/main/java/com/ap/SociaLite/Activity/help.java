package com.ap.SociaLite.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.ap.SociaLite.R;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class help extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.img_back, R.id.help_center, R.id.policy, R.id.contact_us})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                onBackPressed();
                break;

            case R.id.help_center:
                startActivity(new Intent(this, help_center.class));
                break;

            case R.id.policy:
                startActivity(new Intent(this, privacy_policy.class));
                break;

            case R.id.contact_us:
                startActivity(new Intent(this, contact_us.class));
                break;
        }
    }
}