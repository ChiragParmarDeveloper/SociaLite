package com.ap.SociaLite.Fragment.profile_connection_fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.ap.SociaLite.Presenter.ProfileConnectionTimelineFragmentPresenter;
import com.ap.SociaLite.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ProfileConnectionTimelineFragment extends Fragment {

    @BindView(R.id.recycleview_timeline)
    public RecyclerView recycleview_timeline;

    @BindView(R.id.progressbar)
    public ProgressBar progressbar;
    //private ProfileConnectionTimelineAdapter timelineAdapter;
    //private RecyclerView.LayoutManager layoutManager;
   public String user_id;
    //  ArrayList Name = new ArrayList<>(Arrays.asList("Name 1", "Name 2", "Name 3", "Name 4", "Name 5"));

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile_connection_timeline, container, false);
        ButterKnife.bind(this, view);

        user_id = getActivity().getIntent().getStringExtra("request_id");

//        layoutManager = new GridLayoutManager(getActivity(), 1);
//        //recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
//        recycleview_timeline.setLayoutManager(layoutManager);
//        timelineAdapter = new ProfileConnectionTimelineAdapter(Name,getActivity());
//        recycleview_timeline.setAdapter(timelineAdapter);

        new ProfileConnectionTimelineFragmentPresenter(getContext(), this).time_line_post(user_id);


        return view;
    }
}