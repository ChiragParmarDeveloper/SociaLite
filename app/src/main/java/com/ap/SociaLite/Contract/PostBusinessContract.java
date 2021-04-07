package com.ap.SociaLite.Contract;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public interface PostBusinessContract {

    void fetch_my_intrest (String user_id);

    void upload_card (MultipartBody.Part[] image, RequestBody user_id);

    void post(RequestBody user_id,
              MultipartBody.Part[] post_image,
              RequestBody description,
              RequestBody intrest_id,
              RequestBody in_bussiness_interaction,
              RequestBody location,
              RequestBody hide_users,
              RequestBody share_users,
              RequestBody schedule_date,
              RequestBody schedule_time);
}
