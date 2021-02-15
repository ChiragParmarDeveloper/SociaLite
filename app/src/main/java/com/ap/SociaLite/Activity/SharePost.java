package com.ap.SociaLite.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.ap.SociaLite.PictureThread;
import com.ap.SociaLite.R;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SharePost extends AppCompatActivity {

    @BindView(R.id.img_cross)
    ImageView img_cross;

    @BindView(R.id.btn_next)
    Button btn_next;

    @BindView(R.id.btn_save)
    Button btn_save;

    @BindView(R.id.imageView)
    ImageView imageView;

    @BindView(R.id.All_contact)
    TextView view_allcontect;

    private Bitmap bitmap;
    String image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_post);
        ButterKnife.bind(this);

        image = getIntent().getStringExtra("img2");
        if (image != null) {
            File imgFile = new File(image);

            if (imgFile.exists()) {

                bitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                imageView.setImageBitmap(bitmap);
                imageView.setTag(imgFile.toString());

            }
        }
    }

    @OnClick({R.id.img_cross, R.id.btn_next, R.id.btn_save, R.id.All_contact})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.img_cross:
                finish();
                ///     startActivity(new Intent(CameraActivity.this,LoginActivity.class));
                break;

            case R.id.btn_next:
                startActivity(new Intent(SharePost.this, Post.class));
                break;

            case R.id.All_contact:
                startActivity(new Intent(SharePost.this, ViewAllContectActivity.class));
                break;

            case R.id.btn_save:
                startActivity(new Intent(SharePost.this, CameraActivity.class));
                break;
        }
    }
}