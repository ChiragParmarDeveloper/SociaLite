package com.ap.SociaLite.Contract;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public interface ProfileActivityContract {

    void profile_my_profile(String user_id);


    void cover_photo(RequestBody user_id, MultipartBody.Part cover_photo);


}
