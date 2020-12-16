package com.ap.SociaLite.Contract;

import android.preference.EditTextPreference;
import android.widget.Button;
import android.widget.EditText;

import com.ap.SociaLite.Activity.Edit;

public interface RegisterContract {

    boolean validate(EditText user_name, EditText email, EditText phone_no, EditText bio, Button dob, EditText location, EditText password );


}
