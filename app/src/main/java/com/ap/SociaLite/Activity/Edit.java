package com.ap.SociaLite.Activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.ap.SociaLite.Application.BitmapUtils;
import com.ap.SociaLite.PictureThread;
import com.ap.SociaLite.R;
import com.zomato.photofilters.imageprocessors.Filter;
import com.zomato.photofilters.imageprocessors.subfilters.BrightnessSubFilter;
import com.zomato.photofilters.imageprocessors.subfilters.ColorOverlaySubFilter;
import com.zomato.photofilters.imageprocessors.subfilters.ContrastSubFilter;
import com.zomato.photofilters.imageprocessors.subfilters.SaturationSubfilter;
import com.zomato.photofilters.imageprocessors.subfilters.VignetteSubfilter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Edit extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    @BindView(R.id.imageView)
    ImageView imageView;

    @BindView(R.id.exposure_seekBar)
    SeekBar exposure_seekBar;

    @BindView(R.id.saturation_seekBar)
    SeekBar saturation_seekBar;

    @BindView(R.id.sharpness_seekBar)
    SeekBar sharpness_seekBar;

    @BindView(R.id.shadow_seekBar)
    SeekBar shadow_seekBar;

    @BindView(R.id.brightness_seekBar)
    SeekBar brightness_seekBar;

    @BindView(R.id.contrast_seekBar)
    SeekBar contrast_seekBar;

    @BindView(R.id.highlight_seekBar)
    SeekBar highlight_seekBar;

    @BindView(R.id.warmth_seekBar)
    SeekBar warmth_seekBar;

    @BindView(R.id.filter_name)
    TextView filter_name;

    private Bitmap bitmap;
    private PictureThread thread;

    Bitmap finalImage, filteredImage, originalImage;
    int brightnessFinal = 0;
    float saturationFinal = 1.0f;
    float contrastFinal = 1.0f;
    int exposureFinal = 0;

    static {
        System.loadLibrary("NativeImageProcessor");
    }

    Uri imgUri1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        ButterKnife.bind(this);

        imgUri1 = Uri.parse(getIntent().getStringExtra("imageUri1"));

        if (imgUri1 != null) {
            bitmap = BitmapUtils.getBitmapFromGallery(this, imgUri1, 800, 800);
            originalImage = bitmap.copy(Bitmap.Config.ARGB_8888, true);
            filteredImage = originalImage.copy(Bitmap.Config.ARGB_8888, true);
            finalImage = originalImage.copy(Bitmap.Config.ARGB_8888, true);
            imageView.setImageBitmap(originalImage);

            exposure_seekBar.setOnSeekBarChangeListener(this);
            highlight_seekBar.setOnSeekBarChangeListener(this);
            shadow_seekBar.setOnSeekBarChangeListener(this);
            contrast_seekBar.setOnSeekBarChangeListener(this);
            brightness_seekBar.setOnSeekBarChangeListener(this);
            saturation_seekBar.setOnSeekBarChangeListener(this);
            warmth_seekBar.setOnSeekBarChangeListener(this);
            sharpness_seekBar.setOnSeekBarChangeListener(this);
        }

        // image = getIntent().getStringExtra("img2");

//        if (image != null) {
//            File imgFile = new File(image);
//
//            if (imgFile.exists()) {
//
//                bitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
//                imageView.setImageBitmap(bitmap);
//                imageView.setTag(imgFile.toString());
//
//                thread = new PictureThread(imageView, bitmap);
//                thread.start();
//            }
//        }


//        saturation_seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
//                loadBitmapSat();
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//                loadBitmapSat();
//            }
//        });

//        brightness_seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
//
//                onBrightnessChanged(progress - 100);
//                //     thread.adjustBrightness(seekBar.getProgress() - 100);
//                imageView.setColorFilter(setBrightness(i));
//                if (listener != null) {

//                }
//                else
//                {
//                    Toast.makeText(getApplicationContext(),"null",Toast.LENGTH_LONG).show();
//                }
        //    }

//        contrast_seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
//                //      imageView.setImageBitmap(changeBitmapContrastBrightness(bitmap, (float) i / 100f, 1));
//
//                //      thread.adjustContrast(seekBar.getProgress());
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//
//            }
//        });

    }

    //Brightness
//    public static PorterDuffColorFilter setBrightness(int progress) {
//        if (progress >= 100) {
//            int value = (int) (progress - 100) * 255 / 100;
//
//            return new PorterDuffColorFilter(Color.argb(value, 255, 255, 255), PorterDuff.Mode.SRC_OVER);
//
//        } else {
//            int value = (int) (100 - progress) * 255 / 100;
//            return new PorterDuffColorFilter(Color.argb(value, 0, 0, 0), PorterDuff.Mode.SRC_ATOP);
//        }
//    }

    //Saturation
//    private void loadBitmapSat() {
//        if (bitmap != null) {
//            int progressSat = saturation_seekBar.getProgress();
//
//            //Saturation, 0=gray-scale. 1=identity
//            float sat = (float) progressSat / 256;
//            imageView.setImageBitmap(updateSat(bitmap, sat));
//        }
//    }

//    private Bitmap updateSat(Bitmap src, float settingSat) {
//
//        int w = src.getWidth();
//        int h = src.getHeight();
//
//        Bitmap bitmapResult =
//                Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
//        Canvas canvasResult = new Canvas(bitmapResult);
//        Paint paint = new Paint();
//        ColorMatrix colorMatrix = new ColorMatrix();
//        colorMatrix.setSaturation(settingSat);
//        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(colorMatrix);
//        paint.setColorFilter(filter);
//        canvasResult.drawBitmap(src, 0, 0, paint);
//
//        return bitmapResult;
//    }

    //contrast
//    public static Bitmap changeBitmapContrastBrightness(Bitmap bmp, float contrast, float brightness) {
//        ColorMatrix cm = new ColorMatrix(new float[]
//                {
//                        contrast, 0, 0, 0, brightness,
//                        0, contrast, 0, 0, brightness,
//                        0, 0, contrast, 0, brightness,
//                        0, 0, 0, 1, 0
//                });
//
//        Bitmap ret = Bitmap.createBitmap(bmp.getWidth(), bmp.getHeight(), bmp.getConfig());
//
//        Canvas canvas = new Canvas(ret);
//
//        Paint paint = new Paint();
//        paint.setColorFilter(new ColorMatrixColorFilter(cm));
//        canvas.drawBitmap(bmp, 0, 0, paint);
//
//        return ret;
//    }


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
        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) {
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

    @OnClick({R.id.img_cross, R.id.btn_save, R.id.exposure, R.id.highlights, R.id.saturation, R.id.sharpness,R.id.shadow,
            R.id.brightness, R.id.contrast, R.id.warmth})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.img_cross:
                finish();
                break;

            case R.id.btn_save:
                final String path = BitmapUtils.insertImage(getContentResolver(), finalImage, System.currentTimeMillis() + "_profile.jpg", null);
                if (!TextUtils.isEmpty(path)) {
                    Intent shape = new Intent(Edit.this, CameraActivity.class);
                    shape.putExtra("Brightness_path", path);
                    startActivity(shape);
                }
                break;

            case R.id.exposure:
                filter_name.setText("Exposure");
                exposure_seekBar.setVisibility(View.VISIBLE);
                highlight_seekBar.setVisibility(View.GONE);
                shadow_seekBar.setVisibility(View.GONE);
                contrast_seekBar.setVisibility(View.GONE);
                brightness_seekBar.setVisibility(View.GONE);
                saturation_seekBar.setVisibility(View.GONE);
                warmth_seekBar.setVisibility(View.GONE);
                sharpness_seekBar.setVisibility(View.GONE);
                break;

            case R.id.highlights:
                filter_name.setText("Highlights");
                exposure_seekBar.setVisibility(View.GONE);
                highlight_seekBar.setVisibility(View.VISIBLE);
                shadow_seekBar.setVisibility(View.GONE);
                contrast_seekBar.setVisibility(View.GONE);
                brightness_seekBar.setVisibility(View.GONE);
                saturation_seekBar.setVisibility(View.GONE);
                warmth_seekBar.setVisibility(View.GONE);
                sharpness_seekBar.setVisibility(View.GONE);
                break;

            case R.id.warmth:
                filter_name.setText("Warmth");
                exposure_seekBar.setVisibility(View.GONE);
                highlight_seekBar.setVisibility(View.GONE);
                shadow_seekBar.setVisibility(View.GONE);
                contrast_seekBar.setVisibility(View.GONE);
                brightness_seekBar.setVisibility(View.GONE);
                saturation_seekBar.setVisibility(View.GONE);
                warmth_seekBar.setVisibility(View.VISIBLE);
                sharpness_seekBar.setVisibility(View.GONE);
                break;

            case R.id.saturation:
                filter_name.setText("Saturation");
                exposure_seekBar.setVisibility(View.GONE);
                highlight_seekBar.setVisibility(View.GONE);
                shadow_seekBar.setVisibility(View.GONE);
                contrast_seekBar.setVisibility(View.GONE);
                brightness_seekBar.setVisibility(View.GONE);
                saturation_seekBar.setVisibility(View.VISIBLE);
                warmth_seekBar.setVisibility(View.GONE);
                sharpness_seekBar.setVisibility(View.GONE);
                break;

            case R.id.sharpness:
                filter_name.setText("Sharpness");
                exposure_seekBar.setVisibility(View.GONE);
                highlight_seekBar.setVisibility(View.GONE);
                shadow_seekBar.setVisibility(View.GONE);
                contrast_seekBar.setVisibility(View.GONE);
                brightness_seekBar.setVisibility(View.GONE);
                saturation_seekBar.setVisibility(View.GONE);
                warmth_seekBar.setVisibility(View.GONE);
                sharpness_seekBar.setVisibility(View.VISIBLE);
                break;

            case R.id.shadow:
                filter_name.setText("Shadow");
                exposure_seekBar.setVisibility(View.GONE);
                highlight_seekBar.setVisibility(View.GONE);
                shadow_seekBar.setVisibility(View.VISIBLE);
                contrast_seekBar.setVisibility(View.GONE);
                brightness_seekBar.setVisibility(View.GONE);
                saturation_seekBar.setVisibility(View.GONE);
                warmth_seekBar.setVisibility(View.GONE);
                sharpness_seekBar.setVisibility(View.GONE);
                break;

            case R.id.brightness:
                filter_name.setText("Brightness");
                exposure_seekBar.setVisibility(View.GONE);
                highlight_seekBar.setVisibility(View.GONE);
                shadow_seekBar.setVisibility(View.GONE);
                contrast_seekBar.setVisibility(View.GONE);
                brightness_seekBar.setVisibility(View.VISIBLE);
                saturation_seekBar.setVisibility(View.GONE);
                warmth_seekBar.setVisibility(View.GONE);
                sharpness_seekBar.setVisibility(View.GONE);
                break;

            case R.id.contrast:
                filter_name.setText("Contrast");
                exposure_seekBar.setVisibility(View.GONE);
                highlight_seekBar.setVisibility(View.GONE);
                shadow_seekBar.setVisibility(View.GONE);
                contrast_seekBar.setVisibility(View.VISIBLE);
                brightness_seekBar.setVisibility(View.GONE);
                saturation_seekBar.setVisibility(View.GONE);
                warmth_seekBar.setVisibility(View.GONE);
                sharpness_seekBar.setVisibility(View.GONE);
                break;
        }
    }

    public void onExposureChanged(Context context,int alpha) {
        exposureFinal = alpha;
        Filter myFilter = new Filter();
        myFilter.addSubFilter(new VignetteSubfilter(context ,alpha));
        imageView.setImageBitmap(myFilter.processFilter(finalImage.copy(Bitmap.Config.ARGB_8888, true)));
    }

    public void onBrightnessChanged(int brightness) {
        brightnessFinal = brightness;
        Filter myFilter = new Filter();
        myFilter.addSubFilter(new BrightnessSubFilter(brightness));
        imageView.setImageBitmap(myFilter.processFilter(finalImage.copy(Bitmap.Config.ARGB_8888, true)));
    }

    public void onSaturationChanged(float saturation) {
        saturationFinal = saturation;
        Filter myFilter = new Filter();
        myFilter.addSubFilter(new SaturationSubfilter(saturation));
        imageView.setImageBitmap(myFilter.processFilter(finalImage.copy(Bitmap.Config.ARGB_8888, true)));
    }

    public void onContrastChanged(float contrast) {
        contrastFinal = contrast;
        Filter myFilter = new Filter();
        myFilter.addSubFilter(new ContrastSubFilter(contrast));
        imageView.setImageBitmap(myFilter.processFilter(finalImage.copy(Bitmap.Config.ARGB_8888, true)));
    }

    public void onEditStarted() {

    }

    public void onEditCompleted() {
// once the editing is done i.e seekbar is drag is completed,
        // apply the values on to filtered image
        final Bitmap bitmap = filteredImage.copy(Bitmap.Config.ARGB_8888, true);

        Filter myFilter = new Filter();
        myFilter.addSubFilter(new BrightnessSubFilter(brightnessFinal));
        myFilter.addSubFilter(new ContrastSubFilter(contrastFinal));
        myFilter.addSubFilter(new SaturationSubfilter(saturationFinal));
        finalImage = myFilter.processFilter(bitmap);
    }


    public static Bitmap hue(Bitmap bitmap, float hue) {
        Bitmap newBitmap = bitmap.copy(bitmap.getConfig(), true);
        final int width = newBitmap.getWidth();
        final int height = newBitmap.getHeight();
        float [] hsv = new float[3];

        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                int pixel = newBitmap.getPixel(x,y);
                Color.colorToHSV(pixel,hsv);
                hsv[0] = hue;
                newBitmap.setPixel(x,y, Color.HSVToColor(Color.alpha(pixel),hsv));
            }
        }

        bitmap.recycle();
        bitmap = null;

        return newBitmap;
       }
//
//else if (selected_effect.equals("hue")) {
//        selected_effect = selected_effect + "_" +
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {

        if(seekBar.getId() == R.id.exposure_seekBar)
        {
//            Filter myFilter = new Filter();
//            myFilter.addSubFilter(new ColorOverlaySubFilter(100, .2f, .2f, .0f));
//            imageView.setImageBitmap(myFilter.processFilter(finalImage.copy(Bitmap.Config.ARGB_8888, true)));

            //    onExposureChanged(this,progress - 100);
        }
        else if (seekBar.getId() == R.id.brightness_seekBar) {
            onBrightnessChanged(progress - 100);
        } else if (seekBar.getId() == R.id.contrast_seekBar) {
            progress += 10;
            float floatVal = .10f * progress;
            onContrastChanged(floatVal);
        } else if (seekBar.getId() == R.id.saturation_seekBar) {
            float floatVal = .10f * progress;
            onSaturationChanged(floatVal);
        }
        else if (seekBar.getId() == R.id.shadow_seekBar) {

            int shadow = -8000 * progress;
            imageView.setImageBitmap(applyShadingFilter(bitmap, shadow));

            //     imageView.setImageBitmap(applyShadingFilter(bitmap,12));
        } else if (seekBar.getId() == R.id.sharpness_seekBar) {

//            sharpness_seekBar.setProgress(180);
      //      bitmap = BitmapProcessing.hue(bitmap, (float) sharpness_seekBar.getProgress());
//            modifyHueHolder();
//            hue_value.setOnSeekBarChangeListener(onHueChange);

  //          bitmap = hue(bitmap, (float) sharpness_seekBar.getProgress());

            hue(bitmap,110);
   //         imageView.setImageBitmap(hue(bitmap,sharpness_seekBar.getProgress()));
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        onEditStarted();
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        onEditCompleted();
    }
}