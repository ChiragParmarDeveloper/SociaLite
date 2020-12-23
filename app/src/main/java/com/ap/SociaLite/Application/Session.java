package com.ap.SociaLite.Application;

import android.content.Context;
import android.content.SharedPreferences;

public class Session {

    SharedPreferences sharedPreferences;
    Context context;

    String email_or_mobile;

    public Session(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("Socialite_Info", Context.MODE_PRIVATE);
    }

    public void removeuser() {
        sharedPreferences.edit().clear().commit();
    }

    public String getEmail_or_mobile() {
        email_or_mobile = sharedPreferences.getString("email_or_mobile", "");
        return email_or_mobile;
    }

    public void setEmail_or_mobile(String email_or_mobile) {
        this.email_or_mobile = email_or_mobile;
        sharedPreferences.edit().putString("email_or_mobile", email_or_mobile).commit();
    }


}
