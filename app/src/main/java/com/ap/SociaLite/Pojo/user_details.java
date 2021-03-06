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

    @SerializedName("profile_pic")
    public String profile_pic;

    @SerializedName("bio")
    public String bio;

    @SerializedName("dob")
    public String dob;

    @SerializedName("location")
    public String location;

    @SerializedName("password")
    public String password;

    @SerializedName("cover_photo")
    public String cover_photo;

    @SerializedName("connection")
    public String connection;

    @SerializedName("notification_on_off")
    public String notification_on_off;

    @SerializedName("notification_count")
    public String notification_count;

}
