package com.ap.SociaLite.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ap.SociaLite.Adapter.HiddenPostAdapter;
import com.ap.SociaLite.Application.Session;
import com.ap.SociaLite.Presenter.HidedPostPresenter;
import com.ap.SociaLite.R;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HidedPost extends AppCompatActivity {

    @BindView(R.id.img_back)
    ImageView img_back;

    @BindView(R.id.rv_hidedpost)
    public RecyclerView rv_hidedpost;

    String user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hided_post);
        ButterKnife.bind(this);

        Session session = new Session(HidedPost.this);
        user_id = session.getUser_id();

        new HidedPostPresenter(this, this).view_hided_post(user_id);
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