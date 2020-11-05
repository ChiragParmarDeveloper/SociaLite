package com.ap.SociaLite.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ap.SociaLite.Adapter.MyNetworkAdapter;
import com.ap.SociaLite.Adapter.SpotlightAdapter;
import com.ap.SociaLite.R;

import java.util.ArrayList;
import java.util.Arrays;

public class SpotLightActivity extends AppCompatActivity {

    ImageView img_back;
    ImageView add_spotlight;
    LinearLayout linearLayout_user_story;

    RecyclerView friends_spotlight;

    private SpotlightAdapter myspotlightadapter;
    private RecyclerView.LayoutManager layoutManager;

    ArrayList Name = new ArrayList<>(Arrays.asList("Name", "Name", "Name", "Name", "Name"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spot_light);

        img_back = findViewById(R.id.img_back);
        add_spotlight = findViewById(R.id.add_spotlight);
        linearLayout_user_story = findViewById(R.id.linearLayout_user_story);

        friends_spotlight = findViewById(R.id.friends_spotlight);
        layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        //recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        friends_spotlight.setLayoutManager(layoutManager);
        myspotlightadapter = new SpotlightAdapter(Name,getApplicationContext());
        friends_spotlight.setAdapter(myspotlightadapter);

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        linearLayout_user_story.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SpotLightActivity.this,UserSpotlightViewActivity.class));
            }
        });

        add_spotlight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent add = new Intent(SpotLightActivity.this,AddSpotlightActivity.class);
                startActivity(add);
            }
        });
    }
}