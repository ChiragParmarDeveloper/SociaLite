package com.ap.SociaLite.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.ap.SociaLite.R;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CameraActivity extends AppCompatActivity {


    @BindView(R.id.layout_lock)
    ConstraintLayout layout_lock;

    @BindView(R.id.layout_clock)
    ConstraintLayout layout_clock;


    @BindView(R.id.imageView)
    ImageView imageView;

    @Nullable
    @VisibleForTesting
    Uri mSaveImageUri;
    String img_url;

    Bitmap bitmap;
    String my_network, business_interaction, path_one, path_two, path_three;
    private static final int PICK_IMAGE_one = 100;
    private static final int PICK_IMAGE_two = 110;
    private static final int PICK_IMAGE_three = 120;
    Uri imageUri1, imageUri2, imageUri3, imageUri;
    String path, Brightness_path, date, time;
    public ArrayList<String> finallist = new ArrayList<>();
    Uri imgURI;
    Uri imageuri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        ButterKnife.bind(this);

//        Bitmap mbitmap = ((BitmapDrawable) getResources().getDrawable(R.drawable.img_plus)).getBitmap();
//        Bitmap imageRounded = Bitmap.createBitmap(mbitmap.getWidth(), mbitmap.getHeight(), mbitmap.getConfig());
//        Canvas canvas = new Canvas(imageRounded);
//        Paint mpaint = new Paint();
//        mpaint.setAntiAlias(true);
//        mpaint.setShader(new BitmapShader(mbitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
//        canvas.drawRoundRect((new RectF(0, 0, mbitmap.getWidth(), mbitmap.getHeight())), 100, 100, mpaint); // Round Image Corner 100 100 100 100
//        imageView.setImageBitmap(imageRounded);

        my_network = getIntent().getStringExtra("network_fragment");
        business_interaction = getIntent().getStringExtra("business_fragment");

        img_url = getIntent().getStringExtra("img_url");

        if (img_url != null) {
            mSaveImageUri = Uri.fromFile(new File(img_url));
            imageView.setImageURI(mSaveImageUri);
        }


    }

    @OnClick({R.id.img_back, R.id.next, R.id.layout_lock, R.id.layout_clock})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                onBackPressed();
                break;

            case R.id.next:

                if (my_network != null) {
                    Intent in = new Intent(CameraActivity.this, PostNetwork.class);
                    in.putExtra("img_url", img_url);
                    in.putExtra("date", date);
                    in.putExtra("time", time);
                    in.putExtra("finallist", finallist);
                    startActivity(in);
                } else if (business_interaction != null) {
                    Intent in = new Intent(CameraActivity.this, PostBusiness.class);
                    in.putExtra("img_url", img_url);
                    in.putExtra("date", date);
                    in.putExtra("time", time);
                    in.putExtra("finallist", finallist);
                    startActivity(in);
                } else {
                    Intent in = new Intent(CameraActivity.this, Post.class);
                    in.putExtra("img_url", img_url);
                    in.putExtra("date", date);
                    in.putExtra("time", time);
                    in.putExtra("finallist", finallist);
                    startActivity(in);
                }
                break;

            case R.id.layout_lock:
                Intent i = new Intent(this, SharePost.class);
                i.putExtra("img_url", getIntent().getStringExtra("img_url"));
                i.putExtra("network_fragment", my_network);
                i.putExtra("business_fragment", business_interaction);
                startActivityForResult(i, 2);
                finallist.clear();
                break;

            case R.id.layout_clock:
                Intent in = new Intent(this, SchedulePost.class);
                in.putExtra("img_url", getIntent().getStringExtra("img_url"));
                in.putExtra("network_fragment", my_network);
                in.putExtra("business_fragment", business_interaction);
                startActivityForResult(in, 1);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                date = data.getStringExtra("date");
                time = data.getStringExtra("time");
                Log.d("date_back", date);
                Log.d("time_back", time);
            }
        } else if (requestCode == 2) {
            if (resultCode == RESULT_OK) {
                finallist = (ArrayList<String>) data.getSerializableExtra("finallist");
                Log.d("finallist++++", String.valueOf(finallist));
            }
        }
    }
}