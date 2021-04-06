package com.ap.SociaLite.Contract;

import java.util.ArrayList;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface PostContract {

    void fetch_my_intrest(String user_id);

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
