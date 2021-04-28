package com.ap.SociaLite.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.ap.SociaLite.Application.Session;
import com.ap.SociaLite.Presenter.PrivacyPresenter;
import com.ap.SociaLite.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Privacy extends AppCompatActivity {

    @BindView(R.id.hidedpost)
    ConstraintLayout hidedpost;

    @BindView(R.id.toggle_account)
    public ToggleButton toggle_account;

    String user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy);
        ButterKnife.bind(this);

        Session session = new Session(Privacy.this);
        user_id = session.getUser_id();

        new PrivacyPresenter(this, this).fetch_profile(user_id);

    }

    @OnClick({R.id.img_back, R.id.hidedpost, R.id.toggle_account})
    public void OnClick(View view) {
        switch (view.getId()) {

            case R.id.img_back:
                onBackPressed();
                break;

            case R.id.hidedpost:
                startActivity(new Intent(Privacy.this, HidedPost.class));
                break;

            case R.id.toggle_account:
                if (toggle_account.isChecked()) {
                    new PrivacyPresenter(this, this).private_account(user_id, "1");
                } else {
                    new PrivacyPresenter(this, this).private_account(user_id, "0");
                }

                break;
        }
    }
}