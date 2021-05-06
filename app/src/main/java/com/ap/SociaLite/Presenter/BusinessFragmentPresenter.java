package com.ap.SociaLite.Presenter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ap.SociaLite.Adapter.BusinessInteractionAdapter;
import com.ap.SociaLite.Adapter.BusinessListAdapter;
import com.ap.SociaLite.Adapter.SearchProfileAdapter;
import com.ap.SociaLite.Application.RService;
import com.ap.SociaLite.Application.json;
import com.ap.SociaLite.Contract.BusinessFragmentContract;
import com.ap.SociaLite.Fragment.BusinessFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BusinessFragmentPresenter implements BusinessFragmentContract {

    public Context mContext;
    public BusinessFragment businessFragment;

    public BusinessFragmentPresenter(Context context, BusinessFragment fragment) {
        this.mContext = context;
        this.businessFragment = fragment;
    }

    @Override
    public void fetch_all_intrest(String user_id) {

        try {
            new RService.api().call(mContext).interest_list_post_page(user_id).enqueue(new Callback<json>() {
                @Override
                public void onResponse(Call<json> call, Response<json> response) {
                    if (response.body().status.equals("1")) {
                        if (response.body().interest_details != null && response.body().interest_details.size() > 0) {
                            businessFragment.rv_interestlist.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
                            businessFragment.rv_interestlist.setAdapter(new BusinessListAdapter(mContext, response.body().interest_details, businessFragment));
                        }
                    } else {
                        //   Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<json> call, Throwable t) {
                    //     Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
                    //    Log.d("error", String.valueOf(t.getMessage()));
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
                        //   Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                        new BusinessFragmentPresenter(mContext, businessFragment).remove_interest(businessFragment.user_id, businessFragment.interest_ids);
                        new BusinessFragmentPresenter(mContext, businessFragment).fetch_all_intrest(businessFragment.user_id);

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
    public void business_post(String interest_id, String user_id) {
        businessFragment.progressbar.setVisibility(View.VISIBLE);
        try {
            new RService.api().call(mContext).post_business(interest_id, user_id).enqueue(new Callback<json>() {
                @Override
                public void onResponse(Call<json> call, Response<json> response) {
                    businessFragment.progressbar.setVisibility(View.GONE);
                    if (response.body().status.equals("1")) {
                        if (response.body().post_list != null && response.body().post_list.size() > 0) {
                         //   businessFragment.recycleview_business_post.setLayoutManager(new GridLayoutManager(mContext, 1));
                          //  businessFragment.recycleview_business_post.setAdapter(new BusinessInteractionAdapter(mContext, businessFragment, response.body().post_list));

                            businessFragment.businessInteractionAdapter = new BusinessInteractionAdapter(mContext,businessFragment,response.body().post_list);
                            businessFragment.recycleview_business_post.setLayoutManager(new GridLayoutManager(mContext, 1));
                            businessFragment.recycleview_business_post.setAdapter(businessFragment.businessInteractionAdapter);
                            businessFragment.businessInteractionAdapter.notifyDataSetChanged();

                        }
                    } else {

                        businessFragment.recycleview_business_post.setLayoutManager(new GridLayoutManager(mContext, 1));
                        businessFragment.recycleview_business_post.setAdapter(new BusinessInteractionAdapter(mContext, businessFragment, response.body().post_list));

                        //    Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<json> call, Throwable t) {
                    businessFragment.progressbar.setVisibility(View.GONE);
                    // Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
                    //    Log.d("error", String.valueOf(t.getMessage()));
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
    public void remove_interest(String user_id, String interest_ids) {
        try {
            new RService.api().call(mContext).delete_interest(user_id, interest_ids).enqueue(new Callback<json>() {
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
                    //    Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
                    //    Log.d("error", String.valueOf(t.getMessage()));
                }
            });
        } catch (Exception e) {

        }
    }

    @Override
    public void interest_button(String user_id, String post_id) {
        try {
            new RService.api().call(mContext).like_interest_btn(user_id, post_id).enqueue(new Callback<json>() {
                @Override
                public void onResponse(Call<json> call, Response<json> response) {
                    if (response.body().status.equals("1")) {
                        Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                        business_post(businessFragment.interest_id,user_id);
                    } else {
                   //     Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<json> call, Throwable t) {
                        Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
                    //    Log.d("error", String.valueOf(t.getMessage()));
                }
            });
        } catch (Exception e) {

        }
    }

    @Override
    public void remove_interest_button(String user_id, String post_id) {
        try {
            new RService.api().call(mContext).remove_interest_btn(user_id, post_id).enqueue(new Callback<json>() {
                @Override
                public void onResponse(Call<json> call, Response<json> response) {
                    if (response.body().status.equals("1")) {
                        Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                        business_post(businessFragment.interest_id,user_id);
                    } else {
                        //     Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<json> call, Throwable t) {
                    Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
                    //    Log.d("error", String.valueOf(t.getMessage()));
                }
            });
        } catch (Exception e) {

        }
    }
}
