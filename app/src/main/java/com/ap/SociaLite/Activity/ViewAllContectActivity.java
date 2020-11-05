package com.ap.SociaLite.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.ap.SociaLite.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ViewAllContectActivity extends AppCompatActivity {

    @BindView(R.id.img_back)
    ImageView img_back;

    @BindView(R.id.btn_share)
    Button btn_share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_contect);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.img_back,R.id.btn_share})
    public void OnClick(View view) {
        switch (view.getId()) {

            case R.id.img_back:
                onBackPressed();
                ///     startActivity(new Intent(CameraActivity.this,LoginActivity.class));
                break;

            case R.id.btn_share:
                startActivity(new Intent(ViewAllContectActivity.this,SharePost.class));
                break;

        }
    }
}