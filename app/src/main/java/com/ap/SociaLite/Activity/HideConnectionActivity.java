package com.ap.SociaLite.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.ap.SociaLite.Adapter.HiddenConnectionAdapter;
import com.ap.SociaLite.Adapter.ShareCareAdapter;
import com.ap.SociaLite.R;

import java.util.ArrayList;
import java.util.Arrays;

public class HideConnectionActivity extends AppCompatActivity {

    ImageView img_back;
    RecyclerView hide_contact_recylerview;

    private HiddenConnectionAdapter myhiddenConnectionAdapter;
    private RecyclerView.LayoutManager layoutManager;

    ArrayList Name = new ArrayList<>(Arrays.asList("Name", "Name", "Name", "Name", "Name"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hide_connection);

        img_back = findViewById(R.id.img_back);
        hide_contact_recylerview = findViewById(R.id.hide_contact_recylerview);

        layoutManager = new GridLayoutManager(getApplicationContext(), 1);
        //recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        hide_contact_recylerview.setLayoutManager(layoutManager);
        myhiddenConnectionAdapter = new HiddenConnectionAdapter(Name,getApplicationContext());
        hide_contact_recylerview.setAdapter(myhiddenConnectionAdapter);

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });



    }
}