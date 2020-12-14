package com.ap.SociaLite.Fragment.Connection;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ap.SociaLite.Activity.ProfileConnectionActivity;
import com.ap.SociaLite.Adapter.ConnectionAdapter.ConnectionAdapter;
import com.ap.SociaLite.Adapter.SearchProfileAdapter;
import com.ap.SociaLite.R;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;

public class ConnectionFragment extends Fragment {

    public ConnectionFragment() {
        // Required empty public constructor
    }

    TextView search_profile_user_name;
    ImageView search_profile_image;

    RecyclerView connections_recyclerview;

    private ConnectionAdapter connectionAdapter;
    private RecyclerView.LayoutManager layoutManager;

    ArrayList Name = new ArrayList<>(Arrays.asList("User 1", "User 2", "User 3", "User 4", "User 5"));

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_connection, container, false);

        search_profile_user_name = view.findViewById(R.id.search_profile_user_name);
        search_profile_image = view.findViewById(R.id.search_profile_image);

        connections_recyclerview = view.findViewById(R.id.connections_recyclerview);
        layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        //recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        connections_recyclerview.setLayoutManager(layoutManager);
        connectionAdapter = new ConnectionAdapter(Name,getContext());
        connections_recyclerview.setAdapter(connectionAdapter);

        search_profile_user_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent con_pro = new Intent(getContext(), ProfileConnectionActivity.class);
                startActivity(con_pro);
            }
        });

        search_profile_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent con_pro = new Intent(getContext(), ProfileConnectionActivity.class);
                startActivity(con_pro);
            }
        });

        return view;

    }
}