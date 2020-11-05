package com.ap.SociaLite.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ap.SociaLite.R;

public class AddSpotlightActivity_2 extends AppCompatActivity {

    ImageView img_cross;
    LinearLayout layout_album,layout_lock,layout_clock,layout_shape,layout_drama;
    ImageView send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_spotlight_2);

        img_cross = findViewById(R.id.img_cross);
        send = findViewById(R.id.send);

        layout_album = findViewById(R.id.layout_album);
        layout_lock = findViewById(R.id.layout_lock);
        layout_clock = findViewById(R.id.layout_clock);
        layout_shape = findViewById(R.id.layout_shape);
        layout_drama = findViewById(R.id.layout_drama);



        layout_album.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddSpotlightActivity_2.this,TemplateBackground.class));
            }
        });

        layout_lock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddSpotlightActivity_2.this,SharePost.class));
            }
        });

        layout_clock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddSpotlightActivity_2.this,SchedulePost.class));
            }
        });

        layout_shape.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddSpotlightActivity_2.this,PropDrama.class));
            }
        });

        layout_drama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddSpotlightActivity_2.this,ShapeCut.class));
            }
        });


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddSpotlightActivity_2.this,SpotLightActivity.class));
                finish();
            }
        });


        img_cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });



    }
}