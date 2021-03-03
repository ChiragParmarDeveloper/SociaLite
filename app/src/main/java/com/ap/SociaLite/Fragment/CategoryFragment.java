package com.ap.SociaLite.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ap.SociaLite.Activity.CameraActivity;
import com.ap.SociaLite.Adapter.CategoryPostAdapter;
import com.ap.SociaLite.Application.Session;
import com.ap.SociaLite.Editors.EditImageActivity;
import com.ap.SociaLite.Presenter.CategoryFragmentPresenter;
import com.ap.SociaLite.R;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CategoryFragment extends Fragment {

    @BindView(R.id.rv_categorypost)
    public RecyclerView rv_categorypost;

    @BindView(R.id.camera_constrain)
    ConstraintLayout camera_constrain;

    @BindView(R.id.rv_interestlist)
    public RecyclerView rv_interestlist;

    public String user_id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        ButterKnife.bind(this, view);
        Session session = new Session(getActivity());
        user_id = session.getUser_id();

        new CategoryFragmentPresenter(getActivity(), this).fetch_all_intrest(user_id);
        new CategoryFragmentPresenter(getActivity(), this).Category_post_fragment(user_id);

        return view;
    }

    @OnClick({R.id.camera_constrain})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.camera_constrain:
                startActivity(new Intent(getActivity(), EditImageActivity.class));
                break;
        }
    }

}