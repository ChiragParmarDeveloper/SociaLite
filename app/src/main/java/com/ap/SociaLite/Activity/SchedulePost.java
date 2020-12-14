package com.ap.SociaLite.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.ap.SociaLite.R;

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

    private DatePickerDialog.OnDateSetListener dateSetListener;

    int hour,minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_post);
        ButterKnife.bind(this);

        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                date.setText( day + "/" + month + "/" + year);
            }
        };

    }

    @OnClick({R.id.img_cross,R.id.btn_next,R.id.btn_save,R.id.imageView,R.id.btn_date,R.id.btn_time})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.img_cross:
                finish();
                ///     startActivity(new Intent(CameraActivity.this,LoginActivity.class));
                break;

            case R.id.btn_next:
                startActivity(new Intent(SchedulePost.this,Post.class));
                break;

            case R.id.btn_save:
                startActivity(new Intent(SchedulePost.this,CameraActivity.class));
                break;
            case R.id.imageView:
               // startActivity(new Intent(SchedulePost.this,CameraActivity.class));
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
                        year,month,day);

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

                                }catch (ParseException e){
                                    e.printStackTrace();
                                }
                            }
                        },12,0,false
                );

                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                timePickerDialog.updateTime(hour,minute);

                timePickerDialog.show();

                break;
        }
    }
}