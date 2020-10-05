package com.ap.SociaLite.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.ap.SociaLite.Activity.CreatePostActivity;
import com.ap.SociaLite.Activity.LoginActivity;
import com.ap.SociaLite.Activity.RegisterActivity;
import com.ap.SociaLite.Adapter.CategoryPostAdapter;
import com.ap.SociaLite.R;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CategoryFragment extends Fragment {

    @BindView(R.id.recycleview_categorypost)
    RecyclerView recycleview_categorypost;

//    @BindView(R.id.floating_action_button)
//    ImageView floating_action_button;

    private CategoryPostAdapter categoryPostAdapter;
    private RecyclerView.LayoutManager layoutManager;

    ArrayList Name = new ArrayList<>(Arrays.asList("Name", "Name", "Name", "Name", "Name"));

//    navigationView = (NavigationView) findViewById(R.id.navigation_view);
//
//
//    View hView =  navigationView.inflateHeaderView(R.layout.abc);
//    ImageView imgvw = (ImageView)hView.findViewById(R.id.profile_image);
//    TextView tv = (TextView)hView.findViewById(R.id.username);
//    imgvw .setImageResource(R.drawable.logo);
//    tv.setText("UserName");



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        ButterKnife.bind(this, view);

        recycleview_categorypost = view.findViewById(R.id.recycleview_categorypost);
        layoutManager = new GridLayoutManager(getActivity(), 1);
        //recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        recycleview_categorypost.setLayoutManager(layoutManager);
        categoryPostAdapter = new CategoryPostAdapter(getActivity(),Name);
        recycleview_categorypost.setAdapter(categoryPostAdapter);

        return view;
    }

//    @OnClick({R.id.floating_action_button})
//    public void OnClick(View view) {
//        switch (view.getId()) {
//            case R.id.floating_action_button:
//                startActivity(new Intent(getActivity(), CreatePostActivity.class));
//
//                break;
//        }
//    }

}