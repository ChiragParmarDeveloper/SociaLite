package com.ap.SociaLite.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.ap.SociaLite.Adapter.HiddenPostAdapter;
import com.ap.SociaLite.Adapter.SavedPostAdapter;
import com.ap.SociaLite.R;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SavedPostActivity extends AppCompatActivity {

    @BindView(R.id.img_back)
    ImageView img_back;

    @BindView(R.id.rec_savedpost)
    RecyclerView rec_savedpost;

    private SavedPostAdapter savedPostAdapter;
    private RecyclerView.LayoutManager layoutManager;

    ArrayList images =new ArrayList<>(Arrays.asList(R.drawable.dummyimage, R.drawable.dummyimage, R.drawable.dummyimage));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_post);
        ButterKnife.bind(this);

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        layoutManager = new GridLayoutManager(getApplicationContext(), 3);
        //recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        rec_savedpost.setLayoutManager(layoutManager);


        savedPostAdapter = new SavedPostAdapter(images,getApplicationContext());
        rec_savedpost.setAdapter(savedPostAdapter);

    }
}