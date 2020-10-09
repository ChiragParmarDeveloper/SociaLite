package com.ap.SociaLite.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ap.SociaLite.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Faq extends AppCompatActivity {

//    @BindView(R.id.img_back)
//    ImageView img_back;

    boolean openlyout =true;

    @BindView(R.id.li_faq_title)
    LinearLayout li_faq_title;

    @BindView(R.id.img_next)
    ImageView img_next;

    @BindView(R.id.img_down)
    ImageView img_down;

    @BindView(R.id.cv_faq_description)
    CardView cv_faq_description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);
        ButterKnife.bind(this);

        cv_faq_description.setVisibility(View.GONE);

        img_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img_next.setVisibility(View.GONE);
                img_down.setVisibility(View.VISIBLE);
                cv_faq_description.setVisibility(View.VISIBLE);
            }
        });

        img_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img_next.setVisibility(View.VISIBLE);
                img_down.setVisibility(View.GONE);
                cv_faq_description.setVisibility(View.GONE);
            }
        });
    }
    @OnClick({R.id.li_faq_title})
    public void OnClick(View view) {
        switch (view.getId()) {
//            case R.id.img_back:
//                onBackPressed();
//                break;
            case R.id.li_faq_title:

                if(openlyout){
                    img_next.setVisibility(View.VISIBLE);
                    img_down.setVisibility(View.GONE);
                    cv_faq_description.setVisibility(View.GONE);
                    openlyout = false;
                }
                else {
                    img_next.setVisibility(View.GONE);
                    img_down.setVisibility(View.VISIBLE);
                    cv_faq_description.setVisibility(View.VISIBLE);
                    openlyout = true;
                }
                break;
        }
    }
}