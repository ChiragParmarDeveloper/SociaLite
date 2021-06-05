package com.ap.SociaLite.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ap.SociaLite.R;

public class post_link extends AppCompatActivity {


    String post_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_link);
        handleIntent();

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        handleIntent();
    }

    private void handleIntent() {
        // ATTENTION: This was auto-generated to handle app links.
        Intent appLinkIntent = getIntent();
        String appLinkAction = appLinkIntent.getAction();
        Uri appLinkData = appLinkIntent.getData();

        if (appLinkData != null) {

            post_id = appLinkData.getQueryParameter("post");
            Log.d("post_id", post_id);

            post(post_id);

        }
    }

    private void post(String post_id) {

        Toast.makeText(this, "post found Successfully", Toast.LENGTH_SHORT).show();
    }

}