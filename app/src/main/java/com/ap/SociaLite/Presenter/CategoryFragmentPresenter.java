package com.ap.SociaLite.Presenter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ap.SociaLite.Adapter.CategoryListAdapter;
import com.ap.SociaLite.Adapter.CategoryPostAdapter;
import com.ap.SociaLite.Application.RService;
import com.ap.SociaLite.Application.json;
import com.ap.SociaLite.Contract.CategoryFragmentContract;
import com.ap.SociaLite.Fragment.CategoryFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryFragmentPresenter implements CategoryFragmentContract {

    public Context mContext;
    public CategoryFragment categoryFragment;

    public CategoryFragmentPresenter(Context context, CategoryFragment fragment) {
        this.mContext = context;
        this.categoryFragment = fragment;
    }

    @Override
    public void fetch_all_intrest(String user_id) {
        categoryFragment.progressbar.setVisibility(View.VISIBLE);
        try {
            new RService.api().call(mContext).interest_list_post_page(user_id).enqueue(new Callback<json>() {
                @Override
                public void onResponse(Call<json> call, Response<json> response) {
                    categoryFragment.progressbar.setVisibility(View.GONE);
                    if (response.body().status.equals("1")) {

                        if (response.body().interest_details != null && response.body().interest_details.size() > 0) {
                            categoryFragment.rv_interestlist.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
                            categoryFragment.rv_interestlist.setAdapter(new CategoryListAdapter(mContext, response.body().interest_details, categoryFragment));
                        }
                    } else {
                        //   Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<json> call, Throwable t) {
                    categoryFragment.progressbar.setVisibility(View.GONE);
                    //     Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
                    //    Log.d("error", String.valueOf(t.getMessage()));
                }
            });
        } catch (Exception e) {

        }
    }

    @Override
    public void Category_post_fragment(String user_id) {
        categoryFragment.progressbar.setVisibility(View.VISIBLE);
        try {
            new RService.api().call(mContext).category_post(user_id).enqueue(new Callback<json>() {
                @Override
                public void onResponse(Call<json> call, Response<json> response) {
                    categoryFragment.progressbar.setVisibility(View.GONE);
                    if (response.body().status.equals("1")) {

                        if (response.body().post_list != null && response.body().post_list.size() > 0) {
                            categoryFragment.rv_categorypost.setLayoutManager(new GridLayoutManager(mContext, 1));
                            categoryFragment.rv_categorypost.setAdapter(new CategoryPostAdapter(mContext, response.body().post_list, categoryFragment));
                        }
                    } else {
                        //       Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<json> call, Throwable t) {
                    categoryFragment.progressbar.setVisibility(View.GONE);
                    //     Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
                    //     Log.d("error", String.valueOf(t.getMessage()));
                }
            });
        } catch (Exception e) {

        }
    }

    @Override
    public void hide_post(String user_id, String post_id) {

        try {
            new RService.api().call(mContext).dashboard_hidepost(user_id, post_id).enqueue(new Callback<json>() {
                @Override
                public void onResponse(Call<json> call, Response<json> response) {
                    if (response.body().status.equals("1")) {
                        Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();

                    } else {
                        //   Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<json> call, Throwable t) {
                    // Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
                    // Log.d("error", String.valueOf(t.getMessage()));
                }
            });
        } catch (Exception e) {

        }
    }

    @Override
    public void category_save_post(String user_id, String post_id) {
        try {
            new RService.api().call(mContext).dashboard_savepost(user_id, post_id).enqueue(new Callback<json>() {
                @Override
                public void onResponse(Call<json> call, Response<json> response) {
                    if (response.body().status.equals("1")) {
                        Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<json> call, Throwable t) {
                    // Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
                    // Log.d("error", String.valueOf(t.getMessage()));
                }
            });
        } catch (Exception e) {

        }
    }

    @Override
    public void rating_post(String user_id, String post_id, String rate) {
        try {
            new RService.api().call(mContext).give_rating(user_id, post_id, rate).enqueue(new Callback<json>() {
                @Override
                public void onResponse(Call<json> call, Response<json> response) {
                    if (response.body().status.equals("1")) {
                        Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<json> call, Throwable t) {
                    Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.d("error", String.valueOf(t.getMessage()));
                }
            });
        } catch (Exception e) {

        }
    }

    @Override
    public void update_new_intrests(String user_id, String interest_id) {
        try {
            new RService.api().call(mContext).plus_add_interest(user_id, interest_id).enqueue(new Callback<json>() {
                @Override
                public void onResponse(Call<json> call, Response<json> response) {
                    if (response.body().status.equals("1")) {
                        Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<json> call, Throwable t) {
                    Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.d("error", String.valueOf(t.getMessage()));
                }
            });
        } catch (Exception e) {

        }

    }
}