package com.ap.SociaLite.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.ap.SociaLite.R;

public class EditProfileActivity extends AppCompatActivity {

    ImageView img_back;
    Button edit_profile_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        img_back = findViewById(R.id.img_back);
        edit_profile_save = findViewById(R.id.edit_profile_save);

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        edit_profile_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent profile = new Intent(EditProfileActivity.this,ProfileActivity.class);
//                startActivity(profile);
//                finish();
                onBackPressed();
            }
        });



    }
}