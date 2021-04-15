package com.ap.SociaLite.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ap.SociaLite.Adapter.SpotlightAdapter;
import com.ap.SociaLite.Application.Session;
import com.ap.SociaLite.Presenter.SpotLightActivityPresenter;
import com.ap.SociaLite.R;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SpotLightActivity extends AppCompatActivity {

    @BindView(R.id.user_profile)
    public CircularImageView user_profile;

    @BindView(R.id.user_name)
    public TextView user_name;

    public RecyclerView friends_spotlight;

    private SpotlightAdapter myspotlightadapter;
    private RecyclerView.LayoutManager layoutManager;

    ArrayList Name = new ArrayList<>(Arrays.asList("Name", "Name"));
    String user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spot_light);
        ButterKnife.bind(this);

        Session session = new Session(SpotLightActivity.this);
        user_id = session.getUser_id();

        friends_spotlight = findViewById(R.id.friends_spotlight);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        //recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        friends_spotlight.setLayoutManager(layoutManager);
        myspotlightadapter = new SpotlightAdapter(Name, getApplicationContext());
        friends_spotlight.setAdapter(myspotlightadapter);


        new SpotLightActivityPresenter(this, this).fetch_profile(user_id);

    }

    @OnClick({R.id.img_back, R.id.linearLayout_user_story})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                onBackPressed();
                break;

            case R.id.linearLayout_user_story:
                startActivity(new Intent(SpotLightActivity.this, AddSpotlightActivity.class));
                break;
        }
    }

}