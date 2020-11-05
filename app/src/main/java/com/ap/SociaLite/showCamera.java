package com.ap.SociaLite;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import java.io.IOException;
import java.util.List;

public class showCamera extends SurfaceView implements SurfaceHolder.Callback {

    Camera camera;
    SurfaceHolder holder;

    public showCamera(Context context, Camera camera) {
        super(context);
        this.camera = camera;
        holder = getHolder();
        holder.addCallback(this);
    }


    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {



    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

       // camera.stopPreview();
//        camera.release();
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        Camera.Parameters params = camera.getParameters();

        List<Camera.Size> sizes = params.getSupportedPictureSizes();
        Camera.Size msize = null;

        for(Camera.Size size : sizes)
        {
            msize = size;
        }


        if(this.getResources().getConfiguration().orientation != Configuration.ORIENTATION_LANDSCAPE){

            params.set("orientation", "portrait");
            camera.setDisplayOrientation(90);
            params.setRotation(90);
        }
        else{
            params.set("orientaion","landscape");
            camera.setDisplayOrientation(0);
            params.setRotation(0);
        }

        params.setPictureSize(msize.width,msize.height);
        camera.setParameters(params);


        try {
            camera.setPreviewDisplay(holder);
            camera.startPreview();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }


    }
}
