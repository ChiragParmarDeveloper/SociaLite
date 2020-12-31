package com.ap.SociaLite.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.ap.SociaLite.Application.RService;
import com.ap.SociaLite.Application.Session;
import com.ap.SociaLite.Application.json;
import com.ap.SociaLite.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Post extends AppCompatActivity {

//    @BindView(R.id.btn_share)
//    Button btn_share;
//
//    @BindView(R.id.img_back)
//    ImageView img_back;

    @BindView(R.id.spinner)
    Spinner spinner;

    @BindView(R.id.constraintLayout37)
    ConstraintLayout constraintLayout37;

    String user_id,my_network,business_interaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        ButterKnife.bind(this);

        Session session = new Session(Post.this);
        user_id = session.getUser_id();
        //     new PostPresenter(this,this).fetch_all_intrest(user_id);

        my_network = getIntent().getStringExtra("network_fragment");
        business_interaction= getIntent().getStringExtra("business_fragment");
        Toast.makeText(getApplicationContext(), business_interaction, Toast.LENGTH_LONG).show();


        if(my_network !=null)
        {
            constraintLayout37.setVisibility(View.GONE);
        }

    }

    @OnClick({R.id.img_back, R.id.btn_share})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                onBackPressed();
                break;

            case R.id.btn_share:
                startActivity(new Intent(Post.this, HomeActivity.class));
                break;
        }
    }


}