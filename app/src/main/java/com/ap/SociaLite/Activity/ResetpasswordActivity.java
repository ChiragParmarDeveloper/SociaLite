package com.ap.SociaLite.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ap.SociaLite.Application.RService;
import com.ap.SociaLite.Application.json;
import com.ap.SociaLite.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResetpasswordActivity extends AppCompatActivity {

    @BindView(R.id.img_back)
    ImageView img_back;

    @BindView(R.id.edt_repassword)
    EditText edt_repassword;

    @BindView(R.id.edt_password)
    EditText edt_password;

    @BindView(R.id.progressbar)
    public ProgressBar progressbar;

    String mobile_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resetpassword);
        ButterKnife.bind(this);

        mobile_number = getIntent().getStringExtra("phone_no");
    }

    @OnClick({R.id.img_back, R.id.btn_submit})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                startActivity(new Intent(ResetpasswordActivity.this, Forgot_Activity.class));
                break;

            case R.id.btn_submit:
                if (validate()) {
                    final String strPwd = edt_password.getText().toString();
                    final String strConfPwd = edt_repassword.getText().toString();

                    if (strPwd.equals(strConfPwd)) {
                        forgot_password(mobile_number, strConfPwd);
                    } else {
                        Toast.makeText(ResetpasswordActivity.this, "password not match", Toast.LENGTH_SHORT).show();
                    }
                } else {

                }
                break;
        }
    }

    private void forgot_password(String mobile_number, String password) {
        progressbar.setVisibility(View.VISIBLE);
        new RService.api().call(ResetpasswordActivity.this).forgot_pwd(mobile_number, password).enqueue(new Callback<json>() {
            @Override
            public void onResponse(Call<json> call, Response<json> response) {
                progressbar.setVisibility(View.GONE);
                if (response.body().status.equals("1")) {
                    Toast.makeText(ResetpasswordActivity.this, response.body().message, Toast.LENGTH_LONG).show();
                    startActivity(new Intent(ResetpasswordActivity.this, LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                    finish();

                } else {
                    //    Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<json> call, Throwable t) {
                progressbar.setVisibility(View.GONE);
                //     Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
//                Log.d("error", String.valueOf(t.getMessage()));
            }
        });

    }


    public boolean validate() {
        if (edt_password.getText().toString().isEmpty()) {
            edt_password.setError("Please enter password");
        } else if (edt_repassword.getText().toString().isEmpty()) {
            edt_repassword.setError("Please re_enter password");
        } else {
            return true;
        }
        return false;
    }
}