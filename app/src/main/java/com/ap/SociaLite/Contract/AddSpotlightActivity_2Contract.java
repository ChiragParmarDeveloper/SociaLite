package com.ap.SociaLite.Contract;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public interface AddSpotlightActivity_2Contract {

    void put_my_story(RequestBody user_id, MultipartBody.Part story_image);

}
