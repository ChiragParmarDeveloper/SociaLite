package com.ap.SociaLite.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ap.SociaLite.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Forgot_Activity extends AppCompatActivity {

    @BindView(R.id.img_back)
    ImageView img_back;

    @BindView(R.id.btn_submit)
    Button btn_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.img_back,R.id.btn_submit})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                startActivity(new Intent(Forgot_Activity.this,LoginActivity.class));
                break;

            case R.id.btn_submit:
                startActivity(new Intent(Forgot_Activity.this,OtpActivity.class));
                break;
        }
    }
}