package com.ap.SociaLite.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.ap.SociaLite.Application.Session;
import com.ap.SociaLite.Presenter.ViewCardActivityPresenter;
import com.ap.SociaLite.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ViewCardActivity extends AppCompatActivity {

    @BindView(R.id.card_name)
   public TextView card_name;

    @BindView(R.id.mobile_view)
    public TextView mobile_view;

    @BindView(R.id.card_email)
    public TextView card_email;

    String user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_card);
        ButterKnife.bind(this);

        user_id=getIntent().getStringExtra("user_id");
        if(user_id != null)
        {
            new ViewCardActivityPresenter(this, this).business_view_card(user_id);
        }
        else{
            Session session = new Session(ViewCardActivity.this);
            user_id = session.getUser_id();
            new ViewCardActivityPresenter(this, this).business_view_card(user_id);
        }
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