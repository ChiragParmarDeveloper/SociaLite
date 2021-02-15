package com.ap.SociaLite.Fragment.Profile_fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.ap.SociaLite.Application.Session;
import com.ap.SociaLite.Presenter.TimeLineFragmentPresenter;
import com.ap.SociaLite.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class TimeLineFragment extends Fragment {

    @BindView(R.id.rv_timeline)
    public RecyclerView rv_timeline;

    public String user_id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_time_line, container, false);
        ButterKnife.bind(this, view);

        Session session = new Session(getActivity());
        user_id = session.getUser_id();

        new TimeLineFragmentPresenter(getActivity(), this).time_line_post(user_id);
        return view;
    }
}