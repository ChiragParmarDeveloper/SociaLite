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
import android.widget.Button;
import android.widget.ImageView;

import com.ap.SociaLite.Activity.BlockedConnectionActivity;
import com.ap.SociaLite.Activity.HideConnectionActivity;
import com.ap.SociaLite.Adapter.BlockedContactAdapter;
import com.ap.SociaLite.Adapter.MyNetworkAdapter;
import com.ap.SociaLite.Adapter.ShareCareAdapter;
import com.ap.SociaLite.R;

import java.util.ArrayList;
import java.util.Arrays;

public class ShareFragment extends Fragment {


    public ShareFragment() {
        // Required empty public constructor
    }

    ConstraintLayout setting_messages_constraint;
    ImageView set_setting_msg;
    Button cancel,blocked_conversation,hide_conversation;


    private ShareCareAdapter myshareCareAdapter;
    private RecyclerView.LayoutManager layoutManager;
    RecyclerView messages_recycler;

    ArrayList Name = new ArrayList<>(Arrays.asList("Name", "Name", "Name", "Name", "Name"));

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_share, container, false);



        setting_messages_constraint = view.findViewById(R.id.setting_messages_constraint);
        set_setting_msg = view.findViewById(R.id.set_setting_msg);
        cancel = view.findViewById(R.id.cancel);
        blocked_conversation = view.findViewById(R.id.blocked_conversation);
        hide_conversation = view.findViewById(R.id.hide_conversation);

        messages_recycler = view.findViewById(R.id.messages_recycler);
        layoutManager = new GridLayoutManager(getActivity(), 1);
        //recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        messages_recycler.setLayoutManager(layoutManager);
        myshareCareAdapter = new ShareCareAdapter(Name,getActivity());
        messages_recycler.setAdapter(myshareCareAdapter);

        set_setting_msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setting_messages_constraint.setVisibility(View.VISIBLE);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setting_messages_constraint.setVisibility(View.GONE);
            }
        });

        blocked_conversation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), BlockedConnectionActivity.class));
            }
        });

        hide_conversation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), HideConnectionActivity.class));
            }
        });




        return view;
    }
}