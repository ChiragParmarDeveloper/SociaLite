package com.ap.SociaLite.Contract;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public interface RegistrationVerificationContract {

    void register (MultipartBody.Part profile_pic, RequestBody username, RequestBody email, RequestBody mobile_number, RequestBody bio,
                   RequestBody dob, RequestBody location, RequestBody password);

}
