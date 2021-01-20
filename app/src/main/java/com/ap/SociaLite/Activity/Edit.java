package com.ap.SociaLite.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

import com.ap.SociaLite.PictureThread;
import com.ap.SociaLite.R;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Edit extends AppCompatActivity {

    @BindView(R.id.img_cross)
    ImageView img_cross;

    @BindView(R.id.btn_save)
    Button btn_save;

    SeekBar seekBar, contrast_seekBar;
    ImageView imageView;
    private Bitmap bitmap;
    private PictureThread thread;
    ImageView contrast, brightness;
    String image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        ButterKnife.bind(this);

        image = getIntent().getStringExtra("img2");
        if(image !=null)
        {
            File imgFile = new File(image);

            if (imgFile.exists()) {

                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                imageView.setImageBitmap(myBitmap);
                imageView.setTag(imgFile.toString());

            }
        }


        seekBar = findViewById(R.id.brightness_seekBar);
        contrast_seekBar = findViewById(R.id.contrast_seekBar);
        imageView = findViewById(R.id.imageView);

        contrast = findViewById(R.id.contrast);
        brightness = findViewById(R.id.brightness);


        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.dummy1);
        imageView.setImageBitmap(bitmap);

        thread = new PictureThread(imageView, bitmap);
        thread.start();

        brightness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                seekBar.setVisibility(View.VISIBLE);
                contrast_seekBar.setVisibility(View.GONE);
            }
        });

        contrast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                seekBar.setVisibility(View.GONE);
                contrast_seekBar.setVisibility(View.VISIBLE);
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                thread.adjustBrightness(seekBar.getProgress() - 100);

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
                thread.adjustContrast(seekBar.getProgress());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }

    @OnClick({R.id.img_cross, R.id.btn_save})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.img_cross:
                finish();
                ///     startActivity(new Intent(CameraActivity.this,LoginActivity.class));
                break;
            case R.id.btn_save:
                startActivity(new Intent(Edit.this, CameraActivity.class));
                break;
        }
    }
}