package com.ap.SociaLite.Application;

import android.widget.Toast;

import com.ap.SociaLite.Pojo.faq_list;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class json {

    @SerializedName("status")
    public String status;

    @SerializedName("message")
    public String message;

    @SerializedName("faq_list")
    public List<com.ap.SociaLite.Pojo.faq_list> faq_list;

    @SerializedName("user_details")
    public com.ap.SociaLite.Pojo.user_details user_details;

    @SerializedName("hide_post")
    public com.ap.SociaLite.Pojo.hide_post hide_post;

    @SerializedName("interest_list")
    public List<com.ap.SociaLite.Pojo.interest_list> interest_list;

    @SerializedName("interest_details")
    public List<com.ap.SociaLite.Pojo.interest_details> interest_details;

    @SerializedName("post_list")
    public List<com.ap.SociaLite.Pojo.post_list> post_list;

    @SerializedName("user_card")
    public List<com.ap.SociaLite.Pojo.user_card> user_card;

    @SerializedName("my_profile_user_details")
    public List<com.ap.SociaLite.Pojo.my_profile_user_details> my_profile_user_details;

    @SerializedName("comments")
    public com.ap.SociaLite.Pojo.comments comments;

    @SerializedName("story_data")
    public List<com.ap.SociaLite.Pojo.story_data> story_data;

    @SerializedName("story_view")
    public List<com.ap.SociaLite.Pojo.story_view> story_view;

    @SerializedName("user_list")
    public List<com.ap.SociaLite.Pojo.user_list> user_list;

    @SerializedName("Report_details")
    public List<com.ap.SociaLite.Pojo.Report_details> Report_details;



}