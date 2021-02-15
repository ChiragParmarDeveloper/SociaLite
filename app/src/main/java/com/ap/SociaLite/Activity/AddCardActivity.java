package com.ap.SociaLite.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ap.SociaLite.Application.Session;
import com.ap.SociaLite.Presenter.AddCardActivityPresenter;
import com.ap.SociaLite.Presenter.LoginPresenter;
import com.ap.SociaLite.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddCardActivity extends AppCompatActivity {

    @BindView(R.id.img_back)
    ImageView img_back;

    @BindView(R.id.btn_cancel)
    Button btn_cancel;

    @BindView(R.id.btn_save)
    Button btn_save;

    @BindView(R.id.card_name)
    public EditText card_name;

    @BindView(R.id.mobile_view)
    public EditText mobile_view;

    @BindView(R.id.card_email)
    public EditText card_email;

    String user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);
        ButterKnife.bind(this);

        Session session = new Session(AddCardActivity.this);
        user_id = session.getUser_id();

    }

    @OnClick({R.id.img_back, R.id.btn_cancel, R.id.btn_save})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                onBackPressed();
                break;

            case R.id.btn_save:

                if (new AddCardActivityPresenter(this, this).validate(card_name, card_email,mobile_view)) {
                    new AddCardActivityPresenter(this,this).business_create_card(user_id,card_name.getText().toString().trim(),
                            card_email.getText().toString().trim(),mobile_view.getText().toString().trim());
                }

                break;

            case R.id.btn_cancel:
                onBackPressed();
                break;
        }
    }
}