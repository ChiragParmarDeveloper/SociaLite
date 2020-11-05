package com.ap.SociaLite.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.FrameLayout;

import com.ap.SociaLite.R;
import com.ap.SociaLite.showCamera;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;

public class AddSpotlightActivity extends AppCompatActivity {

    Camera camera;
    FrameLayout frameLayout;
    showCamera showCamera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_spotlight);

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
}