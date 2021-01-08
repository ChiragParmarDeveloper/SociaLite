package com.ap.SociaLite.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ap.SociaLite.Adapter.SpotlightAdapter;
import com.ap.SociaLite.Adapter.SpotlightViewerAdapter;
import com.ap.SociaLite.R;

import java.util.ArrayList;
import java.util.Arrays;

public class UserSpotlightViewActivity extends AppCompatActivity {


    ImageView img_back;
    TextView txt_viewers;
    NestedScrollView views_nested_scr;
    ImageView views_back;

    RecyclerView views_recycler;

    private SpotlightViewerAdapter myspotlightadapter;
    private RecyclerView.LayoutManager layoutManager;

    ArrayList Name = new ArrayList<>(Arrays.asList("User 1", "User 2", "User 3", "User 4", "User 5"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_spotlight_view);

        img_back = findViewById(R.id.img_back);
        txt_viewers = findViewById(R.id.txt_viewers);
        views_nested_scr = findViewById(R.id.views_nested_scr);
        views_back = findViewById(R.id.img_back_views);
        views_recycler = findViewById(R.id.views_recycler);

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        txt_viewers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                views_nested_scr.setVisibility(View.VISIBLE);
            }
        });

        views_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                views_nested_scr.setVisibility(View.GONE);
            }
        });

//        layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
//        //recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
//        views_recycler.setLayoutManager(layoutManager);
//        myspotlightadapter = new SpotlightViewerAdapter(Name,getApplicationContext());
//        views_recycler.setAdapter(myspotlightadapter);


    }
}