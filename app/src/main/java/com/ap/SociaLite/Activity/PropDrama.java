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

public class PropDrama extends AppCompatActivity {

    @BindView(R.id.img_cross)
    ImageView img_cross;

    @BindView(R.id.btn_next)
    Button btn_next;

    @BindView(R.id.btn_save)
    Button btn_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prop_drama);
        ButterKnife.bind(this);
    }
    @OnClick({R.id.img_cross,R.id.btn_next,R.id.btn_save})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.img_cross:
                finish();
                break;

            case R.id.btn_next:
                startActivity(new Intent(PropDrama.this,Post.class));
                break;

            case R.id.btn_save:
                startActivity(new Intent(PropDrama.this,CameraActivity.class));
                break;
        }
    }
}