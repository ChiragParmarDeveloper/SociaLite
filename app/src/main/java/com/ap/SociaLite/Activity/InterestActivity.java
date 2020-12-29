package com.ap.SociaLite.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.ap.SociaLite.Adapter.MyInterestAdapter;
import com.ap.SociaLite.Application.Session;
import com.ap.SociaLite.Pojo.interest_list;
import com.ap.SociaLite.Presenter.InterestActivityPresenter;
import com.ap.SociaLite.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InterestActivity extends AppCompatActivity {

    @BindView(R.id.btn_done)
    Button btn_done;

    @BindView(R.id.img_back)
    ImageView img_back;

    @BindView(R.id.rv_interest)
    public RecyclerView rv_interest;
    String user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interest);
        ButterKnife.bind(this);

        Session session = new Session(InterestActivity.this);
        user_id = session.getUser_id();
        new InterestActivityPresenter(this, this).interest();

    }

    @OnClick({R.id.btn_done, R.id.img_back})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.btn_done:


                new InterestActivityPresenter(this, this).add_user_intrest(user_id,MyInterestAdapter.interest_ids);

                break;

            case R.id.img_back:
                onBackPressed();
                break;
        }
    }
}