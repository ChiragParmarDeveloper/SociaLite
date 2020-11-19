package com.ap.SociaLite.Fragment.Profile_fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ap.SociaLite.Adapter.BusinessInteractionAdapter;
import com.ap.SociaLite.Adapter.Profile_adapters.ProfileBusinessInteractionAdapter;
import com.ap.SociaLite.R;

import java.util.ArrayList;
import java.util.Arrays;

public class BusinessInteractionFragment extends Fragment {

    public BusinessInteractionFragment() {
        // Required empty public constructor
    }

    RecyclerView recycleview_business_interaction;

    private ProfileBusinessInteractionAdapter myprofileBusinessInteractionAdapter;
    private RecyclerView.LayoutManager layoutManager;

    ArrayList Name = new ArrayList<>(Arrays.asList("Name 1", "Name 2", "Name 3", "Name 4", "Name 5"));

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_business_interaction, container, false);

        recycleview_business_interaction = view.findViewById(R.id.recycleview_business_interaction);
        layoutManager = new GridLayoutManager(getActivity(), 1);
        //recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        recycleview_business_interaction.setLayoutManager(layoutManager);
        myprofileBusinessInteractionAdapter = new ProfileBusinessInteractionAdapter(Name,getActivity());
        recycleview_business_interaction.setAdapter(myprofileBusinessInteractionAdapter);

        return view;
    }
}