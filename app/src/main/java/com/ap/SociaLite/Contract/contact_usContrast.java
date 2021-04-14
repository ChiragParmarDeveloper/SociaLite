package com.ap.SociaLite.Contract;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public interface contact_usContrast {
    void fetch_profile(String user_id);

    boolean validate(TextView txt_name, TextView txt_mail, EditText edt_msgs);

    void contact_us_api(String name,String email,String message);

}
