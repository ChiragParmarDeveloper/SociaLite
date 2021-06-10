package com.ap.SociaLite.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.ap.SociaLite.Application.Session;
import com.ap.SociaLite.Presenter.post_link_presenter;
import com.ap.SociaLite.R;
import com.mikhaellopez.circularimageview.CircularImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class post_link extends AppCompatActivity {

    @BindView(R.id.txt_name)
    public TextView txt_name;

    @BindView(R.id.img_popup)
    public ImageView img_popup;

    @BindView(R.id.constraint_popup)
    public ConstraintLayout constraint_popup;

    @BindView(R.id.layout_share)
    public LinearLayout layout_share;

    @BindView(R.id.layout_comment)
    public LinearLayout layout_comment;

    @BindView(R.id.layout_star)
    public LinearLayout layout_star;

    @BindView(R.id.rating_bar)
    public CardView rating_bar;

    @BindView(R.id.img_star)
    public ImageView img_star;

    @BindView(R.id.rating_star1)
    public ImageView rating_star1;

    @BindView(R.id.rating_star2)
    public ImageView rating_star2;

    @BindView(R.id.rating_star3)
    public ImageView rating_star3;

    @BindView(R.id.rating_star4)
    public ImageView rating_star4;

    @BindView(R.id.rating_star5)
    public ImageView rating_star5;

    @BindView(R.id.img_category)
    public ImageView img_category;

    @BindView(R.id.txt_description)
    public TextView txt_description;

    @BindView(R.id.txt_rating)
    public TextView txt_rating;

    @BindView(R.id.txt_allcomment)
    public TextView txt_allcomment;

    @BindView(R.id.txt_name_position_0)
    public TextView txt_name_position_0;

    @BindView(R.id.txt_comment_pos_0)
    public TextView txt_comment_pos_0;


    @BindView(R.id.txt_name_pos_1)
    public TextView txt_name_pos_1;

    @BindView(R.id.txt_comment_pos_1)
    public TextView txt_comment_pos_1;

    @BindView(R.id.txt_time)
    public TextView txt_time;

    @BindView(R.id.layout)
    public RelativeLayout layout;

    @BindView(R.id.layout1)
    public RelativeLayout layout1;

    @BindView(R.id.circularImageView)
    public CircularImageView circularImageView;

    @BindView(R.id.circular)
    public CircularImageView circular;

    @BindView(R.id.circularImageView3)
    public CircularImageView circularImageView3;

    @BindView(R.id.viewer)
    public ImageView viewer;

    @BindView(R.id.text_avatar_title)
    public TextView text_avatar_title;

    @BindView(R.id.viewer_profile)
    public ImageView viewer_profile;

    @BindView(R.id.img_pic)
    public ImageView img_pic;

    public String post_id;
    public Boolean click = true;
    public String rate = "";
    public int position;
    public String user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_link);
        ButterKnife.bind(this);
        Session session = new Session(post_link.this);
        user_id = session.getUser_id();
        handleIntent();

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        handleIntent();
    }

    private void handleIntent() {
        // ATTENTION: This was auto-generated to handle app links.
        Intent appLinkIntent = getIntent();
        String appLinkAction = appLinkIntent.getAction();
        Uri appLinkData = appLinkIntent.getData();

        if (appLinkData != null) {

            post_id = appLinkData.getQueryParameter("post");
            Log.d("post_id", post_id);

            new post_link_presenter(this, this).post(post_id);
        }
    }
}

