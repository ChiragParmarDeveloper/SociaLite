package com.ap.SociaLite.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.ap.SociaLite.Activity.CameraActivity;
import com.ap.SociaLite.Activity.CreatePostActivity;
import com.ap.SociaLite.Activity.LoginActivity;
import com.ap.SociaLite.Activity.RegisterActivity;
import com.ap.SociaLite.Adapter.CategoryListAdapter;
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

    @BindView(R.id.camera_constrain)
    ConstraintLayout camera_constrain;

    @BindView(R.id.recyclerview_categorylist)
    RecyclerView recyclerview_categorylist;



//    @BindView(R.id.floating_action_button)
//    ImageView floating_action_button;

    private CategoryPostAdapter categoryPostAdapter;
    private RecyclerView.LayoutManager layoutManager;

    ArrayList Name = new ArrayList<>(Arrays.asList("Name", "Name", "Name", "Name", "Name"));

    CategoryListAdapter categoryListAdapter;
    private RecyclerView.LayoutManager layoutManager_list;

    ArrayList CategoryNames = new ArrayList<>(Arrays.asList("Photography", "Sports", "Games", "Fun", "Laugh"));
    ArrayList CategoryImages = new ArrayList<>(Arrays.asList(R.drawable.photography, R.drawable.sport, R.drawable.photography, R.drawable.sport, R.drawable.photography));

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

        //-------------------------------for post--------------------------------------------
        recycleview_categorypost = view.findViewById(R.id.recycleview_categorypost);
        layoutManager = new GridLayoutManager(getActivity(), 1);
        //recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        recycleview_categorypost.setLayoutManager(layoutManager);
        categoryPostAdapter = new CategoryPostAdapter(getActivity(),Name);
        recycleview_categorypost.setAdapter(categoryPostAdapter);


        //------------------------------------for category----------------------------------------
        recyclerview_categorylist.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true));
        // recycler_brand_icon.addItemDecoration(new CenterZoomLayoutManager());
        categoryListAdapter = new CategoryListAdapter(getActivity(), CategoryNames, CategoryImages);
        recyclerview_categorylist.setAdapter(categoryListAdapter); // set the Adapter to RecyclerVie



        camera_constrain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), CameraActivity.class));
            }
        });

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