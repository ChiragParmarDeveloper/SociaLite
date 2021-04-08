package com.ap.SociaLite.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;

import com.ap.SociaLite.Application.Session;
import com.ap.SociaLite.Editors.EditImageActivity;
import com.ap.SociaLite.Presenter.SpotlightActivityForUserPresenter;
import com.ap.SociaLite.R;
import com.mikhaellopez.circularimageview.CircularImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SpotlightActivityForUser extends AppCompatActivity {

    @BindView(R.id.views_nested_scr)
    NestedScrollView views_nested_scr;

    @BindView(R.id.rv_mystory)
    public RecyclerView rv_mystory;

    @BindView(R.id.views_recycler)
    public RecyclerView views_recycler;

    @BindView(R.id.user_profile)
    public CircularImageView user_profile;

    @BindView(R.id.user_name)
    public TextView user_name;

    @BindView(R.id.img_status)
    public ImageView img_status;

    @BindView(R.id.view_count)
    public TextView view_count;

    public String user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spotlight_for_user);
        ButterKnife.bind(this);

        Session session = new Session(SpotlightActivityForUser.this);
        user_id = session.getUser_id();

        new SpotlightActivityForUserPresenter(this, this).fetch_profile(user_id);
        new SpotlightActivityForUserPresenter(this, this).my_all_storys(user_id);

    }

    @OnClick({R.id.img_back, R.id.linearLayout_user_story, R.id.views_cardview, R.id.img_back_views})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                onBackPressed();
                break;

            case R.id.linearLayout_user_story:
             //   startActivity(new Intent(SpotlightActivityForUser.this, AddSpotlightActivity.class));
                Intent in = new Intent(SpotlightActivityForUser.this, EditImageActivity.class);
                in.putExtra("user_story", "user_story");
                startActivity(in);
                break;

            case R.id.views_cardview:
                views_nested_scr.setVisibility(View.VISIBLE);
                break;

            case R.id.img_back_views:
                views_nested_scr.setVisibility(View.GONE);
                break;


        }
    }
}