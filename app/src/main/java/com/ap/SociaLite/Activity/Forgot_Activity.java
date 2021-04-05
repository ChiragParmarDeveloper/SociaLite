package com.ap.SociaLite.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.ap.SociaLite.R;
import com.hbb20.CountryCodePicker;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Forgot_Activity extends AppCompatActivity {

    @BindView(R.id.mobile)
    EditText mobile;

    @BindView(R.id.code_picker)
    CountryCodePicker code_picker;

    String mobile_number;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.img_back, R.id.btn_submit})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                onBackPressed();
                break;

            case R.id.btn_submit:
                Intent in = new Intent(Forgot_Activity.this, OtpActivity.class);
                in.putExtra("country_code", code_picker.getTextView_selectedCountry().getText().toString().trim());
                in.putExtra("phone_no", mobile.getText().toString().trim());
                startActivity(in);
                break;
        }
    }
}