package com.ap.SociaLite.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.ap.SociaLite.Fragment.BusinessFragment;
import com.ap.SociaLite.Fragment.Profile_fragments.BusinessInteractionFragment;
import com.ap.SociaLite.Fragment.Profile_fragments.TimeLineFragment;
import com.ap.SociaLite.R;

public class ProfileActivity extends AppCompatActivity {

    ImageView img_back;
    Button timeline_btn,business_btn,spotlight_btn;
    FrameLayout frame_profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_profile, new TimeLineFragment()).commit();

        img_back = findViewById(R.id.img_back);
        timeline_btn = findViewById(R.id.timeline_btn);
        business_btn = findViewById(R.id.business_btn);
        spotlight_btn = findViewById(R.id.spotlight_btn);
        frame_profile = findViewById(R.id.frame_profile);



        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        timeline_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timeline_btn.setBackgroundColor(Color.parseColor("#EE6851"));
                timeline_btn.setTextColor(Color.WHITE);
                business_btn.setBackground(getResources().getDrawable(R.drawable.border_square_rs));
                business_btn.setTextColor(Color.BLACK);
                spotlight_btn.setBackground(getResources().getDrawable(R.drawable.border_square_rs));
                spotlight_btn.setTextColor(Color.BLACK);

                getSupportFragmentManager().beginTransaction().replace(R.id.frame_profile, new TimeLineFragment()).commit();
            }
        });

        business_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                business_btn.setBackgroundColor(Color.parseColor("#EE6851"));
                business_btn.setTextColor(Color.WHITE);
                timeline_btn.setBackground(getResources().getDrawable(R.drawable.border_square_rs));
                timeline_btn.setTextColor(Color.BLACK);
                spotlight_btn.setBackground(getResources().getDrawable(R.drawable.border_square_rs));
                spotlight_btn.setTextColor(Color.BLACK);

                getSupportFragmentManager().beginTransaction().replace(R.id.frame_profile, new BusinessInteractionFragment()).commit();
            }
        });

        spotlight_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                spotlight_btn.setBackgroundColor(Color.parseColor("#EE6851"));
                spotlight_btn.setTextColor(Color.WHITE);
                timeline_btn.setBackground(getResources().getDrawable(R.drawable.border_square_rs));
                timeline_btn.setTextColor(Color.BLACK);
                business_btn.setBackground(getResources().getDrawable(R.drawable.border_square_rs));
                business_btn.setTextColor(Color.BLACK);

                Intent spotlight = new Intent(ProfileActivity.this,SpotLightActivity.class);
                startActivity(spotlight);
            }
        });
    }
}