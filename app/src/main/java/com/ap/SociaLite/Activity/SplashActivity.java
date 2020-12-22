package com.ap.SociaLite.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.ap.SociaLite.Application.BaseActivity;
import com.ap.SociaLite.Application.Session;
import com.ap.SociaLite.R;

import butterknife.ButterKnife;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void load() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Session session = new Session(SplashActivity.this);

                if (session.getEmail_or_mobile() != ""){
                    Intent in = new Intent(SplashActivity.this, HomeActivity.class);
                    startActivity(in);
                    finish();
                }
                else {
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    finish();
                }






            }
        }, 2000);
    }
}