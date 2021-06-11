package com.ap.SociaLite.Pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class data {

    @SerializedName("request_id")
    public String request_id;

    @SerializedName("username")
    public String username;

    @SerializedName("profile_pic")
    public String profile_pic;

    @SerializedName("is_connected")
    public String is_connected;

    @SerializedName("story")
    public List<com.ap.SociaLite.Pojo.story> story;

    @SerializedName("user_id")
    public String user_id;

    @SerializedName("user_profile_pic")
    public String user_profile_pic;

    @SerializedName("request_username")
    public String request_username;

    @SerializedName("request_user_profile_pic")
    public String request_user_profile_pic;

    @SerializedName("notification_type")
    public String notification_type;

    @SerializedName("user_id__connected")
    public String user_id__connected;

    @SerializedName("reuest_profile_pics")
    public String reuest_profile_pics;

    @SerializedName("request_id_connection")
    public String request_id_connection;

    @SerializedName("rate")
    public String rate;

    @SerializedName("email")
    public String email;

    @SerializedName("post_image")
    public String post_image;

    @SerializedName("name")
    public String name;

    @SerializedName("number")
    public String number;

    @SerializedName("url")
    public String url;

    @SerializedName("shareuser_profile_pic")
    public String shareuser_profile_pic;

    @SerializedName("share_username")
    public String share_username;
}
