package com.ap.SociaLite.Pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class post_list {

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

    @SerializedName("in_bussiness_interaction")
    public String in_bussiness_interaction;

    @SerializedName("schedule_date")
    public String schedule_date;

    @SerializedName("schedule_time")
    public String schedule_time;
//    @SerializedName("comments")
//    public List<String> comments;
}