package com.ap.SociaLite.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.ap.SociaLite.Application.Session;
import com.ap.SociaLite.Presenter.PostBusinessPresenter;
import com.ap.SociaLite.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PostBusiness extends AppCompatActivity {

    @BindView(R.id.btn_share)
    Button btn_share;

    @BindView(R.id.business_create_your_card)
    Button business_create_your_card;

    @BindView(R.id.business_upload_your_card)
    Button business_upload_your_card;

    @BindView(R.id.img_back)
    ImageView img_back;

    @BindView(R.id.imageView8)
    ImageView imageView8;

    @BindView(R.id.constraintLayout67)
    ConstraintLayout constraintLayout67;

    @BindView(R.id.spinner)
    public Spinner spinner;

    boolean click = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_business);
        ButterKnife.bind(this);

        Session session = new Session(PostBusiness.this);

        new PostBusinessPresenter(this,this).fetch_my_intrest(session.getUser_id());
    }

    @OnClick({R.id.img_back, R.id.btn_share, R.id.imageView8, R.id.business_create_your_card, R.id.business_upload_your_card})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                onBackPressed();
                break;

            case R.id.btn_share:
                startActivity(new Intent(PostBusiness.this, HomeActivity.class));
                break;

            case R.id.imageView8:

                if (click) {
                    constraintLayout67.setVisibility(View.VISIBLE);
                    click = false;
                } else {
                    constraintLayout67.setVisibility(View.INVISIBLE);
                    click = true;
                }
                break;

            case R.id.business_create_your_card:
                startActivity(new Intent(PostBusiness.this, AddCardActivity.class));
                break;

            case R.id.business_upload_your_card:
//                startActivity(new Intent(PostBusiness.this,HomeActivity.class));
                break;

        }
    }
}