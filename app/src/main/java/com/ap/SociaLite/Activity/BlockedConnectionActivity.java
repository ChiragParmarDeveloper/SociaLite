package com.ap.SociaLite.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.ap.SociaLite.Adapter.BlockedContactAdapter;
import com.ap.SociaLite.Adapter.SpotlightAdapter;
import com.ap.SociaLite.R;

import java.util.ArrayList;
import java.util.Arrays;

public class BlockedConnectionActivity extends AppCompatActivity {

    private BlockedContactAdapter myblockedContactAdapter;
    private RecyclerView.LayoutManager layoutManager;

    RecyclerView blocked_contact;

    ImageView img_back;

    ArrayList Name = new ArrayList<>(Arrays.asList("User 1", "User 2", "User 3", "User 4", "User 5"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blocked_connection);

        img_back = findViewById(R.id.img_back);

        blocked_contact = findViewById(R.id.blocked_contact_recylerview);
        layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        //recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        blocked_contact.setLayoutManager(layoutManager);
        myblockedContactAdapter = new BlockedContactAdapter(Name,getApplicationContext());
        blocked_contact.setAdapter(myblockedContactAdapter);


        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}