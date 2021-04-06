package com.ap.SociaLite.Activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;

import com.ap.SociaLite.R;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SchedulePost extends AppCompatActivity {

    @BindView(R.id.img_cross)
    ImageView img_cross;

    @BindView(R.id.btn_next)
    Button btn_next;

    @BindView(R.id.btn_save)
    Button btn_save;

    @BindView(R.id.btn_date)
    Button btn_date;

    @BindView(R.id.btn_time)
    Button btn_time;

    @BindView(R.id.imageView)
    ImageView imageView;

    @BindView(R.id.date)
    TextView date;

    @BindView(R.id.time)
    TextView time;

    @BindView(R.id.img_one)
    ImageView img_one;

    @BindView(R.id.img_two)
    ImageView img_two;

    @BindView(R.id.img_three)
    ImageView img_three;

    @BindView(R.id.layout_img1)
    LinearLayout layout_img1;

    @BindView(R.id.layout_img2)
    LinearLayout layout_img2;

    @BindView(R.id.layout_img3)
    LinearLayout layout_img3;

    @BindView(R.id.video_one)
    VideoView video_one;

    @BindView(R.id.video)
    VideoView video;

    @BindView(R.id.video_two)
    VideoView video_two;

    @BindView(R.id.video_three)
    VideoView video_three;

    private DatePickerDialog.OnDateSetListener dateSetListener;

    int hour, minute;
    String imagetwo, imageone, imagethree;

    @Nullable
    @VisibleForTesting
    Uri mSaveImageUri;
    String img_url;
    String my_network, business_interaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_post);
        ButterKnife.bind(this);


        my_network = getIntent().getStringExtra("network_fragment");

        business_interaction = getIntent().getStringExtra("business_fragment");
//        imageone = getIntent().getStringExtra("img1");
//        imagetwo = getIntent().getStringExtra("img2");
//        imagethree = getIntent().getStringExtra("img3");
//
//        if (imageone != null) {
//            layout_img1.setVisibility(View.VISIBLE);
//            File imgFile = new File(imageone);
//
//            if (imgFile.exists()) {
//
//                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
//                imageView.setImageBitmap(myBitmap);
//                img_one.setImageBitmap(myBitmap);
//                imageView.setTag(imgFile.toString());
//
//            }
//        }
//        if (imagetwo != null) {
//            layout_img2.setVisibility(View.VISIBLE);
//            File imgFile = new File(imagetwo);
//
//            if (imgFile.exists()) {
//                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
//                imageView.setImageBitmap(myBitmap);
//                img_two.setImageBitmap(myBitmap);
//            }
//        }
//
//        if (imagethree != null) {
//
//            layout_img3.setVisibility(View.VISIBLE);
//            File imgFile = new File(imagethree);
//
//            if (imgFile.exists()) {
//                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
//                imageView.setImageBitmap(myBitmap);
//                img_three.setImageBitmap(myBitmap);
//            }
//        }

        img_url = getIntent().getStringExtra("img_url");

        if (img_url != null) {
            mSaveImageUri = Uri.fromFile(new File(img_url));
            imageView.setImageURI(mSaveImageUri);
        }

        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                date.setText(day + "/" + month + "/" + year);
            }
        };
    }

    @OnClick({R.id.img_cross, R.id.btn_next, R.id.btn_save, R.id.imageView, R.id.img_one, R.id.img_two, R.id.img_three, R.id.btn_date, R.id.btn_time})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.img_cross:
                onBackPressed();
                break;

            case R.id.btn_next:
                if (my_network != null) {
                    Intent in = new Intent(SchedulePost.this, PostNetwork.class);
                    in.putExtra("img_url", img_url);
                    in.putExtra("date", date.getText().toString());
                    in.putExtra("time", time.getText().toString());
                    startActivity(in);
                } else if (business_interaction != null) {
                    Intent in = new Intent(SchedulePost.this, PostBusiness.class);
                    in.putExtra("img_url", img_url);
                    in.putExtra("date", date.getText().toString());
                    in.putExtra("time", time.getText().toString());
                    startActivity(in);
                } else {
                    startActivity(new Intent(SchedulePost.this, Post.class)
                            .putExtra("img_url", img_url)
                            .putExtra("date", date.getText().toString())
                            .putExtra("time", time.getText().toString()));

                }


                break;

            case R.id.btn_save:
                Intent intent = new Intent();
                intent.putExtra("date", date.getText().toString());
                intent.putExtra("time", time.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
//                mFinalList.clear();
                //  startActivity(new Intent(SchedulePost.this, CameraActivity.class));
                break;

            case R.id.imageView:
                // startActivity(new Intent(SchedulePost.this,CameraActivity.class));
                break;

            case R.id.img_one:
                if (imageone != null) {
                    File imgone = new File(imageone);
                    if (imgone.exists()) {
                        //        imageView.setImageURI(Uri.fromFile(imgone));

                        Bitmap myBitmap = BitmapFactory.decodeFile(imgone.getAbsolutePath());
                        imageView.setImageBitmap(myBitmap);
                        imageView.setTag(imgone.toString());
                    }
                }

                break;

            case R.id.img_two:

                if (imagetwo != null) {
                    File imgtwo = new File(imagetwo);
                    if (imgtwo.exists()) {
                        //   imageView.setImageURI(Uri.fromFile(imgtwo));
                        Bitmap myBitmap = BitmapFactory.decodeFile(imgtwo.getAbsolutePath());
                        imageView.setImageBitmap(myBitmap);

                        imageView.setTag(imgtwo.toString());
                        //     path = imageView.getTag().toString();

                    }
                }
                break;

            case R.id.img_three:

                if (imagethree != null) {
                    File imgthree = new File(imagethree);
                    if (imgthree.exists()) {
                        //       imageView.setImageURI(Uri.fromFile(imgthree));
                        Bitmap myBitmap = BitmapFactory.decodeFile(imgthree.getAbsolutePath());
                        imageView.setImageBitmap(myBitmap);

                        imageView.setTag(imgthree.toString());
                        //    path = imageView.getTag().toString();
                    }
                }
                break;

            case R.id.btn_date:

                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dailog = new DatePickerDialog(
                        SchedulePost.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        dateSetListener,
                        year, month, day);

                dailog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dailog.show();

                break;

            case R.id.btn_time:

                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        SchedulePost.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int hourofday, int min) {
                                hour = hourofday;
                                minute = min;
                                String time_ = hour + ":" + minute;

                                SimpleDateFormat f24hour = new SimpleDateFormat(
                                        "HH:mm"
                                );
                                try {
                                    Date date = f24hour.parse(time_);

                                    SimpleDateFormat f12hour = new SimpleDateFormat(
                                            "hh:mm aa"
                                    );

                                    time.setText(f12hour.format(date));

                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, 12, 0, false
                );

                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                timePickerDialog.updateTime(hour, minute);

                timePickerDialog.show();

                break;
        }
    }
}