package com.ap.SociaLite.Application;

import android.content.Context;
import android.content.SharedPreferences;

public class Remember_Session {

    SharedPreferences sharedPreferences_remember;
    Context context;

    String email_or_mobile;
    String password;

    public Remember_Session(Context context) {
        this.context = context;
        sharedPreferences_remember = context.getSharedPreferences("remember_Info", Context.MODE_PRIVATE);
    }

    public void removeuser() {
        sharedPreferences_remember.edit().clear().commit();
    }

    public String getEmail_or_mobile() {
        email_or_mobile = sharedPreferences_remember.getString("email_or_mobile", "");
        return email_or_mobile;
    }

    public void setEmail_or_mobile(String email_or_mobile) {
        this.email_or_mobile = email_or_mobile;
        sharedPreferences_remember.edit().putString("email_or_mobile", email_or_mobile).commit();
    }

    public String getPassword() {
        password = sharedPreferences_remember.getString("password", "");
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        sharedPreferences_remember.edit().putString("password",password).commit();
    }
}
