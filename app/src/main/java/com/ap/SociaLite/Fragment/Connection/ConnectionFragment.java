package com.ap.SociaLite.Fragment.Connection;

import android.content.Intent;
import android.net.Uri;
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
import com.ap.SociaLite.Adapter.ConnectionAdapter.ConnectionAdapter;
import com.ap.SociaLite.Application.Session;
import com.ap.SociaLite.Pojo.user_connection;
import com.ap.SociaLite.Presenter.ConnectionFragmentPresenter;
import com.ap.SociaLite.R;
import com.mikhaellopez.circularimageview.CircularImageView;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ConnectionFragment extends Fragment {

    @BindView(R.id.progressbar)
    public ProgressBar progressbar;

    @BindView(R.id.search_profile_user_name)
    public TextView search_profile_user_name;

    @BindView(R.id.search_profile_image)
    public CircularImageView search_profile_image;

    @BindView(R.id.connections_recyclerview)
    public RecyclerView connections_recyclerview;

    @BindView(R.id.layout_connection)
    public ConstraintLayout layout_connection;

    @BindView(R.id.img_pic)
    public ImageView img_pic;

    @BindView(R.id.connect_to)
    public TextView connect_to;


    public int position;
    public static ConnectionAdapter connectionAdapter;
    public String UserId, RequestId;
    public static List<user_connection> user_connections;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_connection, container, false);
        ButterKnife.bind(this, view);
        Session session = new Session(getContext());
        UserId = session.getUser_id();

        new ConnectionFragmentPresenter(this, getContext()).followers(UserId);
        return view;
    }

    @OnClick({R.id.layout_connect, R.id.connection_msg, R.id.share, R.id.search_profile_user_name, R.id.search_profile_image})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.layout_connect:
                new ConnectionFragmentPresenter(this, getContext()).send_request(UserId, RequestId);
                break;

            case R.id.connection_msg:
//                Toast.makeText(getApplicationContext(), RequestId, Toast.LENGTH_LONG).show();
//                Intent chat = new Intent(view.getContext(), MessageChatActivity.class);
//                view.getContext().startActivity(chat);
                Toast.makeText(getContext(), "Coming Soon", Toast.LENGTH_SHORT).show();
                break;

            case R.id.share:
                Uri.Builder builder = new Uri.Builder();
                builder.scheme("http")
                        .authority("the-socialite.com")
                        .appendPath("profile/")
                        .appendQueryParameter("account", RequestId);

                String myUrl = builder.build().toString();

                Intent in = new Intent(view.getContext(), ShareToFriend.class);
                in.putExtra("url",myUrl);
                in.putExtra("share_profile","share_profile");
                in.putExtra("profile_share_id",RequestId);
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
}