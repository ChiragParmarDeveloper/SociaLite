package com.ap.SociaLite.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.ap.SociaLite.Adapter.TabaccssorAdapter_myconnection.TabaccssorAdapter_myconnection;
import com.ap.SociaLite.R;
import com.google.android.material.tabs.TabLayout;

public class ProfileConnectionsActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private TabaccssorAdapter_myconnection tabaccssorAdapter_myconnection;

    TabLayout pharmacy_tabs;
    ViewPager pharmacy_tabs_pager;

    ImageView img_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_connections);

        img_back = findViewById(R.id.img_back);
        pharmacy_tabs_pager = findViewById(R.id.connection_tabs_pager);
        tabLayout = findViewById(R.id.connection_tabs);

        tabaccssorAdapter_myconnection = new TabaccssorAdapter_myconnection(getSupportFragmentManager());
        pharmacy_tabs_pager.setAdapter(tabaccssorAdapter_myconnection);

        tabLayout.setupWithViewPager(pharmacy_tabs_pager);

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }
}