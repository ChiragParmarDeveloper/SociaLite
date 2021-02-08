package com.ap.SociaLite.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.ap.SociaLite.PictureThread;
import com.ap.SociaLite.R;

import java.io.File;

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

    @BindView(R.id.imageView)
    ImageView imageView;

    @BindView(R.id.img_one)
    ImageView img_one;

    @BindView(R.id.img_two)
    ImageView img_two;

    @BindView(R.id.img_three)
    ImageView img_three;

    @BindView(R.id.video_one)
    VideoView video_one;

    @BindView(R.id.video)
    VideoView video;

    @BindView(R.id.video_two)
    VideoView video_two;

    @BindView(R.id.video_three)
    VideoView video_three;

    Bitmap bitmap;
    String my_network, business_interaction, path_one, path_two, path_three;
    private static final int PICK_IMAGE_one = 100;
    private static final int PICK_IMAGE_two = 110;
    private static final int PICK_IMAGE_three = 120;
    Uri imageUri1, imageUri2, imageUri3;
    String path,Brightness_path;

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

        Brightness_path = getIntent().getStringExtra("Brightness_path");
        Toast.makeText(getApplicationContext(),Brightness_path,Toast.LENGTH_LONG).show();
        if(Brightness_path !=null)
        {
            File imgFile = new File(Brightness_path);

            if (imgFile.exists()) {

                bitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                imageView.setImageBitmap(bitmap);
                imageView.setTag(imgFile.toString());

            }
        }

    }

    @OnClick({R.id.img_back, R.id.next, R.id.layout_edit, R.id.layout_lock, R.id.layout_clock, R.id.layout_filter, R.id.layout_drama,
            R.id.img_one, R.id.img_two, R.id.img_three, R.id.btn_one, R.id.btn_two, R.id.btn_three})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                onBackPressed();
                break;

            case R.id.next:

                if (my_network != null) {
                    Intent in = new Intent(CameraActivity.this, PostNetwork.class);
                    in.putExtra("img1", path_one);
                    in.putExtra("img2", path_two);
                    in.putExtra("img3", path_three);
                    startActivity(in);
                } else if (business_interaction != null) {
                    Intent in = new Intent(CameraActivity.this, PostBusiness.class);
                    in.putExtra("img1", path_one);
                    in.putExtra("img2", path_two);
                    in.putExtra("img3", path_three);
                    startActivity(in);
                } else {
                    Intent in = new Intent(CameraActivity.this, Post.class);
                    in.putExtra("img1", path_one);
                    in.putExtra("img2", path_two);
                    in.putExtra("img3", path_three);
                    startActivity(in);
                }
                break;

            case R.id.layout_edit:
                Intent in = new Intent(CameraActivity.this, Text.class);
                //  in.putExtra("img1", path_one);
                in.putExtra("img2", path);
                startActivity(in);
                break;

            case R.id.layout_lock:
                Intent sharepost = new Intent(CameraActivity.this, SharePost.class);
                sharepost.putExtra("img2", path);
                startActivity(sharepost);
                break;

            case R.id.layout_clock:
                Intent post = new Intent(CameraActivity.this, SchedulePost.class);
                post.putExtra("img1", path_one);
                post.putExtra("img2", path_two);
                post.putExtra("img3", path_three);
                startActivity(post);
                break;

            case R.id.layout_filter:
                Intent edit = new Intent(CameraActivity.this, Edit.class);
                edit.putExtra("img2", path);
                startActivity(edit);
                break;

            case R.id.layout_drama:

                Intent shape = new Intent(CameraActivity.this, ShapeCut.class);
                shape.putExtra("img1", path_one);
                shape.putExtra("img2", path_two);
                shape.putExtra("img3", path_three);
                startActivity(shape);
                break;

            case R.id.img_one:
                if (path_one != null) {
                    File imgone = new File(path_one);
                    if (imgone.exists()) {
                        //        imageView.setImageURI(Uri.fromFile(imgone));

                        Bitmap myBitmap = BitmapFactory.decodeFile(imgone.getAbsolutePath());
                        imageView.setImageBitmap(myBitmap);

                        imageView.setTag(imgone.toString());
                        path = imageView.getTag().toString();
                    }
                }

                break;

            case R.id.img_two:

                if (path_two != null) {
                    File imgtwo = new File(path_two);
                    if (imgtwo.exists()) {
                        //   imageView.setImageURI(Uri.fromFile(imgtwo));
                        Bitmap myBitmap = BitmapFactory.decodeFile(imgtwo.getAbsolutePath());
                        imageView.setImageBitmap(myBitmap);

                        imageView.setTag(imgtwo.toString());
                        path = imageView.getTag().toString();

                    }
                }
                break;

            case R.id.img_three:

                if (path_three != null) {
                    File imgthree = new File(path_three);
                    if (imgthree.exists()) {
                        //       imageView.setImageURI(Uri.fromFile(imgthree));
                        Bitmap myBitmap = BitmapFactory.decodeFile(imgthree.getAbsolutePath());
                        imageView.setImageBitmap(myBitmap);

                        imageView.setTag(imgthree.toString());
                        path = imageView.getTag().toString();
                    }
                }
                break;

            case R.id.btn_one:
                openGallery();
                break;

            case R.id.btn_two:
                openGallery1();
                break;
            case R.id.btn_three:
                openGallery2();
                break;

        }
    }

    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        gallery.setType("image/* video/*");
        startActivityForResult(gallery, PICK_IMAGE_one);
    }

    private void openGallery1() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        gallery.setType("image/* video/*");
        startActivityForResult(gallery, PICK_IMAGE_two);
    }

    private void openGallery2() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        gallery.setType("image/* video/*");
        startActivityForResult(gallery, PICK_IMAGE_three);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case PICK_IMAGE_one:
                if (requestCode == PICK_IMAGE_one && resultCode == RESULT_OK && null != data) {
                    imageUri1 = data.getData();
                    if (imageUri1.toString().contains("image")) {
                        imageView.setImageURI(imageUri1);
                        img_one.setImageURI(imageUri1);
                        path_one = getRealPathFromURI(imageUri1);
                    }
//                    else if (imageUri.toString().contains("video")) {
//                        imageView.setImageURI(null);
//                        img_one.setImageURI(null);
//
//                        video.setVisibility(View.VISIBLE);
//                        video_one.setVisibility(View.VISIBLE);
//                        video_one.setVideoURI(imageUri);
//                        video.setVideoURI(imageUri);
//                        video.seekTo(1);
//                        video_one.seekTo(1);
//                        // img_two.start();
//                        path_one = getRealPathFromURI(imageUri);

//                MediaController mediaController = new MediaController(this);
//// initiate a video view
//          //      VideoView simpleVideoView = (VideoView) findViewById(R.id.simpleVideoView);
//// set media controller object for a video view
//                img_two.setMediaController(mediaController);
                    //             }


                }
                break;

            case PICK_IMAGE_two:
                if (requestCode == PICK_IMAGE_two && resultCode == RESULT_OK && null != data) {
                    imageUri2 = data.getData();
                    if (imageUri2.toString().contains("image")) {
                        img_two.setImageURI(imageUri2);
                        path_two = getRealPathFromURI(imageUri2);
                    }

//                    else if (imageUri.toString().contains("video")) {
//                        imageView.setImageURI(null);
//                        img_two.setImageURI(null);
//
//                        video_two.setVisibility(View.VISIBLE);
//                        video_two.setVideoURI(imageUri);
//                        video_two.seekTo(1);
                    // img_two.start();
                    //     path_two = getRealPathFromURI(imageUri);

//                MediaController mediaController = new MediaController(this);
//// initiate a video view
//          //      VideoView simpleVideoView = (VideoView) findViewById(R.id.simpleVideoView);
//// set media controller object for a video view
//                img_two.setMediaController(mediaController);
                    //          }
                }
                break;

            case PICK_IMAGE_three:
                if (requestCode == PICK_IMAGE_three && resultCode == RESULT_OK && null != data) {
                    imageUri3 = data.getData();
                    if (imageUri3.toString().contains("image")) {
                        img_three.setImageURI(imageUri3);
                        path_three = getRealPathFromURI(imageUri3);
                    }
//                    else if (imageUri.toString().contains("video")) {
//                        video_three.setVisibility(View.VISIBLE);
//                        video_three.setVideoURI(imageUri);
//                        video_three.seekTo(1);
                    // img_two.start();
                    //       path_three = getRealPathFromURI(imageUri);

//                MediaController mediaController = new MediaController(this);
//// initiate a video view
//          //      VideoView simpleVideoView = (VideoView) findViewById(R.id.simpleVideoView);
//// set media controller object for a video view
//                img_two.setMediaController(mediaController);
                    //            }
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
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