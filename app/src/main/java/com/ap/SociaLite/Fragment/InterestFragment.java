package com.ap.SociaLite.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ap.SociaLite.Activity.CameraActivity;
import com.ap.SociaLite.Adapter.InterestPostAdapter;
import com.ap.SociaLite.Application.Session;
import com.ap.SociaLite.Presenter.InterestFragmentPresenter;
import com.ap.SociaLite.R;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InterestFragment extends Fragment {

    @BindView(R.id.rv_interestlist)
    public RecyclerView rv_interestlist;

    @BindView(R.id.camera)
    ImageView camera;

    @BindView(R.id.rv_post_recycler)
    public RecyclerView rv_post_recycler;

    @BindView(R.id.camera_constrain)
    ConstraintLayout camera_constrain;

    public String user_id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_interest, container, false);
        ButterKnife.bind(this, view);
        Session session = new Session(getActivity());
        user_id = session.getUser_id();

        new InterestFragmentPresenter(getActivity(), this).fetch_my_intrest(user_id);

        return view;
    }

    @OnClick({R.id.camera_constrain})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.camera_constrain:
                startActivity(new Intent(getActivity(), CameraActivity.class));
                break;
        }
    }
}