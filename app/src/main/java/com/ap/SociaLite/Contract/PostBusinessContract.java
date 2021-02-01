package com.ap.SociaLite.Contract;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public interface PostBusinessContract {

    void fetch_my_intrest (String user_id);

    void upload_card (MultipartBody.Part[] image, RequestBody user_id);

}
