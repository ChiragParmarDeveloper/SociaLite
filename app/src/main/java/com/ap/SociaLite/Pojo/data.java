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

}
