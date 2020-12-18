package com.ap.SociaLite.Presenter;

import android.content.Context;
import android.widget.EditText;

import com.ap.SociaLite.Activity.LoginActivity;
import com.ap.SociaLite.Activity.RegisterActivity;
import com.ap.SociaLite.Application.AppUtils;
import com.ap.SociaLite.Contract.LoginContract;
import com.ap.SociaLite.Contract.RegisterContract;

public class LoginPresenter implements LoginContract {

    private Context mContext;
    private LoginActivity loginActivity;

    public LoginPresenter(Context context, LoginActivity loginActivity) {
        this.mContext = context;
        this.loginActivity = loginActivity;
    }

    @Override
    public boolean validate(EditText edt_email, EditText password) {

        if (edt_email.getText().toString().isEmpty()) {
            edt_email.setError("Please enter email/phone number");
            return false;
        }
        if (password.getText().toString().isEmpty()) {
            password.setError("Please enter password");
            return false;
        }
        return true;
    }
}
