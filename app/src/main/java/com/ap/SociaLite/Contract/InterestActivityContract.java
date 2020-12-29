package com.ap.SociaLite.Contract;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface InterestActivityContract {

    void interest();

    void add_user_intrest(String user_id,ArrayList<String>interest_ids);

}
