package com.ap.SociaLite.Fragment.Connection;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.ap.SociaLite.Activity.ProfileConnectionActivity;
import com.ap.SociaLite.Activity.ShareToFriend;
import com.ap.SociaLite.Adapter.ConnectionAdapter.MyConnectionAdapter;
import com.ap.SociaLite.Application.Session;
import com.ap.SociaLite.Pojo.user_connection;
import com.ap.SociaLite.Presenter.MyConnectionFragmentPresenter;
import com.ap.SociaLite.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MyConnectionFragment extends Fragment {

    @BindView(R.id.progressbar)
    public ProgressBar progressbar;

    @BindView(R.id.myconnections_recyclerview)
    public RecyclerView myconnections_recyclerview;

    @BindView(R.id.search_profile_user_name)
    public TextView search_profile_user_name;

    @BindView(R.id.search_profile_image)
    public ImageView search_profile_image;

    @BindView(R.id.constraint_myconnection)
    public ConstraintLayout constraint_myconnection;

    public static MyConnectionAdapter myConnectionAdapter;
    public String UserId, RequestId;
    public List<user_connection> user_connections;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_connection, container, false);
        ButterKnife.bind(this, view);

        Session session = new Session(getContext());
        UserId = session.getUser_id();

        new MyConnectionFragmentPresenter(this, getContext()).following(UserId);
        return view;
    }

    @OnClick({R.id.connection_msg, R.id.layout_disconnect, R.id.search_profile_user_name, R.id.share, R.id.search_profile_image})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.layout_disconnect:
                new MyConnectionFragmentPresenter(this, getContext()).remove_frnd(UserId, RequestId);
                new MyConnectionFragmentPresenter(this, getContext()).following(UserId);
                break;

            case R.id.connection_msg:
//                Toast.makeText(getApplicationContext(), RequestId, Toast.LENGTH_LONG).show();
//                Intent chat = new Intent(view.getContext(), MessageChatActivity.class);
//                view.getContext().startActivity(chat);
                Toast.makeText(getContext(), "Coming Soon", Toast.LENGTH_SHORT).show();
                break;

            case R.id.share:
                Intent in = new Intent(view.getContext(), ShareToFriend.class);
                view.getContext().startActivity(in);
                break;

            case R.id.search_profile_user_name:
                Intent con_pro = new Intent(getContext(), ProfileConnectionActivity.class);
                con_pro.putExtra("request_id", RequestId);
                startActivity(con_pro);
                break;

            case R.id.search_profile_image:
                Intent con_pro1 = new Intent(getContext(), ProfileConnectionActivity.class);
                con_pro1.putExtra("request_id", RequestId);
                startActivity(con_pro1);
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        new MyConnectionFragmentPresenter(this, getContext()).following(UserId);
    }
}