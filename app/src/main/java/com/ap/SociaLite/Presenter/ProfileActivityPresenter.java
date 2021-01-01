package com.ap.SociaLite.Presenter;

import android.content.Context;
import android.widget.Toast;

import com.ap.SociaLite.Activity.ProfileActivity;
import com.ap.SociaLite.Application.RService;
import com.ap.SociaLite.Application.json;
import com.ap.SociaLite.Contract.ProfileActivityContract;
import com.ap.SociaLite.R;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivityPresenter implements ProfileActivityContract {

    public Context mContext;
    public ProfileActivity profileActivity;

    public ProfileActivityPresenter(Context context, ProfileActivity fragment) {
        this.mContext = context;
        this.profileActivity = fragment;
    }

    @Override
    public void profile_my_profile(String user_id) {
        try {
            new RService.api().call(mContext).my_profileActivity(user_id).enqueue(new Callback<json>() {
                @Override
                public void onResponse(Call<json> call, Response<json> response) {

                    if (response.body().status.equals("1")) {
                        if (response.body().my_profile_user_details != null && response.body().my_profile_user_details.size() > 0) {

                            if(response.body().my_profile_user_details.get(0).profile_pic.length() > 0)
                            {
                                Picasso.get().load(response.body().my_profile_user_details.get(0).profile_pic).placeholder(R.mipmap.ic_launcher).into(profileActivity.circularImageView6);
                            }

                            if(response.body().my_profile_user_details.get(0).cover_photo.length() > 0)
                            {
                                Picasso.get().load(response.body().my_profile_user_details.get(0).cover_photo).placeholder(R.mipmap.ic_launcher).into(profileActivity.imageView12);
                            }

                            profileActivity.user_name.setText(response.body().my_profile_user_details.get(0).username);
                            profileActivity.textView16.setText(response.body().my_profile_user_details.get(0).bio);
                            profileActivity.textView10.setText(response.body().my_profile_user_details.get(0).connection);


                        }
                    } else {
                        //      Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
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
}