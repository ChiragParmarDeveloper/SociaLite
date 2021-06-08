package com.ap.SociaLite.Activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.ap.SociaLite.Application.RService;
import com.ap.SociaLite.Application.Session;
import com.ap.SociaLite.Application.json;
import com.ap.SociaLite.Presenter.business_post_link_Presenter;
import com.ap.SociaLite.R;
import com.mikhaellopez.circularimageview.CircularImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class business_post_link extends AppCompatActivity {

    @BindView(R.id.txt_name)
    public TextView txt_name;

    @BindView(R.id.textView11)
    public TextView textView11;

    @BindView(R.id.textView12)
    public TextView textView12;

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

    @BindView(R.id.intrested)
    public Button intrested;

    @BindView(R.id.card)
    public Button card;

    @BindView(R.id.message)
    public Button message;

    @BindView(R.id.layout)
    public RelativeLayout layout;

    @BindView(R.id.layout1)
    public RelativeLayout layout1;

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

    @BindView(R.id.circularImageView)
    public CircularImageView circularImageView;

    @BindView(R.id.circularImageView5)
    public CircularImageView circularImageView5;

    @BindView(R.id.circularImageView3)
    public CircularImageView circularImageView3;

    @BindView(R.id.post_image)
    public ImageView post_image;

    @BindView(R.id.description_post)
    public TextView description_post;

    @BindView(R.id.address_post)
    public TextView address_post;

    @BindView(R.id.txt_rating)
    public TextView txt_rating;

    @BindView(R.id.txt_time)
    public TextView txt_time;

    @BindView(R.id.textView15)
    public TextView textView15;

    @BindView(R.id.textView14)
    public TextView textView14;

    @BindView(R.id.textView13)
    public TextView textView13;

    @BindView(R.id.viewer)
    public ImageView viewer;

    @BindView(R.id.text_avatar_title)
    public TextView text_avatar_title;

    @BindView(R.id.viewer_profile)
    public ImageView viewer_profile;

    @BindView(R.id.img_pic)
    public ImageView img_pic;

    public String post_id, user_id;
    public Boolean click = true;
    public  String rate = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_post_link);
        ButterKnife.bind(this);
        Session session = new Session(business_post_link.this);
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

            new business_post_link_Presenter(this, this).business_post_link(user_id, post_id);
        }
    }




}







