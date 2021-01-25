package com.ap.SociaLite.Activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.ap.SociaLite.R;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Text extends AppCompatActivity implements View.OnTouchListener {

    @BindView(R.id.img_cross)
    ImageView img_cross;

    @BindView(R.id.btn_save)
    Button btn_save;

    @BindView(R.id.white)
    ImageView white;

    @BindView(R.id.red)
    ImageView red;

    @BindView(R.id.yellow)
    ImageView yellow;

    @BindView(R.id.green)
    ImageView green;

    @BindView(R.id.orange)
    ImageView orange;

    @BindView(R.id.aqua)
    ImageView aqua;

    @BindView(R.id.blue)
    ImageView blue;

    @BindView(R.id.violet)
    ImageView violet;

    @BindView(R.id.pink)
    ImageView pink;

    @BindView(R.id.black)
    ImageView black;

    @BindView(R.id.imageView)
    ImageView imageView;

    @BindView(R.id.edt_text)
    EditText edt_text;

    final static float move = 200;
    float ratio = 1.0f;
    int baseDist;
    float baseRatio;

    private float dxx, dyy;
    private int lastAction;

    private Bitmap bitmap;
    String image, imageone;

  //  RelativeLayout constraintLayout48;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
        ButterKnife.bind(this);

        edt_text.setTextSize(ratio + 15);

     //   constraintLayout48 = findViewById(R.id.constraintLayout48);
        edt_text.setOnTouchListener(this);

        //   image = getIntent().getStringExtra("img1");
        imageone = getIntent().getStringExtra("img2");

//        if(image !=null)
//        {
//            File imgFile = new File(image);
//
//            if (imgFile.exists()) {
//
//                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
//                imageView.setImageBitmap(myBitmap);
//                imageView.setTag(imgFile.toString());
//                getIntent().removeExtra("img1");
//            }
//        }

        if (imageone != null) {
            File imgFile = new File(imageone);

            if (imgFile.exists()) {

                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                imageView.setImageBitmap(myBitmap);
                imageView.setTag(imgFile.toString());

            }
        }

    }

    @OnClick({R.id.img_cross, R.id.btn_save, R.id.white, R.id.red, R.id.yellow, R.id.green, R.id.orange, R.id.aqua, R.id.blue, R.id.violet, R.id.pink, R.id.black})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.img_cross:
                finish();
                ///     startActivity(new Intent(CameraActivity.this,LoginActivity.class));
                break;
            case R.id.btn_save:
                startActivity(new Intent(Text.this, CameraActivity.class));
                break;

            case R.id.white:
                edt_text.setTextColor(getResources().getColor(R.color.colorwhite));
                break;

            case R.id.red:
                edt_text.setTextColor(getResources().getColor(R.color.colorred));
                break;

            case R.id.yellow:
                edt_text.setTextColor(getResources().getColor(R.color.coloryellow));
                break;

            case R.id.green:
                edt_text.setTextColor(getResources().getColor(R.color.colorgreen));
                break;

            case R.id.orange:
                edt_text.setTextColor(getResources().getColor(R.color.colororange));
                break;

            case R.id.aqua:
                edt_text.setTextColor(getResources().getColor(R.color.coloraqva));
                break;

            case R.id.blue:
                edt_text.setTextColor(getResources().getColor(R.color.colorblue));
                break;

            case R.id.violet:
                edt_text.setTextColor(getResources().getColor(R.color.colorviolet));
                break;

            case R.id.pink:
                edt_text.setTextColor(getResources().getColor(R.color.colorpink));
                break;

            case R.id.black:
                edt_text.setTextColor(getResources().getColor(R.color.colorBlack));
                break;
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                lastAction = MotionEvent.ACTION_DOWN;
                dxx = v.getX() - event.getRawX();
                dyy = v.getY() - event.getRawY();
                break;

            case MotionEvent.ACTION_MOVE:
                lastAction = MotionEvent.ACTION_DOWN;
                v.setX(event.getRawX() + dxx);
                v.setY(event.getRawY() + dyy);
                break;

            case MotionEvent.ACTION_UP:
                if (lastAction == MotionEvent.ACTION_DOWN) {
                    edt_text.requestFocus();
                    edt_text.setFocusableInTouchMode(true);

                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(edt_text, InputMethodManager.SHOW_FORCED);
//                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
                }

        }

        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getPointerCount() == 2) {
            int action = event.getAction();
            int mainaction = action & MotionEvent.ACTION_MASK;
            if (mainaction == MotionEvent.ACTION_POINTER_DOWN) {
                baseDist = getDistance(event);
                baseRatio = ratio;
            } else {
                float scale = (getDistance(event) - baseDist) / move;
                float factor = (float) Math.pow(2, scale);
                ratio = Math.min(1024.0f, Math.max(0.1f, baseRatio * factor));
                edt_text.setTextSize(ratio + 15);
            }
        }
        return true;
    }

    private int getDistance(MotionEvent event) {

        int dx = (int) (event.getX(0) - event.getX(1));
        int dy = (int) (event.getY(0) - event.getY(1));

        return (int) (Math.sqrt(dx * dx + dy * dy));
    }
}