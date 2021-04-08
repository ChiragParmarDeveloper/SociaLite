package com.ap.SociaLite.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.ap.SociaLite.R;
import com.ap.SociaLite.showCamera;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddSpotlightActivity extends AppCompatActivity {

    Camera camera;
    FrameLayout frameLayout;
    showCamera showCamera;
    private static final int PICK_IMAGE = 100;
    Uri imageUri;
    String picture_path;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_spotlight);
        ButterKnife.bind(this);

        frameLayout = findViewById(R.id.camera_screen);

        camera = Camera.open();

        showCamera = new showCamera(this, camera);


        frameLayout.addView(showCamera);

    }


    Camera.PictureCallback mPictureCallback = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] bytes, Camera camera) {
            File picture_file = getOutputMediaFile();

            if (picture_file == null){


                return;
            }
            else
            {
                try{
                    FileOutputStream fos = new FileOutputStream(picture_file);

                    fos.write(bytes);
                    fos.close();

                    camera.startPreview();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }

            }

        }
    };

    private File getOutputMediaFile() {
        String state = Environment.getExternalStorageState();
        if(!state.equals(Environment.MEDIA_MOUNTED)){
            return null;
        }
        else{
            File folder_gui = new File(Environment.getExternalStorageDirectory() + File.separator + "Socialite");

            if (!folder_gui.exists())
            {
                folder_gui.mkdirs();
            }

            File outputFile = new File(folder_gui,"temp.jpg");

            return outputFile;

        }
    }

    public void captureImage(View v)
    {
//        if(camera!=null){
//            camera.takePicture(null,null,mPictureCallback);
//        }
        Intent capture = new Intent(AddSpotlightActivity.this,AddSpotlightActivity_2.class);
        startActivity(capture);
    }

    @OnClick({R.id.img_cross,R.id.gallary})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.img_cross:
                onBackPressed();
                break;
            case R.id.gallary:
                openGallery();
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
                picture_path = getRealPathFromURI(imageUri);

                Intent capture = new Intent(AddSpotlightActivity.this,AddSpotlightActivity_2.class);
                capture.putExtra("path",picture_path);
                startActivity(capture);

            } else if (imageUri.toString().contains("video")) {

//                img_two.setVideoURI(imageUri);
//                img_two.seekTo( 1 );
                // img_two.start();
              //  picture_path = getRealPathFromURI(imageUri);
                //Toast.makeText(getApplicationContext(), picture_path, Toast.LENGTH_SHORT).show();


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