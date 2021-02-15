package com.ap.SociaLite.Fragment.Profile_fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.ap.SociaLite.Application.Session;
import com.ap.SociaLite.Presenter.BusinessInteractionFragmentPresenter;
import com.ap.SociaLite.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BusinessInteractionFragment extends Fragment {

    @BindView(R.id.rv_business_interaction)
    public RecyclerView rv_business_interaction;

    public String user_id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_business_interaction, container, false);
        ButterKnife.bind(this, view);

        Session session = new Session(getActivity());
        user_id = session.getUser_id();

        new BusinessInteractionFragmentPresenter(getActivity(), this).my_post_business_intrection(user_id);

        return view;
    }
}