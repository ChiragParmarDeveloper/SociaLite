package com.ap.SociaLite.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ap.SociaLite.R;

import java.io.ByteArrayOutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CameraActivity extends AppCompatActivity {


    @BindView(R.id.layout_edit)
    LinearLayout layout_edit;

    @BindView(R.id.img_edit)
    ImageView img_edit;

    @BindView(R.id.layout_lock)
    LinearLayout layout_lock;

    @BindView(R.id.img_lock)
    ImageView img_lock;

    @BindView(R.id.layout_clock)
    LinearLayout layout_clock;

    @BindView(R.id.img_clock)
    ImageView img_clock;


    @BindView(R.id.layout_filter)
    LinearLayout layout_filter;

    @BindView(R.id.img_filter)
    ImageView img_filter;

    @BindView(R.id.layout_drama)
    LinearLayout layout_drama;

    @BindView(R.id.img_drama)
    ImageView img_drama;

    @BindView(R.id.img_back)
    ImageView img_back;

    @BindView(R.id.next)
    Button next;

    @BindView(R.id.imageView)
    ImageView imageView;

    @BindView(R.id.img_one)
    ImageView img_one;

    @BindView(R.id.img_three)
    ImageView img_three;

    Bitmap bitmap;
    String my_network, business_interaction, picture_path;
    private static final int PICK_IMAGE = 100;
    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        ButterKnife.bind(this);

        Bitmap mbitmap = ((BitmapDrawable) getResources().getDrawable(R.drawable.img_plus)).getBitmap();
        Bitmap imageRounded = Bitmap.createBitmap(mbitmap.getWidth(), mbitmap.getHeight(), mbitmap.getConfig());
        Canvas canvas = new Canvas(imageRounded);
        Paint mpaint = new Paint();
        mpaint.setAntiAlias(true);
        mpaint.setShader(new BitmapShader(mbitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        canvas.drawRoundRect((new RectF(0, 0, mbitmap.getWidth(), mbitmap.getHeight())), 100, 100, mpaint); // Round Image Corner 100 100 100 100
        imageView.setImageBitmap(imageRounded);

        my_network = getIntent().getStringExtra("network_fragment");
        business_interaction = getIntent().getStringExtra("business_fragment");

    }

    @OnClick({R.id.img_back, R.id.next, R.id.layout_edit, R.id.layout_lock, R.id.layout_clock, R.id.layout_filter, R.id.layout_drama,
            R.id.img_one, R.id.img_three})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                onBackPressed();
                break;

            case R.id.next:

                if (my_network != null) {
                    Intent in = new Intent(CameraActivity.this, PostNetwork.class);
                    startActivity(in);
                } else if (business_interaction != null) {
                    Intent in = new Intent(CameraActivity.this, PostBusiness.class);
                    startActivity(in);
                } else {

                    if (bitmap != null) {
                        Intent in = new Intent(CameraActivity.this, Post.class);
                        // your bitmap
                        ByteArrayOutputStream bs = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.PNG, 50, bs);
                        in.putExtra("byteArray", bs.toByteArray());
                        startActivity(in);
                    } else if (picture_path != null) {

                        Intent in = new Intent(CameraActivity.this, Post.class);
                        in.putExtra("path", picture_path);
                        startActivity(in);

                    }

                }
                break;

            case R.id.layout_edit:
                startActivity(new Intent(CameraActivity.this, Text.class));
                break;

            case R.id.layout_lock:
                startActivity(new Intent(CameraActivity.this, SharePost.class));
                break;

            case R.id.layout_clock:
                startActivity(new Intent(CameraActivity.this, SchedulePost.class));
                break;

            case R.id.layout_filter:
                startActivity(new Intent(CameraActivity.this, Edit.class));
                break;

            case R.id.layout_drama:
                startActivity(new Intent(CameraActivity.this, ShapeCut.class));
                break;

            case R.id.img_one:
                openGallery();
                break;

            case R.id.img_three:

                img_three.buildDrawingCache();
                bitmap = img_three.getDrawingCache();
                imageView.setImageBitmap(bitmap);

                break;

        }
    }

    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        gallery.setType("image/* video/*");
        startActivityForResult(gallery, PICK_IMAGE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Result if we want hint number
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {

            imageUri = data.getData();
            if (imageUri.toString().contains("image")) {

                //  imageView.setImageBitmap(bitmap);
                imageView.setImageURI(imageUri);
//                imageView.buildDrawingCache();
//                bitmap = imageView.getDrawingCache();
                picture_path = getRealPathFromURI(imageUri);


            } else if (imageUri.toString().contains("video")) {

//                img_two.setVideoURI(imageUri);
//                img_two.seekTo(1);
                // img_two.start();
               picture_path = getRealPathFromURI(imageUri);
           //     Toast.makeText(getApplicationContext(), picture_path, Toast.LENGTH_SHORT).show();

//                MediaController mediaController = new MediaController(this);
//// initiate a video view
//          //      VideoView simpleVideoView = (VideoView) findViewById(R.id.simpleVideoView);
//// set media controller object for a video view
//                img_two.setMediaController(mediaController);

            }

        } else {

        }
    }

    public String getRealPathFromURI(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        @SuppressWarnings("deprecation")
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor
                .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

}