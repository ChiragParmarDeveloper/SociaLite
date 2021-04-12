package com.ap.SociaLite.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.ap.SociaLite.Application.RService;
import com.ap.SociaLite.Application.json;
import com.ap.SociaLite.Fragment.profile_connection_fragments.ProfileConnectionBusinessFragment;
import com.ap.SociaLite.Fragment.profile_connection_fragments.ProfileConnectionTimelineFragment;
import com.ap.SociaLite.R;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileConnectionActivity extends AppCompatActivity {

    @BindView(R.id.progressbar)
    public ProgressBar progressbar;

    @BindView(R.id.profile_pic)
    public CircularImageView profile_pic;

    @BindView(R.id.cover_image)
    public ImageView cover_image;

    @BindView(R.id.user_name)
    public TextView user_name;

    @BindView(R.id.profile_bio)
    public TextView profile_bio;

    @BindView(R.id.msg)
    public ConstraintLayout msg;

    @BindView(R.id.share)
    public ConstraintLayout share;

    Button timeline_btn, business_btn, spotlight_btn;
    String user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_connection);
        ButterKnife.bind(this);

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_profile, new ProfileConnectionTimelineFragment()).commit();

        timeline_btn = findViewById(R.id.timeline_btn);
        business_btn = findViewById(R.id.business_btn);
        spotlight_btn = findViewById(R.id.spotlight_btn);

        user_id = getIntent().getStringExtra("request_id");

        timeline_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timeline_btn.setBackgroundColor(Color.parseColor("#EE6851"));
                timeline_btn.setTextColor(Color.WHITE);
                business_btn.setBackground(getResources().getDrawable(R.drawable.border_square_rs));
                business_btn.setTextColor(Color.BLACK);
                spotlight_btn.setBackground(getResources().getDrawable(R.drawable.border_square_rs));
                spotlight_btn.setTextColor(Color.BLACK);

                getSupportFragmentManager().beginTransaction().replace(R.id.frame_profile, new ProfileConnectionTimelineFragment()).commit();
            }
        });

        business_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                business_btn.setBackgroundColor(Color.parseColor("#EE6851"));
                business_btn.setTextColor(Color.WHITE);
                timeline_btn.setBackground(getResources().getDrawable(R.drawable.border_square_rs));
                timeline_btn.setTextColor(Color.BLACK);
                spotlight_btn.setBackground(getResources().getDrawable(R.drawable.border_square_rs));
                spotlight_btn.setTextColor(Color.BLACK);

                getSupportFragmentManager().beginTransaction().replace(R.id.frame_profile, new ProfileConnectionBusinessFragment()).commit();
            }
        });

        spotlight_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                spotlight_btn.setBackgroundColor(Color.parseColor("#EE6851"));
                spotlight_btn.setTextColor(Color.WHITE);
                timeline_btn.setBackground(getResources().getDrawable(R.drawable.border_square_rs));
                timeline_btn.setTextColor(Color.BLACK);
                business_btn.setBackground(getResources().getDrawable(R.drawable.border_square_rs));
                business_btn.setTextColor(Color.BLACK);

                Intent spotlight = new Intent(ProfileConnectionActivity.this, SpotLightActivity.class);
                startActivity(spotlight);

            }
        });

        my_profile(user_id);
    }

    private void my_profile(String user_id) {
        progressbar.setVisibility(View.VISIBLE);
        try {
            new RService.api().call(this).my_profileActivity(user_id).enqueue(new Callback<json>() {
                @Override
                public void onResponse(Call<json> call, Response<json> response) {
                    progressbar.setVisibility(View.GONE);
                    if (response.body().status.equals("1")) {
                        if (response.body().my_profile_user_details != null && response.body().my_profile_user_details.size() > 0) {

                            if (response.body().my_profile_user_details.get(0).profile_pic.equals("http://the-socialite.com/admin/")) {
                                Drawable upload_img = getDrawable(R.drawable.ic_user_icon);
                                profile_pic.setImageDrawable(upload_img);
                            } else {
                                Picasso.get().load(response.body().my_profile_user_details.get(0).profile_pic).into(profile_pic);
                            }

                            if (response.body().my_profile_user_details.get(0).cover_photo.equals("http://the-socialite.com/admin/")) {

                            } else {
                                Picasso.get().load(response.body().my_profile_user_details.get(0).cover_photo).into(cover_image);
                            }

                            user_name.setText(response.body().my_profile_user_details.get(0).username);
                            profile_bio.setText(response.body().my_profile_user_details.get(0).bio);
                            //profileActivity.textView10.setText(response.body().my_profile_user_details.get(0).connection);


                        }
                    } else {
                        //      Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<json> call, Throwable t) {
                    progressbar.setVisibility(View.GONE);
                    //     Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
                    //    Log.d("error", String.valueOf(t.getMessage()));
                }
            });
        } catch (Exception e) {

        }

    }

    @OnClick({R.id.img_back,R.id.share,R.id.msg})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                onBackPressed();
                break;

            case R.id.share:
                Intent in = new Intent(view.getContext(), ShareToFriend.class);
                view.getContext().startActivity(in);
                break;

            case R.id.msg:
                Toast.makeText(this, "Coming soon...", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}