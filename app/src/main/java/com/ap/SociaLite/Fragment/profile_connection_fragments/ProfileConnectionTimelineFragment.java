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

    public String user_id;

    public ProfileConnectionTimelineFragment(String user_id){
        this.user_id = user_id;
    }




    @BindView(R.id.recycleview_timeline)
    public RecyclerView recycleview_timeline;

    @BindView(R.id.progressbar)
    public ProgressBar progressbar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile_connection_timeline, container, false);
        ButterKnife.bind(this, view);

//        user_id = getActivity().getIntent().getStringExtra("request_id");

        new ProfileConnectionTimelineFragmentPresenter(getContext(), this).time_line_post(user_id);

        return view;
    }
}