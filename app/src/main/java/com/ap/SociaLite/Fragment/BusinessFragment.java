package com.ap.SociaLite.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ap.SociaLite.Activity.CameraActivity;
import com.ap.SociaLite.Adapter.BusinessInteractionAdapter;
import com.ap.SociaLite.Adapter.CategoryListAdapter;
import com.ap.SociaLite.Adapter.MyNetworkAdapter;
import com.ap.SociaLite.Presenter.BusinessFragmentPresenter;
import com.ap.SociaLite.Presenter.InterestFragmentPresenter;
import com.ap.SociaLite.R;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class BusinessFragment extends Fragment {

    public BusinessFragment() {
        // Required empty public constructor
    }
    @BindView(R.id.post_constraint)
    ConstraintLayout post_constraint;

    @BindView(R.id.recycleview_business_post)
    RecyclerView recycleview_business_post;

    @BindView(R.id.rv_interestlist)
    public RecyclerView rv_interestlist;

    private BusinessInteractionAdapter businessInteractionAdapter;
    private RecyclerView.LayoutManager layoutManager;

    ArrayList Name = new ArrayList<>(Arrays.asList("Name 1", "Name 2", "Name 3", "Name 4", "Name 5"));

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_business, container, false);
        ButterKnife.bind(this, view);
     //   post_constraint = view.findViewById(R.id.post_constraint);

        //---------------------------------------------for post-----------------------------------------
        recycleview_business_post = view.findViewById(R.id.recycleview_business_post);
        layoutManager = new GridLayoutManager(getActivity(), 1);
        //recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        recycleview_business_post.setLayoutManager(layoutManager);
        businessInteractionAdapter = new BusinessInteractionAdapter(Name,getActivity());
        recycleview_business_post.setAdapter(businessInteractionAdapter);

        new BusinessFragmentPresenter(getActivity(),this).interest();

        return view;
    }

    @OnClick({R.id.post_constraint})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.post_constraint:
                Intent in = new Intent(getActivity(), CameraActivity.class);
                in.putExtra("business_fragment", "business_interaction");
                startActivity(in);
                break;
        }
    }

}