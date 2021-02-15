package com.ap.SociaLite.Contract;

import android.widget.Button;
import android.widget.EditText;

public interface LoginContract {

    boolean validate(EditText edt_email, EditText password);

    void login(String edt_email, String password);

}
