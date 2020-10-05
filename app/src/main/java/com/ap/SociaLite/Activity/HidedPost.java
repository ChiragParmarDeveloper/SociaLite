package com.ap.SociaLite.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.ap.SociaLite.Adapter.CategoryPostAdapter;
import com.ap.SociaLite.Adapter.HiddenPostAdapter;
import com.ap.SociaLite.R;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HidedPost extends AppCompatActivity {

    @BindView(R.id.img_back)
    ImageView img_back;

    @BindView(R.id.rec_hidedpost)
    RecyclerView rec_hidedpost;

    private HiddenPostAdapter hiddenPostAdapter;
    private RecyclerView.LayoutManager layoutManager;

    ArrayList images =new ArrayList<>(Arrays.asList(R.drawable.dummyimage, R.drawable.dummyimage, R.drawable.dummyimage));


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hided_post);
        ButterKnife.bind(this);


        layoutManager = new GridLayoutManager(getApplicationContext(), 3);
        //recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        rec_hidedpost.setLayoutManager(layoutManager);


        hiddenPostAdapter = new HiddenPostAdapter(getApplicationContext(),images);
        rec_hidedpost.setAdapter(hiddenPostAdapter);

    }
    @OnClick({R.id.img_back})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                onBackPressed();
                break;
        }
    }
}