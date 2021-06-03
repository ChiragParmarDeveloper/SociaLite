package com.ap.SociaLite.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.ap.SociaLite.Presenter.FaqPresenter;
import com.ap.SociaLite.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Faq extends AppCompatActivity {

    @BindView(R.id.rv_faq)
    public RecyclerView rv_faq;

    public RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);
        ButterKnife.bind(this);
        new FaqPresenter(this, this).faq_listdata();

    }

    @OnClick({R.id.img_back})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                onBackPressed();
                break;
        }
    }

//    public void updateData(ArrayList<cart_details> models) {
//        mList.clear();
//        mList.addAll(models);
//        notifyDataSetChanged();
//    }

}