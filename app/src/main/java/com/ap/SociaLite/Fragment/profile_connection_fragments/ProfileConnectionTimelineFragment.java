package com.ap.SociaLite.Fragment.profile_connection_fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ap.SociaLite.Adapter.Profile_adapters.TimelineAdapter;
import com.ap.SociaLite.Adapter.Profile_connection_adapters.ProfileConnectionTimelineAdapter;
import com.ap.SociaLite.R;

import java.util.ArrayList;
import java.util.Arrays;


public class ProfileConnectionTimelineFragment extends Fragment {

    public ProfileConnectionTimelineFragment() {
        // Required empty public constructor
    }

    RecyclerView recycleview_timeline;

    private ProfileConnectionTimelineAdapter timelineAdapter;
    private RecyclerView.LayoutManager layoutManager;

    ArrayList Name = new ArrayList<>(Arrays.asList("Name 1", "Name 2", "Name 3", "Name 4", "Name 5"));

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile_connection_timeline, container, false);

        recycleview_timeline = view.findViewById(R.id.recycleview_timeline);
        layoutManager = new GridLayoutManager(getActivity(), 1);
        //recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        recycleview_timeline.setLayoutManager(layoutManager);
        timelineAdapter = new ProfileConnectionTimelineAdapter(Name,getActivity());
        recycleview_timeline.setAdapter(timelineAdapter);

        return view;
    }
}