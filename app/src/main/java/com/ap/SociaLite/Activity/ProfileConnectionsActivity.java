package com.ap.SociaLite.Activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.ap.SociaLite.Adapter.ConnectionAdapter.ConnectionAdapter;
import com.ap.SociaLite.Adapter.TabaccssorAdapter_myconnection.TabaccssorAdapter_myconnection;
import com.ap.SociaLite.Fragment.Connection.ConnectionFragment;
import com.ap.SociaLite.Fragment.Connection.MyConnectionFragment;
import com.ap.SociaLite.R;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProfileConnectionsActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private TabaccssorAdapter_myconnection tabaccssorAdapter_myconnection;

    ViewPager pharmacy_tabs_pager;

    @BindView(R.id.connection_serach)
    public EditText connection_serach;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_connections);
        ButterKnife.bind(this);

        pharmacy_tabs_pager = findViewById(R.id.connection_tabs_pager);
        tabLayout = findViewById(R.id.connection_tabs);

        tabaccssorAdapter_myconnection = new TabaccssorAdapter_myconnection(getSupportFragmentManager());
        pharmacy_tabs_pager.setAdapter(tabaccssorAdapter_myconnection);

        tabLayout.setupWithViewPager(pharmacy_tabs_pager);

        filter();
        filter_myconnection();
    }

    @OnClick({R.id.img_back})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                onBackPressed();
                break;
        }
    }


    public void filter() {

        connection_serach.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ConnectionFragment.connectionAdapter.filter(String.valueOf(s));
                ConnectionFragment.connectionAdapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void filter_myconnection() {

        connection_serach.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                MyConnectionFragment.myConnectionAdapter.filter(String.valueOf(s));
                MyConnectionFragment.myConnectionAdapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}