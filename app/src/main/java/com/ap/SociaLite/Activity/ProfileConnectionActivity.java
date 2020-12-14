package com.ap.SociaLite.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.ap.SociaLite.Fragment.Profile_fragments.BusinessInteractionFragment;
import com.ap.SociaLite.Fragment.Profile_fragments.TimeLineFragment;
import com.ap.SociaLite.Fragment.profile_connection_fragments.ProfileConnectionBusinessFragment;
import com.ap.SociaLite.Fragment.profile_connection_fragments.ProfileConnectionTimelineFragment;
import com.ap.SociaLite.R;

public class ProfileConnectionActivity extends AppCompatActivity {

    ImageView img_back;
    Button timeline_btn,business_btn,spotlight_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_connection);

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_profile, new ProfileConnectionTimelineFragment()).commit();

        img_back = findViewById(R.id.img_back);
        timeline_btn = findViewById(R.id.timeline_btn);
        business_btn = findViewById(R.id.business_btn);
        spotlight_btn = findViewById(R.id.spotlight_btn);

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

                getSupportFragmentManager().beginTransaction().replace(R.id.frame_profile, new ProfileConnectionTimelineFragment()).commit();
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

                getSupportFragmentManager().beginTransaction().replace(R.id.frame_profile, new ProfileConnectionBusinessFragment()).commit();
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

                Intent spotlight = new Intent(ProfileConnectionActivity.this,SpotLightActivity.class);
                startActivity(spotlight);

            }
        });

    }
}