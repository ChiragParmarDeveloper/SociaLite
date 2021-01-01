package com.ap.SociaLite.Contract;

import android.widget.EditText;

public interface AddCardActivityContract {

    boolean validate(EditText card_name, EditText card_email,EditText mobile_view);

    void business_create_card(String user_id, String name, String website, String mobile);

}
