package com.ap.SociaLite.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.ap.SociaLite.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.txt_Register)
    TextView txt_Register;

    @BindView(R.id.btn_login)
    TextView btn_login;

    @BindView(R.id.txt_forgot)
    TextView txt_forgot;

    @BindView(R.id.checkbox)
    CheckBox checkbox;

    @BindView(R.id.edt_email)
    EditText edt_email;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @SuppressLint("ResourceAsColor")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @OnClick({R.id.txt_Register, R.id.btn_login, R.id.txt_forgot,R.id.checkbox})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.txt_Register:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;

            case R.id.btn_login:
                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                finish();
                break;

            case R.id.txt_forgot:
                startActivity(new Intent(LoginActivity.this, Forgot_Activity.class));
                break;
        }
    }
}