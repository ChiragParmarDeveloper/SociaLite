package com.ap.SociaLite.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

import com.ap.SociaLite.Adapter.SearchProfileAdapter;
import com.ap.SociaLite.Application.Session;
import com.ap.SociaLite.Pojo.data;
import com.ap.SociaLite.Presenter.SearchPresenter;
import com.ap.SociaLite.R;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Search extends AppCompatActivity {

    @BindView(R.id.rv_search_profile)
    public RecyclerView rv_search_profile;

    @BindView(R.id.search_profile_user_name)
    public TextView search_profile_user_name;

    @BindView(R.id.search_profile_image)
    public CircularImageView search_profile_image;

    @BindView(R.id.search_view)
    public SearchView search_view;

    @BindView(R.id.progressbar)
    public ProgressBar progressbar;

    @BindView(R.id.txt_connection)
    public TextView txt_connection;

    @BindView(R.id.img_pic)
    public ImageView img_pic;

    public SearchProfileAdapter searchProfileAdapter;
    public List<data> datas;

    public String user_id, RequestId;
    public int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        Session session = new Session(getApplicationContext());
        user_id = session.getUser_id();

        new SearchPresenter(this, this).all_user(user_id);
        filter();
    }

    @OnClick({R.id.img_back, R.id.search_connect, R.id.search_msg, R.id.search_share, R.id.search_profile_user_name, R.id.search_profile_image})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                onBackPressed();
                break;

            case R.id.search_connect:
                new SearchPresenter(this, this).send_request(user_id, RequestId);
                Log.d("adapter_position", String.valueOf(position));
                break;

            case R.id.search_msg:
//                Toast.makeText(getApplicationContext(), RequestId, Toast.LENGTH_LONG).show();
//                Intent chat = new Intent(view.getContext(), MessageChatActivity.class);
//                view.getContext().startActivity(chat);
                Toast.makeText(this, "Coming Soon", Toast.LENGTH_SHORT).show();
                break;

            case R.id.search_share:
                Intent in = new Intent(view.getContext(), ShareToFriend.class);
                view.getContext().startActivity(in);
                break;

            case R.id.search_profile_user_name:
                Intent con_pro1 = new Intent(view.getContext(), ProfileConnectionActivity.class);
                con_pro1.putExtra("request_id", RequestId);
                startActivity(con_pro1);
                break;

            case R.id.search_profile_image:
                Intent con_pro = new Intent(view.getContext(), ProfileConnectionActivity.class);
                con_pro.putExtra("request_id", RequestId);
                startActivity(con_pro);
                break;

        }
    }

    private void filter() {
        search_view.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //  search_history.setVisibility(View.VISIBLE);
                Search.this.searchProfileAdapter.filter(query);
                searchProfileAdapter.notifyDataSetChanged();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (TextUtils.isEmpty(newText)) {
                  /*  search_madical_recyclerview.setVisibility(View.GONE);
                    rcycl_search_history.setVisibility(View.VISIBLE);
                    search_madical_search.setVisibility(View.VISIBLE);
                    tv_data_not_found.setVisibility(View.GONE);*/
                } else {
                  /*  search_madical_recyclerview.setVisibility(View.VISIBLE);
                    rcycl_search_history.setVisibility(View.GONE);
                    search_madical_search.setVisibility(View.GONE);*/
                }

               /* if (mList.isEmpty()) {
                    // mRecyclerMessage.setVisibility(View.GONE);
                    tv_data_not_found.setVisibility(View.VISIBLE);

                } else {
                    //  mRecyclerMessage.setVisibility(View.VISIBLE);
                    tv_data_not_found.setVisibility(View.GONE);
                }
*/
                Search.this.searchProfileAdapter.filter(newText);
                searchProfileAdapter.notifyDataSetChanged();
                return false;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        new SearchPresenter(this, this).all_user(user_id);
        filter();
    }
}