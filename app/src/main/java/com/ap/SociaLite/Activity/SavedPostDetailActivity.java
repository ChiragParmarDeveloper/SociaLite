package com.ap.SociaLite.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.ap.SociaLite.Adapter.HiddedPostDetailAdapter;
import com.ap.SociaLite.Adapter.SavedPostDetailAdapter;
import com.ap.SociaLite.R;

import java.util.ArrayList;
import java.util.Arrays;

public class SavedPostDetailActivity extends AppCompatActivity {

    private RecyclerView rec_savedpost_detail;

    ImageView img_back;

    private SavedPostDetailAdapter savedPostDetailAdapter;
    private RecyclerView.LayoutManager layoutManager;

    ArrayList Name = new ArrayList<>(Arrays.asList("Name 1", "Name 2", "Name 3", "Name 4", "Name 5"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_post_detail);

        img_back = findViewById(R.id.img_back);

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        rec_savedpost_detail = findViewById(R.id.rec_savedpost_detail);
        layoutManager = new GridLayoutManager(SavedPostDetailActivity.this, 1);
        //recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        rec_savedpost_detail.setLayoutManager(layoutManager);
        savedPostDetailAdapter = new SavedPostDetailAdapter(Name,SavedPostDetailActivity.this);
        rec_savedpost_detail.setAdapter(savedPostDetailAdapter);


    }
}