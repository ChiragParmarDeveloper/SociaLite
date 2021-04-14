package com.ap.SociaLite.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ap.SociaLite.Application.Session;
import com.ap.SociaLite.Presenter.EditProfilePresenter;
import com.ap.SociaLite.Presenter.contact_usPresenter;
import com.ap.SociaLite.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class contact_us extends AppCompatActivity {

    @BindView(R.id.progressbar)
    public ProgressBar progressbar;

    @BindView(R.id.txt_name)
    public TextView txt_name;

    @BindView(R.id.txt_mail)
    public TextView txt_mail;

    @BindView(R.id.edt_msgs)
    public EditText edt_msgs;

    String user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        ButterKnife.bind(this);

        Session session = new Session(this);
        user_id=session.getUser_id();
        new contact_usPresenter(this,this).fetch_profile(user_id);
    }
    @OnClick({R.id.img_back,R.id.btn_submit})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                onBackPressed();
                break;

            case R.id.btn_submit:
               if( new contact_usPresenter(this,this).validate(txt_name,txt_mail,edt_msgs))
               {
                  new contact_usPresenter(this,this).contact_us_api(txt_name.getText().toString().trim(),
                          txt_mail.getText().toString().trim(),edt_msgs.getText().toString().trim());
               }
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        new contact_usPresenter(this,this).fetch_profile(user_id);
    }
}