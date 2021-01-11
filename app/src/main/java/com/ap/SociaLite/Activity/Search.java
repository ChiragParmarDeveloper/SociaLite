package com.ap.SociaLite.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.ap.SociaLite.Adapter.SearchProfileAdapter;
import com.ap.SociaLite.Presenter.SearchPresenter;
import com.ap.SociaLite.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Search extends AppCompatActivity {

    @BindView(R.id.rv_search_profile)
    public RecyclerView rv_search_profile;

    @BindView(R.id.search_profile_user_name)
    public TextView search_profile_user_name;

    @BindView(R.id.search_profile_image)
    public ImageView search_profile_image;

    @BindView(R.id.search_connect)
    public ConstraintLayout search_connect;

    @BindView(R.id.search_msg)
    public ConstraintLayout search_msg;

    @BindView(R.id.search_share)
    public ConstraintLayout search_share;

    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        new SearchPresenter(this, this).all_user();
    }

    @OnClick({R.id.img_back, R.id.search_connect,R.id.search_msg,R.id.search_share})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                onBackPressed();
                break;

            case R.id.search_connect:
                id = SearchProfileAdapter.User_id;
                Toast.makeText(getApplicationContext(),id,Toast.LENGTH_LONG).show();
                break;

            case R.id.search_msg:
                id = SearchProfileAdapter.User_id;
                Toast.makeText(getApplicationContext(),id,Toast.LENGTH_LONG).show();
                break;

            case R.id.search_share:
                id = SearchProfileAdapter.User_id;
                Toast.makeText(getApplicationContext(),id,Toast.LENGTH_LONG).show();
                break;
        }
    }
}