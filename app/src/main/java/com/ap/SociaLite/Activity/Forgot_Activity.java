package com.ap.SociaLite.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.ap.SociaLite.R;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Forgot_Activity extends AppCompatActivity {

    @BindView(R.id.img_back)
    ImageView img_back;

    @BindView(R.id.btn_submit)
    Button btn_submit;

    @BindView(R.id.email_id)
    EditText email_id;
    String email;
    // FirebaseAuth auth;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    String emailAddress = "chirag.parmar1994@gmail.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_);
        ButterKnife.bind(this);
        email = email_id.getText().toString().trim();
    }

    @OnClick({R.id.img_back, R.id.btn_submit})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                onBackPressed();
                //    startActivity(new Intent(Forgot_Activity.this,LoginActivity.class));
                break;

            case R.id.btn_submit:
                startActivity(new Intent(Forgot_Activity.this, OtpActivity.class));
                break;
        }
    }
}