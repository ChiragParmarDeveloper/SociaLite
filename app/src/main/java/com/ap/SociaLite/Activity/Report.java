package com.ap.SociaLite.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.ap.SociaLite.Application.Session;
import com.ap.SociaLite.Presenter.Report_Presenter;
import com.ap.SociaLite.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Report extends AppCompatActivity {

    @BindView(R.id.rv_report_list)
    public RecyclerView rv_report_list;

    public String post_id ,user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        ButterKnife.bind(this);

        Session session = new Session(Report.this);
        user_id = session.getUser_id();
        post_id = getIntent().getStringExtra("post_id");

        new Report_Presenter(this, this).report_list();
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