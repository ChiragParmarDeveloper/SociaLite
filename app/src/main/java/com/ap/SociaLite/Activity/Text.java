package com.ap.SociaLite.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.ap.SociaLite.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Text extends AppCompatActivity {


    @BindView(R.id.img_cross)
    ImageView img_cross;

    @BindView(R.id.next)
    Button next;


    @BindView(R.id.yellow)
    ImageView yellow;


    @BindView(R.id.edt_text)
    EditText edt_text;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.img_cross,R.id.next,R.id.yellow})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.img_cross:
                finish();
                ///     startActivity(new Intent(CameraActivity.this,LoginActivity.class));
                break;
            case R.id.next:
               // finish();
                ///     startActivity(new Intent(CameraActivity.this,LoginActivity.class));
                break;
            case R.id.yellow:
                edt_text.setTextColor(getResources().getColor(R.color.coloryellow));
                break;
        }
    }


}