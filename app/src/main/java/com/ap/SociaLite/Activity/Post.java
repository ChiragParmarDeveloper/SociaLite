package com.ap.SociaLite.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.ap.SociaLite.Application.Session;
import com.ap.SociaLite.Presenter.PostPresenter;
import com.ap.SociaLite.R;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Post extends AppCompatActivity {

//    @BindView(R.id.btn_share)
//    Button btn_share;

    @BindView(R.id.imageView)
    ImageView imageView;

    @BindView(R.id.spinner)
    public Spinner spinner;

    @BindView(R.id.constraintLayout37)
    ConstraintLayout constraintLayout37;

    String user_id, picture_path, business_interaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        ButterKnife.bind(this);

        Session session = new Session(Post.this);
        user_id = session.getUser_id();
        picture_path = getIntent().getStringExtra("path");

        if (getIntent().hasExtra("byteArray")) {
            Bitmap b = BitmapFactory.decodeByteArray(
                    getIntent().getByteArrayExtra("byteArray"), 0, getIntent().getByteArrayExtra("byteArray").length);
            imageView.setImageBitmap(b);
        } else {

            File imgFile = new File(picture_path);

            if (imgFile.exists()) {

                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

                imageView.setImageBitmap(myBitmap);
                imageView.setTag(imgFile.toString());
            }

        }







        new PostPresenter(this, this).fetch_my_intrest(user_id);

    }

    @OnClick({R.id.img_back, R.id.btn_share})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                onBackPressed();
                break;

            case R.id.btn_share:
             //   startActivity(new Intent(Post.this, HomeActivity.class));
                break;
        }
    }


}