package com.ap.SociaLite.Pojo;

import com.google.gson.annotations.SerializedName;

public class user_details {

    @SerializedName("user_id")
    public String user_id;

    @SerializedName("username")
    public String username;

    @SerializedName("mobile_number")
    public String mobile_number;

    @SerializedName("email")
    public String email;

    @SerializedName("is_private_account")
    public String is_private_account;

}
