package com.ap.SociaLite.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.ap.SociaLite.Application.Session;
import com.ap.SociaLite.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Post extends AppCompatActivity {

//    @BindView(R.id.btn_share)
//    Button btn_share;

    @BindView(R.id.imageView)
    ImageView imageView;

    @BindView(R.id.spinner)
    Spinner spinner;

    @BindView(R.id.constraintLayout37)
    ConstraintLayout constraintLayout37;

    String user_id, my_network, business_interaction;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        ButterKnife.bind(this);

        Session session = new Session(Post.this);
        user_id = session.getUser_id();
        //     new PostPresenter(this,this).fetch_all_intrest(user_id);

        if(getIntent().hasExtra("byteArray"))
        {
          //  ImageView previewThumbnail = new ImageView(this);
            Bitmap b = BitmapFactory.decodeByteArray(
                    getIntent().getByteArrayExtra("byteArray"),0,getIntent().getByteArrayExtra("byteArray").length);
            imageView.setImageBitmap(b);
        }
    }

    @OnClick({R.id.img_back, R.id.btn_share})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                onBackPressed();
                break;

            case R.id.btn_share:
                startActivity(new Intent(Post.this, HomeActivity.class));
                break;
        }
    }


}