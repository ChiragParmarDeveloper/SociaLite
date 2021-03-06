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

import com.ap.SociaLite.Adapter.BusinessInteractionAdapter;
import com.ap.SociaLite.Application.Session;
import com.ap.SociaLite.Editors.EditImageActivity;
import com.ap.SociaLite.Presenter.BusinessFragmentPresenter;
import com.ap.SociaLite.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class BusinessFragment extends Fragment {

    @BindView(R.id.post_constraint)
    ConstraintLayout post_constraint;

    @BindView(R.id.recycleview_business_post)
    public RecyclerView recycleview_business_post;

    @BindView(R.id.rv_interestlist)
    public RecyclerView rv_interestlist;

    @BindView(R.id.progressbar)
    public ProgressBar progressbar;

    public String user_id, interest_ids;
    public String interest_id;

    public BusinessInteractionAdapter businessInteractionAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_business, container, false);
        ButterKnife.bind(this, view);
        Session session = new Session(getActivity());
        user_id = session.getUser_id();

        new BusinessFragmentPresenter(getActivity(), this).fetch_all_intrest(session.getUser_id());

        return view;
    }

    @OnClick({R.id.post_constraint})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.post_constraint:
                Intent in = new Intent(getActivity(), EditImageActivity.class);
                in.putExtra("business_fragment", "business_interaction");
                startActivity(in);
                break;
        }
    }
}