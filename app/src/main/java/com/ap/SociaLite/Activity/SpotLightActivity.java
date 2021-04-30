package com.ap.SociaLite.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ap.SociaLite.Adapter.SpotlightAdapter;
import com.ap.SociaLite.Application.Session;
import com.ap.SociaLite.Editors.EditImageActivity;
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

    @BindView(R.id.friends_spotlight)
    public RecyclerView friends_spotlight;

    @BindView(R.id.user_frndstory)
    public ImageView user_frndstory;

    @BindView(R.id.viewer_profile)
    public ImageView viewer_profile;


    public String user_id,UserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spot_light);
        ButterKnife.bind(this);

        Session session = new Session(SpotLightActivity.this);
        user_id = session.getUser_id();
        UserId= session.getUser_id();

        new SpotLightActivityPresenter(this, this).fetch_profile(user_id);
        new SpotLightActivityPresenter(this,this).frnd_story(UserId);
    }

    @OnClick({R.id.img_back, R.id.linearLayout_user_story})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                onBackPressed();
                break;

            case R.id.linearLayout_user_story:
                Intent in = new Intent(SpotLightActivity.this, EditImageActivity.class);
                in.putExtra("my_network_user_story", "my_network_user_story");
                startActivity(in);
              //  startActivity(new Intent(SpotLightActivity.this, AddSpotlightActivity.class));
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        new SpotLightActivityPresenter(this, this).fetch_profile(user_id);
        new SpotLightActivityPresenter(this,this).frnd_story(UserId);
    }
}