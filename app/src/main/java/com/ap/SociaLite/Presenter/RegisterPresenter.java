package com.ap.SociaLite.Presenter;

import android.content.Context;
import android.widget.Button;
import android.widget.EditText;

import com.ap.SociaLite.Activity.RegisterActivity;
import com.ap.SociaLite.Application.AppUtils;
import com.ap.SociaLite.Contract.RegisterContract;

public class RegisterPresenter implements RegisterContract {

    private Context mContext;
    private RegisterActivity registerActivity;

    public RegisterPresenter(Context context, RegisterActivity registerActivity) {
        this.mContext = context;
        this.registerActivity = registerActivity;
    }


    @Override
    public boolean validate(EditText user_name, EditText email, EditText phone_no, EditText bio, Button dob, EditText location, EditText password) {

        if (user_name.getText().toString().isEmpty()) {
            user_name.setError("Please enter username");
            return false;
        }
        if (email.getText().toString().isEmpty()) {
            email.setError("Please enter email");
            return false;
        }
        if (!AppUtils.isEmailValid(AppUtils.getText(email))) {
            email.setError("Please enter valid email");
            return false;
        }
        if (phone_no.getText().toString().isEmpty()) {
            phone_no.setError("Please enter phone number");
            return false;
        }
        if (!AppUtils.isValidMobile(AppUtils.getText(phone_no))) {
            phone_no.setError("Please enter valid phone number");
            return false;
        }
        if (bio.getText().toString().isEmpty()) {
            bio.setError("Please enter bio");
            return false;
        }
        if (dob.getText().toString().isEmpty()) {
            dob.setError("Please enter valid dob");
            return false;
        } else {
            dob.setError(null);
        }
        if (location.getText().toString().isEmpty()) {
            location.setError("Please enter location");
            return false;
        }
        if (password.getText().toString().isEmpty()) {
            password.setError("Please enter password");
            return false;
        }
        return true;
    }
}
