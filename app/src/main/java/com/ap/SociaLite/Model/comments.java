package com.ap.SociaLite.Model;

import com.google.gson.annotations.SerializedName;

public class comments {

    @SerializedName("user_name")
    public String user_name;

    @SerializedName("comment")
    public String comment;

    @SerializedName("created_at")
    public String created_at;

    @SerializedName("profile_pic")
    public String profile_pic;


}
