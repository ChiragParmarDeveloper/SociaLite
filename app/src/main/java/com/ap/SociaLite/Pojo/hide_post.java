package com.ap.SociaLite.Pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class hide_post {

    @SerializedName("post_id")
    public String post_id;

    @SerializedName("image")
    public String image;

    @SerializedName("description")
    public String description;

    @SerializedName("location")
    public String location;

    @SerializedName("rate")
    public String rate;

    @SerializedName("comments")
    public List<String> comments;

    @SerializedName("in_bussiness_interaction")
    public String in_bussiness_interaction;

    @SerializedName("post_time")
    public String post_time;

    @SerializedName("username")
    public String username;

    @SerializedName("profile_pic")
    public String profile_pic;


}
