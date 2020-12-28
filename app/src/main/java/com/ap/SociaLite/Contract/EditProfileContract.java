package com.ap.SociaLite.Contract;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.ap.SociaLite.R;

import butterknife.BindView;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public interface EditProfileContract {

    void fetch_profile(String user_id);

    boolean validate(EditText edt_username, EditText edt_email, EditText edt_no, Button edt_dob, EditText edt_location, EditText edt_pwd );

    void edit_profile (RequestBody user_id, RequestBody username, RequestBody email, RequestBody mobile_number,
                   RequestBody password, RequestBody location, RequestBody bio,RequestBody dob, MultipartBody.Part profile_pic);



}
