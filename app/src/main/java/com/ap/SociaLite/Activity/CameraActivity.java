package com.ap.SociaLite.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.ap.SociaLite.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CameraActivity extends AppCompatActivity {


    @BindView(R.id.layout_album)
    LinearLayout layout_album;

    @BindView(R.id.img_album)
    ImageView img_album;

    @BindView(R.id.layout_lock)
    LinearLayout layout_lock;

    @BindView(R.id.img_lock)
    ImageView img_lock;

    @BindView(R.id.layout_clock)
    LinearLayout layout_clock;

    @BindView(R.id.img_clock)
    ImageView img_clock;


    @BindView(R.id.layout_shape)
    LinearLayout layout_shape;

    @BindView(R.id.img_shape)
    ImageView img_shape;

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

    @BindView(R.id.img_edit1)
    ImageView img_edit1;

    @BindView(R.id.img_edit2)
    ImageView img_edit2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        ButterKnife.bind(this);



//        TextView textView=(TextView) findViewById(R.id.textView);
//        textView.setTextColor(Color.RED);
//        textView.setTextSize(20);
     //   ImageView mimageView=(ImageView) findViewById(R.id.imageView);
        Bitmap mbitmap=((BitmapDrawable) getResources().getDrawable(R.drawable.dummy1)).getBitmap();
        Bitmap imageRounded=Bitmap.createBitmap(mbitmap.getWidth(), mbitmap.getHeight(), mbitmap.getConfig());
        Canvas canvas=new Canvas(imageRounded);
        Paint mpaint=new Paint();
        mpaint.setAntiAlias(true);
        mpaint.setShader(new BitmapShader(mbitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        canvas.drawRoundRect((new RectF(0, 0, mbitmap.getWidth(), mbitmap.getHeight())), 100, 100, mpaint); // Round Image Corner 100 100 100 100
        imageView.setImageBitmap(imageRounded);



    }

    @OnClick({R.id.img_back,R.id.next,R.id.img_edit1,R.id.img_edit2,R.id.layout_album,R.id.layout_lock,R.id.layout_clock,R.id.layout_shape,R.id.layout_drama})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                onBackPressed();
                ///     startActivity(new Intent(CameraActivity.this,LoginActivity.class));
                break;

            case R.id.next:
                startActivity(new Intent(CameraActivity.this,Post.class));
                break;

            case R.id.img_edit1:
                startActivity(new Intent(CameraActivity.this,Text.class));
                break;
            case R.id.img_edit2:
                startActivity(new Intent(CameraActivity.this,Edit.class));
                break;

            case R.id.layout_album:
                startActivity(new Intent(CameraActivity.this,TemplateBackground.class));
                break;

            case R.id.layout_lock:
                startActivity(new Intent(CameraActivity.this,SharePost.class));
                break;

            case R.id.layout_clock:
                startActivity(new Intent(CameraActivity.this,SchedulePost.class));
                break;

            case R.id.layout_shape:
                startActivity(new Intent(CameraActivity.this,PropDrama.class));
                break;

            case R.id.layout_drama:
                startActivity(new Intent(CameraActivity.this,ShapeCut.class));
                break;
        }
    }
}