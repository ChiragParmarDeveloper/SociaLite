package com.ap.SociaLite.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ap.SociaLite.Activity.CameraActivity;
import com.ap.SociaLite.Activity.SpotLightActivity;
import com.ap.SociaLite.Adapter.CategoryPostAdapter;
import com.ap.SociaLite.Adapter.MyNetworkAdapter;
import com.ap.SociaLite.R;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NetworkFragment extends Fragment {


    public NetworkFragment() {
        // Required empty public constructor
    }

    @BindView(R.id.recycleview_network_post)
    RecyclerView recycleview_network_post;

    ConstraintLayout network_image_constrain;
    ConstraintLayout network_story_constrain;

    private MyNetworkAdapter myNetworkAdapter;
    private RecyclerView.LayoutManager layoutManager;

    ArrayList Name = new ArrayList<>(Arrays.asList("Name", "Name", "Name", "Name", "Name"));


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_network, container, false);
        ButterKnife.bind(this, view);

        network_image_constrain = view.findViewById(R.id.network_image_constrain);
        network_story_constrain = view.findViewById(R.id.network_story_constrain);


        recycleview_network_post = view.findViewById(R.id.recycleview_network_post);
        layoutManager = new GridLayoutManager(getActivity(), 1);
        //recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        recycleview_network_post.setLayoutManager(layoutManager);
        myNetworkAdapter = new MyNetworkAdapter(Name,getActivity());
        recycleview_network_post.setAdapter(myNetworkAdapter);


        network_image_constrain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), CameraActivity.class));
            }
        });

        network_story_constrain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), SpotLightActivity.class));
            }
        });

        return view;
    }
}