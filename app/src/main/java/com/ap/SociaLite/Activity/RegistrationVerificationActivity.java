package com.ap.SociaLite.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.ap.SociaLite.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegistrationVerificationActivity extends AppCompatActivity {

    @BindView(R.id.btn_submit)
    Button btn_submit;

    @BindView(R.id.txt_no)
    TextView txt_no;
    String phone_no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_verification);
        ButterKnife.bind(this);

        phone_no = getIntent().getStringExtra("phone_no");
        String mask = phone_no.replaceAll("\\w(?=\\w{4})", "X");
        txt_no.setText(mask);

    }

    @OnClick({R.id.btn_submit})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.btn_submit:
                startActivity(new Intent(RegistrationVerificationActivity.this, InterestActivity.class));
                finish();
                break;
        }
    }
}