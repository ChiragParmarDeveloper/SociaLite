package com.ap.SociaLite.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.ap.SociaLite.Adapter.SharetoFrndAdapter;
import com.ap.SociaLite.Adapter.viewallshareAdapter;
import com.ap.SociaLite.Application.RService;
import com.ap.SociaLite.Application.Session;
import com.ap.SociaLite.Application.json;
import com.ap.SociaLite.Pojo.data;
import com.ap.SociaLite.Presenter.ShareToFriendPresenter;
import com.ap.SociaLite.Presenter.ViewAllContectActivityPresenter;
import com.ap.SociaLite.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewAllContectActivity extends AppCompatActivity {


    @BindView(R.id.progressbar)
    public ProgressBar progressbar;

    @BindView(R.id.rv_sharetofrnd)
    public RecyclerView rv_sharetofrnd;

    @BindView(R.id.edt_search)
    public EditText edt_search;
    String UserId;
    public List<data> datas;
    public viewallshareAdapter viewallshareAdapter;
    public static ArrayList<String> sharefrnd_id = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_contect);
        ButterKnife.bind(this);


        Session session = new Session(getApplicationContext());
        UserId = session.getUser_id();
        new ViewAllContectActivityPresenter(this, this).friend_list(UserId);
        filter();
    }

    private void filter() {

        edt_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ViewAllContectActivity.this.viewallshareAdapter.filter(String.valueOf(s));
                viewallshareAdapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }



    @OnClick({R.id.img_back, R.id.btn_share})
    public void OnClick(View view) {
        switch (view.getId()) {

            case R.id.img_back:
                onBackPressed();
                break;

            case R.id.btn_share:
                startActivity(new Intent(ViewAllContectActivity.this, SharePost.class));
                break;

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        new ViewAllContectActivityPresenter(this, this).friend_list(UserId);
        filter();
    }
}