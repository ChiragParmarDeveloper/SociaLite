package com.ap.SociaLite.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.ap.SociaLite.Adapter.HiddenPostAdapter;
import com.ap.SociaLite.Adapter.SchedulePostAdapter.SchedulePostSettingAdapter;
import com.ap.SociaLite.R;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SchedulePostSettingActivity extends AppCompatActivity {

    @BindView(R.id.img_back)
    ImageView img_back;

    @BindView(R.id.rec_schedulepost)
    RecyclerView rec_schedulepost;

    private SchedulePostSettingAdapter schedulePostSettingAdapter;
    private RecyclerView.LayoutManager layoutManager;

    ArrayList images =new ArrayList<>(Arrays.asList(R.drawable.dummyimage, R.drawable.dummyimage, R.drawable.dummyimage));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_post_setting);
        ButterKnife.bind(this);

        layoutManager = new GridLayoutManager(SchedulePostSettingActivity.this, 1);
        //recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        rec_schedulepost.setLayoutManager(layoutManager);


        schedulePostSettingAdapter = new SchedulePostSettingAdapter(images,SchedulePostSettingActivity.this);
        rec_schedulepost.setAdapter(schedulePostSettingAdapter);

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }
}