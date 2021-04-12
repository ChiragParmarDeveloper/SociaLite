package com.ap.SociaLite.Fragment.profile_connection_fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.ap.SociaLite.Presenter.ProfileConnectionBusinessFragmentPresenter;
import com.ap.SociaLite.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ProfileConnectionBusinessFragment extends Fragment {

    @BindView(R.id.progressbar)
    public ProgressBar progressbar;

    @BindView(R.id.recycleview_business_interaction)
    public RecyclerView recycleview_business_interaction;

   public String user_id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile_connection_business, container, false);
        ButterKnife.bind(this, view);

        user_id = getActivity().getIntent().getStringExtra("request_id");

        new ProfileConnectionBusinessFragmentPresenter(getActivity(), this).my_post_business_intrection(user_id);
        return view;
    }
}