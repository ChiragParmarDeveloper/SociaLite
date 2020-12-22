package com.ap.SociaLite.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.ap.SociaLite.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InterestActivity extends AppCompatActivity {

    @BindView(R.id.btn_done)
    Button btn_done;

    @BindView(R.id.img_back)
    ImageView img_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interest);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_done,R.id.img_back})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.btn_done:
                startActivity(new Intent(InterestActivity.this, HomeActivity.class));
                finish();
                break;

            case R.id.img_back:
               onBackPressed();
                break;
        }
    }
}