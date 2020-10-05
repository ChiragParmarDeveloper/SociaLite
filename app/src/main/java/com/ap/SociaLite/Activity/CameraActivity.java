package com.ap.SociaLite.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ap.SociaLite.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CameraActivity extends AppCompatActivity {


    @BindView(R.id.layout_album)
    LinearLayout layout_album;

    @BindView(R.id.img_album)
    ImageView img_album;

    @BindView(R.id.layout_lock)
    LinearLayout layout_lock;

    @BindView(R.id.img_lock)
    ImageView img_lock;

    @BindView(R.id.layout_clock)
    LinearLayout layout_clock;

    @BindView(R.id.img_clock)
    ImageView img_clock;


    @BindView(R.id.layout_shape)
    LinearLayout layout_shape;

    @BindView(R.id.img_shape)
    ImageView img_shape;

    @BindView(R.id.layout_drama)
    LinearLayout layout_drama;

    @BindView(R.id.img_drama)
    ImageView img_drama;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        ButterKnife.bind(this);
    }
}