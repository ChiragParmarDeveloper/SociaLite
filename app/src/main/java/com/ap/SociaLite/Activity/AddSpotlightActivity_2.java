package com.ap.SociaLite.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ap.SociaLite.Application.Session;
import com.ap.SociaLite.Presenter.AddSpotlightActivity_2Presenter;
import com.ap.SociaLite.R;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class AddSpotlightActivity_2 extends AppCompatActivity {

    @BindView(R.id.add_spotlight_image)
    ImageView add_spotlight_image;
    @BindView(R.id.progressbar)
    public ProgressBar progressbar;
    LinearLayout layout_album, layout_lock, layout_clock, layout_shape, layout_drama;
    // ImageView send;

    public String picture_path, id,my_network_user_story;
    MultipartBody.Part story_file;
    Uri mSaveImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_spotlight_2);
        ButterKnife.bind(this);

        Session session = new Session(AddSpotlightActivity_2.this);
        id = session.getUser_id();
        my_network_user_story = getIntent().getStringExtra("my_network_user_story");

        //    img_cross = findViewById(R.id.img_cross);
        //  send = findViewById(R.id.send);

//        layout_album = findViewById(R.id.layout_album);
//        layout_lock = findViewById(R.id.layout_lock);
//        layout_clock = findViewById(R.id.layout_clock);
//        layout_shape = findViewById(R.id.layout_shape);
//        layout_drama = findViewById(R.id.layout_drama);

        picture_path = getIntent().getStringExtra("path");

//        File imgFile = new File(picture_path);
//
//        if (imgFile.exists()) {
//
//            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
//
//            add_spotlight_image.setImageBitmap(myBitmap);
//            add_spotlight_image.setTag(imgFile.toString());
//        }

        if (picture_path != null) {
            mSaveImageUri = Uri.fromFile(new File(picture_path));
            add_spotlight_image.setImageURI(mSaveImageUri);
        }

//        layout_album.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(AddSpotlightActivity_2.this, TemplateBackground.class));
//            }
//        });
//
//        layout_lock.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(AddSpotlightActivity_2.this, SharePost.class));
//            }
//        });
//
//        layout_clock.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(AddSpotlightActivity_2.this, SchedulePost.class));
//            }
//        });
//
//        layout_shape.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(AddSpotlightActivity_2.this, PropDrama.class));
//            }
//        });
//
//        layout_drama.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(AddSpotlightActivity_2.this, ShapeCut.class));
//            }
//        });

    }

    @OnClick({R.id.img_cross, R.id.send})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.img_cross:
                onBackPressed();
                break;

            case R.id.send:
                if (picture_path != null) {
                    File idfile = new File(picture_path);
                    RequestBody idcardfile = RequestBody.create(MediaType.parse("image/*"), idfile);
                    story_file = MultipartBody.Part.createFormData("story_file", idfile.getPath(), idcardfile);
                } else {
                    RequestBody idcardfile = RequestBody.create(MediaType.parse("image/*"), "");
                    story_file = MultipartBody.Part.createFormData("story_file", "", idcardfile);
                }

                RequestBody user_id = RequestBody.create(MediaType.parse("text/plain"), id);

                new AddSpotlightActivity_2Presenter(this,this).put_my_story(user_id,story_file);

                break;
        }
    }
}