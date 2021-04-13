package com.ap.SociaLite.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.ap.SociaLite.Activity.SpotLightActivity;
import com.ap.SociaLite.Application.Session;
import com.ap.SociaLite.Editors.EditImageActivity;
import com.ap.SociaLite.Presenter.NetworkFragmentPresenter;
import com.ap.SociaLite.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NetworkFragment extends Fragment {

    @BindView(R.id.recycleview_network_post)
    public RecyclerView recycleview_network_post;

    @BindView(R.id.progressbar)
    public ProgressBar progressbar;

    @BindView(R.id.network_story_constrain)
    ConstraintLayout network_story_constrain;

    @BindView(R.id.network_image_constrain)
    ConstraintLayout network_image_constrain;

    public String UserId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_network, container, false);
        ButterKnife.bind(this, view);

        Session session = new Session(getActivity());
        UserId = session.getUser_id();

        new NetworkFragmentPresenter(getActivity(), this).my_network_post(UserId);
        return view;
    }

    @OnClick({R.id.network_story_constrain,R.id.network_image_constrain})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.network_story_constrain:
                startActivity(new Intent(getActivity(), SpotLightActivity.class));
                break;

            case R.id.network_image_constrain:
                Intent in = new Intent(getActivity(), EditImageActivity.class);
                in.putExtra("network_fragment", "my_network");
                startActivity(in);
                break;
        }
    }
}