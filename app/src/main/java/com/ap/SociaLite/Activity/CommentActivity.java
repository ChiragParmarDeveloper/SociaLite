package com.ap.SociaLite.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.ap.SociaLite.Application.Session;
import com.ap.SociaLite.Presenter.CommentPresenter;
import com.ap.SociaLite.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CommentActivity extends AppCompatActivity {

    @BindView(R.id.img_back)
    ImageView img_back;

    @BindView(R.id.img_send)
    ImageView img_send;

    @BindView(R.id.edt_comment)
    public EditText edt_comment;

    @BindView(R.id.rv_view_comment)
    public RecyclerView rv_view_comment;

    String post_id, user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        ButterKnife.bind(this);

        post_id = getIntent().getStringExtra("post_id");
        Session session = new Session(CommentActivity.this);
        user_id = session.getUser_id();

        new CommentPresenter(this, this).view_comment(post_id);
    }

    @OnClick({R.id.img_back, R.id.img_send})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                onBackPressed();
                break;
            case R.id.img_send:
                new CommentPresenter(this, this).add_comment(user_id, post_id, edt_comment.getText().toString().trim());
                break;
        }
    }
}