package com.ap.SociaLite.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.ap.SociaLite.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ResetpasswordActivity extends AppCompatActivity {

    @BindView(R.id.img_back)
    ImageView img_back;

   String phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resetpassword);
        ButterKnife.bind(this);

        phoneNumber = getIntent().getStringExtra("phone_no");

        Toast.makeText(this, phoneNumber, Toast.LENGTH_SHORT).show();
    }

    @OnClick({R.id.img_back})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                startActivity(new Intent(ResetpasswordActivity.this,Forgot_Activity.class));
                break;
        }
    }

}