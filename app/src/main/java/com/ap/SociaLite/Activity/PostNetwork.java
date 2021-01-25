package com.ap.SociaLite.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.ap.SociaLite.R;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PostNetwork extends AppCompatActivity {

    @BindView(R.id.imageView)
    ImageView imageView;

    @BindView(R.id.img_one)
    ImageView img_one;

    @BindView(R.id.img_two)
    ImageView img_two;

    @BindView(R.id.img_three)
    ImageView img_three;

    @BindView(R.id.layout_img1)
    LinearLayout layout_img1;

    @BindView(R.id.layout_img2)
    LinearLayout layout_img2;

    @BindView(R.id.layout_img3)
    LinearLayout layout_img3;

    @BindView(R.id.video_one)
    VideoView video_one;

    @BindView(R.id.video)
    VideoView video;

    @BindView(R.id.video_two)
    VideoView video_two;

    @BindView(R.id.video_three)
    VideoView video_three;

    String imagetwo, imageone, imagethree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_network);
        ButterKnife.bind(this);

        imageone = getIntent().getStringExtra("img1");
        imagetwo = getIntent().getStringExtra("img2");
        imagethree = getIntent().getStringExtra("img3");

        if (imageone != null) {
            layout_img1.setVisibility(View.VISIBLE);
            File imgFile = new File(imageone);

            if (imgFile.exists()) {

                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                imageView.setImageBitmap(myBitmap);
                img_one.setImageBitmap(myBitmap);
                imageView.setTag(imgFile.toString());

            }
        }
        if (imagetwo != null) {
            layout_img2.setVisibility(View.VISIBLE);
            File imgFile = new File(imagetwo);

            if (imgFile.exists()) {
                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                imageView.setImageBitmap(myBitmap);
                img_two.setImageBitmap(myBitmap);
            }
        }

        if (imagethree != null) {

            layout_img3.setVisibility(View.VISIBLE);
            File imgFile = new File(imagethree);

            if (imgFile.exists()) {
                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                imageView.setImageBitmap(myBitmap);
                img_three.setImageBitmap(myBitmap);
            }
        }
    }

    @OnClick({R.id.img_back, R.id.btn_share,R.id.img_one,R.id.img_two,R.id.img_three})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                onBackPressed();
                break;

            case R.id.btn_share:
                startActivity(new Intent(PostNetwork.this, HomeActivity.class));
                break;

            case R.id.img_one:
                if (imageone != null) {
                    File imgone = new File(imageone);
                    if (imgone.exists()) {
                        //        imageView.setImageURI(Uri.fromFile(imgone));

                        Bitmap myBitmap = BitmapFactory.decodeFile(imgone.getAbsolutePath());
                        imageView.setImageBitmap(myBitmap);
                        imageView.setTag(imgone.toString());
                    }
                }

                break;

            case R.id.img_two:

                if (imagetwo != null) {
                    File imgtwo = new File(imagetwo);
                    if (imgtwo.exists()) {
                        //   imageView.setImageURI(Uri.fromFile(imgtwo));
                        Bitmap myBitmap = BitmapFactory.decodeFile(imgtwo.getAbsolutePath());
                        imageView.setImageBitmap(myBitmap);

                        imageView.setTag(imgtwo.toString());
                    }
                }
                break;

            case R.id.img_three:

                if (imagethree != null) {
                    File imgthree = new File(imagethree);
                    if (imgthree.exists()) {
                        //       imageView.setImageURI(Uri.fromFile(imgthree));
                        Bitmap myBitmap = BitmapFactory.decodeFile(imgthree.getAbsolutePath());
                        imageView.setImageBitmap(myBitmap);

                        imageView.setTag(imgthree.toString());
                        //    path = imageView.getTag().toString();
                    }
                }
                break;
        }
    }

}