package com.ap.SociaLite.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ap.SociaLite.Adapter.SearchProfileAdapter;
import com.ap.SociaLite.Adapter.SpotlightViewerAdapter;
import com.ap.SociaLite.R;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Search extends AppCompatActivity {

    @BindView(R.id.img_back)
    ImageView img_back;

    @BindView(R.id.search_profile_recyclerview)
    RecyclerView search_profile_recyclerview;

    private SearchProfileAdapter searchProfileAdapter;
    private RecyclerView.LayoutManager layoutManager;

    ArrayList Name = new ArrayList<>(Arrays.asList("User 1", "User 2", "User 3", "User 4", "User 5"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        //recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        search_profile_recyclerview.setLayoutManager(layoutManager);
        searchProfileAdapter = new SearchProfileAdapter(Name,getApplicationContext());
        search_profile_recyclerview.setAdapter(searchProfileAdapter);
        
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