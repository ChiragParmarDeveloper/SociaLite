package com.ap.SociaLite.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

import com.ap.SociaLite.Application.ConvolutionMatrix;
import com.ap.SociaLite.PictureThread;
import com.ap.SociaLite.R;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Edit extends AppCompatActivity {

    @BindView(R.id.imageView)
    ImageView imageView;

    @BindView(R.id.exposure)
    ImageView exposure;

    @BindView(R.id.brightness)
    ImageView brightness;

    @BindView(R.id.contrast)
    ImageView contrast;

    @BindView(R.id.saturation)
    ImageView saturation;

    @BindView(R.id.exposure_seekBar)
    SeekBar exposure_seekBar;

    @BindView(R.id.saturation_seekBar)
    SeekBar saturation_seekBar;

    @BindView(R.id.sharpness)
    ImageView sharpness;

    @BindView(R.id.shadow)
    ImageView shadow;


    @BindView(R.id.sharpness_seekBar)
    SeekBar sharpness_seekBar;

    @BindView(R.id.shadow_seekBar)
    SeekBar shadow_seekBar;

    SeekBar seekBar, contrast_seekBar;
    private Bitmap bitmap;
    private PictureThread thread;

    String image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        ButterKnife.bind(this);

        image = getIntent().getStringExtra("img2");
        if (image != null) {
            File imgFile = new File(image);

            if (imgFile.exists()) {

                bitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                imageView.setImageBitmap(bitmap);
                imageView.setTag(imgFile.toString());
                thread = new PictureThread(imageView, bitmap);
                thread.start();

            }
        }

        seekBar = findViewById(R.id.brightness_seekBar);
        contrast_seekBar = findViewById(R.id.contrast_seekBar);

        brightness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exposure_seekBar.setVisibility(View.GONE);
                saturation_seekBar.setVisibility(View.GONE);
                seekBar.setVisibility(View.VISIBLE);
                contrast_seekBar.setVisibility(View.GONE);
                sharpness_seekBar.setVisibility(View.GONE);
                shadow_seekBar.setVisibility(View.GONE);

            }
        });

        contrast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exposure_seekBar.setVisibility(View.GONE);
                seekBar.setVisibility(View.GONE);
                saturation_seekBar.setVisibility(View.GONE);
                contrast_seekBar.setVisibility(View.VISIBLE);
                sharpness_seekBar.setVisibility(View.GONE);
                shadow_seekBar.setVisibility(View.GONE);

            }
        });

        exposure_seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        saturation_seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                loadBitmapSat();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                loadBitmapSat();
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                //    thread.adjustBrightness(seekBar.getProgress() - 100);

                imageView.setColorFilter(setBrightness(i));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        contrast_seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                imageView.setImageBitmap(changeBitmapContrastBrightness(bitmap, (float) i / 100f, 1));

                //      thread.adjustContrast(seekBar.getProgress());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        sharpness_seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                imageView.setImageBitmap(sharpenImage(bitmap,12));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        shadow_seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                imageView.setImageBitmap(applyShadingFilter(BitmapFactory.decodeResource(getResources(), R.drawable.dummy1),-180000));

          //      imageView.setImageBitmap(applyShadingFilter(bitmap,12));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    //Brightness
    public static PorterDuffColorFilter setBrightness(int progress) {
        if (progress >= 100) {
            int value = (int) (progress - 100) * 255 / 100;

            return new PorterDuffColorFilter(Color.argb(value, 255, 255, 255), PorterDuff.Mode.SRC_OVER);

        } else {
            int value = (int) (100 - progress) * 255 / 100;
            return new PorterDuffColorFilter(Color.argb(value, 0, 0, 0), PorterDuff.Mode.SRC_ATOP);
        }
    }

    //contrast
    public static Bitmap changeBitmapContrastBrightness(Bitmap bmp, float contrast, float brightness) {
        ColorMatrix cm = new ColorMatrix(new float[]
                {
                        contrast, 0, 0, 0, brightness,
                        0, contrast, 0, 0, brightness,
                        0, 0, contrast, 0, brightness,
                        0, 0, 0, 1, 0
                });

        Bitmap ret = Bitmap.createBitmap(bmp.getWidth(), bmp.getHeight(), bmp.getConfig());

        Canvas canvas = new Canvas(ret);

        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(cm));
        canvas.drawBitmap(bmp, 0, 0, paint);

        return ret;
    }

    //Saturation
    private void loadBitmapSat() {
        if (bitmap != null) {

            int progressSat = saturation_seekBar.getProgress();

            //Saturation, 0=gray-scale. 1=identity
            float sat = (float) progressSat / 256;
            imageView.setImageBitmap(updateSat(bitmap, sat));
        }
    }

    private Bitmap updateSat(Bitmap src, float settingSat) {

        int w = src.getWidth();
        int h = src.getHeight();

        Bitmap bitmapResult =
                Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvasResult = new Canvas(bitmapResult);
        Paint paint = new Paint();
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(settingSat);
        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(colorMatrix);
        paint.setColorFilter(filter);
        canvasResult.drawBitmap(src, 0, 0, paint);

        return bitmapResult;
    }

    //Sharpness
    public  Bitmap sharpenImage(Bitmap src, double weight) {
        // set sharpness configuration
        double[][] SharpConfig = new double[][] {
                { 0 , -2    , 0  },
                { -2, weight, -2 },
                { 0 , -2    , 0  }
        };
        //create convolution matrix instance
        ConvolutionMatrix convMatrix = new ConvolutionMatrix(3);
        //apply configuration
        convMatrix.applyConfig(SharpConfig);
        //set weight according to factor
        convMatrix.Factor = weight - 8;
        return ConvolutionMatrix.computeConvolution3x3(src, convMatrix);
    }

    //shadow
    public Bitmap applyShadingFilter(Bitmap source, int shadingColor) {
        // get original image size
        int width = source.getWidth();
        int height = source.getHeight();
        int[] pixels = new int[width * height];
        // get pixel array from source image
        source.getPixels(pixels, 0, width, 0, 0, width, height);

        int index = 0;
        // iteration through all pixels
        for(int y = 0; y < height; ++y) {
            for(int x = 0; x < width; ++x) {
                // get current index in 2D-matrix
                index = y * width + x;
                // AND
                pixels[index] &= shadingColor;
            }
        }
        // output bitmap
        Bitmap bmOut = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        bmOut.setPixels(pixels, 0, width, 0, 0, width, height);
        return bmOut;
    }


    @OnClick({R.id.img_cross, R.id.btn_save, R.id.exposure,R.id.saturation,R.id.sharpness,R.id.shadow})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.img_cross:
                finish();
                break;
            case R.id.btn_save:
                startActivity(new Intent(Edit.this, CameraActivity.class));
                break;

            case R.id.exposure:
                seekBar.setVisibility(View.GONE);
                contrast_seekBar.setVisibility(View.GONE);
                exposure_seekBar.setVisibility(View.VISIBLE);
                saturation_seekBar.setVisibility(View.GONE);
                sharpness_seekBar.setVisibility(View.GONE);
                shadow_seekBar.setVisibility(View.GONE);

                break;

            case R.id.saturation:
                seekBar.setVisibility(View.GONE);
                contrast_seekBar.setVisibility(View.GONE);
                exposure_seekBar.setVisibility(View.GONE);
                saturation_seekBar.setVisibility(View.VISIBLE);
                sharpness_seekBar.setVisibility(View.GONE);
                shadow_seekBar.setVisibility(View.GONE);
                break;

            case R.id.sharpness:
                seekBar.setVisibility(View.GONE);
                contrast_seekBar.setVisibility(View.GONE);
                exposure_seekBar.setVisibility(View.GONE);
                saturation_seekBar.setVisibility(View.GONE);
                shadow_seekBar.setVisibility(View.GONE);
                sharpness_seekBar.setVisibility(View.VISIBLE);

                break;
            case R.id.shadow:
                seekBar.setVisibility(View.GONE);
                contrast_seekBar.setVisibility(View.GONE);
                exposure_seekBar.setVisibility(View.GONE);
                saturation_seekBar.setVisibility(View.GONE);
                sharpness_seekBar.setVisibility(View.GONE);
                shadow_seekBar.setVisibility(View.VISIBLE);
                break;


        }
    }
}